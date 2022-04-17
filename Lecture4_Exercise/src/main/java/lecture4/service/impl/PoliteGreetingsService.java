package lecture4.service.impl;

import lecture4.Main;
import lecture4.service.IGreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("devel")
public class PoliteGreetingsService implements IGreetingService {

    private static final Logger log = LoggerFactory.getLogger(PoliteGreetingsService.class);

    @Override
    public String greet(String name)
    {
        log.debug("Polite greeting for %s".formatted(name));
        return "Hello %s".formatted(name);
    }
}
