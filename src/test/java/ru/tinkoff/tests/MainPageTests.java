package ru.tinkoff.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.tinkoff.pages.CreditCardsPage;
import ru.tinkoff.pages.MainPage;

import java.util.List;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;

public class MainPageTests extends BaseTest {
    MainPage mainPage = new MainPage();
    CreditCardsPage creditCardsPage = new CreditCardsPage();

    @Test
    @DisplayName("Успешное открытие главной страницы")
    @Severity(SeverityLevel.BLOCKER)
    public void successfullyOpenMainPage() {
        step("Открываем главную страницу", mainPage::openPage);
        step("Проверяем что страница загрузилась", mainPage::checkLogoVisibility);
    }

    @Test
    @DisplayName("Проверка заголовков в навбаре")
    @Severity(SeverityLevel.CRITICAL)
    public void checkItemsTitle() {
        List<String> titles = List.of("Частным лицам", "Бизнесу", "Премиум", "Еще");
        step("Открываем главную страницу", mainPage::openPage);
        step("Проверяем наличие заголовков в навбаре", () -> mainPage.checkNavigationBarItemNames(titles));

    }

    @ParameterizedTest
    @MethodSource("getLoginsHref")
    @DisplayName("Проверка корректности связок названий ЛК и ссылок")

    public void checkLoginHref(String title, String link) {
        step("Открываем главную страницу", mainPage::openPage);
        step("Проверяем корректность ссылок на ЛК", () -> mainPage.checkLoginsHref(title, link));
    }

    @Test
    @DisplayName("Переход на страницу кредитных карт через выпадающее меню навбара")
    @Severity(SeverityLevel.CRITICAL)
    public void checkOpenCreditCardsPageByNavigationBar() {
        step("Открываем главную страницу", mainPage::openPage);
        step("Открываем выпадающее меню Частным лицам", () -> mainPage.openPrivateClientDropDownMenu());
        step("Выбираем Кредитные карты в выпадающем списке навбара Частным клиентам", () -> mainPage.clickOnCreditCardsOnDropDownMenu());
        step("Проверяем что открылась страница Кредитные карты", () -> creditCardsPage.pageShouldBeOpen());
    }

    @Test
    @DisplayName("Переход на страницу кредитных карт через слайд панель")
    @Severity(SeverityLevel.CRITICAL)
    public void checkOpenCreditCardsPageBySlidesPanel() {
        step("Открываем главную страницу", mainPage::openPage);
        step("Выбираем Кредитные карты в слайд панели", () -> mainPage.clickOnCreditCardsOnSlidesPanel());
        step("Проверяем что открылась страница Кредитные карты", () -> creditCardsPage.pageShouldBeOpen());
    }

    static Stream<Arguments> getLoginsHref() {
        return Stream.of(
                Arguments.of("Интернет-банк", "/login/"),
                Arguments.of("Тинькофф Бизнес", "https://sme.tinkoff.ru/"),
                Arguments.of("Инвестиции", "https://www.tinkoff.ru/login/?redirectTo=/invest/portfolio/"),
                Arguments.of("Тинькофф Мобайл", "/mobile-operator/login/")
        );
    }

}
