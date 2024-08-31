package org.example.reader;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.example.Main;

import java.io.InputStream;
import java.nio.file.Files;

public class ReadCountries {
    public static Dataset<Row> readJsonFile(SparkSession spark, String filePath) {
        try {
            InputStream inputStream = Main.class.getResourceAsStream("/"+ filePath);
            java.nio.file.Path tempFile = Files.createTempFile("country", ".json");
            Files.copy(inputStream, tempFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            Dataset<Row> result= spark.read().option("header","true").json(tempFile.toString());
            return result;
        }catch (NullPointerException e){
            System.out.println("source file might be empty");
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void JsonToParquet(SparkSession spark, String jsonPath, String paruqetPath){
        try {
            InputStream inputStream = Main.class.getResourceAsStream("/" + jsonPath);
            java.nio.file.Path tempFile = Files.createTempFile("country", ".json");
            Files.copy(inputStream, tempFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            Dataset<Row> jsonData = spark.read().json(tempFile.toString());

            jsonData.write().option("header", "true").mode("overwrite").parquet(paruqetPath);
        }catch (NullPointerException e){
            System.out.println("source file might be empty");
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
