import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WeatherMapper extends Mapper<Object, Text, Text, IntWritable> {

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split(","); // Adjust delimiter if necessary

        if (fields.length >= 2) {  // Ensure the record has enough fields
            String city = fields[0].trim();
            String tempStr = fields[1].trim().replaceAll("[^0-9-]", ""); // Remove non-numeric characters

            try {
                int temp = Integer.parseInt(tempStr);
                context.write(new Text(city), new IntWritable(temp));
            } catch (NumberFormatException e) {
                System.err.println("Skipping invalid temperature value: " + fields[1]);
            }
        }
    }
}


/*
Process: 
C:\Windows\System32>cd C:\hadoop_projects\weather_analysis 
 
C:\hadoop_project\weather_analysis>javac -classpath "C:\hadoop\share\hadoop\common\*;C:\hadoop\share\hadoop\mapreduce\*;C:\hadoop\share\hadoop\common\lib\*" -d . src/*.java 
 
 
C:\hadoop_project\weather_analysis>jar -cvf weather_analysis.jar *.class 
 
 
C:\hadoop_project\weather_analysis>hadoop jar weather_analysis.jar WeatherDriver /weather/input /weather/output 
 
C:\hadoop_project\weather_analysis>hdfs dfs -cat /weather/output/part-r-00000
*/
