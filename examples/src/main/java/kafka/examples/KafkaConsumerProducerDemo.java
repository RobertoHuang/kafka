/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kafka.examples;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaConsumerProducerDemo {
    public static void main(String[] args) throws Exception{
//        boolean isAsync = args.length == 0 || !args[0].trim().equalsIgnoreCase("sync");
//        Producer producerThread = new Producer(KafkaProperties.TOPIC, isAsync);
//        producerThread.start();
//
//        Consumer consumerThread = new Consumer(KafkaProperties.TOPIC);
//        consumerThread.start();

        testCreateTopics();
    }

    /**
     * 功能描述: <br>
     * 〈测试创建TOPIC〉
     *
     * @author HuangTaiHong
     * @date 2018.12.07 10:20:51
     */
    private static void testCreateTopics() throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        AdminClient adminClient = AdminClient.create(properties);
        NewTopic newTopic = new NewTopic("topic-test", 4, (short)1);
        Collection<NewTopic> newTopicList = new ArrayList<>();
        newTopicList.add(newTopic);
        CreateTopicsResult topics = adminClient.createTopics(newTopicList);
        topics.all().get();
        System.out.println("123");
    }
}
