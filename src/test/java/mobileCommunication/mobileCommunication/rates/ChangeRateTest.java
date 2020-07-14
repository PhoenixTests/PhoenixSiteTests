package mobileCommunication.mobileCommunication.rates;

import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.DataProviderExtension;
import com.tngtech.junit.dataprovider.UseDataProvider;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import io.qameta.allure.*;
import mobileCommunication.mobileCommunication.rates.RatePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(UseDataProviderExtension.class)
@ExtendWith(DataProviderExtension.class)
@Epic("Изменение тарифа")
@Feature("Тестирование ссылок для смены тарифа")
public class ChangeRateTest {
    private RatePage ratePage;

    @Step("Перейти по ссылке {linkText}")
    public void clickOnLink(String linkText) {
        ratePage.getChangeRatesLink(linkText).click();
    }

    @DataProvider
    public static Object[][] links() {
        return new Object[][] {
                {"в Личном кабинете абонента", "https://my.phoenix-dnr.ru/login"},
                {"через Telegram-бот «Мой Феникс»", "https://t.me/myphoenix_bot"}
        };
    }


    @TestTemplate
    @UseDataProvider("links")
    @DisplayName("Перейти по ссылке для смены тарифа")
    @Description("Тест на корректность ссылок для смены тарифа")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldRedirectToPrivateCabinet(String linkText, String url) {
        ratePage= DefaultSteps.openPage();
        clickOnLink(linkText);
        DefaultSteps.checkIsCorrectUrl(ratePage,url);
        DefaultSteps.returnBack(ratePage);
    }
}
