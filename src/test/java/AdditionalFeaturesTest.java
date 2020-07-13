import io.qameta.allure.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("�������������� �����������")
@Feature("������������ ������ '������ ������ ���������' � '�������� �����'")
public class AdditionalFeaturesTest {
    private RatePage ratePage;

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
        ratePage.closeCurrentTabAndReturnToMain();
    }

    @BeforeEach
    public void openRatePage() {
        ratePage= DefaultSteps.openPage();
    }

    @Test
    @DisplayName("���������� � ��������� �� �������")
    @Description("������� �� �������� ������� ��������, ����� ��������� � ������-���� � ����������")
    @Severity(SeverityLevel.NORMAL)
    public void shouldRedirectToAskOperatorPage() {
        askOperatorButtonClick();
        DefaultSteps.switchToAnotherWindow(ratePage, 1);
        DefaultSteps.checkIsCorrectUrl(ratePage,"https://my.phoenix-dnr.ru/login");
        closeWindow();
    }

    @Test
    @DisplayName("������� �� �������� �������")
    @Description("������� �� ��������, ��� ����� ��������� � �������� ������")
    @Severity(SeverityLevel.NORMAL)
    public void shouldRedirectToFeedbackPage() {
        feedbackButtonClick();
        DefaultSteps.checkIsCorrectUrl(ratePage, "http://phoenix-dnr.ru/contacts-questionnaire.php");
    }
}
