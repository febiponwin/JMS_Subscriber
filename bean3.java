import java.nio.charset.StandardCharsets;
import org.apache.jmeter.samplers.SampleResult;

SampleResult result = prev; // Get the previous sampler result
byte[] rawBytes = result.getResponseData(); // Extract raw bytes from ResponseData

if (rawBytes != null && rawBytes.length > 0) {
    try {
        // Convert bytes to a UTF-8 string
        String messageText = new String(rawBytes, StandardCharsets.UTF_8);

        // Log the extracted message
        log.info("Extracted ByteMessage: " + messageText);

        // Store the message in a JMeter variable for UI display
        vars.put("JMS_Message", messageText);
    } catch (Exception e) {
        log.error("Error while decoding ByteMessage: " + e.getMessage());
    }
} else {
    log.warn("No bytes received from JMS Topic.");
}
