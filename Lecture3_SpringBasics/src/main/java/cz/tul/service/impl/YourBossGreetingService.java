package cz.tul.service.impl;

import cz.tul.service.GreetingService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class YourBossGreetingService implements GreetingService {



    @Override
    public String greet(String name) {
        return "Hi - I am your boss, go back and do your job !!!!!";
    }

    @Override
    public int howManyPeopleDoYouKnow() {
        return 1; // i do know only myself
    }
}
