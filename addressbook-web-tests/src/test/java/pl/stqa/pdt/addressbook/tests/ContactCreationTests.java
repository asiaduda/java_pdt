package pl.stqa.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pdt.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddNewContact() {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Ala", "Nowak", "Krakow",
            "111333222", "a@b.com", "test1");
    app.getContactHelper().createContact(contact,true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }


}
