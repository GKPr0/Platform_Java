package cz.tul;

import cz.tul.configurations.AppConfiguration;
import cz.tul.data.User;
import cz.tul.data.UsersDao;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(AppConfiguration.class);
        ApplicationContext ctx = app.run(args);

        UsersDao usersDao = ctx.getBean(UsersDao.class);

        List<User> users = usersDao.getAllUsers();
        System.out.println(users);

    }

}