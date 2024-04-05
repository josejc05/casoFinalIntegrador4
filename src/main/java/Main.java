import editor.TextEditor;
import search.ContactManager;
import comparator.ContentComparator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.border.EmptyBorder;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        JLabel mouseXLabel = new JLabel("X: ");
        JLabel mouseYLabel = new JLabel("Y: ");

        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                mouseXLabel.setText("X: " + e.getX());
                mouseYLabel.setText("Y: " + e.getY());
            }
        });

        ContactManager contactManager = new ContactManager();

        Color backgroundColor = new Color(238, 238, 238);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon(new ImageIcon("C:/maxresdefault.jpg").getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT)).getImage(), 0, 0, this);
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(mouseXLabel);
        panel.add(mouseYLabel);

        JButton registrarContactoButton = new JButton("Registrar Contacto");
        registrarContactoButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        registrarContactoButton.setFont(new Font("Arial", Font.BOLD, 14));
        registrarContactoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registrarContactoButton.setBackground(backgroundColor);
        registrarContactoButton.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del contacto");
            String correo = JOptionPane.showInputDialog("Ingrese el correo del contacto");
            contactManager.addContact(nombre, correo);
            JOptionPane.showMessageDialog(null, "Contacto registrado exitosamente");
        });

        JButton verContactosButton = new JButton("Ver Contactos Registrados");
        verContactosButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        verContactosButton.setFont(new Font("Arial", Font.BOLD, 14));
        verContactosButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        verContactosButton.setBackground(backgroundColor);
        verContactosButton.addActionListener(e -> {
            StringBuilder contactos = new StringBuilder();
            for (String nombre : contactManager.getContactos().keySet()) {
                contactos.append("Nombre: ").append(nombre).append(", ")
                        .append("Correo: ").append(contactManager.getContact(nombre)).append("\n");
            }
            JOptionPane.showMessageDialog(null, contactos.toString());
        });

        TextEditor textEditor = new TextEditor();
        JButton abrirEditorTextoButton = new JButton("Abrir Editor de Texto");
        abrirEditorTextoButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        abrirEditorTextoButton.setFont(new Font("Arial", Font.BOLD, 14));
        abrirEditorTextoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        abrirEditorTextoButton.setBackground(backgroundColor);
        abrirEditorTextoButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(textEditor);
            try {
                textEditor.loadFromFile("savedText.txt"); // cargar el texto guardado
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            frame.revalidate();
            frame.repaint();
        });

        JButton buscarArchivoButton = new JButton("Buscar en Archivo");
        buscarArchivoButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        buscarArchivoButton.setFont(new Font("Arial", Font.BOLD, 14));
        buscarArchivoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buscarArchivoButton.setBackground(backgroundColor);
        buscarArchivoButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                String palabraBuscada = JOptionPane.showInputDialog("Ingrese la palabra a buscar");
                try {
                    String texto = new String(Files.readAllBytes(Paths.get(fileChooser.getSelectedFile().getAbsolutePath())));
                    ContentComparator contentComparator = new ContentComparator();
                    int ocurrencias = contentComparator.countWords(texto);
                    JOptionPane.showMessageDialog(null, "La palabra '" + palabraBuscada + "' aparece " + ocurrencias + " veces en el archivo.");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        panel.add(registrarContactoButton);
        panel.add(verContactosButton);
        panel.add(abrirEditorTextoButton);
        panel.add(buscarArchivoButton);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}