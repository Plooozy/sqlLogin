package ru.netology.login.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement code = $("[data-test-id=code] input");
    private final SelenideElement button = $("[data-test-id=action-verify]");
    private final SelenideElement error = $("[data-test-id=error-notification] .notification__content");

    public void pageVisible() {
        code.shouldBe(Condition.visible);
    }

    public DashboardPage verification(String verificationCode) {
        code.setValue(verificationCode);
        button.click();
        return new DashboardPage();
    }

    public void verificationError(String errorMessage) {
        error.shouldHave(Condition.text(errorMessage)).shouldBe(Condition.visible);
    }
}
