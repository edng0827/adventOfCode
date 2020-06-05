import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

    @Test
    void countChars() {
        Map<Character, Integer> map = new HashMap<>();
        String input = "abbbcc";
        Day4.countChars(input, map);
        assertEquals(new Integer(1), map.get('a'));
        assertEquals(new Integer(3), map.get('b'));
        assertEquals(new Integer(2), map.get('c'));
    }

    @Test
    void getFreqChars() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('b', 1);
        map.put('a', 1);
        map.put('c', 5);
        map.put('d', 2);
        map.put('e', 3);
        map.put('f', 1);
        assertEquals("cedab", Day4.getFreqChars(map));
    }

    @Test
    void sortMap() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('b', 1);
        map.put('a', 1);
        map.put('c', 5);
        map.put('d', 2);
        map.put('e', 3);
        map.put('f', 1);
        Map<Character,Integer> sortedMap = Day4.sortMap(map);
        StringBuilder inOrder = new StringBuilder();
        for(HashMap.Entry<Character, Integer> entry: sortedMap.entrySet()){
            inOrder.append(entry.getKey());
        }
        assertEquals("cedabf", inOrder.toString());
    }

    @Test
    void checkNorthPole() {
        String northName = "ijmockjgzjwezxonojmvbz";
        int sector = 993;
        assertEquals(993, Day4.checkNorthPole(northName,sector));
        assertEquals(0, Day4.checkNorthPole("sassafrass",548));
    }
}