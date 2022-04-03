package cz.tul;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Word implements Comparable<Word> {

    public String word;
    public int index; //Index in the original list

    Word(String word, int index) {
        this.word = word;
        this.index = index;
    }

    public static ArrayList<Word> GetWordsFrom(ArrayList<String> context) {
        ArrayList<Word> words = new ArrayList<>();

        for(int i=0; i < context.size(); i++)
        {
            Word word = new Word(context.get(i), i);
            words.add(word);
        }

        return words;
    }

    public void SortByCharacters() {
        char[] char_word = word.toCharArray();
        Arrays.sort(char_word);
        this.word = new String(char_word);
    }

    @Override
    public int compareTo(Word other) {
        return this.word.compareTo(other.word);
    }

    @Override
    public boolean equals(Object  other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Word externalWord)) return false;

        return Objects.equals(this.word, externalWord.word);
    }
}
