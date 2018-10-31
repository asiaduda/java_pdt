package pl.stqa.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pdt.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Asia", "Duda", "Kraków",
              "111333222", "a@b.com", "test1"),true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Asia", "D-Z", "Krakow",
            "000333222", "a@b.com",null);
    app.getContactHelper().fillContactForm(contact,false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
