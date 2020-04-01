package com.example.aws.sqs;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StandardProducer {

  private final AtomicInteger counter = new AtomicInteger(1);

  private final QueueMessagingTemplate messagingTemplate;

  private final String queue;

  public StandardProducer(AmazonSQSAsync amazonSQS, @Value("${sqs.standard-queue}") String queue) {
    this.queue = queue;
    this.messagingTemplate = new QueueMessagingTemplate(amazonSQS);
  }

  @Scheduled(initialDelay = 1000, fixedDelay = 10000)
  public void send() {
    var payload = "Standard Message " + counter.getAndIncrement();
    log.info("Sending message {} to {}", payload, queue);

    this.messagingTemplate.convertAndSend(queue, payload);
  }

}