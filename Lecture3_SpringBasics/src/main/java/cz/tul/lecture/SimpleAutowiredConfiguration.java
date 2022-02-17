package cz.tul.lecture;

import cz.tul.service.GreetingService;
import cz.tul.service.impl.SimpleGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleAutowiredConfiguration {

    @Autowired
    private GreetingService greetingService;

    @Bean
    public HardcodedDependencySolution hardcodedDependencySolution2(){
        return new HardcodedDependencySolution(greetingService);
    }
}
