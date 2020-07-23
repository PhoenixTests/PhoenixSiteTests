package search;

import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.DataProviderExtension;
import com.tngtech.junit.dataprovider.UseDataProvider;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import common.CommonPageActions;
import common.CommonSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@Epic("Поиск")
@ExtendWith(UseDataProviderExtension.class)
@ExtendWith(DataProviderExtension.class)
public class SearchTest extends SearchSteps {

    @DataProvider
    public static Object[][] textForFind() {
        return new Object[][]{
                {"мобильная связь", "http://phoenix-dnr.ru/"},
                {"тарифы", "http://phoenix-dnr.ru/rates/"},
                {"услуги", "http://phoenix-dnr.ru/mobile-services.php"},
                {"подключение", "http://phoenix-dnr.ru/mobile-connection.php"},
                {"дополнительные смс", "http://phoenix-dnr.ru/sms-pack.php"},
                {"пункты продаж", "http://phoenix-dnr.ru/mobile-sale.php"},
                { "где купить", "http://phoenix-dnr.ru/mobile-sale.php"},
                { "позвонить в россию", "http://phoenix-dnr.ru/mobile-intl.php"},
                { "новая зеландия", "http://phoenix-dnr.ru/world_cost/?type=all"},
                { "германия", "http://phoenix-dnr.ru/world_cost/?type=all"},
                { "интернет", "http://phoenix-dnr.ru/mobile-iconnection.php"},
                { "дополнительный мобильный интернет", "http://phoenix-dnr.ru/mobile-internet-packs.php"},
                { "настройка интернета", "http://phoenix-dnr.ru/mobile-settings.php"},
                { "карта", "http://phoenix-dnr.ru/mobile-4g.php"},
                { "купить телефон", "http://phoenix-dnr.ru/catalog.php?category=1"},
                { "купить модем", "http://phoenix-dnr.ru/catalog.php?category=2"},
                { "купить маршрутизатор", "http://phoenix-dnr.ru/catalog.php?category=3"},
                { "где пополнить счёт", "http://phoenix-dnr.ru/mobile-scratch.php"},
                { "проверить счёт", "http://phoenix-dnr.ru/mobile-check.php"},
                { "домашний интернет", "http://phoenix-dnr.ru/internet.php"},
                { "домашний интернет тарифы", "http://phoenix-dnr.ru/internet-rates.php"},
                { "акции", "http://phoenix-dnr.ru/internet-actions.php"},
                { "настройка компьютера", "http://phoenix-dnr.ru/internet-setup.php"},
                { "настройка роутера", "http://phoenix-dnr.ru/internet-router.php"},
                { "настройка iptv", "http://phoenix-dnr.ru/internet-iptv.php"},
                { "список каналов", "http://phoenix-dnr.ru/internet-channels.php"},
                { "домашний интернет пополнение счёта", "http://phoenix-dnr.ru/internet-refill.php"},
                { "для бизнеса", "http://phoenix-dnr.ru/business-main.php"},
                { "смс рассылка", "http://phoenix-dnr.ru/business-sms-dispatch.php"},
                { "интернет для бизнеса", "http://phoenix-dnr.ru/business-internet.php"},
                { "IP VPN", "http://phoenix-dnr.ru/business-ip-vpn.php"},
                { "sip", "http://phoenix-dnr.ru/business-sip-telephony.php"},
                {"ISDN PRI", "http://phoenix-dnr.ru/business-sip-telephony.php"},
                {"корпоративная связь", "http://phoenix-dnr.ru/business-corporative.php"},
                {"мобильный интернет для бизнеса", "http://phoenix-dnr.ru/business-mobile-internet.php"},
                {"телеметрия", "http://phoenix-dnr.ru/business-mobile-telemetry.php"},
                {"короткий номер", "http://phoenix-dnr.ru/business-short-number.php"},
                {"новости", "http://phoenix-dnr.ru/news.php"},
                {"о нас", "http://phoenix-dnr.ru/about.php"},
                {"контакты", "http://phoenix-dnr.ru/contacts.php"},
        };
    }

    @BeforeAll
    public static void openPage() {
        CommonSteps.openPage(SearchPage.getNamePage(), SearchPage.getURL());
        searchPage = new SearchPage();
    }

    @BeforeEach
    public void clickOnSearchButton() {
        SearchSteps.clickSearch();
        SearchSteps.clickInput();
    }

    @Attachment(value = "Скриншот результатов поиска", type = "image/png")
    public byte[] takeScreenshot() {
        return CommonPageActions.takeScreenshot();
    }

    @TestTemplate
    @UseDataProvider("textForFind")
    @Feature("Поиск разделов")
    @DisplayName("Поиск")
    @Description("Ввод строки в поисковик с целью поиска раздела")
    @Severity(SeverityLevel.CRITICAL)
    public void findTest(String textFind, String URL) {
        SearchSteps.textInput(textFind + "\n");
        SearchSteps.checkLink(URL);
        takeScreenshot();
    }

    @Test
    @Feature("Статистика")
    @DisplayName("Просмотр статистики")
    @Description("Открытие статистики и переход по ссылке")
    @Severity(SeverityLevel.NORMAL)
    public void statistics() {
        SearchSteps.textInput(textForFind()[39][0].toString() + "\n");
        SearchSteps.checkLink(textForFind()[39][1].toString());
        SearchSteps.clickStatistics();
        CommonSteps.switchToAnotherWindow(1);
        SearchSteps.clickLinkWebsite();
        SearchSteps.closeWebsite();
        CommonSteps.switchToAnotherWindow(1);
        SearchSteps.checkWebsite();
        CommonPageActions.closeCurrentTab();
    }

    @AfterAll
    public static void closePage() {
        CommonPageActions.closeCurrentTab();
    }

//    @Test
//    @Feature("Статистика")
//    @DisplayName("Закрытие статистики")
//    @Description("Открытие статистики и закрытие кнопкой")
//    public void statisticsClose() {
//        CommonSteps.openPage(SearchPage.getNamePage(), SearchPage.getURL());
//        SearchSteps.clickSearch(searchPage);
//        SearchSteps.clickInput(searchPage);
//        SearchSteps.textInput(searchPage,textForFind()[39][0].toString() + "\n");
//        SearchSteps.checkLink(searchPage, textForFind()[39][1].toString());
//        SearchSteps.clickStatistics(searchPage);
//        CommonSteps.switchToAnotherWindow(1);
//        SearchSteps.closeWebsite(searchPage);
//    }

}
