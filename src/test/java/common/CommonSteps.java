package common;

import io.qameta.allure.Step;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
        try {
            String currentURL = CommonPageActions.getCurrentURL();
            assertEquals(URLDecoder.decode(url, "UTF-8"), URLDecoder.decode(currentURL, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Step("Выход из фрейма")
    public static void exitFrame() {
        CommonPageActions.exitFromFrame();
    }
}
