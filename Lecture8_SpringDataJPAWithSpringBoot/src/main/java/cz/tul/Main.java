package cz.tul;

import cz.tul.data.Offer;
import cz.tul.data.User;
import cz.tul.service.OfferService;
import cz.tul.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("cz.tul.repositories")
//@EntityScan("cz.tul.data")
public class Main {

//    @Bean
//    public OfferService offerService() {
//        return new OfferService();
//    }

    @Bean
    public UserService usersService() {
        return new UserService();
    }

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext context = app.run(args);

        UserService usersService = context.getBean(UserService.class);

        List<User> users = usersService.getAllUsers();
        System.out.println(users);


        OfferService offersService = context.getBean(OfferService.class);
        List<Offer> offers = offersService.getOffersByName("jan.novak");
        System.out.println(offers);

    }
}
