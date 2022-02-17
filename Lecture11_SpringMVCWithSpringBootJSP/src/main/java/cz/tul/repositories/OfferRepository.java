package cz.tul.repositories;

import cz.tul.data.Offer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Integer> {

    //Query using JPQL
//    @Query("from Offer where user.username=? and user.enabled=true")
//    public List<Offer> findByUsername(String username);

    //Query using JPQL and named parameter
    @Query("select o from Offer as o where user.username=:username and user.enabled=true")
    public List<Offer> findByUsername(@Param("username") String username);
}
