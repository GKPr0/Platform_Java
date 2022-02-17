package cz.tul.rabbitmq;

import cz.tul.rabbitmq.configurations.RabbitMQConfiguration;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        SpringApplication app = new SpringApplication(RabbitMQConfiguration.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = app.run(args);

        AmqpTemplate ampq = context.getBean(AmqpTemplate.class);
        ampq.convertAndSend(RabbitMQConfiguration.RABBITMQ_FANOUT_EXCHANGE, RabbitMQConfiguration.DRINK_DIRECT_ROUTING_KEY, "water");

        Thread.sleep(1000);
        System.exit(0);
    }
}
