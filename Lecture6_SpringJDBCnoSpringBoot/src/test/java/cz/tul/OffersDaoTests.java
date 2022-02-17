package cz.tul;

import cz.tul.configurations.MainSpringConfiguration;
import cz.tul.data.Offer;
import cz.tul.data.OffersDao;
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
public class OffersDaoTests {

    @Autowired
    private OffersDao offersDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private DataSource dataSource;


    @Test
    public void Test1_createOffer() {
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);

        jdbc.getJdbcOperations().execute("DELETE FROM OFFERS");
        jdbc.getJdbcOperations().execute("DELETE FROM USERS");

        User user = new User("developer", "Petr", "hellothere",
                "petr@seznam.cz", true, "user");

        assertTrue("User creation should return true", usersDao.create(user));

        Offer offer = new Offer(user, "This is a test offer.");

        assertTrue("Offer creation should return true", offersDao.create(offer));

    }

    @Test
    public void Test2_listOffers() {

        List<Offer> offers = offersDao.getOffers();
        // Get the offer with ID filled in.
        Offer offer = offers.get(0);

        assertEquals("Should be one offer in database.", 1, offers.size());

        assertEquals("Retrieved offer should match created offer.", offer,
                offers.get(0));
    }

    @Test
    public void Test3_updateOffer() {

        List<Offer> offers = offersDao.getOffers();

        // Get the offer with ID filled in.
        Offer offer = offers.get(0);

        offer.setText("Updated offer text.");
        assertTrue("Offer update should return true", offersDao.update(offer));

        Offer updated = offersDao.getOffer(offer.getId());

        assertEquals("Updated offer should match retrieved updated offer", offer, updated);
    }

    @Test
    public void Test4_getOfferById() {

        List<User> users = usersDao.getAllUsers();

        // Test get by ID ///////
        Offer offer2 = new Offer(users.get(0), "This is a test offer.");

        assertTrue("Offer creation should return true", offersDao.create(offer2));

        List<Offer> userOffers = offersDao.getOffers();
        assertEquals("Should be two offers for user.", 2, userOffers.size());

        List<Offer> secondList = offersDao.getOffers();
        System.out.println(secondList);

        for (Offer current : secondList) {
            Offer retrieved = offersDao.getOffer(current.getId());

            assertEquals("Offer by ID should match offer from list.", current, retrieved);
        }
    }

    @Test
    public void Test5_deleteOffer() {

        List<Offer> offers = offersDao.getOffers();

        // Get the offer with ID filled in.
        Offer offer = offers.get(0);

        // Test deletion
        offersDao.delete(offer.getId());

        List<Offer> finalList = offersDao.getOffers();

        assertEquals("Offers lists should contain one offer.", 1, finalList.size());
    }

}
