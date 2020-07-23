package mobileCommunication.mobileCommunication.mobileScratch;

import common.CommonSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("������ ����������� �����")
@Feature("������������ ��������� ���������� � �������� ���������� ����� �� �������")
public class MobileScratchInfoTest extends DefaultMobileScratchSteps{

    @Step("������ �� ������ ���������� ����� ���������� � �������� ���������")
    public void clickOnRefillByPost() {
        mobileScratchPage.getRefillByPost().click();
    }

    @Step("������ �� ������ '^' ������ �� ���������� � �������� ���������")
    public void clickOnCloseRefillByPostInfoButton() {
        mobileScratchPage.getCloseRefillByPostInfoButton().click();
    }

    @Step("������ �� ������ '^' ������ �� ���������� � ������� ������-������")
    public void clickOnCloseRefillByScratchInfoButton() {
        mobileScratchPage.getCloseRefillByScratchInfoButton().click();
    }

    @Step("���������, ��� ����� ���� ���������� � ���������� ����� � �������� ����������")
    public void checkIsRefillByPostInfoDisplayed() {
        assertTrue(mobileScratchPage.checkIsDisplayed(mobileScratchPage.getRefillByPostInfo()));
    }

    @Step("���������, ��� ����� ���� ���������� � ���������� ����� � ������� ������-�����")
    public void checkIsRefillByScratchInfoDisplayed() {
        assertTrue(mobileScratchPage.checkIsDisplayed(mobileScratchPage.getRefillByScratchInfo()));
    }

    @Step("������� ����� �� ����������� ������ (���������� ����� � �������� ���������)")
    public void selectCityByPost() {
        mobileScratchPage.getByPostSelectCity().selectOption(random.nextInt(mobileScratchPage.getByPostCities().size()));
    }

    @Step("���������, ��� ������������ ������ �������� ��������� � ������ {city}")
    public void checkIsCityByPostDisplayed(String city) {
        mobileScratchPage.setByPostAddresses(mobileScratchPage.getByPostSelectCity().getSelectedValue());
        assertTrue(mobileScratchPage.checkIsDisplayed(mobileScratchPage.getByPostAddresses()));
    }

    @Step("���������, ��� ������������ ������ ����� ������� ������-���� � ������ {city}")
    public void checkIsCityByScratchDisplayed(String city) {
        mobileScratchPage.setByScratchAddresses(mobileScratchPage.getByScratchSelectCity().getSelectedValue());
        assertTrue(mobileScratchPage.checkIsDisplayed(mobileScratchPage.getByScratchAddresses()));
    }

    @Step("���������, ��� ���� ���������� � ���������� ����� � �������� ���������� �����")
    public void checkIsRefillByPostInfoNotDisplayed() {
        assertTrue(mobileScratchPage.checkIsNotDisplayed(mobileScratchPage.getRefillByPostInfo()));
    }

    @Step("���������, ��� ���� ���������� � ���������� ����� � ������� ������-����")
    public void checkIsRefillByScratchInfoNotDisplayed() {
        assertTrue(mobileScratchPage.checkIsNotDisplayed(mobileScratchPage.getRefillByScratchInfo()));
    }

    @BeforeAll
    public static void openPage() {
        random=new Random();
        mobileScratchPage=new MobileScratchPage();
        CommonSteps.openPage(MobileScratchPage.getPageName(), MobileScratchPage.getURL());
    }

    @Test
    @DisplayName("��������� � �������� ���������")
    @Description("�������� ��������� ������ ��������, ��� ����������� ������� ������ѻ")
    @Severity(SeverityLevel.MINOR)
    public void shouldOpenRefillByPostInfo() {
        clickOnRefillByPost();
        checkIsRefillByPostInfoDisplayed();
        selectCityByPost();
        checkIsCityByPostDisplayed(mobileScratchPage.getByPostSelectCity().getSelectedText());
        clickOnCloseRefillByPostInfoButton();
        checkIsRefillByPostInfoNotDisplayed();
    }

    @Test
    @DisplayName("��������� � ������� ������-�����")
    @Description("�������� ����� ������� ������-����")
    @Severity(SeverityLevel.MINOR)
    public void shouldOpenRefillByScratchInfo() {
        clickOnRefillByScratch();
        checkIsRefillByScratchInfoDisplayed();
        selectCityByScratch();
        checkIsCityByScratchDisplayed(mobileScratchPage.getByScratchSelectCity().getSelectedText());
        clickOnCloseRefillByScratchInfoButton();
        checkIsRefillByScratchInfoNotDisplayed();
    }

}
