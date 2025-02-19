import jakarta.jms.*;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import java.nio.charset.StandardCharsets;

public class JMSTopicByteConsumer {

    private static final String BROKER_URL = "tcp://localhost:61616"; // Replace with your broker's URL
    private static final String TOPIC_NAME = "myTopic"; // Replace with your topic name

    public static void main(String[] args) {
        try (ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
             JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE)) {

            // Create a JMS topic
            Topic topic = context.createTopic(TOPIC_NAME);

            // Create a consumer
            JMSConsumer consumer = context.createConsumer(topic);

            System.out.println("Listening for byte messages on topic: " + TOPIC_NAME);

            // Continuously listen for messages
            while (true) {
                Message message = consumer.receive();
                
                if (message instanceof BytesMessage) {
                    BytesMessage byteMessage = (BytesMessage) message;
                    
                    // Read bytes from the message
                    byte[] data = new byte[(int) byteMessage.getBodyLength()];
                    byteMessage.readBytes(data);
                    
                    // Convert bytes to a string (assuming UTF-8 encoding)
                    String receivedText = new String(data, StandardCharsets.UTF_8);
                    
                    System.out.println("Received Byte Message: " + receivedText);
                } else {
                    System.out.println("Received Non-Byte Message: " + message);
                }
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
