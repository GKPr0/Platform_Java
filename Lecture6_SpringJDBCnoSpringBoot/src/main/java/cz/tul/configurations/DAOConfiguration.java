package cz.tul.configurations;

import cz.tul.data.OffersDao;
import cz.tul.data.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@Configuration
public class DAOConfiguration {

    @Autowired
    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Bean
    public OffersDao offersDao() {
        return new OffersDao(namedParameterJdbcOperations);
    }

    @Bean
    public UsersDao usersDao() {
        return new UsersDao(namedParameterJdbcOperations);
    }
}
