import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day4 {
    public static String INPUT = "src/main/files/day4";

    public static void main(String[] args) throws IOException {
        int idSum = 0;

        //part2
        int northPole = 0;

        BufferedReader buff = new BufferedReader(new FileReader(INPUT));
        String room;

        while((room = buff.readLine()) != null){
            String[] encrypted = room.split("-");
            StringBuilder name = new StringBuilder();
            for(int i = 0; i < encrypted.length-1; i++){
                name.append(encrypted[i]);
            }

            String sectorId = encrypted[encrypted.length-1].substring(0,encrypted[encrypted.length-1].indexOf('['));
            String checkSum = encrypted[encrypted.length-1].substring(encrypted[encrypted.length-1].indexOf('[')+1, encrypted[encrypted.length-1].indexOf(']'));

            Map<Character, Integer> charCount = new HashMap<>();
            countChars(name.toString(), charCount);
            String frequent = getFreqChars(charCount);
            if(frequent.equals(checkSum)){
                idSum = idSum + Integer.parseInt(sectorId);

                //part 2
                northPole = northPole == 0 ? checkNorthPole(name.toString(), Integer.parseInt(sectorId)) : northPole;
            }
        }

        System.out.println("Sum of real sector IDs is: " + idSum);
        System.out.println("The goods are in sector: " + northPole);

    }

    public static void countChars(String name, Map<Character, Integer> charCount){
        for(int i = 0; i < name.length(); i++){
            char c = name.charAt(i);
            if(charCount.containsKey(c)){
                int count = charCount.get(c);
                charCount.replace(c, count+1);
            } else {
                charCount.put(c, 1);
            }
        }
    }

    public static String getFreqChars(Map<Character, Integer> map){
        Map<Character, Integer> sorted = sortMap(map);
        StringBuilder freqChar = new StringBuilder();
        for(Map.Entry<Character, Integer> entry : sorted.entrySet()){
            freqChar.append(entry.getKey());
        }
        return freqChar.toString().substring(0,5);
    }

    public static <K extends Comparable<? super K>, V extends Comparable<? super V>> Map<K,V> sortMap(Map<K, V> map){

        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                if(o1.getValue() != o2.getValue()){
                    if(o1.getValue().compareTo(o2.getValue()) > 0 ) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    return o1.getKey().compareTo(o2.getKey());
                }
            }
        });

        Map<K, V> sorted = new LinkedHashMap<>();
        for(Map.Entry<K, V> entry : list){
            sorted.put(entry.getKey(), entry.getValue());
        }
        return sorted;
    }

    //part 2
    public static int checkNorthPole(String name, int sector){
        StringBuilder decrypted = new StringBuilder();
        int rotation = sector % 26;
        int northId = 0;
        for(int i = 0; i < name.length(); i++){
            char encrypted = name.charAt(i);
            char real;
            if(encrypted+rotation > 122){
                real = (char) (encrypted + rotation -26);
            } else {
                real = (char) (encrypted + rotation);
            }
            decrypted.append(real);
        }

        if(decrypted.toString().contains("northpole")){
            return sector;
        }
        return 0;
    }
}
