package com.example.aws.sqs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FifoConsumer {

  @SqsListener(value = "${sqs.fifo-queue}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
  public void process(String payload) {
    log.info("Consuming message=[{}]", payload);
  }

}
