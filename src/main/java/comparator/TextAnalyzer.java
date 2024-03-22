package comparator;

import java.util.HashMap;
import java.util.Map;

public class TextAnalyzer {

    public static Map<String, Integer> wordFrequency(String text) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        String[] words = text.toLowerCase().split("\\W+");

        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        return frequencyMap;
    }
}