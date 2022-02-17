package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.controllers.exceptions.APIErrorMessage;
import cz.tul.controllers.exceptions.APIException;
import cz.tul.data.User;
import cz.tul.service.OfferService;
import cz.tul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UsersController {

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

    @RequestMapping(value = ServerApi.USERS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<User>> showUsers() {
        List<User> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.USERS_PATH, method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (usersService.exists(user.getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            usersService.create(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @RequestMapping(value = ServerApi.USER_PATH, method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        if (usersService.exists(username)) {
            User user = usersService.getUser(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = ServerApi.USER_PATH, method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("username") String username) {
        if (usersService.exists(username)) {
            if (offersService.hasOffer(username)) {
                throw new APIException("Cannot delete user who has offers");
            } else {
                usersService.delete(username);
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIErrorMessage> handleAPIException(APIException ex) {
        return new ResponseEntity<>(new APIErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
