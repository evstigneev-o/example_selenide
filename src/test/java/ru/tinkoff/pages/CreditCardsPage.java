package ru.tinkoff.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CreditCardsPage {
    SelenideElement
	creditCardsPageText= $(byText("Кредитные карты — это платежные карты," +
            " позволяющие оплачивать покупки за счет средств банка." +
            " Расплачивайтесь кредиткой и получайте кэшбэк бонусами за любые покупки"));

    public void pageShouldBeOpen(){
        creditCardsPageText.shouldBe(Condition.visible);
    }
}
