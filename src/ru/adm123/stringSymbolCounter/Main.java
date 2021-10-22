package ru.adm123.stringSymbolCounter;

import com.sun.istack.internal.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Dmitry Ushakov on 22.10.21
 */
public class Main {

    public static void main(String[] args) {
        Map<Character, Integer> uniqueSymbolMap = stringSymbolCounter("rtyurtyrtymmrty");
        System.out.println(uniqueSymbolMap);
        String sortedString = getStringBySymbolCount(uniqueSymbolMap);
        System.out.println(sortedString);
    }

    @NotNull
    public static Map<Character, Integer> stringSymbolCounter(@NotNull String str) {
        Map<Integer, Integer> map = new HashMap<>();
        StreamSupport.stream(str.chars().spliterator(), false)
                .forEach(c -> {
                    Integer value = map.getOrDefault(c, 0);
                    map.put(c, ++value);
                });
        return map.entrySet().stream()
                .collect(Collectors.toMap(e -> (char) e.getKey().intValue(), Map.Entry::getValue));
    }

    @NotNull
    public static String getStringBySymbolCount(@NotNull Map<Character, Integer> map) {
        StringBuilder sb = new StringBuilder();
        map.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .forEach(sb::append);
        return sb.toString();
    }

}
