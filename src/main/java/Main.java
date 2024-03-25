import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextEditor extends JPanel {
    private JTextArea textArea;

    public TextEditor() {
        textArea = new JTextArea();
        add(textArea);
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public String getText() {
        return textArea.getText();
    }

    public void saveToFile(String filePath) throws IOException {
        String text = getText();
        Files.write(Paths.get(filePath), text.getBytes());
    }

    public void loadFromFile(String filePath) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(filePath)));
        setText(text);
    }
}