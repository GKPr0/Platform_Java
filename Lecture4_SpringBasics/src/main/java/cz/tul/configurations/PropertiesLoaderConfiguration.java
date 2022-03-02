package cz.tul.configurations;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import({
        PropertiesLoaderConfiguration.TestPropertiesLoaderConfiguration.class,
        PropertiesLoaderConfiguration.DevelPropertiesLoaderConfiguration.class,
        PropertiesLoaderConfiguration.ProductionPropertiesLoaderConfiguration.class
})
public class PropertiesLoaderConfiguration {


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Configuration
    @PropertySource("classpath:app_test.properties")
    @Profile("TEST")
    public static class TestPropertiesLoaderConfiguration {

    }

    @Configuration
    @PropertySource("classpath:app_devel.properties")
    @Profile("DEV")
    public static class DevelPropertiesLoaderConfiguration {

    }

    @Configuration
    @PropertySource("classpath:app.properties")
    @Profile("PROD")
    public static class ProductionPropertiesLoaderConfiguration {

    }
}
