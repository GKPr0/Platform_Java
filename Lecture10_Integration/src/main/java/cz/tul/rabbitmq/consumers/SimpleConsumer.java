package cz.tul.rabbitmq.consumers;

public class SimpleConsumer {
    public void receiveMessage(String message) {
        System.out.println("SimpleConsumer received <" + message + ">");
    }
}
