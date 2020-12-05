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
import org.apache.hadoop.util.GenericOptionsParser;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;


public class WordClassify {

    public static class TokenizerMapper extends Mapper<Object, Text, Text, Map<String, String>>{

        private final static IntWritable count  = new IntWritable(1);        
        private Text word = new Text();
        
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

            FileSplit fileSplit = (FileSplit)context.getInputSplit();
            String filename = fileSplit.getPath().getName();
            StringTokenizer itr = new StringTokenizer(value.toString());

            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                Text c = new Text(filename + ":" + "{}" + ";" + count.toString());
                context.write(word, c);
            }
        }
    }

    public static class IntSumReducer extends Reducer<Text, Text, Text, Text> {

        private Text result = new Text("");

        public void reduce(
            Text key, Iterable<Text> values, Context context
        ) throws IOException, InterruptedException {
            String longString = "";

            for (Text val : values) {
                longString += val.toString();
            }

            result.set(longString);
            context.write(key, result);
        }

        
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Main First Line");
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2) {
            System.err.println("Usage: wordcount <in> <out>");
            System.exit(2);
        }
        Job job = new Job(conf, "word classufy");
        job.setJarByClass(WordClassify.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        job.waitForCompletion(true);
       
        System.exit(1);
    }
}