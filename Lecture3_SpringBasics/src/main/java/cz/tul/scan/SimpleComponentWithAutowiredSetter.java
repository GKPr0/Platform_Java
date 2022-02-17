package cz.tul.scan;

import cz.tul.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class SimpleComponentWithAutowiredSetter {

    private GreetingService greetingService;


    public void doSomething(){
        System.out.println(this.greetingService.greet(this.getClass().getName()));
    }

    @Autowired
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
}
