package cz.tul.client;

import cz.tul.data.Offer;
import cz.tul.data.User;
import retrofit.http.*;

import java.util.List;

public interface ServerApi {

    public static final String OFFERS_PATH = "/offers";
    public static final String OFFER_PATH = OFFERS_PATH + "/{id}";
    public static final String USERS_PATH = "/users";
    public static final String USER_PATH = USERS_PATH + "/{username}";

    @GET(OFFERS_PATH)
    public List<Offer> showOffers();

    @POST(OFFERS_PATH)
    public void addOffer(@Body Offer offer);

    @GET(OFFER_PATH)
    public Offer getOffer(@Path("id") long id);

    @DELETE(OFFER_PATH)
    public void deleteOffer(@Path("id") long id);

    @GET(USERS_PATH)
    public List<User> showUsers();

    @POST(USERS_PATH)
    public void addUser(@Body User user);

    @GET(USER_PATH)
    public User getUser(@Path("username") String username);

    @DELETE(USER_PATH)
    public void deleteUser(@Path("username") String username);
}
