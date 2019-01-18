package pl.stqa.pdt.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pdt.addressbook.appmanager.ContactHelper;
import pl.stqa.pdt.addressbook.model.ContactData;
import pl.stqa.pdt.addressbook.model.GroupData;
import pl.stqa.pdt.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;


public class ContactGroupAddRemoveTests extends TestBase {
  private ContactData myContact = null;
  private GroupData myGroup = null;

  @BeforeMethod
  void preconditions() {
    if (app.contact().count() == 0)
      app.contact().create(new ContactData().withFirstname("John").withLastname("Doe"), true);
    myContact = app.db().contacts().iterator().next();
    Groups allGroups = app.db().groups();
    Groups contactGroups = myContact.getGroups();
    if (contactGroups.size() < allGroups.size()) {
      allGroups.removeAll(contactGroups);
      myGroup = allGroups.iterator().next();
    } else {
      myGroup = new GroupData().withName("Group-"+System.currentTimeMillis());
      app.goTo().GroupPage();
      app.group().create(myGroup);
      myGroup = app.db().groups().stream().filter(g -> g.getName().equals(myGroup.getName())).iterator().next();
    }
    assertThat("Precondition check",! myContact.getGroups().contains(myGroup));
  }

  @Test
  void testAddAndRemove() {
    app.goTo().homePage();
    app.contact().addContactToGroup(myContact,myGroup);
    ContactData updatedContact = app.db().contacts().stream().filter(c -> c.getId() == myContact.getId()).iterator().next();
    assertThat("Contact contains group",updatedContact.getGroups().contains(myGroup));
    app.goTo().homePage();
    app.contact().removeContactFromGroup(myContact,myGroup);
    updatedContact = app.db().contacts().stream().filter(c -> c.getId() == myContact.getId()).iterator().next();
    assertThat("Contact no longer in group",!updatedContact.getGroups().contains(myGroup));
  }
}
