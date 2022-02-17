package cz.tul;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.ConfigurableWebApplicationContext;

public class ContextProfileInitializer implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {

    @Override
    public void initialize(final ConfigurableWebApplicationContext ctx) {
        ConfigurableEnvironment environment = ctx.getEnvironment();

        environment.setActiveProfiles("PROD");
    }
}