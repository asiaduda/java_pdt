package pl.stqa.pdt.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.stqa.pdt.addressbook.model.ContactData;
import pl.stqa.pdt.addressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null){
      String[] split = line.split(";");
      list.add(new Object[] {new ContactData().withFirstname(split[0]).withLastname(split[1])
              .withAddress(split[2]).withEmail(split[3])});
      line = reader.readLine();
    }
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
