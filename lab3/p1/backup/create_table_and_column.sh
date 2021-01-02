#!/bin/bash

# usage: sh 109524012_practice1.sh the_table_name_u_wanna_create

TableName=$1
echo "Create Table Named $TableName With Columns: $Column"
javac -cp `hbase classpath` createHBaseTable.java
hbase createHBaseTable $TableName RAM CPU HD MB
