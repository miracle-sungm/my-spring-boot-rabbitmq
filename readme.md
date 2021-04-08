# My Spring Boot RabbitMQ Project

Spring Boot 整合 RabbitMQ 项目，用于个人学习和总结，如果您喜欢，请点个Star

Rabbit MQ官网：https://www.rabbitmq.com/

模块介绍：
- common-api: 基本模块，定义API和常量等等
- common-consumer: RabbitMQ通用消费者模块
- fanout-producer: 分发消息生产者（发布订阅模式）
- direct-producer: 路由消息生产者（简单模式、工作队列模式、路由模式）
- topic-producer: 主题消息生产者（主题模式）
- advance-feature: RabbitMQ高级特性，包括死信队列、消息过期设置、分布式事务，其他特性待完善。

RabbitMQ其他特性完善ing...

# RabbitMQ介绍

RabbitMQ是一个基于AMQP应用层协议，通过Erlang语言编写的消息中间件。具备异步，解耦，削峰，支持分布式事务等特性，并且支持多种开发语言。

官网地址（通过官网更好的了解RabbitMQ吧）：https://www.rabbitmq.com/

这是一个Spring Boot整合RabbitMQ的Demo项目，包含几种常见的交换机类型：fanout, direct, topic等等。介绍了Rabbit的一些其他特性：消息过期处理等。

fanout通常用于发布/订阅模式，并且不需要设置routingKey。生产者发布的消息通过交换机分发给不同的消息队列，每个与交换机绑定的消息队列都会接收到生产者发布的每个消息。

direct通常工作队列模式，路由模式。
路由模式和工作队列模式都是将生产者发布的消息通过指定的交换机和routingKey传递到消息队列中。
工作队列模式分为两种：轮询工作队列模式、公平工作队列模式。
轮询工作队列模式是一种绝对公平的模式，不同的消费者依次从消息队列中消费消息，每个消费者获得的消息是公平的。
公平工作队列模式是一种不公平的模式，“消费能力强”的消费者能够消费队列中更多的消息，反之，“消费能力弱”的消费者无法消费队列中更多的消息。

topic通常用于主题模式。
在主题模式中，消息队列绑定指定的交换机并设置“模糊”的routingKey(包括*和#表达式：*表示只能匹配一项，#表示可以不匹配、匹配一项或多项)。
当生产者发布的消息时，通过指定的交换机和“具体”的routingKey传递消息时，“模糊”的routingKey会与“具体的”routingKey进行匹配，匹配正确的routingKey会把消息传递给绑定的消息队列，反之不传递。
这种模式下，可以将生产者发布的消息，通过指定的交换机，同时将消息传递给绑定了相似routingKey的消息队列，实现一消息多发的机制（注意：fanout是一消息全发，direct是一消息一发）。




