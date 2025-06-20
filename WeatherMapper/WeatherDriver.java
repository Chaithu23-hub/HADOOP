import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WeatherDriver {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "weather analysis");

        job.setJarByClass(WeatherDriver.class);
        job.setMapperClass(WeatherMapper.class);
        job.setReducerClass(WeatherReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
/*
Create a weather dataset (weather_data.txt): 
2025-03-20,12:00,New York,15°C 
2025-03-20,13:00,New York,16°C 
2025-03-20,12:00,London,10°C 
2025-03-20,13:00,London,12°C 
 
 
Upload it to Hadoop HDFS: 
hdfs dfs -mkdir  /weather/input 
hdfs dfs -put weather_data.txt /weather/input/ 
 
 
Create three files in a folder 
(C:\hadoop_projects\weather_analysis\src).
*/
