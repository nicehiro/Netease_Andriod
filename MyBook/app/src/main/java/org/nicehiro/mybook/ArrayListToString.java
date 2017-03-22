package org.nicehiro.mybook;


import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hiro on 17-3-19.
 */

public class ArrayListToString {

    public static String listToString(ArrayList<String> list) {
        String temp = " ";
        for (int i=0; i<list.size(); i++) {
            temp += list.get(i) + " ";
        }
        return temp;
    }

    public static ArrayList<String> stringToList(String string) {
        String[] strings = string.split(" ");
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, strings);
        return list;
    }

    public static String tagsToString(ArrayList<Book.Tag> tags) {
        String temp = "";
        Book.Tag tag;
        for (int i=0; i<tags.size(); i++) {
            tag = tags.get(i);
            temp += tag.getTitle() + " ";
        }
        return temp;
    }
}
