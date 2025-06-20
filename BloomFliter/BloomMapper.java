import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class BloomMapper extends Mapper<Object, Text, Text, Text> {
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String email = value.toString().trim();

        if (email != null && !email.isEmpty()) {
            int hash = email.hashCode();  // Java's default hash function
            context.write(new Text(email), new Text(Integer.toString(hash)));
        }
    }
}

/*
Compilation and Execution 
The following steps outline the compilation and execution process for the Bloom Filter 
MapReduce program: 
 
1. Navigate to the project directory: 
C:\BloomFilterMR 
2. Compile the Java code: 
javac -classpath "C:\hadoop\share\hadoop\common\*;C:\hadoop\share\hadoop\mapreduce\*;C:\hadoop\share\hadoop\common\lib\*" -d . src/*.java 
3. Create the JAR file: 
jar -cvf bloom_filter_email.jar *.class 
4. Run the job on Hadoop: 
hadoop jar bloom_filter_email.jar BloomDriver /input /output 
 Important Reminders:

Make sure /input exists in HDFS:

hdfs dfs -ls /input
If /output already exists, delete it:

hdfs dfs -rm -r /output

hdfs dfs -cat /output/part-r-00000

ee way lo file create cheyi 
C:\BloomFilterMR
│
├── src\
│   ├── BloomMapper.java
│   ├── BloomReducer.java
│   └── BloomDriver.java
├── bloom_filter_email.jar
  */

