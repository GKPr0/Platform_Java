package cz.tul.lecture;

import cz.tul.service.GreetingService;
import cz.tul.service.impl.SimpleGreetingService;

public class HardcodedDependencySetup {


    public static void main(String[] args) {
        GreetingService serv = new SimpleGreetingService("Hi!");
        HardcodedDependencySolution h3 = new HardcodedDependencySolution(serv);
        h3.doSomething();
    }
}
