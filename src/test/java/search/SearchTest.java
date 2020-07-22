package search;

import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.DataProviderExtension;
import com.tngtech.junit.dataprovider.UseDataProvider;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import common.CommonSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;


@Epic("�����")
@ExtendWith(UseDataProviderExtension.class)
@ExtendWith(DataProviderExtension.class)
public class SearchTest {

    SearchPage searchPage = new SearchPage();

    @DataProvider
    public static Object[][] textForFind() {
        return new Object[][] {
                { "��������� �����", "http://phoenix-dnr.ru/"},
                { "������", "http://phoenix-dnr.ru/rates/"},
                { "������", "http://phoenix-dnr.ru/mobile-services.php"},
                { "�����������", "http://phoenix-dnr.ru/mobile-connection.php"},
                { "�������������� ���", "http://phoenix-dnr.ru/sms-pack.php"},
                { "������ ������", "http://phoenix-dnr.ru/mobile-sale.php"},
                { "��� ������", "http://phoenix-dnr.ru/mobile-sale.php"},
                { "��������� � ������", "http://phoenix-dnr.ru/mobile-intl.php"},
                { "����� ��������", "http://phoenix-dnr.ru/world_cost/?type=all"},
                { "��������", "http://phoenix-dnr.ru/world_cost/?type=all"},
                { "��������", "http://phoenix-dnr.ru/mobile-iconnection.php"},
                { "�������������� ��������� ��������", "http://phoenix-dnr.ru/mobile-internet-packs.php"},
                { "��������� ���������", "http://phoenix-dnr.ru/mobile-settings.php"},
                { "�����", "http://phoenix-dnr.ru/mobile-4g.php"},
                { "������ �������", "http://phoenix-dnr.ru/catalog.php?category=1"},
                { "������ �����", "http://phoenix-dnr.ru/catalog.php?category=2"},
                { "������ �������������", "http://phoenix-dnr.ru/catalog.php?category=3"},
                { "��� ��������� ����", "http://phoenix-dnr.ru/mobile-scratch.php"},
                { "��������� ����", "http://phoenix-dnr.ru/mobile-check.php"},
                { "�������� ��������", "http://phoenix-dnr.ru/internet.php"},
                { "�������� �������� ������", "http://phoenix-dnr.ru/internet-rates.php"},
                { "�����", "http://phoenix-dnr.ru/internet-actions.php"},
                { "��������� ����������", "http://phoenix-dnr.ru/internet-setup.php"},
                { "��������� �������", "http://phoenix-dnr.ru/internet-router.php"},
                { "��������� iptv", "http://phoenix-dnr.ru/internet-iptv.php"},
                { "������ �������", "http://phoenix-dnr.ru/internet-channels.php"},
                { "�������� �������� ���������� �����", "http://phoenix-dnr.ru/internet-refill.php"},
                { "��� �������", "http://phoenix-dnr.ru/business-main.php"},
                { "��� ��������", "http://phoenix-dnr.ru/business-sms-dispatch.php"},
                { "�������� ��� �������", "http://phoenix-dnr.ru/business-internet.php"},
                { "IP VPN", "http://phoenix-dnr.ru/business-ip-vpn.php"},
                { "sip", "http://phoenix-dnr.ru/business-sip-telephony.php"},
                { "ISDN PRI", "http://phoenix-dnr.ru/business-sip-telephony.php"},
                { "������������� �����", "http://phoenix-dnr.ru/business-corporative.php"},
                { "��������� �������� ��� �������", "http://phoenix-dnr.ru/business-mobile-internet.php"},
                { "����������", "http://phoenix-dnr.ru/business-mobile-telemetry.php"},
                { "�������� �����", "http://phoenix-dnr.ru/business-short-number.php"},
                { "�������", "http://phoenix-dnr.ru/news.php"},
                { "� ���", "http://phoenix-dnr.ru/about.php"},
                { "��������", "http://phoenix-dnr.ru/contacts.php"},
        };
    }

    @TestTemplate
    @UseDataProvider("textForFind")
    @Feature("����� ��������")
    @DisplayName("�����")
    @Description("���� ������ � ��������� � ����� ������ �������")
    @Severity(SeverityLevel.CRITICAL)
    public void findTest(String textFind, String URL) {
        CommonSteps.openPage(searchPage.getNamePage(), searchPage.getURL());
        SearchSteps.clickSearch(searchPage);
        SearchSteps.clickInput(searchPage);
        SearchSteps.textInput(searchPage,textFind + "\n");
        SearchSteps.checkLink(searchPage, URL);
        SearchSteps.clickLink(searchPage);
    }

    @Test
    @Feature("����������")
    @DisplayName("�������� ����������")
    @Description("�������� ���������� � ������� �� ������")
    @Severity(SeverityLevel.NORMAL)
    public void statistics() {
        CommonSteps.openPage(searchPage.getNamePage(), searchPage.getURL());
        SearchSteps.clickSearch(searchPage);
        SearchSteps.clickInput(searchPage);
        SearchSteps.textInput(searchPage,textForFind()[39][0].toString() + "\n");
        SearchSteps.checkLink(searchPage,textForFind()[39][1].toString());
        SearchSteps.clickStatistics(searchPage);
        CommonSteps.switchToAnotherWindow(1);
        SearchSteps.clickLinkWebsite(searchPage);
        CommonSteps.switchToAnotherWindow(2);
        SearchSteps.checkWebsite(searchPage);
    }

    @Test
    @Feature("����������")
    @DisplayName("�������� ����������")
    @Description("�������� ���������� � �������� �������")
    @Severity(SeverityLevel.NORMAL)
    public void statisticsClose() {
        CommonSteps.openPage(searchPage.getNamePage(), searchPage.getURL());
        SearchSteps.clickSearch(searchPage);
        SearchSteps.clickInput(searchPage);
        SearchSteps.textInput(searchPage,textForFind()[39][0].toString() + "\n");
        SearchSteps.checkLink(searchPage, textForFind()[39][1].toString());
        SearchSteps.clickStatistics(searchPage);
        CommonSteps.switchToAnotherWindow(1);
        SearchSteps.closeWebsite(searchPage);
    }

}
