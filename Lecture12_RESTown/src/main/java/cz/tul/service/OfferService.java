package cz.tul.service;

import cz.tul.data.Offer;
import cz.tul.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<Offer> getCurrent() {
        return StreamSupport.stream(offerRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void create(Offer offer) {
        offerRepository.save(offer);
    }

    public boolean hasOffer(String username) {

        if (username == null) {
            return false;
        }

        return offerRepository.findByUsername(username).size() != 0;

    }

    public Offer getOffer(long id) {

        if (offerRepository.exists(id)) {
            return offerRepository.findOne(id);
        } else {
            return null;
        }
    }

    public void delete(long id) {
        offerRepository.delete(id);
    }
}
