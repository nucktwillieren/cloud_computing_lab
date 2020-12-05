# Save output file(part-r-00000) for practice 1 and source code(.java) 
# for practice 2 in ~/lab1_practice1 & ~/lab1_practice2 directory seperately 
# (not in hadoop file system), we will check them after the deadline.

# You can check your Hand-in Status here. 
# If you cannot find your file, 
# try to make the two directories clean and name your files like others.

hadoop com.sun.tools.javac.Main WordClassify.java
jar cf wc.jar WordClassify*.class

# Create folder in hadoop file system (fs)
hadoop fs -mkdir -p /user/s109524012/lab1/input

# Create input file in local file system and upload them to hadoop fs
echo "Hello World Bye World" > file1
echo "Hello Hadoop Goodbye Hadoop" > file2
hadoop fs -copyFromLocal ./file1 /user/s109524012/lab1/input/file1
hadoop fs -copyFromLocal ./file2 /user/s109524012/lab1/input/file2

# Run the application
hadoop jar wc.jar WordClassify /user/s109524012/lab1/input /user/s109524012/lab1/output

# Cat the result
hadoop fs -cat /user/s109524012/lab1/output/part-r-00000