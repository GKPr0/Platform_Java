package cz.tul;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class MainJavaFeatures {
    public static void main(String[] args) {
        java7Test();
        //java8Test();
        //java11Test();
    }

    private static void java7Test(){
        var java7 = new Java7();

        java7.stringSwitch("A");
        java7.java6ResourceManagement();
        java7.java7AutomaticResourceManagement();
        try {
            java7.nio2();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void java8Test(){
        var java8 = new Java8();

        System.out.println(java8.filter());
        java8.map().forEach(System.out::println);
        System.out.println(java8.reduce());
        java8.datetime();
    }

    private static void java11Test(){
        var java11 = new Java11();

        try {
            System.out.println(java11.get());
        }catch (URISyntaxException | IOException | InterruptedException e){
            e.printStackTrace();
        }

        java11.newApis();
        java11.streamApi();
        java11.stringApi();

        List<Integer> someIntList = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8); //Immutable

        List<Integer> evenList = someIntList.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList()); //Mutable

        evenList.add(5);


        Function<String, String> append = (var string) -> string + " Lambda";
        System.out.println(append.apply("Test"));
    }
}
