package cz.tul;

import cz.tul.app.Main;
import cz.tul.data.User;
import cz.tul.data.UsersDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Main.class})
@ActiveProfiles({"test"})
public class UserDaoTests {

    @Autowired
    private UsersDao usersDao;


    private User user1 = new User("johnwpurcell", "John Purcell", "hellothere",
            "john@caveofprogramming.com", true, "ROLE_USER");
    private User user2 = new User("richardhannay", "Richard Hannay", "the39steps",
            "richard@caveofprogramming.com", true, "ROLE_ADMIN");
    private User user3 = new User("suetheviolinist", "Sue Black", "iloveviolins",
            "sue@caveofprogramming.com", true, "ROLE_USER");
    private User user4 = new User("rogerblake", "Rog Blake", "liberator",
            "rog@caveofprogramming.com", false, "user");

    @Before
    public void init() {
        usersDao.deleteUsers();
    }


    @Test
    public void testCreateRetrieve() {
        usersDao.create(user1);

        List<User> users1 = usersDao.getAllUsers();

        System.out.println(users1);

        assertEquals("One user should have been created and retrieved", 1, users1.size());

        assertEquals("Inserted user should match retrieved", user1, users1.get(0));

        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);

        List<User> users2 = usersDao.getAllUsers();

        assertEquals("Should be four retrieved users.", 4, users2.size());
    }

    @Test
    public void testExists() {
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user3);

        assertTrue("User should exist.", usersDao.exists(user2.getUsername()));
        assertFalse("User should not exist.", usersDao.exists("xkjhsfjlsjf"));
    }
}
