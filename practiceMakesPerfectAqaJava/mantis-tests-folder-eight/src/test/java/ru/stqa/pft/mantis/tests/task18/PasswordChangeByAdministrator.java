package ru.stqa.pft.mantis.tests.task18;

import org.apache.hc.core5.http.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.appmanager.UserEditHelper;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.tests.TestBase;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangeByAdministrator extends TestBase {

    @BeforeMethod
    public void startMailServer() {

        app.mail().start();
    }

    @Test
    public void testPasswordReset() throws IOException, MessagingException, InterruptedException, ParseException {
        String newPassword = "newpassword123";

        app.login().loginAsAdmin();

        UserEditHelper userEditHelper = app.navigation()
                .goToManagementPage()
                .goToUserManagement()
                .selectRandomUserWithOutAdmin();

        UserData selectedUser = app.userManagement().getSelectedUser();

        System.out.println("Выбран пользователь: " + selectedUser.getUsername() +
                " с email: " + selectedUser.getEmail());


        userEditHelper.clickResetPassword();
        Thread.sleep(5000);

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 30000);
        System.out.println("Получено писем ========================================================= " + mailMessages.size());

        String confirmationLink = findConfirmationLink(mailMessages, selectedUser.getEmail());
        System.out.println("Ссылка для смены пароля ================================================" + confirmationLink);

        app.registration().finish(confirmationLink, newPassword);

        HttpSession session = app.newSession();
        boolean loginSuccess = session.login(selectedUser.getUsername(), newPassword);

        assertTrue(loginSuccess,
                "Пользователь " + selectedUser.getUsername() + " не может войти с новым паролем");
    }


    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream()
                .filter((m) -> m.to.equals(email))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Не найдено письмо для " + email));

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
