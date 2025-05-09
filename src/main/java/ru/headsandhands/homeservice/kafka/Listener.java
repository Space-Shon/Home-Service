package ru.headsandhands.homeservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import ru.headsandhands.homeservice.Service.HomeService;

@Slf4j
@Component
@RequiredArgsConstructor
public class Listener {

    private final HomeService homeService;

    @KafkaListener(topics = "user-topic")
    public void listener1(
            ConsumerRecord<String, String> record,
            Acknowledgment acknowledgment
    ) {
        try {
            var userId = record.value();

            homeService.getHomeBy(userId).ifPresentOrElse(
                    home -> {
                        homeService.deleteHome(userId, 1); // здесь пока "1" — магическое число
                        log.info("Удален дом пользователя с ID {}", userId);
                    },
                    () -> log.info("Дом для пользователя {} не найден", userId)
            );

            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.error("Ошибка при обработке сообщения из Kafka: {}", e.getMessage(), e);
        }
    }
}