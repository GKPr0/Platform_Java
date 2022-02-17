package cz.tul.lecture;

import cz.tul.service.GreetingService;
import cz.tul.service.impl.SimpleGreetingService;

public class HardcodedDependencySolution {

  private final GreetingService greetingService;

    public HardcodedDependencySolution(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void doSomething(){
        System.out.println(greetingService.greet("Pepa"));
    }
}
