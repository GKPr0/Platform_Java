package cz.tul;

import cz.tul.configurations.MainSpringConfiguration;
import cz.tul.data.Offer;
import cz.tul.data.User;
import cz.tul.repositories.OfferRepository;
import cz.tul.repositories.UserRepository;
import cz.tul.service.OfferService;
import cz.tul.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        ConfigurableEnvironment environment = context.getEnvironment();

        environment.setActiveProfiles("PROD");
        context.register(MainSpringConfiguration.class);
        context.refresh();

        UserService usersService = new UserService(context.getBean(UserRepository.class));

        List<User> users = usersService.getAllUsers();
        System.out.println(users);


        OfferService offerService = new OfferService(context.getBean(OfferRepository.class));
        List<Offer> offers = offerService.getOffersByName("jan.novak");
        System.out.println(offers);


//    SET DATABASE DEFAULT TABLE TYPE CACHED do souboru develdb.script

    }
}
