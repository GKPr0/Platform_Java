package cz.tul;

import cz.tul.data.Offer;
import cz.tul.data.User;
import cz.tul.service.OfferService;
import cz.tul.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {Main.class})
@SpringBootTest(classes = {Main.class})
@ActiveProfiles({"test"})
public class OfferDaoTests {

    @Autowired
    private OfferService offerService;

    @Autowired
    private UserService userService;

    private User user1 = new User("petr", "Petr Malý", "hellothere",
            "petr@gmail.com", true, "ROLE_USER");
    private User user2 = new User("pavel", "Pavel Nový",
            "me", "pavel@gmail.com", true, "ROLE_ADMIN");
    private User user3 = new User("jarda", "Jarda Černý",
            "hi", "sue@caveofprogramming.com", true, "ROLE_USER");
    private User user4 = new User("roger", "Roger Blake", "thisisme",
            "rog@gmail.com", false, "user");

    private Offer offer1 = new Offer(user1, "This is a test offer.");
    private Offer offer2 = new Offer(user1, "This is another test offer.");
    private Offer offer3 = new Offer(user2, "This is yet another test offer.");
    private Offer offer4 = new Offer(user3, "This is a test offer once again.");
    private Offer offer5 = new Offer(user3,
            "Here is an interesting offer of some kind.");
    private Offer offer6 = new Offer(user3, "This is just a test offer.");
    private Offer offer7 = new Offer(user4,
            "This is a test offer for a user that is not enabled.");

    @Before
    public void init() {
        userService.deleteUsers();
    }


    @Test
    public void testDelete() {
        userService.create(user1);
        userService.create(user2);
        userService.create(user3);
        userService.create(user4);
        offerService.saveOrUpdate(offer2);
        offerService.saveOrUpdate(offer3);
        offerService.saveOrUpdate(offer4);
        offerService.saveOrUpdate(offer5);
        offerService.saveOrUpdate(offer6);
        offerService.saveOrUpdate(offer7);

        Optional<Offer> retrieved1 = offerService.getOffer(offer2.getId());
        assertNotNull("Offer with ID " + retrieved1.get() + " should not be null (deleted, actual)", retrieved1);

        offerService.delete(offer2.getId());

        Optional<Offer> retrieved2 = offerService.getOffer(offer2.getId());
        assertFalse("Offer with ID " + retrieved1.get() + " should be null (deleted, actual)", retrieved2.isPresent());
    }

    @Test
    public void testGetById() {
        userService.create(user1);
        userService.create(user2);
        userService.create(user3);
        userService.create(user4);
        offerService.saveOrUpdate(offer1);
        offerService.saveOrUpdate(offer2);
        offerService.saveOrUpdate(offer3);
        offerService.saveOrUpdate(offer4);
        offerService.saveOrUpdate(offer5);
        offerService.saveOrUpdate(offer6);
        offerService.saveOrUpdate(offer7);

        Optional<Offer> retrieved1 = offerService.getOffer(offer1.getId());
        assertEquals("Offers should match", offer1, retrieved1.get());

//        Offer retrieved2 = offerService.getOffer(offer7.getId());
//        assertNull("Should not retrieve offer for disabled user.", retrieved2);
    }

    @Test
    public void testCreateRetrieve() {
        userService.create(user1);
        userService.create(user2);
        userService.create(user3);
        userService.create(user4);

        offerService.saveOrUpdate(offer1);

        List<Offer> offers1 = offerService.getOffers();
        assertEquals("Should be one offer.", 1, offers1.size());

        assertEquals("Retrieved offer should equal inserted offer.", offer1,
                offers1.get(0));

        offerService.saveOrUpdate(offer2);
        offerService.saveOrUpdate(offer3);
        offerService.saveOrUpdate(offer4);
        offerService.saveOrUpdate(offer5);
        offerService.saveOrUpdate(offer6);
//        offerService.saveOrUpdate(offer7);

        List<Offer> offers2 = offerService.getOffers();
        assertEquals("Should be six offers for enabled users.", 6,
                offers2.size());

    }

    @Test
    public void testUpdate() {
        userService.create(user1);
        userService.create(user2);
        userService.create(user3);
        userService.create(user4);
        offerService.saveOrUpdate(offer2);
        offerService.saveOrUpdate(offer3);
        offerService.saveOrUpdate(offer4);
        offerService.saveOrUpdate(offer5);
        offerService.saveOrUpdate(offer6);
        offerService.saveOrUpdate(offer7);

        offer3.setText("This offer has updated text.");
        offerService.saveOrUpdate(offer3);

        Optional<Offer> retrieved = offerService.getOffer(offer3.getId());
        assertEquals("Retrieved offer should be updated.", offer3, retrieved.get());
    }

    @Test
    public void testGetUsername() {
        userService.create(user1);
        userService.create(user2);
        userService.create(user3);
        userService.create(user4);

        offerService.saveOrUpdate(offer1);
        offerService.saveOrUpdate(offer2);
        offerService.saveOrUpdate(offer3);
        offerService.saveOrUpdate(offer4);
        offerService.saveOrUpdate(offer5);
        offerService.saveOrUpdate(offer6);
        offerService.saveOrUpdate(offer7);

        List<Offer> offers1 = offerService.getOffersByName(user3.getUsername());
        assertEquals("Should be three offers for this user.", 3, offers1.size());

//        List<Offer> offers2 = offerService.getOffers("sdfsfd");
//        assertEquals("Should be zero offers for this user.", 0, offers2.size());

        List<Offer> offers3 = offerService.getOffersByName(user2.getUsername());
        assertEquals("Should be 1 offer for this user.", 1, offers3.size());
    }


}
