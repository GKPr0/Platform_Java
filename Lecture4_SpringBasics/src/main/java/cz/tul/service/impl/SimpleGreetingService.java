package cz.tul.service.impl;

import cz.tul.service.GreetingService;

import java.util.HashSet;
import java.util.Set;

public class SimpleGreetingService implements GreetingService{

    private final String profileDescription;
    private final Set<String> knownPeople = new HashSet<>();

    public SimpleGreetingService(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    @Override
    public String greet(String name) {
        knownPeople.add(name);
        return String.format("Hello %s !!!\nActive profiles: [%s] ",name, profileDescription);
    }

    @Override
    public int howManyPeopleDoYouKnow() {
        return knownPeople.size();
    }


}
