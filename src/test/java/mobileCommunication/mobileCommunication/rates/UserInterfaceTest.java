package mobileCommunication.mobileCommunication.rates;

import common.CommonPageActions;
import common.CommonSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;

@Epic("���������������� ���������")
@Feature("������������ ����������������� ����������")
public class UserInterfaceTest {
    private static RatePage ratePage;

    @Step("������� �� �������� � ����������� �������")
    public void rateInfo() {
        ratePage.getRateInfo().click();
    }

    @Step("������� ����������� ����")
    public void closePopup() {
        ratePage.getClosePopupWindow().click();
    }

    @Step("�������� �������� �������� ������� ������ ���� �� {calls}")
    public void setCalls(int calls) {
        ratePage.setCallsRangeVal(calls);
    }

    @Step("���������, ��������� �� ����������� ���� � ������� '�������'")
    public void isPopupVisible() {
        ratePage.getRateInfo().shouldNotBe(visible);
    }

    @Attachment(value = "�������� �������� ������� ����� �������� �� ���������� ��������", type = "image/png")
    public byte[] takeScreenshot() {
        return CommonPageActions.takeScreenshot();
    }

    /*@BeforeAll
    public static void openPage() {
        ratePage= new RatePage();
        CommonSteps.openPage(ratePage.getPageName(), ratePage.getURL());
    }*/

    //������ ���������� �� ������� ��� �� @BeforeAll, ��� ���� �� �������� �������, �� �.�. ���
    //���� ��������� �����, ������� �� �������� ������, ��������� �����, �������� �������� � ����� ������ ��
    //������ ������� ������,������� �� �����, �� ���� shouldClosePopupWindowWithSelectedRate ����������
    //� ������ �� ���� ��� ���� ����������
    @BeforeEach
    public void defaultSteps() {
        ratePage= new RatePage();
        CommonSteps.openPage(RatePage.getPageName(), RatePage.getURL());
        DefaultRateSteps.changeRanges(ratePage,0,50,0,25);
        DefaultRateSteps.getRateClick(ratePage);
    }

    @Test
    @DisplayName("������� �� ���������� ��������")
    @Description("������� �� ���������� �������� ����� ������� ������ � �������� �� �������� ������")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldReturnToThePreviousSelectedRate() {
        rateInfo();
        CommonSteps.returnBack();
        setCalls(50);
        isPopupVisible();
    }

    @Test
    @DisplayName("������� ���� � �������")
    @Description("�������� ������������ ���� � ����������� �������")
    @Severity(SeverityLevel.NORMAL)
    public void shouldClosePopupWindowWithSelectedRate() {
        closePopup();
    }
}
