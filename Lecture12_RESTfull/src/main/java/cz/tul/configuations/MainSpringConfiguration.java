package cz.tul.configuations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
@Import({
        PropertiesLoaderConfiguration.class,
        PersistenceConfiguration.class,
        RepositoryRestMvcConfiguration.class,
})

public class MainSpringConfiguration {

    //Specialization of PlaceholderConfigurerSupport that resolves ${...} placeholders within bean definition property values and @Value annotations against the current Spring Environment and its set of PropertySources.
    //Tento Bean provádí vnitřní konfikuraci platformy Spring. Je využíván platformou samotnou k načítání dat z PropertyResources pomocí anotace @Value
    //Může být umístěn i v jiné konfigurační třídě než Main, protože se týká konfigurace celé platformy a není nikde injektován v žádné třídě aplikace
    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
