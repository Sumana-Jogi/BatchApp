# BatchApp
A sample Java application designed to demonstrate processing files using Spark SQL. This project showcases the ability to handle data from both local directories and a Dockerized Cassandra database.

Overview
Illustrates how to process data using Apache Spark SQL within a Java application. The application includes examples of reading from and writing to a local file system as well as interacting with a Dockerized version of Cassandra for dataset operations.

Features
Read/Write Files:
The application can read data from and write processed data back to local directories.
Read/Write Datasets in Cassandra:
Demonstrates how to read from and write datasets to a Cassandra database running in a Docker container.
Read/Write Datasets in Mysql:
Demonstrates how to read from and write datasets to a Mysql database via JDBC connection.

Prerequisites
- Java 8 or higher
- Apache Spark 3.x
- Docker (for running Cassandra)
- Cassandra 3.x or 4.x (Docker version)
- Maven (for building the project)
- Mysql
