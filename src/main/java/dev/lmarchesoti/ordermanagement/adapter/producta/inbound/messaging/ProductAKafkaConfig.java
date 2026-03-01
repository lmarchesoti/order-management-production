package dev.lmarchesoti.ordermanagement.adapter.producta.inbound.messaging;

import dev.lmarchesoti.ordermanagement.adapter.producta.dto.ProductAOrder;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ProductAKafkaConfig {

    private final ProductAKafkaConfigurationProperties configurationProperties;

    @Bean
    public ConsumerFactory<String, ProductAOrder> productAKafkaListenerConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, configurationProperties.bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, configurationProperties.consumerGroupId);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, configurationProperties.consumerAutoOffsetReset);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(ProductAOrder.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProductAOrder> productAKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ProductAOrder> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(productAKafkaListenerConsumerFactory());
        factory.getContainerProperties().setGroupId(configurationProperties.getConsumerGroupId());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.valueOf(configurationProperties.listenerAckMode.toUpperCase()));
        factory.setAutoStartup(configurationProperties.autoStartup);
        return factory;
    }
}
