package cz.tul.configuration;


import cz.tul.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableWebMvcSecurity
@Import({SecurityConfiguration.ProvidedInMemorySecurityConfiguration.class, SecurityConfiguration.CustomSecurityConfiguration.class})
public class SecurityConfiguration {


    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @Profile("default")
    // annotation based  request authentication configuration
    public static class ProvidedInMemorySecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .inMemoryAuthentication()
                    .withUser("user").password("password").roles("USER").and()
                    .withUser("admin").password("admin").roles("USER", "ADMIN", "OPERATOR").and()
                    .withUser("operator").password("operator").roles("USER", "OPERATOR");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .anyRequest().authenticated().and()
                    .csrf().disable()
                    .formLogin().and()
                    .httpBasic().and()
                    .anonymous().and()
                    .logout();
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }

    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @Profile("custom")
    // java code based authentication configuration
    public static class CustomSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .inMemoryAuthentication()
                    .withUser("user").password("password").roles("USER").and()
                    .withUser("admin").password("admin").roles("USER", "ADMIN", "OPERATOR").and()
                    .withUser("operator").password("operator").roles("USER", "OPERATOR");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/").hasRole("USER")
                    .antMatchers(HttpMethod.GET,"/secret").hasRole("OPERATOR")
                    .antMatchers(HttpMethod.GET, "/secret/*").hasRole("OPERATOR")
                    .antMatchers("/secret").hasRole("ADMIN")
                    .anyRequest().denyAll()
                    .and()
                    .formLogin().and()
                    .httpBasic().and().logout();
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }


    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @Profile("manager")
    // java code based authentication configuration with own authentication provider
    public static class CustomAuthenticationManagerSecurityConfiguration extends WebSecurityConfigurerAdapter {


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/").hasRole("USER")
                    .anyRequest().denyAll()
                    .and()
                    .formLogin().and()
                    .httpBasic().and().logout();
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return new ProviderManager(Collections.singletonList(new CustomAuthenticationProvider()));
        }
    }
}
