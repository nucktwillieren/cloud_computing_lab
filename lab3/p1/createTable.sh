#!/bin/bash

javac -cp `hbase classpath` createHBaseTable.java
hbase createHBaseTable s109524012 CPU RAM HD MB