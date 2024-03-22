import java.util.Map;
import comparator.ContentComparator;
import comparator.TextAnalyzer;
import editor.DocumentManager;
import editor.TextEditor;
import gui.MouseTracker;
import gui.ScrollBar;
import gui.WindowSystem;
import search.ContactManager;
import search.WordSearcher;
import validation.EmailValidator;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de TextEditor
        TextEditor textEditor = new TextEditor();
        textEditor.setVisible(true);

        // Crear una instancia de WindowSystem y crear una ventana
        WindowSystem.createWindow("My Window");

        // Crear una instancia de MouseTracker y agregarla a la ventana
        MouseTracker mouseTracker = new MouseTracker();
        textEditor.add(mouseTracker);

        // Crear una instancia de ScrollBar y agregarla a la ventana
        ScrollBar.createScrollBar(textEditor.getTextArea());

        // Crear una instancia de ContactManager y agregar un contacto
        ContactManager contactManager = new ContactManager();
        contactManager.addContact("John Doe", "johndoe@example.com");

        // Validar el correo electrónico del contacto
        boolean isValid = EmailValidator.validate(contactManager.getContact("John Doe"));
        System.out.println("Is the email valid? " + isValid);

        // Crear una instancia de WordSearcher y buscar una palabra en un archivo
        try {
            int occurrences = WordSearcher.countOccurrences("path/to/your/file", "wordToSearch");
            System.out.println("Occurrences of the word: " + occurrences);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crear una instancia de DocumentManager y guardar un documento
        try {
            DocumentManager.saveDocument("path/to/your/file", textEditor.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Analizar el texto en el editor de texto
        Map<String, Integer> frequencyMap = TextAnalyzer.wordFrequency(textEditor.getText());
        System.out.println("Word frequencies: " + frequencyMap);

        // Comparar el contenido de dos textos
        boolean isSameContent = ContentComparator.compareContent("text1", "text2");
        System.out.println("Is the content the same? " + isSameContent);
    }
}