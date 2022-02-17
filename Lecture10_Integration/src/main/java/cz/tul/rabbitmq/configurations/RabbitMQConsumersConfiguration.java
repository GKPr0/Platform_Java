package cz.tul.rabbitmq.configurations;

import cz.tul.rabbitmq.consumers.SimpleConsumer;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConsumersConfiguration {


    @RabbitListener(queues = {RabbitMQConfiguration.RABBITMQ_FRUIT_QUEUE})
    public void consumeFromFruitsQueue(String data) {
        System.out.println("Consumed from fruit queue " + data);
    }

    @RabbitListener(queues = {RabbitMQConfiguration.RABBITMQ_VEGETABLE_QUEUE})
    public void consumeFromVegetableQueue(String data) {
        System.out.println("Consumed from vegetable queue " + data);
    }

    @RabbitListener(queues = {RabbitMQConfiguration.RABBITMQ_DRINK_QUEUE})
    public void consumeDrinkQueue(String data) {
        if(data.equals("water")){
            // we must instruct Rabbitmq to do not requeue this message as it will try to redeliver this message infinitely
            // second option is to implement own exception handler
            throw new AmqpRejectAndDontRequeueException("water ? MUST BE KIDDING - send me bear instead");
        }else{
            System.out.println("Consumed from drink queue " + data);
        }
    }

    @RabbitListener(queues = {RabbitMQConfiguration.RABBITMQ_DLC_QUEUE})
    public void consumeFromDLC(String data) {
        System.out.println("DLC queue - OH MY GOD - got some dead letter - " + data);
    }

    @RabbitListener(queues = {RabbitMQConfiguration.RABBITMQ_FANOUT_1_QUEUE})
    public void consumeFanout1(String data) {
        System.out.println("Consumed from fanout1 queue " + data);
    }

    @RabbitListener(queues = {RabbitMQConfiguration.RABBITMQ_FANOUT_2_QUEUE})
    public void consumeFanout2(String data) {
        System.out.println("Consumed from fanout2 queue " + data);
    }

    @RabbitListener(queues = {RabbitMQConfiguration.RABBITMQ_DIRECT_QUEUE})
    public void consumeDirect(String data) {
        System.out.println("Consumed from direct queue " + data);
    }


    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(RabbitMQConfiguration.RABBITMQ_FANOUT_3_QUEUE);
        container.setMessageListener(new MessageListenerAdapter(new SimpleConsumer(), "receiveMessage"));
        return container;
    }

}




