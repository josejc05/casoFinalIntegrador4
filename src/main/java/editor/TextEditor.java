package editor;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextEditor extends JPanel {
    private JTextArea textArea;
    private String filePath = "savedText.txt"; // archivo donde se guardará el texto

    public TextEditor() {
        textArea = new JTextArea();
        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            try {
                saveToFile(filePath);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        add(textArea);
        add(saveButton);
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