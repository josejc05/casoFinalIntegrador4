package editor;

import javax.swing.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextEditor extends JPanel {
    private JTextArea textArea;
    private String filePath = "savedText.txt"; // archivo donde se guardará el texto
    private JFrame frame;
    private JPanel panel;
    private JScrollBar verticalScrollBar;

    public TextEditor(JFrame frame, JPanel panel) {
        this.frame = frame;
        this.panel = panel;
        textArea = new JTextArea(20, 50);
        JScrollPane scrollPane = new JScrollPane(textArea);
        verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int max = verticalScrollBar.getMaximum();
                int extent = verticalScrollBar.getModel().getExtent();
                float percentage = (float) verticalScrollBar.getValue() / (max - extent);
                System.out.println("Scroll percentage: " + percentage * 100 + "%");
            }
        });
        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            try {
                saveToFile(filePath);
                JOptionPane.showMessageDialog(null, "El texto se ha guardado correctamente");
                frame.getContentPane().removeAll();
                frame.getContentPane().add(panel);
                frame.revalidate();
                frame.repaint();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        add(scrollPane);
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