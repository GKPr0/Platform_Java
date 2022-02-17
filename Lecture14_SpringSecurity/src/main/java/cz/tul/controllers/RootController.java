package cz.tul.controllers;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class RootController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ImmutablePair<String,List<String>> printRoles(@AuthenticationPrincipal Authentication u ) {
        return new ImmutablePair<>("roles" , u == null ? Collections.emptyList() : rolesToList(u.getAuthorities()));
    }

    private List<String> rolesToList(Collection<? extends GrantedAuthority> authorities) {
        return StreamSupport.stream(authorities.spliterator(), false).map(Object::toString).collect(Collectors.toList());
    }
}
