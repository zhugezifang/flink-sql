/*
package org.example;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;

public class SqlKafka {
    public static void main(String[] args) {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        //todo 创建表环境   bug比较多，建议指定版本
        EnvironmentSettings settings = EnvironmentSettings.newInstance()
                .inStreamingMode()
                .useBlinkPlanner()
                .build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(senv);

        //把kafka 中的topic映射成一个输入临时表
        tableEnv.executeSql(
                "create table sensor_source (id string,ts bigint,vc int) with ("+
                        " 'connector' = 'kafka'," +
                        "  'topic' = 'topic_source'," +
                        "  'properties.bootstrap.servers' = 'hadoop102:9092'," +
                        "  'properties.group.id' = 'testGroup'," +
                        "  'scan.startup.mode' = 'earliest-offset'," +
                        "  'format' = 'csv')"
        );
        //把mysql 中的表映射成一个输出临时表
        tableEnv.executeSql(
                "create table mysql_sink (id string ,ts bigint,vc int) with (" +
                        "   'connector' = 'jdbc'," +
                        "   'url' = 'jdbc:mysql://hadoop102:3306/test'," +
                        "   'table-name' = 'sensor'," +
                        "   'username'='root'," +
                        "   'password'='123456')");
        //插入数据的sql语句
        tableEnv.executeSql("insert into mysql_sink select * from sensor_source");
    }
}
*/
