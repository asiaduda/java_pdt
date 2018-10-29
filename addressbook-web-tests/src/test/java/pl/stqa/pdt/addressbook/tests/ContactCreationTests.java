package pl.stqa.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pdt.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddNewContact() {
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("Asia", "Duda", "Kraków",
            "111333222", "a@b.com", "test1"),true);
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }


}
