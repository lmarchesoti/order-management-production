package dev.lmarchesoti.ordermanagement.infrastructure.config;

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
