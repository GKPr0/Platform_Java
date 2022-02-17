package cz.tul.scan;

import cz.tul.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class SimpleComponentWithAutowiredConstructor {

    private final GreetingService greetingService;

    @Autowired
    public SimpleComponentWithAutowiredConstructor(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void doSomething(){
        System.out.println(this.greetingService.greet(this.getClass().getName()));
    }
}
