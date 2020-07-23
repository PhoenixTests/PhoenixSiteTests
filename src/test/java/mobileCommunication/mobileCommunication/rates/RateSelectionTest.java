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
@Epic("������")
@Feature("������������ ������� ������ � �������� �������")
public class RateSelectionTest extends DefaultRateSteps {

    @Step("�������� �� ���������� ������������ ������ � {rateName}")
    public void checkRateName(String rateName) {
        assertEquals(rateName, ratePage.getRateName().getText());
    }

    @Step("�������� �� �������� ����������� ����� ������������ ������ � {ratePrice}")
    public void checkRatePrice(int ratePrice) {
        assertEquals(ratePrice, Integer.parseInt(ratePage.getRatePrice().getText()));
    }

    @Step("������ �� ������ '���������'")
    public void rateNameButtonClick() {
        ratePage.getRateInfo().click();
    }

    @Step("��������� ��������� �������� �� ���������� � {rateTitle}")
    public void checkPageTitle(String rateTitle) {
        assertEquals(rateTitle, ratePage.getPageTitle());
    }

    @Step("���������, ����� �� �� ������ ������ ����� {rateName} � �������� �������")
    public void getVisibleRate(String rateName) {
        CommonPageActions.scrollIntoView(ratePage.getActiveElements().last());
        ratePage.isRateVisible(rateName);
    }

    @Step("������� �������� ������ �� ���� �������, ���� �� ����� ����� ����� {rateName}")
    public void forwardButtonClick(String rateName) {
        while(!ratePage.getCarouselRate().exists()) {
            ratePage.getCarouselForwardButton().click();
        }
    }

    @Step("������� �������� ����� �� ���� �������, ���� �� ����� ����� ����� {rateName}")
    public void backwardButtonClick(String rateName) {
        while(!ratePage.getCarouselRate().exists())
            ratePage.getCarouselBackButton().click();
    }

    @Step("������ �� ������ '���������' ������ ������ � ��������")
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
                {0, 99, 0, 99, "���� �������", 300, "����� ���� �����ֻ"},
                {99, 99, 99, 0, "�����������", 230, "����� ����������ɻ"},
                {99, 50, 50, 0, "��������", 150, "����� �������ɻ"},
                {0, 50, 0, 0, "��������� ��������", 95, "�������� �������� �����"},
                {0, 0, 0, 0, "���������", 50, "�������� �����"}
        };
    }

    @DataProvider
    public static Object[][] rates() {
        return new Object[][]{
                {"���� �����ֻ", "����� ���� �����ֻ"},
                {"�������ɻ", "����� �������ɻ"},
                {"������ͻ", "����� ������ͻ"},
                { "����������ɻ", "����� ����������ɻ"},
                {"���������ɻ", "��������� �����"},
                {"������ɻ", "������ �����"},
                {"��������� �������һ", "�������� �������� �����"},
                {"��������ɻ", "�������� �����"}
        };
    }

    @TestTemplate
    @UseDataProvider("rateInfo")
    @DisplayName("������ ����������� ������")
    @Description("���� �� ������������ �������, � ������������ �� ���������� ���������")
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

    //TODO ��� ����� � ��������� ���������� �� ����, ����� �� ���������� � ����, ����� ������� �����������

    @TestTemplate
    @UseDataProvider("rates")
    @DisplayName("������� �� ������ '>' � ����� ������ �� ��������")
    @Description("���� �� ������ ������ '>' � �������� � ���������� ������� �� ������ '���������' � ������ �� ��������")
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
    @DisplayName("������� �� ������ '<' � ����� ������ �� ��������")
    @Description("���� �� ������ ������ '<' � �������� � ���������� ������� �� ������ '���������' � ������ �� ��������")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldClickBackwardButtonSelectCorrectRateFromCarousel(String rateName, String rateTitle) {
        getVisibleRate(rateName);
        backwardButtonClick(rateName);
        carouselRateButtonClick();
        checkPageTitle(rateTitle);
        CommonSteps.returnBack();
    }
}
