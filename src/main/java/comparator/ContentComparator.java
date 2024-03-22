package comparator;

import java.util.Map;

public class ContentComparator {

    public static boolean compareContent(String text1, String text2) {
        Map<String, Integer> frequencyMap1 = TextAnalyzer.wordFrequency(text1);
        Map<String, Integer> frequencyMap2 = TextAnalyzer.wordFrequency(text2);

        return frequencyMap1.equals(frequencyMap2);
    }
}