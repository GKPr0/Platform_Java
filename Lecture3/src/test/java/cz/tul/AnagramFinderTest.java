package cz.tul;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnagramFinderTest {

    @Test
    void ShouldFindAnagrams()
    {
        ArrayList<String>  words = new ArrayList<>(Arrays.asList("cat", "dog", "tac", "god", "act", "prd"));
        ArrayList<ArrayList<String>> expectedResults = new ArrayList<>();
        expectedResults.add(new ArrayList<>(Arrays.asList("cat", "tac", "act")));
        expectedResults.add(new ArrayList<>(Arrays.asList("dog", "god")));

        ArrayList<ArrayList<String>> anagrams = AnagramFinder.Find(words);

        assertEquals(expectedResults, anagrams);
    }

    @Test
    void ShouldReturnEmptyListIfNoAnagramsFind()
    {
        ArrayList<String>  words = new ArrayList<>(Arrays.asList("cat", "dog"));
        ArrayList<ArrayList<String>> expectedResults = new ArrayList<>();

        ArrayList<ArrayList<String>> anagrams = AnagramFinder.Find(words);

        assertEquals(expectedResults, anagrams);
    }

    @Test
    void ShouldReturnEmptyListIfEmptyListIsGiven()
    {
        ArrayList<String>  words = new ArrayList<>();
        ArrayList<ArrayList<String>> expectedResults = new ArrayList<>();

        ArrayList<ArrayList<String>> anagrams = AnagramFinder.Find(words);

        assertEquals(expectedResults, anagrams);
    }

    @Test
    void ShouldNotFindAnagramsFromDuplicatedWords()
    {
        ArrayList<String>  words = new ArrayList<>(Arrays.asList("cat", "dog", "cat", "dog", "god"));
        ArrayList<ArrayList<String>> expectedResults = new ArrayList<>();
        expectedResults.add(new ArrayList<>(Arrays.asList("dog", "god")));

        ArrayList<ArrayList<String>> anagrams = AnagramFinder.Find(words);

        assertEquals(expectedResults, anagrams);
    }


}
