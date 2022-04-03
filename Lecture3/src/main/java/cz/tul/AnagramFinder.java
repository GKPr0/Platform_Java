package cz.tul;

import java.util.*;

public class AnagramFinder {

    public static ArrayList<ArrayList<String>> Find(ArrayList<String> context)
    {
        if(context.size() == 0)
            return new ArrayList<>();

        context = Utils.RemoveDuplicates(context);

        ArrayList<Word> words = Word.GetWordsFrom(context);

        for (Word word: words)
            word.SortByCharacters();

        Collections.sort(words);

        return GetGroupedAnagrams(words, context);
    }

    private static ArrayList<ArrayList<String>> GetGroupedAnagrams(ArrayList<Word> sortedWords, ArrayList<String> originalData)
    {
        ArrayList<ArrayList<String>> groupedAnagrams = new ArrayList<>();

        ArrayList<String> anagramsGroup = new ArrayList<>();
        Word lastWord = sortedWords.get(0);
        anagramsGroup.add(originalData.get(lastWord.index));

        for (int i = 1; i < sortedWords.size(); i++) {
            Word actualWord = sortedWords.get(i);
            if(!lastWord.equals(actualWord))
            {
                if(anagramsGroup.size() > 1)
                    groupedAnagrams.add(anagramsGroup);
                anagramsGroup = new ArrayList<>();
            }
            anagramsGroup.add(originalData.get(actualWord.index));
            lastWord = actualWord;
        }

        if(anagramsGroup.size() > 1)
            groupedAnagrams.add(anagramsGroup);

        return groupedAnagrams;
    }

}
