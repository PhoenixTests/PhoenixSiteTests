package mobileCommunication.mobileCommunication.mobileScratch;

import common.CommonSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

@Epic("������ ����������� �����")
@Feature("������������ ��������� ���������� � �������� ���������� ����� �� �������")
public class MobileScratchTest extends MobileScratchSteps {

    @BeforeAll
    public static void openPage() {
        random = new Random();
        mobileScratchPage = new MobileScratchPage();
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
    @Description("�������� ����� ������� ������-����, ����� �� �����")
    @Severity(SeverityLevel.MINOR)
    public void shouldOpenRefillByScratchInfo() {
        clickOnRefillByScratch();
        checkIsRefillByScratchInfoDisplayed();
        selectCityByScratch();
        checkIsCityByScratchDisplayed(mobileScratchPage.getByScratchSelectCity().getSelectedText());
        String address = selectFromSalePointsTable();
        clickOnClosestSalePointButton();
        checkIsUserAddressTextFieldDisplayed();
        inputAddress(address);
        clickOnFindOnMapButton();
        checkIsMapDisplayed();
        closeMapWindow();
        checkIsMapWindowClosed();
        clickOnCloseRefillByScratchInfoButton();
        checkIsRefillByScratchInfoNotDisplayed();
    }

}
