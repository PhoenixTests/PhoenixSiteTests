package common;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

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

    public static void scrollIntoView(SelenideElement element) {
        element.scrollIntoView(true);
    }

    public static int getCountOfOpenedWindows() {
        return WebDriverRunner.getWebDriver().getWindowHandles().size();
    }

    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
