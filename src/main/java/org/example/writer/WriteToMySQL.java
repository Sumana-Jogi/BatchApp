package org.example.writer;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.example.util.Configs;

public class WriteToMySQL {
    public static void writeToMysql(Dataset<Row> data, String pass){
        data.write()
                .format("jdbc")
                .mode(SaveMode.Append)
                .option("driver", Configs.MYSQL_DRIVER)
                .option("url", Configs.MYSQL_URL)
                .option("dbtable", Configs.MYSQL_TABLE_NAME )
                .option("user", Configs.MYSQL_USER)
                .option("password", pass)
                .save();
    }
}
