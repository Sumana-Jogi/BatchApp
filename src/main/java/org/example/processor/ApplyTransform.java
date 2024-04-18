package org.example.processor;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.expressions.WindowSpec;

import static org.apache.spark.sql.functions.*;

public class ApplyTransform {
    //grp countries by First alphabet Letter
    public static Dataset<Row> grpByFirstLetter(SparkSession spark, String srcpath){
        Dataset<Row> srcData = spark.read().parquet(srcpath);
        Dataset<Row> grpData = srcData.groupBy(substring(col("name"),0,1).as("letter"))
                .agg(collect_list("name").as("coutries"))
                .sort(col("letter"));
        return grpData;
    }

    public static Dataset<Row> findNeighbours(SparkSession spark, String country, String srcpath){
        Dataset<Row> srcData = spark.read().parquet(srcpath);
        Dataset<Row> neighbors = srcData.filter(array_contains(col("borders"),country))
                .withColumn("neighbors",col("name"))
                .select(col("neighbors").as("neighborsof"+country));
        return neighbors;
    }

    public static Dataset<Row> HighPopultnCntry(SparkSession spark, String srcpath){
        Dataset<Row> srcData = spark.read().parquet(srcpath);
        WindowSpec windowSpec = Window.partitionBy("region").orderBy(desc("population"));

        Dataset<Row> window1 = srcData.withColumn("rownumber", row_number().over(windowSpec));
        Dataset<Row>  CountrywithHighestPopulation = window1.filter(col("rownumber").equalTo(1))
                .select(col("region"),col("name").as("HighestPopulationCntryContinentWise"));

        return CountrywithHighestPopulation;
    }
}
