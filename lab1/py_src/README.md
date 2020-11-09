# Hadoop for Python

We can use Hadoop Streaming Jar to create connection between Python and Hadoop, 
so that we can easily use STDIN and STDOUT to receive and transmit data.

## About This Case
- If not using Hadoop, we can run the following script in bash.
```bash=
cat file1 | python3 map.py | python3 reduce.py
```
- For Hadoop, we might need a shellscript to clarify the command.
  - Here, we use "mapred streaming" to call the streaming api to connect to Hadoop Mapper and Reducer.
(mapred => mapper and reducer)
  - In the stage of command "-mapper", we will call python map.py 
to let the python interpreter intervent in the Hadoop Mapper to do what we wanna do.
  - In the stage of command "-reducer", we will call python reduce.py to make the same thing happened, 
and the only difference is to intervent in the Hadoop Reducer here.
```bash=
mapred streaming \
-input /user/s109524012/lab1/input/file1 \
-output /user/s109524012/lab1/output \
-mapper "python map.py" \
-reducer "python reduce.py" \
-file ./map.py \
-file ./reduce.py \
```
