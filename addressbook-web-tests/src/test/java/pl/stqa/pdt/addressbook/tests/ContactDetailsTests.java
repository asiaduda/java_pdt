package pl.stqa.pdt.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pdt.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.contact().all().size() == 0){
      app.contact().create(new ContactData()
              .withFirstname("Asia").withLastname("Bebe").withAddress("Krk")
              .withHomePhone("111").withEmail("a@b.com"),true);
    }
  }

  @Test
  public void testContactDetails(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData editDets = app.contact().infoFromEditForm(contact);

    String pageContactDetails =app.contact().infoFromContactDetails(contact);

    String fullName = Arrays.asList(editDets.getFirstname(), editDets.getMiddleName(), editDets.getLastname())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining(" "));
    String expected = fullName;
    expected += editDets.getNickName();
    expected += editDets.getTitle();
    expected += editDets.getCompany();
    expected += editDets.getAddress();
    expected = appendWithPrefixIfExists(expected,"H: ",editDets.getHomePhone());
    expected = appendWithPrefixIfExists(expected,"M: ",editDets.getMobilePhone());
    expected = appendWithPrefixIfExists(expected,"W: ",editDets.getWorkPhone());
    expected = appendWithPrefixIfExists(expected,"F: ",editDets.getFax());
    expected += editDets.getEmail() + editDets.getEmail2() + editDets.getEmail3();
    expected = appendWithPrefixIfExists(expected,"Homepage:",editDets.getHomepage());

    assertThat(cleaned(pageContactDetails), equalTo(cleaned(expected)));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\n","");
  }

  public String appendWithPrefixIfExists(String s, String prefix, String add) {
    if (add != null)
      s += prefix + add;
    return s;
  }
}
