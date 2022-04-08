package lecture4.service.impl;

import lecture4.service.IGreetingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("TEST")
public class FriendlyGreetingService implements IGreetingService {}
