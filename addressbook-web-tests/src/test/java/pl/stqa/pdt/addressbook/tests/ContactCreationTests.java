package pl.stqa.pdt.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pdt.addressbook.model.ContactData;
import pl.stqa.pdt.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddNewContact() {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/pictr.png");
    ContactData contact = new ContactData()
            .withFirstname("Ala").withLastname("Nowak").withAddress("Krakow")
            .withHomePhone("111333222").withEmail("a@b.com").withGroup("test1").withPhoto(photo);
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
