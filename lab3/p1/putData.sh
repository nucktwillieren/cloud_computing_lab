#!/bin/bash
table="s109524012"
row="test2"
javac -cp `hbase classpath` putDataToHBaseTable.java
hbase putDataToHBaseTable $table $row CPU BRAND AMD
hbase putDataToHBaseTable $table $row CPU ITEM TR2-2990WX
hbase putDataToHBaseTable $table $row CPU PRICE 57999
hbase putDataToHBaseTable $table $row HD BRAND TOSHIBA
hbase putDataToHBaseTable $table $row HD ITEM MG06ACA10TE-10TB
hbase putDataToHBaseTable $table $row HD PRICE 11800
hbase putDataToHBaseTable $table $row MB BRAND ASUS
hbase putDataToHBaseTable $table $row MB ITEM RogZenithExtremeAlpha
hbase putDataToHBaseTable $table $row MB PRICE 17990
hbase putDataToHBaseTable $table $row TEST BRAND ASUS
hbase putDataToHBaseTable $table $row TEST ITEM RogZenithExtremeAlpha
hbase putDataToHBaseTable $table $row TEST PRICE 17990
hbase putDataToHBaseTable $table $row TEST3 BRAND ASUS
hbase putDataToHBaseTable $table $row TEST3 ITEM RogZenithExtremeAlpha
hbase putDataToHBaseTable $table $row TEST3 PRICE 17990
hbase putDataToHBaseTable $table $row RAM BRAND GSKILL
hbase putDataToHBaseTable $table $row RAM ITEM F4-3200C14Q-32GTZRX
hbase putDataToHBaseTable $table $row RAM PRICE 81000