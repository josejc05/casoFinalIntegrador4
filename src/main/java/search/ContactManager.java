package search;

import java.util.HashMap;
import java.util.Map;

public class ContactManager {
    private Map<String, String> contacts = new HashMap<>();

    public void addContact(String name, String email) {
        contacts.put(name, email);
    }

    public Map<String, String> getContacts() {
        return new HashMap<>(contacts);
    }

    public String getContact(String name) {
        return contacts.get(name);
    }

    public void removeContact(String name) {
        contacts.remove(name);
    }

    public String findContact(String name) {
        return contacts.get(name);
    }
}