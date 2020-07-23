package common;

import io.qameta.allure.Step;

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

    @Step("Выход из фрейма")
    public static void exitFrame() {
        CommonPageActions.exitFromFrame();
    }
}
