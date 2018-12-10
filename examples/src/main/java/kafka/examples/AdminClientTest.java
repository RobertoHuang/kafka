/**
 * FileName: AdminClientTest
 * Author:   HuangTaiHong
 * Date:     2018/12/10 9:42
 * Description: AdminClientTest
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package kafka.examples;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.RecordsToDelete;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 〈一句话功能简述〉<br>
 * 〈AdminClientTest〉
 *
 * @author HuangTaiHong
 * @create 2018/12/10
 * @since 1.0.0
 */
public class AdminClientTest {
    private static AdminClient ADMIN_CLIENT;

    static {
        Properties properties = new Properties();
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        AdminClientTest.ADMIN_CLIENT = AdminClient.create(properties);
    }

    public static void main(String[] args) throws Exception {
        testDeleteRecords();
    }

    /**
     * 功能描述: <br>
     * 〈测试创建TOPIC〉
     *
     * 3个分区 1个副本
     *
     * @author HuangTaiHong
     * @date 2018.12.07 10:20:51
     */
    private static void testCreateTopics() throws Exception {
        NewTopic newTopic = new NewTopic("topic-test", 3, (short) 1);
        Collection<NewTopic> newTopicList = new ArrayList<>(Arrays.asList(newTopic));
        ADMIN_CLIENT.createTopics(newTopicList).all().get();
    }

    /**
     * 功能描述: <br>
     * 〈测试删除消息 在beforeOffset前的消息将被删除〉
     *
     * @throws Exception
     * @author HuangTaiHong
     * @date 2018.12.10 10:00:03
     */
    private static void testDeleteRecords() throws Exception {
        Map<TopicPartition, RecordsToDelete> deleteRecords = new HashMap<>();
        deleteRecords.put(new TopicPartition("topic-test", 0), RecordsToDelete.beforeOffset(1367238));
        ADMIN_CLIENT.deleteRecords(deleteRecords).all().get();
        System.out.println("123");
    }
}