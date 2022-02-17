package cz.tul.repositories;

import cz.tul.data.Offer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "offers", path = "offers")
public interface OfferRepository extends CrudRepository<Offer, Integer> {

    //Query using JPQL and named parameter
    @Query("select o from Offer as o where user.username=:username and user.enabled=true")
    public List<Offer> findByUsername(@Param("username") String username);

    //@Query("select o from Offer as o where user.username=:username and user.enabled=true")
    public List<Offer> findByText(@Param("text") String text);
}
