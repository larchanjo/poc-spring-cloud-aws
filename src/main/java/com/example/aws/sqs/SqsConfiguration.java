package com.example.aws.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqsConfiguration {

  private final AmazonSQS amazonSQS;

  public SqsConfiguration(AmazonSQS amazonSQS) {
    this.amazonSQS = amazonSQS;
  }

  @Bean("FifoQueue")
  public CreateQueueResult createFifoQueue(@Value("${sqs.fifo-queue}") String queue) {
    CreateQueueRequest createQueueRequest = new CreateQueueRequest()
        .withQueueName(queue)
        .withAttributes(Map.of("FifoQueue", "true",
            "ContentBasedDeduplication", "true"));

    return amazonSQS.createQueue(createQueueRequest);
  }

  @Bean("StandardQueue")
  public CreateQueueResult createStandardQueue(@Value("${sqs.standard-queue}") String queue) {
    CreateQueueRequest createQueueRequest = new CreateQueueRequest()
        .withQueueName(queue);

    return amazonSQS.createQueue(createQueueRequest);
  }

}
