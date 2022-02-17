package cz.tul.rabbitmq.configurations;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableRabbit
public class RabbitMQConfiguration {

    public static final String RABBITMQ_FANOUT_EXCHANGE = "tul.fanout.ex";
    public static final String RABBITMQ_TOPIC_EXCHANGE = "tul.topic.ex";
    public static final String RABBITMQ_DIRECT_EXCHANGE = "tul.direct.ex";


    public static final String RABBITMQ_DIRECT_QUEUE = "tul.direct";

    public static final String RABBITMQ_VEGETABLE_QUEUE = "tul.vegetable";
    public static final String RABBITMQ_FRUIT_QUEUE = "tul.fruit";
    public static final String RABBITMQ_DRINK_QUEUE = "tul.drink";
    public static final String RABBITMQ_DLC_QUEUE = "tul.dlc";
    public static final String RABBITMQ_DLC_EXCHANGE = "tul.dlx";

    public static final String RABBITMQ_FANOUT_1_QUEUE = "tul.fanout1";
    public static final String RABBITMQ_FANOUT_2_QUEUE = "tul.fanout2";
    public static final String RABBITMQ_FANOUT_3_QUEUE = "tul.fanout3";


    public static final String FRUIT_DIRECT_ROUTING_KEY = "fruit";
    public static final String VEGETABLE_DIRECT_ROUTING_KEY = "veg";
    public static final String DRINK_DIRECT_ROUTING_KEY = "drink";

    @Bean
    public Queue vegQueue() {
        return new Queue(RABBITMQ_VEGETABLE_QUEUE);
    }

    @Bean
    public Queue fruitQueue() {
        return new Queue(RABBITMQ_FRUIT_QUEUE);
    }

    @Bean
    public Queue drinkQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", RABBITMQ_DLC_EXCHANGE);
        return new Queue(RABBITMQ_DRINK_QUEUE, true, false, false, args);
    }

    @Bean
    public Queue dlcQueue() {
        return new Queue(RABBITMQ_DLC_QUEUE);
    }

    @Bean
    public Queue directQueue() {
        return new Queue(RABBITMQ_DIRECT_QUEUE);
    }

    @Bean
    public Queue fan1Queue() {
        return new Queue(RABBITMQ_FANOUT_1_QUEUE);
    }

    @Bean
    public Queue fan2Queue() {
        return new Queue(RABBITMQ_FANOUT_2_QUEUE);
    }

    @Bean
    public Queue fan3Queue() {
        return new Queue(RABBITMQ_FANOUT_3_QUEUE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RABBITMQ_FANOUT_EXCHANGE);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RABBITMQ_DIRECT_EXCHANGE);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RABBITMQ_TOPIC_EXCHANGE);
    }

    @Bean
    public FanoutExchange dlcExchange() {
        return new FanoutExchange(RABBITMQ_DLC_EXCHANGE);
    }

    @Bean
    public Binding fanout1Binding() {
        return BindingBuilder.bind(fan1Queue()).to(fanoutExchange());
    }

    @Bean
    public Binding fanout2Binding() {
        return BindingBuilder.bind(fan2Queue()).to(fanoutExchange());
    }

    @Bean
    public Binding fanout3Binding() {
        return BindingBuilder.bind(fan3Queue()).to(fanoutExchange());
    }

    @Bean
    public Binding directBindingFruit() {
        return BindingBuilder.bind(fruitQueue()).to(directExchange()).with(FRUIT_DIRECT_ROUTING_KEY);
    }

    @Bean
    public Binding directBindingVeg() {
        return BindingBuilder.bind(vegQueue()).to(directExchange()).with(VEGETABLE_DIRECT_ROUTING_KEY);
    }

    @Bean
    public Binding directBindingDrink() {
        return BindingBuilder.bind(drinkQueue()).to(directExchange()).with(DRINK_DIRECT_ROUTING_KEY);
    }

    @Bean
    public Binding dlcBinding() {
        return BindingBuilder.bind(dlcQueue()).to(dlcExchange());
    }

    @Bean
    public Binding topicBindingDrink() {
        return BindingBuilder.bind(drinkQueue()).to(topicExchange()).with(DRINK_DIRECT_ROUTING_KEY + ".#");
    }

    @Bean
    public Binding topicBindingVeg() {
        return BindingBuilder.bind(drinkQueue()).to(topicExchange()).with(VEGETABLE_DIRECT_ROUTING_KEY + ".#");
    }

    @Bean
    public Binding topicBindingFruit() {
        return BindingBuilder.bind(drinkQueue()).to(topicExchange()).with(FRUIT_DIRECT_ROUTING_KEY + ".#");
    }
}




