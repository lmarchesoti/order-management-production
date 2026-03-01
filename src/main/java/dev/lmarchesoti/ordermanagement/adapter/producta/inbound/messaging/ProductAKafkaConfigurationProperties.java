package dev.lmarchesoti.ordermanagement.adapter.producta.inbound.messaging;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "producta.kafka")
public class ProductAKafkaConfigurationProperties {
    String bootstrapServers;
    String consumerGroupId;
    String consumerAutoOffsetReset;
    String listenerAckMode;
    Boolean autoStartup;
    String dltTopic;
}
