package ru.netology.login.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.login.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement login = $("[data-test-id=login] input");
    private final SelenideElement password = $("[data-test-id=password] input");
    private final SelenideElement button = $("[data-test-id=action-login]");
    private final SelenideElement error = $("[data-test-id=error-notification] .notification__content");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        button.click();
        return new VerificationPage();
    }

    public void loginError(String errorMessage) {
        error.shouldHave(Condition.text(errorMessage)).shouldBe(Condition.visible);
    }
}