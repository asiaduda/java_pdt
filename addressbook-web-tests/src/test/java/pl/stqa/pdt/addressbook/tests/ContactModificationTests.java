package pl.stqa.pdt.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pdt.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Asia", "D-Z", "Kraków",
            "000333222", "a@b.com",null),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
