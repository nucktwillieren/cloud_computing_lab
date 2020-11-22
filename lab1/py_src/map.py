import sys

for line in sys.stdin:  # 這邊 stdin 就是要讀入檔案 或者 讓Hadoop fs 傳送資料序列進入這個程式進行處理
    fields = line.strip().split() # 這邊就是處理的部分
    for item in fields: 
        print(item + ' ' + '1') # print function相當於 stdout。
# 在 Hadoop streaming的機制中，只要是stdout的東西就會被return進hadoop被處裡，
# 所以這個print就相當於把資料放到Hadoop中。
# 而在這個Case中，就是把資料預先處理後再傳到Hadoop Mapper內，讓Hadoop平行運算
# 另外，這份檔案本身也是跑在Hadoop內的
