package org.example.util;

import org.apache.spark.sql.SparkSession;

public class SparkSessionUtil {
    public static SparkSession spark = SparkSession.builder()
            .appName("countryApp")
            .master("local[*]")
            .config("spark.cassandra.connection.host", "localhost")
            .config("spark.cassandra.connection.port", "9042")
            .getOrCreate();

}
