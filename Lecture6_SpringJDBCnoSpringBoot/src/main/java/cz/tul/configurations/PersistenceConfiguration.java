package cz.tul.configurations;

import cz.tul.provisioning.Provisioner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class PersistenceConfiguration {

    //Pro správnou funkci této anotace je nutné definovat Bean PropertySourcesPlaceholderConfigurer - viz třída MainSpringConfiguration
    @Value("${reldb.connection.driver}")
    private String connectionDriver;

    @Value("${reldb.connection.url}")
    private String connectionURL;

    @Value("${reldb.connection.username}")
    private String connectionUsername;

    @Value("${reldb.connection.password}")
    private String connectionPassword;

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() throws ClassNotFoundException, NamingException {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() throws NamingException, ClassNotFoundException {
        SimpleDriverDataSource source = new SimpleDriverDataSource();
        source.setDriverClass((Class<? extends Driver>) Class.forName(connectionDriver));
        source.setUrl(connectionURL);
        source.setUsername(connectionUsername);
        source.setPassword(connectionPassword);

        return source;
    }

    @Bean(initMethod = "doProvision")
    public Provisioner provisioner() throws ClassNotFoundException, NamingException {
        return new Provisioner(namedParameterJdbcTemplate());
    }
}
