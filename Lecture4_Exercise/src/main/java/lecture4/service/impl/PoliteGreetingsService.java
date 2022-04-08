package lecture4.service.impl;

import lecture4.service.IGreetingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("PROD")
public class PoliteGreetingsService implements IGreetingService {

    @Override
    public String greet(String name)
    {
        return "Hello %s".formatted(name);
    }
}
