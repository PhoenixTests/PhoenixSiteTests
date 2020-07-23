package internet.connections.stocks;

import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.DataProviderExtension;
import com.tngtech.junit.dataprovider.UseDataProvider;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import common.CommonSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@Epic("Домашний интернет - Акции")
@ExtendWith(UseDataProviderExtension.class)
@ExtendWith(DataProviderExtension.class)
public class StocksTest extends StocksSteps {

    @DataProvider
    public static Object[][] stocksInformation() {
        return new Object[][]{
                {"Акция «Приведи друга»", "http://phoenix-dnr.ru/internet-actions-1.php", 0},
                {"« Тариф «Единый» »", "http://phoenix-dnr.ru/internet-actions-2.php", 1},
                {"«100 за 160»", "http://phoenix-dnr.ru/internet-actions-6.php", 2},
        };
    }

    @BeforeAll
    public static void openPage() {
        CommonSteps.openPage(StocksPage.getPageName(), StocksPage.getURL());
        stocksPage = new StocksPage();
    }

    @TestTemplate
    @DisplayName("Акция")
    @Description("Просмотр условий акции и открытие окна заявки")
    @UseDataProvider("stocksInformation")
    @Severity(SeverityLevel.CRITICAL)
    public void stocks(String stockName, String URLPage, int key) {
        StocksSteps.clickButtonFriends(stockName, key);
        CommonSteps.checkIsCorrectUrl(URLPage);
        StocksSteps.clickButtonConnect();
        StocksSteps.checkWindow();
        StocksSteps.clickClose();
        CommonSteps.returnBack();
    }

}
