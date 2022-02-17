package cz.tul;

import cz.tul.configurations.MainSpringConfiguration;
import cz.tul.data.User;
import cz.tul.data.UsersDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;

public class Main {

        public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        ConfigurableEnvironment environment = context.getEnvironment();

        environment.setActiveProfiles("DEV");
        context.register(MainSpringConfiguration.class);
        context.refresh();

        UsersDao usersDao = context.getBean(UsersDao.class);

        List<User> users = usersDao.getAllUsers();
        System.out.println(users);

        }
}
