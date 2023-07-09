package ru.tinkoff.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.href;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    SelenideElement
            logo = $("[data-test=\"logo\"]"),
            navigationBar = $("[data-test=\"navigation\"]"),
            loginWrapper = $("[data-test=\"loginWrapper\"]");

    String loginTitle = "[title=\"Личный кабинет\"]";

    public MainPage openPage() {
        open("https://www.tinkoff.ru/");
        return this;
    }

    public MainPage checkLogoVisibility() {
        logo.shouldBe(Condition.visible);
        return this;
    }

    public MainPage checkNavigationBarItemNames(String ItemName) {
        navigationBar.shouldHave(text(ItemName));
        return this;
    }

    public MainPage checkOrdersLoginsList(int index, String title) {
        ElementsCollection loginsList = loginWrapper.hover().$$(loginTitle);
        loginsList.get(index).shouldHave(text(title));
        return this;
    }

    public MainPage checkLoginsHref(String title, String link) {
        ElementsCollection loginsList = loginWrapper.hover().$$(loginTitle);
        loginsList.findBy(text(title)).shouldHave(href(link));
        return this;
    }
}
