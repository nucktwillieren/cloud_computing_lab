import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

// 想法流程： 
// 1. 在Map階段，由於為分散讀檔運算，我們可以建立類似於HashMap的字詞次數字典再傳入Reduce。
// 2. 在Reduce階段，將利用Map所傳入類似於HashMap之Key-Value字詞字典產生迭代，並逐字疊加。

public class WordCounter {
    public static class WordCounterMapper
            extends Mapper<Object, Text, Text, IntWritable>{
        private final static IntWritable count  = new IntWritable(1);
        private Text word = new Text();

        @Override
        public void map(
            Object key, Text value, Context context
        ) throws IOException, InterruptedException {

            StringTokenizer str = new StringTokenizer(value.toString());

            while (str.hasMoreTokens()) { // 迴圈等到沒有字詞後斷開
                word.set(str.nextToken()); // 取出字詞
                context.write(word, count); // 記錄字詞與其對應之count=1 -> Ex: <hi, 1>
            }
        }
    }

    public static class WordCounterReducer
            extends Reducer<Text,IntWritable,Text,IntWritable> {
        private IntWritable result = new IntWritable();

        @Override
        public void reduce(
            Text key, Iterable<IntWritable> values, Context context
        ) throws IOException, InterruptedException {
            int totalCount = 0;

            for (IntWritable value : values) {
                totalCount += value.get(); // 透過外層for，將分類從Map中一筆一筆拿出來
            }

            result.set(totalCount);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Word Count");
        
        job.setJarByClass(WordCounter.class);

        job.setMapperClass(WordCounterMapper.class);
        job.setCombinerClass(WordCounterReducer.class);
        job.setReducerClass(WordCounterReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}