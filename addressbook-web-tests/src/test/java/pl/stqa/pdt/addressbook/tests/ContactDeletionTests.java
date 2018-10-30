package pl.stqa.pdt.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pdt.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {

    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Asia", "Duda", "Kraków",
              "111333222", "a@b.com", "test1"),true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().confirmContactDeletion();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
