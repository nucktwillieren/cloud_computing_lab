#!/bin/bash

table=$1
echo "Get Value For Checking"
javac -cp `hbase mapredcp`:`hadoop classpath` getHBaseTableValue.java
echo "Get warmpc cpu For Checking"
hbase getHBaseTableValue $table warmpc CPU ITEM
echo "Get warmpc hd For Checking"
hbase getHBaseTableValue $table warmpc HD ITEM
echo "Get warmpc ram For Checking"
hbase getHBaseTableValue $table warmpc RAM ITEM
