import editor.TextEditor;
import search.ContactManager;
import comparator.ContentComparator;
import validation.EmailValidator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

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
            public void mouseMoved(MouseEvent e) {
                mouseXLabel.setText("X: " + e.getX());
                mouseYLabel.setText("Y: " + e.getY());
            }
        });

        ContactManager contactManager = new ContactManager();

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
        registrarContactoButton.setFont(new Font("Arial", Font.BOLD, 14));
        registrarContactoButton.setBackground(Color.ORANGE);
        registrarContactoButton.setForeground(Color.BLACK);
        registrarContactoButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        registrarContactoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registrarContactoButton.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del contacto");
            String correo = JOptionPane.showInputDialog("Ingrese el correo del contacto");
            while (!EmailValidator.validate(correo)) {
                JOptionPane.showMessageDialog(null, "Correo no válido. Inténtelo de nuevo.");
                correo = JOptionPane.showInputDialog("Ingrese el correo del contacto");
            }
            contactManager.addContact(nombre, correo);
            JOptionPane.showMessageDialog(null, "Contacto registrado exitosamente");
        });

        JButton verContactosButton = new JButton("Ver Contactos Registrados");
        verContactosButton.setFont(new Font("Arial", Font.BOLD, 14));
        verContactosButton.setBackground(Color.GREEN);
        verContactosButton.setForeground(Color.BLACK);
        verContactosButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        verContactosButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        verContactosButton.addActionListener(e -> {
            StringBuilder contactos = new StringBuilder();
            for (String nombre : contactManager.getContactos().keySet()) {
                contactos.append("Nombre: ").append(nombre).append(", ")
                        .append("Correo: ").append(contactManager.getContact(nombre)).append("\n");
            }
            JOptionPane.showMessageDialog(null, contactos.toString());
        });

        TextEditor textEditor = new TextEditor(frame, panel);
        JButton abrirEditorTextoButton = new JButton("Abrir Editor de Texto");
        abrirEditorTextoButton.setFont(new Font("Arial", Font.BOLD, 14));
        abrirEditorTextoButton.setBackground(Color.BLUE);
        abrirEditorTextoButton.setForeground(Color.WHITE);
        abrirEditorTextoButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        abrirEditorTextoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        buscarArchivoButton.setFont(new Font("Arial", Font.BOLD, 14));
        buscarArchivoButton.setBackground(Color.RED);
        buscarArchivoButton.setForeground(Color.WHITE);
        buscarArchivoButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        buscarArchivoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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

        JButton dibujarButton = new JButton("Dibujar");
        dibujarButton.setFont(new Font("Arial", Font.BOLD, 14));
        dibujarButton.setBackground(Color.MAGENTA);
        dibujarButton.setForeground(Color.WHITE);
        dibujarButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        dibujarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        dibujarButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new DrawingPanel(frame, panel));
            frame.revalidate();
            frame.repaint();
        });

        panel.add(registrarContactoButton);
        panel.add(verContactosButton);
        panel.add(abrirEditorTextoButton);
        panel.add(buscarArchivoButton);
        panel.add(dibujarButton);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    static class DrawingPanel extends JPanel {
        private List<List<Point>> lines = new ArrayList<>();
        private List<Point> currentLine;

        public DrawingPanel(JFrame frame, JPanel panel) {
            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    currentLine.add(e.getPoint());
                    repaint();
                }
            });

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    currentLine = new ArrayList<>();
                    currentLine.add(e.getPoint());
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    lines.add(currentLine);
                }
            });

            JButton saveButton = new JButton("Guardar Dibujo");
            saveButton.addActionListener(e -> {
                BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = image.createGraphics();
                paintAll(g2d);
                g2d.dispose();
                try {
                    ImageIO.write(image, "png", new File("dibujo.png"));
                    JOptionPane.showMessageDialog(null, "Dibujo guardado correctamente");
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(panel);
                    frame.revalidate();
                    frame.repaint();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

            JButton clearButton = new JButton("Limpiar Dibujo");
            clearButton.addActionListener(e -> {
                lines.clear();
                repaint();
            });

            add(saveButton);
            add(clearButton);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (List<Point> line : lines) {
                for (int i = 1; i < line.size(); i++) {
                    int x1 = line.get(i - 1).x;
                    int y1 = line.get(i - 1).y;
                    int x2 = line.get(i).x;
                    int y2 = line.get(i).y;
                    g.drawLine(x1, y1, x2, y2);
                }
            }
            if (currentLine != null) {
                for (int i = 1; i < currentLine.size(); i++) {
                    int x1 = currentLine.get(i - 1).x;
                    int y1 = currentLine.get(i - 1).y;
                    int x2 = currentLine.get(i).x;
                    int y2 = currentLine.get(i).y;
                    g.drawLine(x1, y1, x2, y2);
                }
            }
        }
    }
}