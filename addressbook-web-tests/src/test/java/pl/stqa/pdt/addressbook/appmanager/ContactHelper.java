package pl.stqa.pdt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import pl.stqa.pdt.addressbook.model.Contact;

public class ContactHelper extends HelperBase{

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitAddContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(Contact contact) {
    type(By.name("firstname"), contact.getFirstname());
    type(By.name("lastname"), contact.getLastname());
    type(By.name("address"), contact.getAddress());
    type(By.name("home"), contact.getHome());
    type(By.name("email"), contact.getEmail());
  }

  public void gotoAddNewPage() {
    click(By.linkText("add new"));
  }
}
