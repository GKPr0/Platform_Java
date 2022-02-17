package cz.tul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@SpringBootApplication
public class Main {

    @Autowired
    private Environment environment;

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    @Value("${app.label}")
    private String label;

    @Autowired
    private MainConfiguration config;

    @RequestMapping("/")
    private String index() {
        return config.getName()+"-"+label;
    }

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        ConfigurableApplicationContext context = app.run(args);

        LocalDateTime now = LocalDateTime.now();

        log.debug("Dnes je {}", now);
        log.debug("DEBUG");
        log.info("INFO");
        log.warn("WARN");
        log.error("ERROR");
        //context.close();

    }

}