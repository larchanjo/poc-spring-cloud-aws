# Overview

This is a poc using [Spring Cloud Amazon Web Service](https://spring.io/projects/spring-cloud-aws)

# Setup

[AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-configure.html)
[AWS SQS](https://aws.amazon.com/sqs/)

# Testing

## Standard Queue

#### StandardProducer

Sends the messages to `standard-queue`

#### StandardConsumer

Process the messages from `standard-queue`

```
The messages should not be received in order

Message 1
Message 3
Message 2
Message 5
```

## FIFO Queue

#### FifoProducer

Sends the messages to `queue.fifo`

#### FifoConsumer

Process the messages from `standard-queue`

```
The messages should be received in order

Message 1
Message 2
Message 3
Message 4
```

# Be Happy

