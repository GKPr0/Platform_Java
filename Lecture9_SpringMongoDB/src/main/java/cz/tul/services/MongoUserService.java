package cz.tul.services;

import cz.tul.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Map;
import java.util.TreeMap;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class MongoUserService implements UserService {

    private final MongoOperations mongo;

    public MongoUserService(MongoOperations mongo) {
        this.mongo = mongo;
    }

    @Override
    public User find(ObjectId objectId) {
        return mongo.findOne(Query.query(where("_id").is(objectId)), User.class);
    }

    @Override
    public User add(User user) {
        mongo.insert(user);
        return user;
    }

    @Override
    public void remove(User user) {
        mongo.remove(user);
    }

    @Override
    public Map<Integer, Integer> ageHistogramUsingMapReduce() {
        MapReduceResults<AgeHistogramEntry> mapReduceResult =
                mongo.mapReduce(new Query(), mongo.getCollectionName(User.class), "classpath:map/user_age.js", "classpath:reduce/user_age.js", AgeHistogramEntry.class);
        Map<Integer, Integer> histogram = new TreeMap<>(); // using tree map as it iterates over keys in natural order

        for (AgeHistogramEntry result : mapReduceResult) {
            histogram.put(result.id, result.value);
        }

        return histogram;
    }

    private static class AgeHistogramEntry {
        public int id; // this is our key (age)
        public int value; // count
    }
}
