package search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordSearcher {

    public static int countOccurrences(String filePath, String word) throws IOException {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                int index = 0;
                while ((index = line.indexOf(word, index)) != -1) {
                    count++;
                    index += word.length();
                }
            }
        }
        return count;
    }

    public static int countOccurrencesInText(String text, String word) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(word, index)) != -1) {
            count++;
            index += word.length();
        }
        return count;
    }
}