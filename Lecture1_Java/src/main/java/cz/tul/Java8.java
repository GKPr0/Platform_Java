package cz.tul;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class Java8 {

    public void filter() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        long count = list.stream().filter(x -> x > 5).collect(Collectors.counting());
    }

    public void map() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        list.stream().map(x -> x * x).collect(Collectors.toList());
    }

    public void reduce() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        int sum = list.stream().reduce(0, (a, b) -> a + b);
    }

    public void infiniteStream(){
        IntStream.iterate(0, i -> i + 1);
    }

    public void optional(){
        Optional<Object> o = Optional.ofNullable(null);
        if(o.isPresent()){
            System.out.print("Java is buggy");
        }
    }
    public void methodReferences() throws IOException {
         Files.lines(Paths.get("Nio.java")).map(Paths::get).map(x -> {
             try {
                 return Files.readAllLines(x);
             } catch (IOException e) {
                 e.printStackTrace();
                 return Collections.emptyList();
             }
         }).forEach(System.out::println);

    }

    public void datetime(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime after2Seconds = now.plusSeconds(2);
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysFromNow = today.plusDays(30);
        LocalDate nextMonth = today.plusMonths(1);
        LocalTime time = LocalTime.of(12, 15, 0);
        Period p = Period.between(today, nextMonth);
        Duration d = Duration.between(now, after2Seconds);
        LocalDate nextTuesday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
    }

   
}
