import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    void testValidContact() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main Street");
        assertEquals("12345", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }

    @Test
    void testInvalidContactIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main Street");
        });
    }

    @Test
    void testInvalidPhoneNotTenDigits() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "Doe", "12345", "123 Main Street");
        });
    }

    @Test
    void testInvalidPhoneNonNumeric() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "Doe", "12AB567890", "123 Main Street");
        });
    }

    @Test
    void testUpdateFirstNameValid() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main Street");
        contact.setFirstName("Jane");
        assertEquals("Jane", contact.getFirstName());
    }

    @Test
    void testUpdateFirstNameInvalidTooLong() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main Street");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName("ThisNameIsWayTooLong");
        });
    }

    @Test
    void testUpdateFirstNameInvalidNull() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main Street");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName(null);
        });
    }

    @Test
    void testUpdateLastNameValid() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main Street");
        contact.setLastName("Brown");
        assertEquals("Brown", contact.getLastName());
    }

    @Test
    void testUpdateLastNameInvalidTooLong() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main Street");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName("ThisLastNameIsTooLong");
        });
    }

    @Test
    void testUpdateLastNameInvalidNull() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main Street");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName(null);
        });
    }

    
    @Test
    void testUpdatePhoneValid() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main Street");
        contact.setPhone("1112223333");
        assertEquals("1112223333", contact.getPhone());
    }

    @Test
    void testUpdatePhoneInvalidLength() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main Street");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone("12345"); // too short
        });
    }

    @Test
    void testUpdatePhoneInvalidNonNumeric() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main Street");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone("abcdefghij"); // not digits
        });
    }

    @Test
    void testUpdatePhoneInvalidNull() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main Street");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone(null);
        });
    }


    @Test
    void testUpdateAddressValid() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main Street");
        contact.setAddress("456 Oak Lane");
        assertEquals("456 Oak Lane", contact.getAddress());
    }

    @Test
    void testUpdateInvalidAddressTooLong() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main Street");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress("This address is definitely way too long to be valid in this system");
        });
    }

    @Test
    void testUpdateAddressInvalidNull() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main Street");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress(null);
        });
    }
    
    @Test
    void testContactIdImmutability() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main Street");
        String originalId = contact.getContactId();

        // Try to "change" ID indirectly
        assertEquals("123", originalId);

        // Ensure it's still the same after updates to other fields
        contact.setFirstName("Jane");
        contact.setLastName("Brown");
        contact.setPhone("1112223333");
        contact.setAddress("456 Oak Lane");

        assertEquals(originalId, contact.getContactId());
    }
}