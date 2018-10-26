package pl.stqa.pdt.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pdt.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Asia", "Duda", "Kraków",
              "111333222", "a@b.com", "test1"),true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Asia", "D-Z", "Kraków",
            "000333222", "a@b.com",null),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
