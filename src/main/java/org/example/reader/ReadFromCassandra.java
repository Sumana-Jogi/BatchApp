package org.example.reader;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class ReadFromCassandra {
    public static Dataset<Row> readFromCassandra(SparkSession spark,String keyspace, String table){
        return spark.read()
                .option("header","true")
                .format("org.apache.spark.sql.cassandra")
                .option("keyspace",keyspace)
                .option("table",table)
                .load();
    }
}
