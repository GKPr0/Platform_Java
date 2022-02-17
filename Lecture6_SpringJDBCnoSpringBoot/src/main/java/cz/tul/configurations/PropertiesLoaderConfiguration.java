package cz.tul.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({
        PropertiesLoaderConfiguration.TestPropertiesLoaderConfiguration.class,
        PropertiesLoaderConfiguration.DevelPropertiesLoaderConfiguration.class,
        PropertiesLoaderConfiguration.ProductionPropertiesLoaderConfiguration.class
})
public class PropertiesLoaderConfiguration {


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
    @Profile("PROD")
    @PropertySource("classpath:app.properties")
    public static class ProductionPropertiesLoaderConfiguration {

    }
}
