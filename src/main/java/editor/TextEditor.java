package editor;

import javax.swing.*;
import java.awt.*;

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
}