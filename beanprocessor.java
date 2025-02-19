import java.nio.charset.StandardCharsets;
import javax.jms.BytesMessage;
import javax.jms.Message;

Message response = sampler.getResponseMessage();

if (response instanceof BytesMessage) {
    BytesMessage byteMessage = (BytesMessage) response;
    
    // Read bytes from the message
    byte[] data = new byte[(int) byteMessage.getBodyLength()];
    byteMessage.readBytes(data);
    
    // Convert bytes to a String (UTF-8)
    String messageText = new String(data, StandardCharsets.UTF_8);
    
    // Print message in JMeter log
    log.info("Received ByteMessage: " + messageText);
    
    // Store message in JMeter variable for display
    vars.put("JMS_Message", messageText);
}
