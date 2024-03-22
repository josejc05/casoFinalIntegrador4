package editor;

import utils.FileUtils;

import java.io.IOException;
import java.util.List;

public class DocumentManager {

    public static void saveDocument(String filePath, String content) throws IOException {
        FileUtils.writeFile(filePath, List.of(content.split("\n")));
    }

    public static String openDocument(String filePath) throws IOException {
        List<String> lines = FileUtils.readFile(filePath);
        return String.join("\n", lines);
    }
}