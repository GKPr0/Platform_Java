package cz.tul.service;

import java.util.List;

public interface GreetingService {


    public default String greet(String name) {
        return "Hello " + name + "!";
    }

    public int howManyPeopleDoYouKnow();
}
