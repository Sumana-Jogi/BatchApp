package org.example.writer;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.example.util.Configs;

public class WriteToCassandra {
    public static void writeToCass(Dataset<Row> data){
        data.write()
                .format("org.apache.spark.sql.cassandra")
                .option("keyspace", Configs.CASS_KEYSPACE)
                .option("table",Configs.CASS_TABLE)
                .mode("append")
                .save();
    }
}
