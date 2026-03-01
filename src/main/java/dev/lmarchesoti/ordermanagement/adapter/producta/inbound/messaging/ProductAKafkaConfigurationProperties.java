package dev.lmarchesoti.ordermanagement.adapter.producta.inbound.messaging;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
//@Component
@ConfigurationProperties(prefix = "producta.kafka")
public class ProductAKafkaConfigurationProperties {
    String bootstrapServers;
    String consumerGroupId;
    String consumerAutoOffsetReset;
    String listenerAckMode;
    Boolean autoStartup;
}
