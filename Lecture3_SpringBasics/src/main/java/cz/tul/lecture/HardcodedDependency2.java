package cz.tul.lecture;

import cz.tul.service.GreetingService;
import cz.tul.service.impl.SimpleGreetingService;

public class HardcodedDependency2 {

  private final GreetingService greetingService = new SimpleGreetingService("TEST");

    public void doSomething(){
        System.out.println(greetingService.greet("Pepa"));
    }
}
