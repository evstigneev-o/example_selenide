package ru.tinkoff.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.tinkoff.pages.MainPage;

import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;

public class MainPageTests extends BaseTest {
    MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Успешное открытие главной страницы")
    @Severity(SeverityLevel.BLOCKER)
    public void successfullyOpenMainPage() {
        step("Открываем главную страницу", mainPage::openPage);
        step("Проверяем что страница загрузилась", mainPage::checkLogoVisibility);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Частным лицам", "Бизнесу", "Премиум", "Личный кабинет"})
    @DisplayName("Проверка заголовков в навбаре")
    @Severity(SeverityLevel.CRITICAL)
    public void checkItemsTitle(String title) {
        step("Открываем главную страницу", mainPage::openPage);
        step("Проверяем что наличие заголовков в навбаре", () -> mainPage.checkNavigationBarItemNames(title));
    }

    @ParameterizedTest()
    @CsvFileSource(resources = "/logins.csv")
    @DisplayName("Проверка доступности и порядка отображения доступных ЛК")
    @Severity(SeverityLevel.CRITICAL)
    public void checkOrderedLoginList(int index, String title) {
        step("Открываем главную страницу", mainPage::openPage);
        step("Проверяем порядок отображения списка доступных ЛК", () -> mainPage.checkOrdersLoginsList(index, title));
    }

    @ParameterizedTest
    @MethodSource("getLoginsHref")
    @DisplayName("Проверка корректности связок названий ЛК и ссылок")
    @Severity(SeverityLevel.CRITICAL)
    public void checkLoginHref(String title, String link) {
        step("Открываем главную страницу", mainPage::openPage);
        step("Проверяем корректность ссылок на ЛК", () -> mainPage.checkLoginsHref(title, link));
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
