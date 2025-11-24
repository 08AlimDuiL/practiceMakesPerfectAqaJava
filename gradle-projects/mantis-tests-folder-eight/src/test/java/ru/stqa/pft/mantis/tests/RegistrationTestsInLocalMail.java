package ru.stqa.pft.mantis.tests;

import org.apache.hc.core5.http.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;


import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTestsInLocalMail extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        System.out.println("Starting mail server...");
        app.mail().start();
        System.out.println("Mail server started on port: " + app.mail().getPort());
    }

    @Test
    public void testRegistration() throws MessagingException, IOException, ParseException, InterruptedException {
        long now = System.currentTimeMillis();
        String user = String.format("user_%s", now);
        String password = "password";
        String email = String.format("user_%s@localhost.localdomain", now);

        Thread.sleep(5000);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 30_000);

        String confirmationLink = findConfirmationLink(mailMessages, email);

        app.registration().finish(confirmationLink, password);

        assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {

        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex()
                .find("http://")
                .nonSpace()
                .oneOrMore()
                .build();

        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {

        app.mail().stop();
    }
}
