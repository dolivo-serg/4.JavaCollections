package com.javarush.task.task35.task3509;

import java.util.*;
import java.util.stream.Collectors;

/* 
Collections & Generics
*/

public class Solution {

    public static void main(String[] args) {
        System.out.println(newHashMap(newArrayList(1,2,3,4), newArrayList("a","b","c",'d')));
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> newArrayList(T... elements) {
        ArrayList<T> list = new ArrayList<>(elements.length);
        Collections.addAll(list, elements);
        return list;
    }

    @SuppressWarnings("unchecked")
    public static <T> HashSet<T> newHashSet(T... elements) {
        return (HashSet<T>) Arrays.stream(elements)
                .collect(Collectors.toSet());
    }


    public static <K, V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        if (keys.size() != values.size()) {
            throw new IllegalArgumentException();
        }
        HashMap<K, V> hashMap = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            hashMap.put(keys.get(i), values.get(i));
        }
        return hashMap;
    }
}
