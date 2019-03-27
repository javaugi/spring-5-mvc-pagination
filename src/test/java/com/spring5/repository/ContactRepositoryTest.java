package com.spring5.repository;

import com.spring5.model.Contact;
import com.spring5.model.ContactNote;

import java.util.List;
import javax.persistence.EntityNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring5.type.PhoneType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void it_can_find_the_contact_after_save_it() {
        Contact contact = new Contact("Mary", "Zheng", "test@email.com", PhoneType.HOME, "6365272943");
        ContactNote note = new ContactNote();
        note.setMessage("Email Test");
        contact.addNote(note);

        contactRepository.save(contact);

        List<Contact> contacts = contactRepository.findAll();

        assertEquals(1, contacts.size());
        assertEquals("Mary", contacts.get(0).getFirstName());
        assertEquals("Zheng", contacts.get(0).getLastName());
        assertEquals("test@email.com", contacts.get(0).getEmail());
        assertEquals(PhoneType.HOME, contacts.get(0).getPhoneType());
        assertEquals("6365272943", contacts.get(0).getPhoneNumber());
        assertEquals(1, contacts.get(0).getNotes().size());
        assertEquals("Email Test", contacts.get(0).getNotes().get(0).getMessage());
    }

    @Test
    public void it_can_delete_the_contact_after_save_it() {
        Contact contact = new Contact("Mary", "Zheng", "test@email.com", PhoneType.HOME, "6365272943");
        ContactNote note = new ContactNote();
        note.setMessage("Email Test");
        contact.addNote(note);

        contactRepository.save(contact);

        List<Contact> foundContacts = contactRepository.findAll();

        contactRepository.delete(foundContacts.get(0));

        List<Contact> contacts = contactRepository.findAll();
        assertEquals(0, contacts.size());

    }

    @Test
    public void it_can_update_the_contact_after_save_it() {
        Contact contact = new Contact("Mary", "Zheng", "test@email.com", PhoneType.HOME, "6365272943");

        contactRepository.save(contact);

        contact.setEmail("mary.zheng@email.com");
        contactRepository.save(contact);

        List<Contact> contacts = contactRepository.findAll();
        assertEquals(1, contacts.size());
        assertEquals("mary.zheng@email.com", contacts.get(0).getEmail());
    }

    @Test
    public void it_can_find_contacts_by_name_and_type() {

        contactRepository.save(new Contact("Mary", "Zheng", "mary.zheng@jcg.org", PhoneType.HOME, "6368168164"));
        contactRepository.save(new Contact("Tom", "Smith", "tom.smith@jcg.org", PhoneType.MOBILE, "(636) 527-2943"));
        contactRepository.save(new Contact("John", "Joe", "john.joe@jcg.org", PhoneType.OFFICE, "(314) 527 2943"));
        contactRepository.save(new Contact("Cindy", "Chang", "cindy.change@jcg.org", PhoneType.OTHER, "404-789-1456"));

        List<Contact> contactsWithZheng = contactRepository.findByLastNameAndPhoneType(PhoneType.HOME, "Zheng");

        assertEquals(1, contactsWithZheng.size());
        Contact foundContact = contactsWithZheng.get(0);
        assertEquals("Mary", foundContact.getFirstName());
        assertEquals("Zheng", foundContact.getLastName());
        assertEquals("mary.zheng@jcg.org", foundContact.getEmail());
        assertEquals(PhoneType.HOME, foundContact.getPhoneType());
        assertEquals("6368168164", foundContact.getPhoneNumber());
        assertEquals(0, foundContact.getNotes().size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void it_return_null_when_not_found() {
        Contact found = contactRepository.getOne(2L);
        assertNull(found);
    }

}
