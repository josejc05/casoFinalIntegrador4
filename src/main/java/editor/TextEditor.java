package editor;

import javax.swing.*;

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

    // Implementar los métodos para guardar y cargar desde un archivo...
}