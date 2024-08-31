package org.example.writer;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class WriteToCassandra {
    public static void writeToCass(Dataset<Row> data,String keyspace, String table){
        data.write()
                .format("org.apache.spark.sql.cassandra")
                .option("keyspace",keyspace)
                .option("table",table)
                .mode("append")
                .save();
    }
}
