import editor.TextEditor;
import search.ContactManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de TextEditor
        TextEditor textEditor = new TextEditor();
        textEditor.setText("Texto de prueba");
        textEditor.setVisible(true);

        // Guardar el contenido del TextEditor en un archivo
        try {
            textEditor.saveToFile("test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Cargar el contenido del archivo en el TextEditor
        try {
            textEditor.loadFromFile("test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprimir el contenido del TextEditor
        System.out.println(textEditor.getText());

        // Crear una instancia de ContactManager
        ContactManager contactManager = new ContactManager();
        contactManager.addContact("John Doe", "johndoe@example.com");

        // Recuperar y imprimir un contacto
        String contactEmail = contactManager.getContact("John Doe");
        System.out.println("Email de John Doe: " + contactEmail);
    }
}