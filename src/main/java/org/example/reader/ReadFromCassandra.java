package org.example.reader;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.example.util.Configs;

public class ReadFromCassandra {
    public static Dataset<Row> readFromCassandra(SparkSession spark){
        return spark.read()
                .option("header","true")
                .format("org.apache.spark.sql.cassandra")
                .option("keyspace", Configs.CASS_KEYSPACE)
                .option("table",Configs.CASS_TABLE)
                .load();
    }
}
