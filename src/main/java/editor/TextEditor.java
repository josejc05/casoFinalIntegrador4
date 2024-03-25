package editor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextEditor extends JFrame {
    private JTextArea textArea;

    public TextEditor() {
        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public String getText() {
        return textArea.getText();
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void saveToFile(String filename) throws IOException {
        Files.write(Paths.get(filename), textArea.getText().getBytes());
    }

    public void loadFromFile(String filename) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        textArea.setText(content);
    }
}