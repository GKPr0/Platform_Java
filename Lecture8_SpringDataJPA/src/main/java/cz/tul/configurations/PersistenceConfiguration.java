package cz.tul.configurations;

import cz.tul.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;


@Configuration
@EnableJpaRepositories(basePackageClasses = {UserRepository.class})
//@EnableTransactionManagement
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
    public NamedParameterJdbcOperations namedParameterJdbcOperations() throws ClassNotFoundException, NamingException {
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

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        //jpaVendorAdapter.setDatabase(Database.H2);
        //jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException, ClassNotFoundException {
        LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
        lemfb.setDataSource(dataSource());
        lemfb.setJpaVendorAdapter(jpaVendorAdapter());
        lemfb.setPackagesToScan("cz.tul.data");
        lemfb.setJpaProperties(properties);
        return lemfb;
    }

}
