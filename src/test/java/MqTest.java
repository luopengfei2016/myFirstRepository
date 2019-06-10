import com.hxgz.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
public class MqTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testSend(){
        rabbitTemplate.convertAndSend("topictest","thank.log","主题模式");
    }
}
