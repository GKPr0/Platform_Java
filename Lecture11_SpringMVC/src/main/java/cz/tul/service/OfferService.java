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

    public Offer getOffer(String username) {

        if (username == null) {
            return null;
        }

        List<Offer> offers = offerRepository.findByUsername(username);

        if (offers.size() == 0) {
            return null;
        }

        return offers.get(0);
    }

    public void saveOrUpdate(Offer offer) {
        offerRepository.save(offer);
    }

    public void delete(int id) {
        offerRepository.delete(id);
    }
}
