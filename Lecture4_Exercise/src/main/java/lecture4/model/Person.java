package lecture4.model;

import lecture4.service.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {

    private final IGreetingService greetingService;

    @Autowired
    public Person(IGreetingService greetingService)
    {
        this.greetingService = greetingService;
    }

    public String great(String name)
    {
        return greetingService.greet(name);
    }
}
