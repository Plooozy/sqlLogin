package ru.netology.login.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.login.data.DataHelper;
import ru.netology.login.data.SQLHelper;
import ru.netology.login.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.login.data.SQLHelper.cleanAuthCodes;
import static ru.netology.login.data.SQLHelper.cleanDatabase;

public class LoginTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @AfterAll
    static void clearDatabase() {
        cleanDatabase();
    }

    @AfterEach
    void clearCodes() {
        cleanAuthCodes();
    }

    @Test
    void shouldLogin() {
        var authInfo = DataHelper.getRealUser();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.pageVisible();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.verification(verificationCode.getCode());
    }

    @Test
    void shouldNotLoginRandomUser() {
        var authInfo = DataHelper.getRandomUser();
        var loginPage = new LoginPage();
        loginPage.validLogin(authInfo);
        loginPage.loginError("Ошибка! " + "Неверно указан логин или пароль");
    }
    @Test
    void shouldNotLoginRandomCode() {
        var authInfo = DataHelper.getRealUser();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.pageVisible();
        var verificationCode = DataHelper.getRandomCode();
        verificationPage.verification(verificationCode.getCode());
        verificationPage.verificationError("Ошибка! " + "Неверно указан код! Попробуйте ещё раз.");
    }

    @Test
    void shouldBlockAccess() {
        var authInfo = DataHelper.getRealUser();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.pageVisible();
        var verificationCode = DataHelper.getRandomCode();
        verificationPage.verification(verificationCode.getCode());
        verificationPage.verificationError("Ошибка! " + "Неверно указан код! Попробуйте ещё раз.");
        verificationPage.verification(verificationCode.getCode());
        verificationPage.verificationError("Ошибка! " + "Неверно указан код! Попробуйте ещё раз.");
        verificationPage.verification(verificationCode.getCode());
        verificationPage.verificationError("Ошибка! " + "Неверно указан код! Попробуйте ещё раз.");
        verificationPage.verification(verificationCode.getCode());
        verificationPage.verificationError("Ошибка! " + "Ваш аккаунт заблокирован, свяжитесь с оператором для разблокировки");
    }
}