package org.example.reader;


import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.example.util.Configs;

public class ReadMysql {
    public static Dataset<Row> readMysql(SparkSession spark, String pass){
        return spark.read()
                .format("jdbc")
                .option("url", Configs.MYSQL_URL)
                .option("dbtable",Configs.MYSQL_TABLE_NAME)
                .option("user", Configs.MYSQL_USER)
                .option("password",pass)
                .load();
    }
    public static Dataset<Row> readMysql(SparkSession spark, String pass, String query){
        return spark.read()
                .format("jdbc")
                .option("url", Configs.MYSQL_URL)
                .option("query", query)
                .option("user", Configs.MYSQL_USER)
                .option("password",pass)
                .load();
    }
}
