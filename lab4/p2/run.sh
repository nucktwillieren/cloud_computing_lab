#!/bin/bash

hadoop fs -rm -r "hdfs://hadoop-master:8020/user/s109524012/count.out"

spark-submit practice2.py

#hadoop fs -cat "hdfs://hadoop-master:8020/user/s109524012/count.out/part-00000"
