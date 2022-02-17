package cz.tul.provisioning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class Provisioner {

    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    private DataSource dataSource;

    public Provisioner(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    public void doProvision() {
        List<String> allTables = jdbc.getJdbcOperations().queryForList("SELECT TABLE_NAME FROM  INFORMATION_SCHEMA.TABLES", String.class);

        if (!allTables.contains("offers")) {
            System.out.println("DB Provisioner: No tables exist and will be created");
            Resource rc = new ClassPathResource("create_tables.hsql");
            try {
                ScriptUtils.executeSqlScript(dataSource.getConnection(), rc);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("DB Provisioner: Table OFFERS exists, all existing tables: " + allTables);
    }
}


/*
public class Provisioner implements InitializingBean {

  private final NamedParameterJdbcOperations jdbc;

    public Provisioner(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc=namedParameterJdbcOperations;
    }

    public void afterPropertiesSet() {
        //......
    }
}
*/

/*
public class Provisioner {

   private final NamedParameterJdbcOperations jdbc;

    public Provisioner(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc=namedParameterJdbcOperations;
    }

    @PostConstruct
    public void doProvision() {
        //......
    }
}
*/