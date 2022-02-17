package cz.tul;

import cz.tul.provision.MongoProvisioner;
import cz.tul.repositories.UserRepository;
import cz.tul.services.UserServiceImpl;
import cz.tul.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Map;


@SpringBootApplication
public class MainConfiguration {

    @Autowired
    private MongoTemplate mongo;

    @Bean
    public UserService userService() {
        return new UserServiceImpl(mongo);
    }

    @Bean
    public MongoProvisioner mongoCollectionProvisioner() {
        return new MongoProvisioner(mongo);
    }


    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(MainConfiguration.class, args);

        UserRepository userRepository = context.getBean(UserRepository.class);
        System.out.println(String.format("Users collection contains %d records", userRepository.count()));


        UserService userService = context.getBean(UserService.class);
        Map<Integer, Integer> ageHistogram = userService.ageHistogramUsingMapReduce();
        for (int age : ageHistogram.keySet()) {
            System.out.println(String.format("Age %d, count %d", age, ageHistogram.get(age)));
        }
    }

}
