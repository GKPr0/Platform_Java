package lecture4.service.impl;

import lecture4.service.IGreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class FriendlyGreetingService implements IGreetingService {
    private static final Logger log = LoggerFactory.getLogger(FriendlyGreetingService.class);

    @Override
    public String greet(String name)
    {
        log.debug("Friendly greeting for %s".formatted(name));
        return "Hi %s".formatted(name);
    }
}
