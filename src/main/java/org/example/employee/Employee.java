package org.example.employee;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import static org.apache.spark.sql.functions.*;


public class Employee {
    public static void main(String[] args) {

        SparkSession spark =  SparkSession.builder()
                .appName("Read Json")
                .master("local[*]")
                .getOrCreate();

        String employeeFile = "src/main/resources/sales.csv";

        StructType schema = new StructType()
                .add("name", DataTypes.StringType)
                .add("dept", DataTypes.StringType)
                .add("country", DataTypes.StringType)
                .add("salary", DataTypes.IntegerType)
                .add("age", DataTypes.IntegerType)
                .add("bonus", DataTypes.IntegerType);

        Dataset<Row> employee = spark.read()
                .schema(schema)
                .csv(employeeFile);

        //employee details
        employee.show();
        //sum of salary for each department
//        employee.groupBy("dept").sum("salary").as("totSalary").show(false);
        //number of employee in each department
//        employee.groupBy("dept").count().show();
//        employee.groupBy("dept").min("salary").as("minSalary").show();
//        employee.groupBy("dept").avg("salary").as("avgSalary").show();
//        employee.groupBy("dept").mean("salary").as("meanSalary").show();

        //total salaries of each dept in each country
//        employee.groupBy("dept","country").sum("salary","bonus").show(false);

        employee.groupBy("dept")
                .agg(sum("salary"),
                        max("salary"),
                        avg("bonus"),
                        max("bonus"))
                .show(false);
    }
}
