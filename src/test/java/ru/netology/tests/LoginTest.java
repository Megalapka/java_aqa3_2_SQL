package ru.netology.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @AfterAll
    static void teardownAll() {
        SQLHelper.cleanDatabase();
    }

    @BeforeEach
    void setUP() {
        Configuration.holdBrowserOpen = true;
    }
    @AfterEach
    void tearDown() {
        closeWindow();
    }

    @Test
    @DisplayName("Should successful login with user test data")
    void shouldSuccessLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithRegisteredUser();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.isVisibleVerificationPage();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName("Should show error message with random user data")
    void shouldErrorMessageWithRandomUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.generateRandomUser();
        loginPage.validLogin(authInfo);
        loginPage.errorMessInvalidLogin();

    }

    @Test
    @DisplayName("Should show error message with random verificationCode")
    void shouldErrorMessageWithRandomVerificationCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithRegisteredUser();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.isVisibleVerificationPage();
        var verificationCode = DataHelper.generateRandomCode();
        verificationPage.getAndSendVerificationCode(verificationCode);
        verificationPage.errorMessInvalidVerificationCode();

    }
}
