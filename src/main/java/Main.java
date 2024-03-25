import editor.TextEditor;
import search.ContactManager;
import comparator.ContentComparator;
import validation.EmailValidator;

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

        // Crear una instancia de ContentComparator
        ContentComparator contentComparator = new ContentComparator();

        // Comparar dos archivos y contar palabras
        try {
            boolean areFilesEqual = contentComparator.compareFiles("file1.txt", "file2.txt");
            int wordCount = contentComparator.countWords("file1.txt");
            System.out.println("Los archivos son iguales: " + areFilesEqual);
            System.out.println("Número de palabras en file1.txt: " + wordCount);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Validar un correo electrónico
        boolean isValidEmail = EmailValidator.validate("test@example.com");
        System.out.println("El correo electrónico es válido: " + isValidEmail);
    }
}