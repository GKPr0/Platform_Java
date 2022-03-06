package cz.tul.lecture;

import cz.tul.service.GreetingService;
import cz.tul.service.impl.SimpleGreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfiguration {

    @Bean
    public GreetingService greetingService()    {
        return new SimpleGreetingService("I DON'T CARE");
    }

    @Bean
    public HardcodedDependencySolution hardcodedDependencySolution(){
        return new HardcodedDependencySolution(greetingService());
    }
}
