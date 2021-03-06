package cz.tul;

import cz.tul.data.User;
import cz.tul.service.UserService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {Main.class})
@SpringBootTest(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTests {

    @Autowired
    private UserService userService;


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
        userService.deleteUsers();
    }


    @Test
    public void testCreateRetrieve() {
        userService.create(user1);

        List<User> users1 = userService.getAllUsers();

        System.out.println(users1);

        assertEquals("One user should have been created and retrieved", 1, users1.size());

        assertEquals("Inserted user should match retrieved", user1, users1.get(0));

        userService.create(user2);
        userService.create(user3);
        userService.create(user4);

        List<User> users2 = userService.getAllUsers();

        assertEquals("Should be four retrieved users.", 4, users2.size());
    }

    @Test
    public void testExists() {
        userService.create(user1);
        userService.create(user2);
        userService.create(user3);

        assertTrue("User should exist.", userService.exists(user2.getUsername()));
        assertFalse("User should not exist.", userService.exists("xkjhsfjlsjf"));
    }
}
