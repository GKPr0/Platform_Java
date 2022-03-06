package cz.tul;

import cz.tul.scan.SimpleComponentWithAutowiredConstructor;
import cz.tul.scan.SimpleComponentWithAutowiredSetter;
import cz.tul.service.GreetingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

public class Main {


    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        ConfigurableEnvironment environment = context.getEnvironment();
        environment.setActiveProfiles( "PROD");


        Properties properties = new Properties();
        properties.setProperty("app.prop","test");
        environment.getPropertySources().addLast(new PropertiesPropertySource("external",properties));


        context.register(MainSpringConfiguration.class);
        context.refresh();

        GreetingService greetingService =  context.getBean(GreetingService.class);
        System.out.println(greetingService.greet("Ondra"));
        System.out.println("Property 'reldb.connection.url' is "+ environment.getProperty("reldb.connection.url"));

        context.getBean(SimpleComponentWithAutowiredConstructor.class).doSomething();
        context.getBean(SimpleComponentWithAutowiredSetter.class).doSomething();
        context.close();
    }
}
