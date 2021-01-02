#!/bin/bash

# usage: sh s109524012_practice1.sh table_u_wanna_put_data_into

table=$1
hbase s109524012_practice1 $table yaya CPU BRAND AMD
hbase s109524012_practice1 $table yaya CPU ITEM TR2-2990WX
hbase s109524012_practice1 $table yaya CPU PRICE 57999
hbase s109524012_practice1 $table yaya HD BRAND TOSHIBA
hbase s109524012_practice1 $table yaya HD ITEM MG06ACA10TE-10TB
hbase s109524012_practice1 $table yaya HD PRICE 11800
hbase s109524012_practice1 $table yaya RAM BRAND GSKILL
hbase s109524012_practice1 $table yaya RAM ITEM F4-3200C14Q-32GTZRX
hbase s109524012_practice1 $table yaya RAM PRICE 15990
hbase s109524012_practice1 $table yaya MB BRAND ASUS
hbase s109524012_practice1 $table yaya MB ITEM 	RogZenithExtremeAlpha
hbase s109524012_practice1 $table yaya MB PRICE 17990

echo "Get Value For Checking\n"
javac -cp `hbase mapredcp`:`hadoop classpath` getHBaseTableValue.java

echo "Get warmpc cpu For Checking\n"
hbase getHBaseTableValue $table yaya CPU ITEM

echo "Get warmpc hd For Checking\n"
hbase getHBaseTableValue $table yaya HD ITEM

echo "Get warmpc ram For Checking\n"
hbase getHBaseTableValue $table yaya RAM ITEM

echo "Get warmpc mb For Checking\n"
hbase getHBaseTableValue $table yaya MB ITEM
