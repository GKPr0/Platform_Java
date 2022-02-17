package cz.tul;

import cz.tul.configurations.MainSpringConfiguration;
import cz.tul.data.User;
import cz.tul.data.UsersDao;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainSpringConfiguration.class)
@ActiveProfiles({"TEST"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsersDaoTests {


    @Autowired
    private UsersDao usersDao;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testUsers() {
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);

        jdbc.getJdbcOperations().execute("DELETE FROM USERS");

        User user = new User("developer", "Petr", "hellothere",
                "petr@seznam.cz", true, "user");

        assertTrue("User creation should return true", usersDao.create(user));

        List<User> users = usersDao.getAllUsers();

        assertEquals("Number of users should be 1.", 1, users.size());

        assertTrue("User should exist.", usersDao.exists(user.getUsername()));

        assertEquals("Created user should be identical to retrieved user",
                user, users.get(0));

    }
}
