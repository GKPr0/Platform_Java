package cz.tul.lecture;

import cz.tul.service.GreetingService;

import java.util.HashSet;
import java.util.Set;

public class Memory {

    private static final String myString = "Hello";
    private int count = 5;

    public String test(String name, int age) {
        String myName = "PPJ";
        return name + " " + age;
    }

    public static void main(String[] args) {

        Memory m = new Memory();
        String result = m.test("Ja",52);
    }
}
