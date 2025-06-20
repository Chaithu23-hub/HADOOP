import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class BloomDriver {
    public static void main(String[] args) throws Exception {
        // Configuration and Job Setup
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Bloom Filter Email Membership");

        job.setJarByClass(BloomDriver.class);
        job.setMapperClass(BloomMapper.class);
        job.setReducerClass(BloomReducer.class);

        // Set output key/value types
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // Set input/output paths from command line
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Submit job and exit
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}

/*
Sample Input Data 
Below is a sample input data used for the Bloom Filter MapReduce job: 
user1@example.com 
user2@example.com 
user3@example.com 
alice@example.com 
bob@example.org 
carol@example.net 
dave@example.com 
eve@example.org 
frank@example.net 
grace@example.com 
heidi@example.org 
ivan@example.net 
judy@example.com 
mallory@example.org 
nia@example.net 
oscar@example.com 
peggy@example.org 
C:\BloomFilterMR>hdfs dfs -mkdir /input 
C:\BloomFilterMR>hdfs dfs -put "C:\BloomFilterMR\input\data.txt" /input 
*/
