package lecture4;

import lecture4.model.Person;
import lecture4.service.IGreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class Main {

    private static ConfigurableApplicationContext context;

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    @Value("${app.profile}")
    private String profile;

    @RequestMapping("/")
    @ResponseBody
    public String index()
    {
        log.debug("Index requested");
        return "Application runs with profile:" + profile ;
    }

    @RequestMapping("/greeting")
    @ResponseBody
    public String greeting()
    {
        log.debug("Greeting requested");
        Person person = context.getBean(Person.class);
        return person.great("Ondra");
    }

    public static void main(String[] args) {
        context = SpringApplication.run(Main.class, args);

        log.info("Application started!");
    }
}
