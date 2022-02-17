package cz.tul.lecture;

import cz.tul.service.GreetingService;

public class HardcodedDependencySolution2 {

    private GreetingService greetingService;

    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void doSomething() {
        System.out.println(greetingService.greet("Pepa"));
    }
}
