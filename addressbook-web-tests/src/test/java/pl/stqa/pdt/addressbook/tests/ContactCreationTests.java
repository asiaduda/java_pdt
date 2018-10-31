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
    ContactData contact = new ContactData("Asia", "Duda", "Kraków",
            "111333222", "a@b.com", "test1");
    app.getContactHelper().createContact(contact,true);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    int max = 0;
    for(ContactData c : after){
      if (c.getId() > max){
        max = c.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }


}
