package pl.stqa.pdt.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.stqa.pdt.addressbook.model.ContactData;
import pl.stqa.pdt.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts(){
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new ContactData().withFirstname("Imie1").withLastname("Nazwisko1")
            .withAddress("Adres1").withEmail("Email1@edu.pl")});
    list.add(new Object[] {new ContactData().withFirstname("Imie2").withLastname("Nazwisko2")
            .withAddress("Adres2").withEmail("Email2@edu.pl")});
    list.add(new Object[] {new ContactData().withFirstname("Imie3").withLastname("Nazwisko3")
            .withAddress("Adres3").withEmail("Email3@edu.pl")});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testAddNewContact(ContactData contact) {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/pictr.png");
    app.contact().create(contact,true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }

  @Test(enabled = false)
  public void testAddNewBadContact() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Ania'").withLastname("Nowak").withAddress("Krakow")
            .withHomePhone("111333222").withEmail("a@b.com").withGroup("test1");
    app.contact().create(contact,true);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }





}
