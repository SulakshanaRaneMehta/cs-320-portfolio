import java.util.ArrayList;

public class ContactService {
    // Store all contacts in memory using a dynamic list
    private ArrayList<Contact> contacts = new ArrayList<>();

    // Add a new contact if the ID is unique
    public boolean addContact(Contact contact) {
        for (Contact c : contacts) {
            if (c.getContactId().equals(contact.getContactId())) {
                return false; // Duplicate ID not allowed
            }
        }
        contacts.add(contact);
        return true;
    }

    // Delete a contact by ID
    public boolean deleteContact(String contactId) {
        for (Contact c : contacts) {
            if (c.getContactId().equals(contactId)) {
                contacts.remove(c); // Remove the first match
                return true;        // Deletion successful
            }
        }
        return false; // Contact not found
    }

    // Update first name of a contact by ID
    public boolean updateFirstName(String contactId, String newFirstName) {
        for (Contact c : contacts) {
            if (c.getContactId().equals(contactId)) {
                c.setFirstName(newFirstName); // Validation handled in Contact class
                return true;
            }
        }
        return false; // Contact not found
    }

    // Update last name of a contact by ID
    public boolean updateLastName(String contactId, String newLastName) {
        for (Contact c : contacts) {
            if (c.getContactId().equals(contactId)) {
                c.setLastName(newLastName);
                return true;
            }
        }
        return false;
    }

    // Update phone number of a contact by ID
    public boolean updatePhone(String contactId, String newPhone) {
        for (Contact c : contacts) {
            if (c.getContactId().equals(contactId)) {
                c.setPhone(newPhone);
                return true;
            }
        }
        return false;
    }

    // Update address of a contact by ID
    public boolean updateAddress(String contactId, String newAddress) {
        for (Contact c : contacts) {
            if (c.getContactId().equals(contactId)) {
                c.setAddress(newAddress);
                return true;
            }
        }
        return false;
    }

    // Return the number of contacts stored
    public int getSize() {
        return contacts.size();
    }
}