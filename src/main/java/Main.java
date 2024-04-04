import editor.TextEditor;
import search.ContactManager;
import comparator.ContentComparator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de JFrame
        JFrame frame = new JFrame("Sistema de Gestión de Publicaciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Crear una instancia de TextEditor y agregarla al JFrame
        TextEditor textEditor = new TextEditor();
        frame.getContentPane().add(textEditor);

        // Crear una instancia de ContactManager
        ContactManager contactManager = new ContactManager();

        // Crear una instancia de ContentComparator
        ContentComparator contentComparator = new ContentComparator();

        // Crear un JMenuBar
        JMenuBar menuBar = new JMenuBar();

        // Crear un JMenuItem para cada opción
        JMenuItem addContactItem = new JMenuItem("Agregar contacto");
        JMenuItem viewContactItem = new JMenuItem("Ver contacto");
        JMenuItem editTextItem = new JMenuItem("Editor de texto");
        JMenuItem searchWordItem = new JMenuItem("Buscador de palabras");

        // Agregar un ActionListener a cada JMenuItem
        addContactItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí debes implementar la lógica para agregar un contacto
            }
        });

        viewContactItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí debes implementar la lógica para ver un contacto
            }
        });

        editTextItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí debes implementar la lógica para editar texto
            }
        });

        searchWordItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí debes implementar la lógica para buscar palabras
            }
        });

        // Agregar los JMenuItem al JMenuBar
        menuBar.add(addContactItem);
        menuBar.add(viewContactItem);
        menuBar.add(editTextItem);
        menuBar.add(searchWordItem);

        // Agregar el JMenuBar al JFrame
        frame.setJMenuBar(menuBar);

        // Hacer visible el JFrame
        frame.setVisible(true);
    }
}