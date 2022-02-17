package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.Offer;
import cz.tul.service.OfferService;
import cz.tul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OfferController {

    private OfferService offersService;

    private UserService usersService;

    @Autowired
    public void setOffersService(OfferService offersService) {
        this.offersService = offersService;
    }

    @Autowired
    public void setUsersService(UserService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = ServerApi.OFFERS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Offer>> showOffers() {
        List<Offer> offers = offersService.getCurrent();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.OFFERS_PATH, method = RequestMethod.POST)
    public ResponseEntity<Offer> addOffer(@RequestBody Offer offer) {
        if (usersService.exists(offer.getUser().getUsername())) {
            offersService.create(offer);
            return new ResponseEntity<>(offer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = ServerApi.OFFER_PATH, method = RequestMethod.GET)
    public ResponseEntity<Offer> getOffer(@PathVariable("id") long id) {
        Offer offer = offersService.getOffer(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<>(offer, HttpStatus.OK);

    }

    @RequestMapping(value = ServerApi.OFFER_PATH, method = RequestMethod.DELETE)
    public ResponseEntity deleteOffer(@PathVariable("id") long id) {
        Offer offer = offersService.getOffer(id);
        if (offer == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            offersService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}