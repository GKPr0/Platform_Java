package cz.tul.services;

import cz.tul.model.User;
import org.bson.types.ObjectId;

import java.util.Map;

public interface UserService {

    Map<Integer,Integer> ageHistogramUsingMapReduce();

    User find(ObjectId objectId);
    User add(User user);
    void remove(User user);

}
