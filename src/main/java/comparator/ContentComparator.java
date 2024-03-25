package comparator;

import java.util.Map;

public class ContentComparator {

    public static boolean compareContent(String text1, String text2) {
        Map<String, Integer> frequencyMap1 = TextAnalyzer.wordFrequency(text1);
        Map<String, Integer> frequencyMap2 = TextAnalyzer.wordFrequency(text2);

        return frequencyMap1.equals(frequencyMap2);
    }
    public static boolean compareFiles(String filePath1, String filePath2) throws IOException {
        String text1 = new String(Files.readAllBytes(Paths.get(filePath1)));
        String text2 = new String(Files.readAllBytes(Paths.get(filePath2)));
        return compareContent(text1, text2);
    }
}