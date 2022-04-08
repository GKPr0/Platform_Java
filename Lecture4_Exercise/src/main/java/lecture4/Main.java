package lecture4;

import lecture4.model.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        ConfigurableEnvironment environment = context.getEnvironment();
        environment.setActiveProfiles("TEST");

        context.register(MainConfiguration.class);
        context.refresh();

        Person person = context.getBean(Person.class);
        System.out.println(person.great("Ondra"));

        context.close();
    }
}
