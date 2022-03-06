package cz.tul;


import cz.tul.configurations.GreeterConfiguration;
import cz.tul.configurations.PropertiesLoaderConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        GreeterConfiguration.class,
        PropertiesLoaderConfiguration.class
})
@ComponentScan("cz.tul.scan")
public class MainSpringConfiguration {




}
