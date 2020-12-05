mapred streaming \
-input /user/s109524012/lab1/input/file1 \
-output /user/s109524012/lab1/output \
-mapper "python map.py" \
-reducer "python reduce.py" \
-file ./map.py \
-file ./reduce.py \
