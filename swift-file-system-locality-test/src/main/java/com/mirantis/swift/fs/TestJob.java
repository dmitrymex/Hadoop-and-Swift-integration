package com.mirantis.swift.fs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 */
public class TestJob extends Configured implements Tool {
    public static class TestMapper extends Mapper<LongWritable, Text, NullWritable, Text> {
        private static final InetAddress LOCAL;

        static {
            try {
                LOCAL = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                throw new IllegalStateException(e);
            }
        }

        private static final Log LOGGER = LogFactory.getLog(TestMapper.class);

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            LOGGER.info(String.format("[%s/%s] Processing value: %s", LOCAL.getHostName(), LOCAL.getHostAddress(), value));
            context.write(NullWritable.get(), value);
        }
    }

    @Override
    public int run(String[] args) throws Exception {
        final Configuration conf = getConf();
        conf.set("mapred.min.split.size", String.valueOf(32 * 1024 * 1024));
        conf.set("mapred.max.split.size", String.valueOf(64 * 1024 * 1024));

        //Swift auth properties
        conf.set("swift.auth.url", "http://172.18.66.110:5000/v2.0/tokens");
        conf.set("swift.tenant", "superuser");
        conf.set("swift.username", "admin1");
        conf.set("swift.password", "password");
        conf.setInt("swift.http.port", 8080);
        conf.setInt("swift.https.port", 443);

        final Job job = new Job(conf, "Test Job");
        job.setJarByClass(TestJob.class);

        FileInputFormat.setInputPaths(job, args[0]);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Text.class);

        job.setMapperClass(TestMapper.class);


        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        ToolRunner.run(new Configuration(), new TestJob(), args);
    }
}
