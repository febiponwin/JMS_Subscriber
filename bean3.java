import java.nio.charset.StandardCharsets;
import javax.jms.BytesMessage;
import javax.jms.Message;
import javax.jms.TextMessage;

Message response = prev.getObject();  // Get the JMS message object

if (response instanceof BytesMessage) {
    BytesMessage byteMessage = (BytesMessage) response;
    
    // Read bytes correctly
    int length = (int) byteMessage.getBodyLength();
    byte[] data = new byte[length];
    byteMessage.readBytes(data);

    // Convert bytes to UTF-8 String
    String messageText = new String(data, StandardCharsets.UTF_8);

    // Log and store the message for JMeter UI
    log.info("Received ByteMessage: " + messageText);
    vars.put("JMS_Message", messageText);
} else if (response instanceof TextMessage) {
    String text = ((TextMessage) response).getText();
    log.info("Received TextMessage: " + text);
    vars.put("JMS_Message", text);
} else {
    log.warn("Received unknown message type: " + response.getClass().getName());
}
