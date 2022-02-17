package cz.tul.controllers;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/secret")
@PreAuthorize("hasRole('ROLE_OPERATOR')")
public class AdminController {

    private Map<String, String> secretDB = new ConcurrentHashMap<>();

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, String> getSecrets() {
        return secretDB;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ImmutablePair> getSecret(@PathVariable String id) {
        if (secretDB.containsKey(id)) {
            return new ResponseEntity<>(new ImmutablePair<>("value", secretDB.get(id)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> updateSecret(@PathVariable String id, @RequestBody String secret) {
        if (secretDB.containsKey(id)) {
            secretDB.put(id, secret);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeSecret(@PathVariable String id) {
        if (secretDB.containsKey(id)) {
            secretDB.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> putSecret(@PathVariable String id, @RequestBody String secret) {
        if (secretDB.containsKey(id)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            secretDB.put(id, secret);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }


}
