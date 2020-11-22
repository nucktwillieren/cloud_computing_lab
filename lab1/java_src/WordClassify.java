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

    public static class TokenizerMapper extends Mapper<Object, Text, Text, Text>{
        
        private Text word = new Text();
        private Text fileIdentity = new Text();
        private String tempWord = new String();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            FileSplit fileSplit = (FileSplit)context.getInputSplit();
            String filename = fileSplit.getPath().getName();
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                tempWord = itr.nextToken();
                word.set(tempWord);
                fileIdentity.set(filename);
                context.write(word, fileIdentity);
            }
        }
    }

    public static class IntSumReducer extends Reducer<Text,Text,Text,Text> {
        private Text result = new Text();

        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            result.set("");
            String sum = "";
            int countFor = 0;
            int listIndex = 0;
            int countFile = 0;
            String[] fileList = new String[20]; // Java 應該可以設定不定長String?
            int[] fileCount = new int[20]; // 20 OK嗎?
            String returnValue = "";

            for (Text val : values) {
                String tmpString = val.toString();
                if (ArrayUtils.indexOf(fileList, tmpString) == -1) {
                    fileList[listIndex] = tmpString;
                    fileCount[listIndex] = 1;
                    listIndex++;
                } else {
                    fileCount[ArrayUtils.indexOf(fileList, tmpString)] += 1;
                }
            }

            
            if (listIndex == 1) {
                returnValue = "";
                returnValue = fileList[0] + ":" + fileCount[0] + ";";
                returnValue = returnValue.replace(";:1;", ";");
            } else if (listIndex == 2) {
                returnValue = "";
                returnValue = fileList[0] + ":" + fileCount[0] + ";" + fileList[1] + ":" + fileCount[1] + ";";
                returnValue = returnValue.replace(";:1;", ";");
            } else if (listIndex == 3) {
                returnValue = "";
                returnValue = fileList[0] + ":" + fileCount[0] + ";" + fileList[1] + ":" + fileCount[1] + ";" + fileList[2] + ":" + fileCount[2] + ";";
                returnValue = returnValue.replace(";:1;", ";");
            } else if (listIndex == 4) {
                returnValue = "";
                returnValue = fileList[0] + fileCount[0] + ";" + fileList[1] + ":" + fileCount[1] + ";" + fileList[2] + ":" + fileCount[2] + ";" + fileList[3] + ":" + fileCount[3] + ";";
                returnValue = returnValue.replace(";:1;", ";");
            } else if (listIndex == 5) {
                returnValue = "";
                returnValue = fileList[0] + ":" + fileCount[0] + ";" + fileList[1] + ":" + fileCount[1] + ";" + fileList[2] + ":" + fileCount[2] + ";" + fileList[3] + ":" + fileCount[3] + ";" + fileList[4] + ":" + fileCount[4] + ";";
                returnValue = returnValue.replace(";:1;", ";");
            }

            if (result.toString().length() == 0) {
                result.set(returnValue);
            }
            
            context.write(key, result);
        }

        
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Main First Line");
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2) {
            System.err.println("Usage: wordClassify <in> <out>");
            System.exit(2);
        }
        Job job = new Job(conf, "word classify");
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