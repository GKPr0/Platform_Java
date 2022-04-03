package cz.tul;

import java.util.ArrayList;
import java.util.Arrays;

public class MainLecture3 {
    public static void main(String[] args) {
        ArrayList<String>  words = new ArrayList<>(Arrays.asList("cat", "dog", "tac", "god", "act"));;

        ArrayList<ArrayList<String>> anagrams = AnagramFinder.Find(words);

    }
}
