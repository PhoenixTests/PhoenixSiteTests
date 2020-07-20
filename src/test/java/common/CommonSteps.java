package common;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonSteps {
    @Step("Открыть страницу {pageName}")
    public static void openPage(String pageName, String URL) {
        CommonPageActions.openPage(URL);
    }

    @Step("Перейти на открывшуюся страницу")
    public static void switchToAnotherWindow(int window) {
        CommonPageActions.switchToAnotherWindow(window);
    }

    @Step("Возврат на предыдущую страницу")
    public static void returnBack() {
        CommonPageActions.returnToPreviousPage();
    }

    @Step("Сравнение url только что открытой вкладки с {url}")
    public static void checkIsCorrectUrl(String url) {
        assertEquals(url, CommonPageActions.getCurrentURL());
    }

    @Step("Переход на открывшийся фрейм")
    public static void switchFrame() {
        CommonPageActions.switchToFrame($(byId("popup-iframe")));
    }

    @Step("Выход из фрейма")
    public static void exitFrame() {
        CommonPageActions.exitFromFrame();
    }
}
