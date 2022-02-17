package cz.tul;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Enabled private methods in interfaces (Java 9)
 *
 */
public class Java11 {

    /**
     * quick http get request
     * added in Java 9
     */
    public void get() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/get"))
                .GET()
                .build();

        HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String jsonResponse = httpResponse.body();
    }


    public void newApis() {
        // Java 9
        Set<String> strKeySet = Set.of("key1", "key2", "key3");
        Set<Integer> ints = Set.of(1, 2, 3);
        List<String> strings = List.of("first", "second");

        // Java 10
        List<Integer> someIntList = new ArrayList<>();
        List<Integer> copyList = List.copyOf(someIntList); // unmodifiable copy!!

        List<Integer> evenList = someIntList.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toUnmodifiableList()); // unmodifiable

        Integer firstEven = someIntList.stream()
                .filter(i -> i % 2 == 0)
                .findFirst()
                .orElseThrow(); // throws NoSuchElementException if no value is present
    }

    /**
     * Java 9
     */
    public void streamApi() {
        IntStream.iterate(1, i -> i < 100, i -> i + 1).forEach(System.out::println);
        Stream<Integer> s = Optional.of(1).stream();
    }

    /**
     * Java 11
     */
    public void stringApi() {
        String output = "La ".repeat(2) + "Land";

        ("\n\t  hello   \u2005".strip()).equals("hello");
        ("\n\t  hello   \u2005".trim()).equals("hello   \u2005");

         if (("\n\t\u2005  ".isBlank()) == true)
             System.out.println("true");

        String multilineStr = "This is\n \n a multiline\n string.";
        long lineCount = multilineStr.lines() // stream of lines
                .filter(String::isBlank)
                .count();
    }

    public void moreJava11() throws IOException {
        Function<String, String> append = (var string) -> string + " World";
        String appendedString = append.apply("Hello");
        System.out.println(appendedString);

        Path studentFilePath = Path.of("/home/some/file.txt");

        String studentFileContent = Files.readString(Path.of("file.txt"));
    }
}
