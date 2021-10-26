package ru.adm123.stringSymbolCounter;

import com.sun.istack.internal.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static ru.adm123.stringSymbolCounter.Main.getStringBySymbolCount;
import static ru.adm123.stringSymbolCounter.Main.stringSymbolCounter;

/**
 * @author Dmitry Ushakov on 26.10.21
 */
public class MainTest {

    private final Map<Character, Integer> map1 = new HashMap<Character, Integer>(){{
        put('a', 3);
        put('b', 2);
        put('c', 1);
    }};
    private final String str1 = "abcbaa";
    private final String readyStr1 = "cba";
    private final Map.Entry<Character, Integer> errorEntry = new AbstractMap.SimpleEntry<>('d', 3);
    private final String errorStr1 = "abcd";

    @Test
    void stringSymbolCounter_returnsTrue_map1Str1() {
        Map<Character, Integer> result = stringSymbolCounter(str1);
        Assertions.assertTrue(isMapEquals(result, map1));
    }

    @Test
    void stringSymbolCounter_returnsFalse_map1WithExtendsStr1() {
        Map<Character, Integer> result = stringSymbolCounter(str1);
        result.put(errorEntry.getKey(), errorEntry.getValue());
        Assertions.assertFalse(isMapEquals(result, map1));
    }

    @Test
    void getStringBySymbolCount_returnsTrue_map1Str1ReadyStr1() {
        String result = getStringBySymbolCount(map1);
        Assertions.assertEquals(readyStr1, result);
    }

    @Test
    void getStringBySymbolCount_returnsFalse_map1Str1ErrorStr1() {
        String result = getStringBySymbolCount(map1);
        Assertions.assertNotEquals(errorStr1, result);
    }

    private boolean isMapEquals(@NotNull Map<Character, Integer> map1,
                                @NotNull Map<Character, Integer> map2) {
        return equalMap(map1, map2) && equalMap(map2, map1);
    }

    private boolean equalMap(@NotNull Map<Character, Integer> map1,
                             @NotNull Map<Character, Integer> map2) {
        return map1.entrySet().stream()
                .allMatch(entry -> Objects.nonNull(map2.get(entry.getKey())) && map2.get(entry.getKey()).equals(entry.getValue()));
    }

}
