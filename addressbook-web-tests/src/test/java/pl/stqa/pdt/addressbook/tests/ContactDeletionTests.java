package pl.stqa.pdt.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pdt.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Asia", "Duda", "Kraków",
              "111333222", "a@b.com", "test1"),true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().confirmContactDeletion();
  }
}
