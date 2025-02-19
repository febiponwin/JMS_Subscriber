import java.nio.charset.StandardCharsets;
import org.apache.jmeter.threads.JMeterContextService;

byte[] responseData = prev.getResponseData(); // Get response bytes

if (responseData != null && responseData.length > 0) {
    // Convert byte array to UTF-8 String
    String messageText = new String(responseData, StandardCharsets.UTF_8);
    
    // Store the message in a JMeter variable
    vars.put("JMS_Message", messageText);
    
    // Log to JMeter console
    log.info("Received ByteMessage: " + messageText);
} else {
    log.warn("No data received from JMS Topic.");
}
