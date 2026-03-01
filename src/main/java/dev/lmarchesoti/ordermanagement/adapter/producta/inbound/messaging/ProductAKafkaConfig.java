package dev.lmarchesoti.ordermanagement.adapter.producta.inbound.messaging;

import dev.lmarchesoti.ordermanagement.adapter.producta.dto.ProductAOrder;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.backoff.FixedBackOff;

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
        factory.setCommonErrorHandler(errorHandler(recoverer(retryableTopicKafkaTemplate())));
        return factory;
    }

    @Bean
    public ProducerFactory<String, ProductAOrder> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, configurationProperties.bootstrapServers);
        return new DefaultKafkaProducerFactory<>(
                config, new StringSerializer(), new JsonSerializer<>());
    }

    @Bean
    public KafkaTemplate<String, ProductAOrder> retryableTopicKafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public DeadLetterPublishingRecoverer recoverer(KafkaTemplate<String, ProductAOrder> template) {
        return new DeadLetterPublishingRecoverer(template,
                (record, ex) -> new TopicPartition(configurationProperties.dltTopic, record.partition()));
    }

    @Bean
    public DefaultErrorHandler errorHandler(DeadLetterPublishingRecoverer recoverer) {
        DefaultErrorHandler handler = new DefaultErrorHandler(recoverer, new FixedBackOff(0L, 0));
        handler.setAckAfterHandle(true);
        return handler;
    }


}
