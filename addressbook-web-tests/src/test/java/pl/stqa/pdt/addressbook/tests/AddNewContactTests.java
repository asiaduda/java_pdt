package pl.stqa.pdt.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pdt.addressbook.model.Contact;

public class AddNewContactTests extends TestBase {


  @Test
  public void testAddNewContact() {
    app.gotoAddNewPage();
    app.fillContactForm(new Contact("Asia", "Duda", "Kraków", "111333222", "a@b.com"));
    app.submitAddContact();
  }


}
