from pyspark import SparkContext, SparkConf
import random

def get_distance(x, y):
    return (float(x)**2 + float(y)**2)**(0.5)

def get_distance_in_line(line):
    x, y = tuple(line.split(","))
    return get_distance(x, y)

def get_max(x, y):
    if x > y: return x
    return y

sc = SparkContext("local", "Collect app")
inputFilePath = "hdfs://hadoop-master:8020/user/s109524012/practice4-2.txt"
text_file = sc.textFile(inputFilePath)
pairs = text_file.map(lambda line: get_distance_in_line(line)) 
min_distance = pairs.min()
print "min_distance: ", min_distance
#distance.collect()
#distance.saveAsTextFile("hdfs://hadoop-master:8020/user/s109524012/count.out")