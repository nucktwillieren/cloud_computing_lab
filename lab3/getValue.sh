#!/bin/bash

table="s109524012"
row=$1
f1=$2
f2=$3
javac -cp `hbase classpath` getHBaseTableValue.java
hbase getHBaseTableValue $table $row $f1 $f2