package cz.tul.configurations;

import cz.tul.data.OffersDao;
import cz.tul.data.UsersDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfiguration {

    @Bean
    public OffersDao offersDao() {
        return new OffersDao();
    }

    @Bean
    public UsersDao usersDao() {
        return new UsersDao();
    }
}
