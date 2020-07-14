import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;

@Epic("���������������� ���������")
@Feature("������������ ����������������� ����������")
public class UserInterfaceTest {
    private RatePage ratePage;

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
        return ratePage.takeScreenshot();
    }

    @BeforeEach
    public void defaultSteps() {
        ratePage= DefaultSteps.openPage();
        DefaultSteps.changeRanges(ratePage,0,50,0,25);
        DefaultSteps.getRateClick(ratePage);
    }

    @Test
    @DisplayName("������� �� ���������� ��������")
    @Description("������� �� ���������� �������� ����� ������� ������ � �������� �� �������� ������")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldReturnToThePreviousSelectedRate() {
        rateInfo();
        DefaultSteps.returnBack(ratePage);
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
