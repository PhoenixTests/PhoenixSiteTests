package mobileCommunication.mobileCommunication.rates;

import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.DataProviderExtension;
import com.tngtech.junit.dataprovider.UseDataProvider;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import common.CommonPageActions;
import common.CommonSteps;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(UseDataProviderExtension.class)
@ExtendWith(DataProviderExtension.class)
@Epic("Тарифы")
@Feature("Тестирование подбора тарифа и карусели тарифов")
public class RateSelectionTest extends DefaultRateSteps {

    @Step("Проверка на совпадение подобранного тарифа с {rateName}")
    public void checkRateName(String rateName) {
        assertEquals(rateName, ratePage.getRateName().getText());
    }

    @Step("Проверка на равность абонентской платы подобранного тарифа с {ratePrice}")
    public void checkRatePrice(int ratePrice) {
        assertEquals(ratePrice, Integer.parseInt(ratePage.getRatePrice().getText()));
    }

    @Step("Нажать на кнопку 'Подробнее'")
    public void rateNameButtonClick() {
        ratePage.getRateInfo().click();
    }

    @Step("Проверить заголовок страницы на совпадение с {rateTitle}")
    public void checkPageTitle(String rateTitle) {
        assertEquals(rateTitle, ratePage.getPageTitle());
    }

    @Step("Проверить, виден ли на данный момент тариф {rateName} в карусели тарифов")
    public void getVisibleRate(String rateName) {
        CommonPageActions.scrollIntoView(ratePage.getActiveElements().last());
        ratePage.isRateVisible(rateName);
    }

    @Step("Листать карусель вперед до того момента, пока не будет виден тариф {rateName}")
    public void forwardButtonClick(String rateName) {
        while(!ratePage.getCarouselRate().exists()) {
            ratePage.getCarouselForwardButton().click();
        }
    }

    @Step("Листать карусель назад до того момента, пока не будет виден тариф {rateName}")
    public void backwardButtonClick(String rateName) {
        while(!ratePage.getCarouselRate().exists())
            ratePage.getCarouselBackButton().click();
    }

    @Step("Нажать на кнопку 'Подробнее' тарифа ЕДИНЫЙ в карусели")
    public void carouselRateButtonClick() {
        ratePage.setCarouselRateButton();
        ratePage.getCarouselRateButton().click();
    }

    @BeforeAll
    public static void openPage() {
        ratePage = new RatePage();
        CommonSteps.openPage(RatePage.getPageName(), RatePage.getURL());
    }

//    @AfterEach
//    public void returnBack() {
//        CommonSteps.returnBack();
//    }

    @DataProvider
    public static Object[][] rateInfo() {
        return new Object[][]{
                {0, 99, 0, 99, "«Без границ»", 300, "Тариф «БЕЗ ГРАНИЦ»"},
                {99, 99, 99, 0, "«Комфортный»", 230, "Тариф «КОМФОРТНЫЙ»"},
                {99, 50, 50, 0, "«Удобный»", 150, "Тариф «УДОБНЫЙ»"},
                {0, 50, 0, 0, "«Народный интернет»", 95, "Народный интернет тариф"},
                {0, 0, 0, 0, "«Народный»", 50, "Народный Тариф"}
        };
    }

    @DataProvider
    public static Object[][] rates() {
        return new Object[][]{
                {"«БЕЗ ГРАНИЦ»", "Тариф «БЕЗ ГРАНИЦ»"},
                {"«УДОБНЫЙ»", "Тариф «УДОБНЫЙ»"},
                {"«ОНЛАЙН»", "Тариф «ОНЛАЙН»"},
                { "«КОМФОРТНЫЙ»", "Тариф «КОМФОРТНЫЙ»"},
                {"«СВОБОДНЫЙ»", "Свободный Тариф"},
                {"«ЕДИНЫЙ»", "Единый Тариф"},
                {"«НАРОДНЫЙ ИНТЕРНЕТ»", "Народный интернет тариф"},
                {"«НАРОДНЫЙ»", "Народный Тариф"}
        };
    }

    @TestTemplate
    @UseDataProvider("rateInfo")
    @DisplayName("Подбор правильного тарифа")
    @Description("Тест на правильность подбора, в соответствии со значениями ползунков")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldSelectCorrectRate(int calls, int gb, int sms, int rf, String rateName,
                                        int ratePrice, String rateTitle) {
        changeRanges(calls, gb, sms, rf);
        getRateClick();
        checkRateName(rateName);
        checkRatePrice(ratePrice);
        rateNameButtonClick();
        checkPageTitle(rateTitle);
        CommonSteps.returnBack();
    }

    //TODO два теста с каруселью одинаковые по сути, нужно их объединить в один, чтобы быстрее прогонялись

    @TestTemplate
    @UseDataProvider("rates")
    @DisplayName("Нажатие на кнопку '>' и выбор тарифа из карусели")
    @Description("Тест на работу кнопки '>' у карусели и корректный переход по кнопке 'Подробнее' у тарифа из карусели")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldClickForwardButtonSelectCorrectRateFromCarousel(String rateName, String rateTitle) {
        getVisibleRate(rateName);
        forwardButtonClick(rateName);
        carouselRateButtonClick();
        checkPageTitle(rateTitle);
        CommonSteps.returnBack();
    }

    @TestTemplate
    @UseDataProvider("rates")
    @DisplayName("Нажатие на кнопку '<' и выбор тарифа из карусели")
    @Description("Тест на работу кнопки '<' у карусели и корректный переход по кнопке 'Подробнее' у тарифа из карусели")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldClickBackwardButtonSelectCorrectRateFromCarousel(String rateName, String rateTitle) {
        getVisibleRate(rateName);
        backwardButtonClick(rateName);
        carouselRateButtonClick();
        checkPageTitle(rateTitle);
        CommonSteps.returnBack();
    }
}
