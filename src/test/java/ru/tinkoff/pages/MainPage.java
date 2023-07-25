package ru.tinkoff.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.href;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    CreditCardsPage creditCardsPage = new CreditCardsPage();

    SelenideElement
            logo = $("[data-test=\"logo\"]"),
            loginWrapper = $("[data-test=\"loginWrapper\"]"),
            privateClients = $("[data-test=\"menu-item-0-title\"]"),
            creditCardsDropDownMenu = $("[data-test=\"clickableArea text-item-0-0\"]"),
            slidesPanel = $("[data-test=\"panel slides\"]"),
            creditCardsSlidesPanel = slidesPanel.$("[data-schema-path=\"1\"]");

    ElementsCollection navigationBar = $$("[data-item-type=\"menu\"]");

    String loginTitle = "[title=\"Личный кабинет\"]";

    public MainPage openPage() {
        open("");
        return this;
    }

    public MainPage checkLogoVisibility() {
        logo.shouldBe(Condition.visible);
        return this;
    }

    public MainPage checkNavigationBarItemNames(List<String> titles) {
        navigationBar.should(texts(titles));
        return this;
    }


    public MainPage checkLoginsHref(String title, String link) {
        ElementsCollection loginsList = loginWrapper.hover().$$(loginTitle);
        loginsList.findBy(text(title)).shouldHave(href(link));
        return this;
    }

    public MainPage openPrivateClientDropDownMenu() {
        privateClients.hover();
        return this;
    }

    public CreditCardsPage clickOnCreditCardsOnDropDownMenu() {
        creditCardsDropDownMenu.click();
        return creditCardsPage;
    }

    public CreditCardsPage clickOnCreditCardsOnSlidesPanel() {
        creditCardsSlidesPanel.click();
        return creditCardsPage;
    }
}
