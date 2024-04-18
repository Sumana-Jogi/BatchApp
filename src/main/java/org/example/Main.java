package org.example;

import org.apache.spark.sql.*;
import org.example.processor.ApplyTransform;
import org.example.reader.ReadCountries;

import javax.xml.crypto.Data;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.collect_list;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SparkSession spark =  SparkSession.builder()
                .appName("Read Json")
                .master("local[*]")
                .getOrCreate();

        String srcPath = "src/main/resources/country.json";
        String trgtPath = "src/main/resources/country.parquet";

        //************************************************

        ReadCountries.readJsonFile(spark, srcPath);
        ReadCountries.JsonToParquet(spark, srcPath, trgtPath);

        ApplyTransform.grpByFirstLetter(spark,trgtPath).show(false);
        ApplyTransform.findNeighbours(spark,"AFG", trgtPath).show(false);
        ApplyTransform.HighPopultnCntry(spark,trgtPath).show(false);
    }
}