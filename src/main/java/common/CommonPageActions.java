package common;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

public class CommonPageActions {
    public static void openPage(String URL) {
        Selenide.open(URL);
    }

    public static void switchToAnotherWindow(int window) {
        Selenide.switchTo().window(window);
    }

    public static void returnToPreviousPage() {
        Selenide.back();
    }

    public static String getCurrentURL() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

    public static void closeCurrentTabAndReturnToMain() {
        Selenide.closeWindow();
        Selenide.switchTo().window(0);
    }
}
