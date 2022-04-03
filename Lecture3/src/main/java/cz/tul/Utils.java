package cz.tul;

import java.util.ArrayList;

public class Utils {

    public static <T> ArrayList<T> RemoveDuplicates(ArrayList<T> list)
    {
        ArrayList<T> newList = new ArrayList<T>();

        for (T element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }

        return newList;
    }
}

