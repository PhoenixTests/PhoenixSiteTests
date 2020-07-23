package mobileCommunication.mobileCommunication.rates;

import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.DataProviderExtension;
import com.tngtech.junit.dataprovider.UseDataProvider;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import common.CommonPageActions;
import common.CommonSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(UseDataProviderExtension.class)
@ExtendWith(DataProviderExtension.class)
@Epic("Соцсети")
@Feature("Тестирование иконок соцсетей")
public class SocialsTest {
    private static RatePage ratePage;

    @Step("Пролистнуть до иконок соцсетей")
    public void scrollIntoView(String url) {
        CommonPageActions.scrollIntoView(ratePage.getSocialIcon(url));
    }

    @Step("Нажать на иконку соцсети")
    public void clickOnSocials(String url) {
        ratePage.getSocialIcon(url).click();
    }

    @Step("Закрыть вкладку с соцсетью")
    public void closeWindow() {
        CommonPageActions.closeCurrentTab();
        CommonPageActions.returnToMain();
    }

    @Step("При проверке значка почты проверяем, в href значка есть mailto")
    public void mailCheck(String url) {
        assertTrue(url.startsWith("mailto:"));
    }

    @DataProvider
    public static Object[] socials() {
        return new Object[] {
                "https://vk.com/phoenix_071",
                "https://twitter.com/Phoenix__071",
                "https://www.instagram.com/rosphoenix071/",
                "mailto:Info@phoenix-dnr.ru"
        };
    }

    @BeforeAll
    public static void openPage() {
        ratePage= new RatePage();
        CommonSteps.openPage(RatePage.getPageName(), RatePage.getURL());
    }

    @TestTemplate
    @UseDataProvider("socials")
    @DisplayName("Перейти по иконке соцсети")
    @Description("Тест на переход по иконкам соцсетей")
    @Severity(SeverityLevel.NORMAL)
    public void shouldRedirectToSocials(String url) {
        if (ratePage.getSocialIcon(url) != null) {
            scrollIntoView(url);
            if (url.equals("mailto:Info@phoenix-dnr.ru"))
                mailCheck(url);
            else {
                clickOnSocials(url);
                CommonSteps.switchToAnotherWindow(1);
                CommonSteps.checkIsCorrectUrl(url);
                closeWindow();
            }
        }
    }
}
