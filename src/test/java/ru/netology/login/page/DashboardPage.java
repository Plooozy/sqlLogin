package ru.netology.login.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]");

    public void  dashboardVisible() {
        heading.shouldHave(Condition.text("  Личный кабинет")).shouldBe(Condition.visible);
    }
}
