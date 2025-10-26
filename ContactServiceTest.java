import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    private ContactService service;
    private Contact contact;

    @BeforeEach
    void setUp() {
        service = new ContactService();
        contact = new Contact("001", "Alice", "Smith", "1234567890", "10 Park Ave");
        service.addContact(contact);
    }

    @Test
    void testAddContactSuccess() {
        Contact newContact = new Contact("002", "Bob", "Brown", "0987654321", "20 Main St");
        assertTrue(service.addContact(newContact));
        assertEquals(2, service.getSize());
    }

    @Test
    void testAddDuplicateContactFails() {
        assertFalse(service.addContact(contact));
        assertEquals(1, service.getSize());
    }

    @Test
    void testDeleteContactSuccess() {
        assertTrue(service.deleteContact("001"));
        assertEquals(0, service.getSize());
    }

    @Test
    void testDeleteContactNotFound() {
        assertFalse(service.deleteContact("999"));
        assertEquals(1, service.getSize());
    }

    @Test
    void testUpdateFirstNameValid() {
        assertTrue(service.updateFirstName("001", "Ann"));
        assertEquals("Ann", contact.getFirstName());
    }

    @Test
    void testUpdateFirstNameInvalidTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateFirstName("001", "ThisNameIsTooLong");
        });
    }
    
    @Test
    void testUpdateLastNameValid() {
        assertTrue(service.updateLastName("001", "Jones"));
        assertEquals("Jones", contact.getLastName());
    }

    @Test
    void testUpdateLastNameInvalidNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateLastName("001", null);
        });
    }

    @Test
    void testUpdatePhoneValid() {
        assertTrue(service.updatePhone("001", "1112223333"));
        assertEquals("1112223333", contact.getPhone());
    }

    @Test
    void testUpdatePhoneInvalidNotDigits() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updatePhone("001", "abc1234567");
        });
    }

    @Test
    void testUpdatePhoneInvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updatePhone("001", "12345");
        });
    }
    
    @Test
    void testUpdateAddressValid() {
        assertTrue(service.updateAddress("001", "55 Elm Street"));
        assertEquals("55 Elm Street", contact.getAddress());
    }

    @Test
    void testUpdateAddressInvalidTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateAddress("001", "This address string is way too long to be accepted in the system");
        });
    }
    
    @Test
    void testContactIdUnchangedAfterUpdates() {
        String idBefore = contact.getContactId();

        service.updateFirstName("001", "Ann");
        service.updateLastName("001", "Jones");
        service.updatePhone("001", "1112223333");
        service.updateAddress("001", "55 Elm Street");

        assertEquals(idBefore, contact.getContactId()); // ID is still the same
    }
}