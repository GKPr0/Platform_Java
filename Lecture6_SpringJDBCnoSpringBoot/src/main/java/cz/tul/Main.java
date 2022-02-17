package cz.tul;

import cz.tul.configurations.MainSpringConfiguration;
import cz.tul.data.User;
import cz.tul.data.UsersDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;

public class Main {

    //@Autowired
    //static UsersDao usersDao;
    //Because at the moment the class loader load static values the context is not yet necessarly loaded. So it won't properly inject the bean and won' t even after.
    //V momentě, kdy jsou vytvářeny statické proměné a metody, ještě není načten kontext frameworku Spring. Proto není možné injektovat příslušné Beany do statických proměných.

    public static void main(String[] args) {

        //@Autowired
        //UsersDao usersDao;
        //Lokální proměnou nelze injektovat, protože je alokována na zásobníku
        //Lokální proměnou lze vytvoři z příslušného Beanu pouze pomocí metody context.getBean

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        ConfigurableEnvironment environment = context.getEnvironment();

        environment.setActiveProfiles("DEV");
        context.register(MainSpringConfiguration.class);
        context.refresh();

        //Pokud bychom vytvořili lokální proměnou usersDao pomocí operátou new, museli bychom jako argument do konstruktoru předat konkrétní objekt dataSource.
        //Tím bychom ovšem přišli o výhodu oddělení konfigurace od kódu aplikace.
        //usersDao proto bude vytvořen z příslušného Beanu dle použitého profilu pomocí metody context.GetBean()
        UsersDao usersDao = context.getBean(UsersDao.class);

        List<User> users = usersDao.getAllUsers();
        System.out.println(users);

        //SET DATABASE DEFAULT TABLE TYPE CACHED do souboru develdb.script

    }
}
