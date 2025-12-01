package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class TestBase {

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", "chrome"));

    @BeforeSuite
    public void setUp() throws Exception {

        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {

        app.stop();
    }

    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData issueData = mc.mc_issue_get(
                app.getProperty("web.adminLogin"),
                app.getProperty("web.adminPassword"),
                BigInteger.valueOf(issueId)
        );

        ObjectRef status = issueData.getStatus();
        String statusName = status.getName();

        return !(statusName.equals("resolved") || statusName.equals("closed"));
    }


    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        String soapUrl = app.getProperty("soap.url");
        return new MantisConnectLocator().getMantisConnectPort(new URL(soapUrl));
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {

            throw new SkipException("Ignored because of issue " + issueId);
        }

    }
}