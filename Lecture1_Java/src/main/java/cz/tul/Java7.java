package cz.tul;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Java7 {


    // Literals

    int a = 0b0010110; // binary
    int b = 1_000_000; //underscore

    // Diamond operator

    public void diamonds() {
        List<Integer> ints = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
    }

    // Multi catch blocks

    public void freakyMethod() throws IOException, SQLException, URISyntaxException {
    }

    @SuppressWarnings("TryWithIdenticalCatches") // to suppress warning - collapse blocks
    public void java6MultiCatch() {
        try {
            freakyMethod();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void java7MultiCatch() {
        try {
            freakyMethod();
        } catch (IOException | SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }



    // Automatic resource management

    public void java6ResourceManagement() {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream("test");
            // do some work
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void java7AutomaticResourceManagement() {
        try (FileInputStream fis = new FileInputStream("test")) {
            System.out.println("I am here!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // String in switch statements
    public void stringSwitch(String a) {
        switch (a) {
            case "A":
                System.out.println("A");
                break;gi
            case "B":
                throw new RuntimeException("B");
            default:
                throw new IllegalArgumentException(a);

        }
    }


    public void nio2() throws IOException {
        Path path = Paths.get("/usr/share");
        path.getRoot();
        path.getParent();
        path.resolve("local"); // /usr/share/local

        Files.lines(path);
        File file = path.toFile();

        // ...
        // Look at methods available in Files.* and path.*
    }

}
