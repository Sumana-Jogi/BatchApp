package org.example;

import org.apache.spark.sql.*;
import org.example.processor.ApplyTransform;
import org.example.reader.ReadCountries;
import org.example.util.SparkSessionUtil;
import org.example.writer.WriteToCassandra;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SparkSession spark =  SparkSession.builder()
                .appName("Read Json")
                .master("local[*]")
//                .config("spark.cassandra.connection.host", "172.17.0.2")// IP of the docker container
//                .config("spark.cassandra.connection.port","9042")
//                .config("spark.cassandra.protocol.version", "4")
                .config("spark.cassandra.connection.host", "localhost")  // Set the Cassandra host
                .config("spark.cassandra.connection.port", "9042")  // Set the Cassandra port (default is 9042)
                .getOrCreate();

//        spark.sparkContext().setLogLevel("DEBUG");

        String srcPath = "country.json";
        String trgtPath = "src/main/resources/country.parquet";

        //************************************************
        Dataset<Row> sampleInput= SparkSessionUtil.spark.read().option("header","true").csv("src/main/resources/sampleInput.csv");

        ReadCountries.readJsonFile(spark, srcPath).show(false);
        ReadCountries.JsonToParquet(spark, srcPath, trgtPath);
        WriteToCassandra.writeToCass(sampleInput,"samplekeyspace","testtable");

        //group countries by their first alphabet letter
        ApplyTransform.grpByFirstLetter(spark,trgtPath).show(false);

//        System.out.println("findNeghbors");
        ApplyTransform.findNeighbours(spark,"AFG", trgtPath).show(false);
//        System.out.println("high population country");
        ApplyTransform.HighPopultnCntry(spark,trgtPath).show(false);

        spark.stop();
    }
}