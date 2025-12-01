package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

    @Test
    public void testGetProjects0() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = new MantisConnectLocator()
                .getMantisConnectPort(new URL("http://localhost/mantisbt-1.3.20/api/soap/mantisconnect.php"));
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
        System.out.println(projects.length);
        for (ProjectData project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProject();

        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProject();
        Issue issue = new Issue()
                .withSummary("Test issue")
                .withDescription("Test issue description")
                .withProject(projects.iterator().next());

        Issue created = app.soap().addIssue(issue);


        System.out.println("=========================================");
        System.out.println("CREATED BUG WITH ID: " + created.getId());
        System.out.println("=========================================");

        assertEquals(issue.getSummary(), created.getSummary());
    }

    @Test
    public void testBugIntegration() throws Exception {
        int testBugId = 3; // Используем реальный ID созданного бага

        System.out.println("=== Testing bug integration ===");
        System.out.println("Checking bug status for ID: " + testBugId);


        boolean isOpen = isIssueOpen(testBugId);
        System.out.println("Bug is open: " + isOpen);


        skipIfNotFixed(testBugId);


        System.out.println("Bug is fixed! Test continues...");
        System.out.println("This message will only appear if bug #3 is resolved or closed");
    }
}