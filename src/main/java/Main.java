import editor.TextEditor;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de JFrame
        JFrame frame = new JFrame("Sistema de Gestión de Publicaciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Crear una instancia de TextEditor y agregarla al JFrame
        TextEditor textEditor = new TextEditor();
        frame.getContentPane().add(textEditor);

        // Hacer visible el JFrame
        frame.setVisible(true);
    }
}