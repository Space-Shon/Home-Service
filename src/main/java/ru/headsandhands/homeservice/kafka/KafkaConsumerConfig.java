package ru.headsandhands.homeservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import java.util.Map;


@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapAddress;

    private final KafkaProducerConfig kafkaProducerConfig;

    public KafkaConsumerConfig(KafkaProducerConfig kafkaProducerConfig) {
        this.kafkaProducerConfig = kafkaProducerConfig;
    }

    @Bean
    ConsumerFactory<? super Object, ? super Object> consumerFactory(
            KafkaProperties kafkaProperties
    ) {
        Map<String, Object> props = Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress,
                ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId(),
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getConsumer().getAutoOffsetReset(),
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class
        );
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(
            KafkaProperties kafkaProperties
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(kafkaProperties));

        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

        var recoverer = new DeadLetterPublishingRecoverer(kafkaProducerConfig.kafkaTemplate());
        var errorHandler = new DefaultErrorHandler(recoverer, new FixedBackOff(1000, 3)); // 3 попытки с интервалом 1 сек
        factory.setCommonErrorHandler(errorHandler);

        return factory;
    }
}