package cz.tul.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
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

    @Autowired
    private Properties properties;

    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations() throws ClassNotFoundException {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource source = new SimpleDriverDataSource();
        source.setDriverClass((Class<? extends Driver>) Class.forName(connectionDriver));
        source.setUrl(connectionURL);
        source.setUsername(connectionUsername);
        source.setPassword(connectionPassword);

        return source;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws ClassNotFoundException {
        LocalSessionFactoryBean builder = new LocalSessionFactoryBean();
        builder.setDataSource(dataSource());
        builder.setPackagesToScan("cz.tul.data");
        builder.setHibernateProperties(properties);

        return builder;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() throws ClassNotFoundException {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

}
