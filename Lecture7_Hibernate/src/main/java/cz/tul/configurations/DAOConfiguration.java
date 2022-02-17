package cz.tul.configurations;

import cz.tul.data.OffersDao;
import cz.tul.data.UsersDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DAOConfiguration {

    @Autowired
    private SessionFactory sessionFactory;

    @Bean
    public OffersDao offersDao() {
        return new OffersDao(sessionFactory);
    }

    @Bean
    public UsersDao usersDao() {
        return new UsersDao(sessionFactory);
    }
}
