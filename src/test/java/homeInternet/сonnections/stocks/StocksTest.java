package homeInternet.сonnections.stocks;

import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.DataProviderExtension;
import com.tngtech.junit.dataprovider.UseDataProvider;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import common.CommonSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@Epic("Домашний интернет - Акции")
@ExtendWith(UseDataProviderExtension.class)
@ExtendWith(DataProviderExtension.class)
public class StocksTest {

    StocksPage stocksPage = new StocksPage();

    @DataProvider
    public static Object[][] stocksInformation() {
        return new Object[][] {
                { "Акция «Приведи друга»", "http://phoenix-dnr.ru/internet-actions-1.php", 0} ,
                { "« Тариф «Единый» »",  "http://phoenix-dnr.ru/internet-actions-2.php", 1},
                { "«100 за 160»",  "http://phoenix-dnr.ru/internet-actions-6.php", 2},
        };
    }

    @TestTemplate
    @DisplayName("Акция {0}")
    @Description("Просмотр условий акции {0} и открытие окна заявки")
    @UseDataProvider("stocksInformation")
    @Severity(SeverityLevel.CRITICAL)
    public void stocks(String nameStocks, String URLPage, int key) {
        CommonSteps.openPage(stocksPage.getPageName(), stocksPage.getURL());
        StocksSteps.clickButtonFriends(stocksPage, key);
        CommonSteps.checkIsCorrectUrl(URLPage);
        StocksSteps.clickButtonConnect(stocksPage);
        StocksSteps.checkWindow(stocksPage);
        StocksSteps.clickClose(stocksPage);
        CommonSteps.returnBack();
    }

}
