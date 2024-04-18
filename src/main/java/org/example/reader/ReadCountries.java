package org.example.reader;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions.*;

import javax.xml.crypto.Data;

public class ReadCountries {
    public static Dataset<Row> readJsonFile(SparkSession spark, String filePath) {
//         Read file using SparkSession
            return spark.read().option("header","true").json(filePath);
    }

    public static void JsonToParquet(SparkSession spark, String jsonPath, String paruqetPath){
        Dataset<Row> jsonData = spark.read().json(jsonPath);
        jsonData.write().option("header","true").mode("overwrite").parquet(paruqetPath);
    }
}
