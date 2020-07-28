package common;

import com.codeborne.selenide.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class CommonPageActions {
    public static void openPage(String URL) {
        Configuration.browser = "firefox";
        System.setProperty("selenide.browser", "firefox");
        Selenide.open(URL);
    }

    public static void switchToAnotherWindow(int window) {
        Selenide.switchTo().window(window);
    }

    public static void returnToPreviousPage() {
        Selenide.back();
    }

    public static String getCurrentURL() {
        while (WebDriverRunner.url().equals("about:blank")) ;
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

    public static void closeCurrentTab() {
        Selenide.closeWindow();
    }

    public static void returnToMain() {
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

    public static void switchToFrame(SelenideElement frame) {
        Selenide.switchTo().frame(frame);
    }

    public static void exitFromFrame() {
        Selenide.switchTo().defaultContent();
    }

    public static void closeAdvertisingPopup() {
        SelenideElement advertising = $(byAttribute("for", "popup_checkbox_greeting_internet"));
        if (advertising.isDisplayed()) {
            advertising.click();
            advertising.waitUntil(Condition.disappear, 3000);
        }
    }
}
