package org.example;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;

import java.util.ArrayList;

/**
 * Author : Jackson
 * Version : 2020/4/21 & 1.0
 */

public class SqlWordCount {
    public static void main(String[] args) throws Exception {

        //创建上下文环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        BatchTableEnvironment fbtableEnv = BatchTableEnvironment.getTableEnvironment(env);



        String words = "hello Jackson hello flink";
        String[] split = words.split("\\W+");
        ArrayList<WC> list = new ArrayList<>();

        for (String word : split) {
            WC wc = new WC(word, 1);
            list.add(wc);
        }
        DataSource<WC> input = env.fromCollection(list);
        //DataSet 转sql，指定字段名
        Table table1 = fbtableEnv.fromDataSet(input, "word,count1");
        //table1.printSchema();

        //注册为一个表
        fbtableEnv.registerTable("WordCount", table1);

        Table table2 = fbtableEnv.sql("select word as word,sum(count1) as count1 from WordCount GROUP BY word");

        //将表转换DaraSet
        DataSet<WC> res = fbtableEnv.toDataSet(table2, WC.class);
        res.printToErr();
    }

    public static class WC {
        public String word;
        public long count1;

        public WC() {
        }

        public WC(String word, long count1) {
            this.word = word;
            this.count1 = count1;
        }

        @Override
        public String toString() {
            return word + "," + count1;
        }
    }
}