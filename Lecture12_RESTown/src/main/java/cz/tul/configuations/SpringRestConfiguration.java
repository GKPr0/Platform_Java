package cz.tul.configuations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"cz.tul.controllers"})
//public class SpringRestConfiguration extends RepositoryRestMvcConfiguration {
public class SpringRestConfiguration extends WebMvcConfigurerAdapter {


}
