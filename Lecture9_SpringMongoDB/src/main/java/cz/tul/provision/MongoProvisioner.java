package cz.tul.provision;

import cz.tul.model.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MongoProvisioner implements InitializingBean {

    private final MongoOperations mongo;

    public MongoProvisioner(MongoOperations mongoTemplate) {
        this.mongo = mongoTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        provisionUsersCollectionIfEmpty();
    }

    private boolean provisionUsersCollectionIfEmpty() throws IOException {
        return provisionIfCollectionIsEmpty(User.class, this.getClass().getResourceAsStream("/provision/users.txt"), (line) -> {
            String[] splits = line.split("\\s");
            return new User(splits[0], Integer.parseInt(splits[1]));
        });
    }

    private <T> boolean provisionIfCollectionIsEmpty(Class<T> clazz, InputStream in, Function<String, T> map) throws IOException {
        boolean isEmpty = isCollectionEmpty(clazz);
        if (isEmpty) {
            doProvision(clazz, in, map);
        }
        return isEmpty;
    }

    private <T> boolean isCollectionEmpty(Class<T> clazz) {
        return !mongo.exists(new Query().limit(1), mongo.getCollectionName(clazz));
    }

    private <T> void doProvision(Class<T> clazz, InputStream in, Function<String, T> map) throws IOException {
        try (BufferedReader read = new BufferedReader(new InputStreamReader(in))) {
            List<T> els = read.lines().map(map).collect(Collectors.toList());
            mongo.insert(els, mongo.getCollectionName(clazz));
        }
    }


}
