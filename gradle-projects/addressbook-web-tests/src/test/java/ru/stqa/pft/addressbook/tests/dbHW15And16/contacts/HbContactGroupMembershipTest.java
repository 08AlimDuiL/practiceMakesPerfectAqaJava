package ru.stqa.pft.addressbook.tests.dbHW15And16.contacts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

// task 16
public class HbContactGroupMembershipTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        app.goTo().groupPageHeader();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test76"));
        }

        try {
            if (app.db().groups().size() == 0) {
                System.out.println("ВНИМАНИЕ: Группа создана через UI, но в базе данных отсутствует!");
            }
        } catch (Exception e) {
            System.out.println("Не удалось проверить группу в базе данных: " + e.getMessage());
        }

        app.goTo().goToHomeHeader();
        if (!app.contact().isThereAGroup()) {
            app.contact().create(new ContactData()
                            .withFirstName("Richard")
                            .withLastName("Heart"),
                    true);
            app.goTo().goToHomeHeader();
        }

        try {
            if (app.db().contacts().size() == 0) {
                System.out.println("ВНИМАНИЕ: Контакт создан через UI, но в базе данных отсутствует!");
            }
        } catch (Exception e) {
            System.out.println("Не удалось проверить контакт в базе данных: " + e.getMessage());
        }

    }

    @Test(priority = 1)
    public void testHbContactAddToGroup() {

        ContactData contact = app.contact().almostAll().iterator().next();
        int contactId = contact.getId();

        GroupData group = app.group().allInHomePage().iterator().next();
        int groupId = group.getId();

        app.contact().selectContact(contact);
        app.contact().selectGroup(group);
        app.contact().addGroup();
        app.contact().goToGroupPageWithNameGroup();

        SessionFactory sessionFactory = initSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();

        ContactData foundContact = null;
        for (ContactData inContacts : result) {
            if (inContacts.getId() == contactId) {
                foundContact = inContacts;
                System.out.println("Найден контакт в БД: " + foundContact);
                break;
            }
        }
        if (foundContact == null) {
            for (ContactData c : result) {
                System.out.println("  Контакт ID: " + c.getId() + " - " + c.getFirstName() + " " + c.getLastName());
            }
        }

        boolean groupFound = false;
        for (GroupData groupFromDb : foundContact.getGroups()) {
            if (groupFromDb.getId() == groupId) {
                groupFound = true;
                break;
            }
        }

        Assert.assertNotNull(contactId, String.valueOf(foundContact));
        Assert.assertTrue(groupFound);
    }

    @Test(priority = 2, dependsOnMethods = "testHbContactAddToGroup")
    public void testHbContactDeleteInGroup(){

        ContactData contactInGroup = findContactInGroup();
        int contactId = contactInGroup.getId();
        int groupId = contactInGroup.getGroups().iterator().next().getId();

        System.out.println("***************************************************" + contactId + "***************************************************");
        System.out.println("***************************************************" + groupId + "***************************************************");

        app.contact().selectGroupIdWhereContactWillBeDeleted(groupId);
        app.contact().selectContactById(contactId);
        app.contact().removeFromGroup();

        SessionFactory sessionFactory = initSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<ContactData> allContacts = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();

        session.getTransaction().commit();
        session.close();

        ContactData contactAfterDeletion = null;
        for (ContactData contact : allContacts) {
            if (contact.getId() == contactId) {
                contactAfterDeletion = contact;
                break;
            }
        }

        boolean groupStillExists = false;
        if (contactAfterDeletion != null && contactAfterDeletion.getGroups() != null) {
            for (GroupData group : contactAfterDeletion.getGroups()) {
                if (group.getId() == groupId) {
                    groupStillExists = true;
                    break;
                }
            }
        }

        if (!groupStillExists) {
            System.out.println("✅ УСПЕХ: Контакт " + contactId + " больше не состоит в группе " + groupId);
        } else {
            System.out.println("❌ ОШИБКА: Контакт " + contactId + " все еще состоит в группе " + groupId);
        }

        Assert.assertFalse(groupStillExists);
    }

    private SessionFactory initSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            return new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw new RuntimeException("Ошибка инициализации Hibernate", e);
        }
    }

    private ContactData findContactInGroup() {
        SessionFactory sessionFactory = initSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();

        for (ContactData contact : result) {
            if (!contact.getGroups().isEmpty()) {
                return contact;
            }
        }
        return null;
    }
}
