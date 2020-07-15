package mobileCommunication.mobileCommunication.rates;

import common.CommonPageActions;
import common.CommonSteps;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Epic("�������������� �����������")
@Feature("������������ ������ '������ ������ ���������' � '�������� �����'")
public class AdditionalFeaturesTest {
    private static RatePage ratePage;

    @Step("������ �� ������ '������ ������ ���������' ����� ������ ��������")
    public void askOperatorButtonClick() {
        ratePage.getAskOperatorButton().click();
    }

    @Step("������ �� ������ '�������� �����' ������ ��������")
    public void feedbackButtonClick() {
        ratePage.getFeedbackButton().click();
    }

    @Step("������� ������� ������� ��������")
    public void closeWindow() {
        CommonPageActions.closeCurrentTabAndReturnToMain();
    }

    @BeforeAll
    public static void openRatePage() {
        ratePage=new RatePage();
        CommonSteps.openPage(RatePage.getPageName(), RatePage.getURL());
    }

    @Test
    @Order(1)
    @DisplayName("���������� � ��������� �� �������")
    @Description("������� �� �������� ������� ��������, ����� ��������� � ������-���� � ����������")
    @Severity(SeverityLevel.NORMAL)
    public void shouldRedirectToAskOperatorPage() {
        askOperatorButtonClick();
        CommonSteps.switchToAnotherWindow(1);
        CommonSteps.checkIsCorrectUrl("https://my.phoenix-dnr.ru/login");
        closeWindow();
    }

    @Test
    @Order(2)
    @DisplayName("������� �� �������� �������")
    @Description("������� �� ��������, ��� ����� ��������� � �������� ������")
    @Severity(SeverityLevel.NORMAL)
    public void shouldRedirectToFeedbackPage() {
        feedbackButtonClick();
        CommonSteps.checkIsCorrectUrl("http://phoenix-dnr.ru/contacts-questionnaire.php");
        CommonSteps.returnBack();
    }
}
