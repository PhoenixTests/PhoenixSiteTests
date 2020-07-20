package mobileCommunication.mobileCommunication.mobileScratch;

import common.CommonPageActions;
import common.CommonSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("������ ����������� �����")
@Feature("������������ ������ �� �����")
public class SearchByMapTest extends DefaultMobileScratchSteps{
    @Step("������ �� ������ '����� ��������� ����� �������'")
    public void clickOnClosestSalePointButton() {
        mobileScratchPage.getFindClosestSalePointButton().click();
    }

    @Step("������� �� ������ ����� ������ ������-����")
    public String selectFromSalePointsTable() {
        mobileScratchPage.setByScratchAddresses(mobileScratchPage.getByScratchSelectCity().getSelectedValue());
        mobileScratchPage.setByScratchCitySalePoints();
        return mobileScratchPage.getByScratchCitySalePoints()
                .get(random.nextInt(mobileScratchPage.getByScratchCitySalePoints().size()))
                .getText();
    }

    @Step("������ � ���� ����� '{address}'")
    public void inputAddress(String address) {
        mobileScratchPage.getUserAddress().setValue(address);
    }

    @Step("������ �� ������ '����� ��������� ����� �������'")
    public void checkIsUserAddressTextFieldDisplayed() {
        CommonPageActions.switchToFrame(mobileScratchPage.getMapFrame());
        mobileScratchPage.checkIsDisplayed(mobileScratchPage.getUserAddress());
    }

    @Step("������ �� ������ '�����'")
    public void clickOnFindOnMapButton() {
        mobileScratchPage.getFindOnMapButton().click();
    }

    @Step("���������, ��� ��������� �����")
    public void checkIsMapDisplayed() {
        assertTrue(mobileScratchPage.checkIsDisplayed(mobileScratchPage.getMap()));
    }

    @Step("������ �� 'x' ����� ������� ���� � ������")
    public void closeMapWindow() {
        CommonPageActions.exitFromFrame();
        mobileScratchPage.getMapCloser().click();
    }

    @Step("���������, ��� ���� � ������ ���������")
    public void checkIsMapWindowClosed() {
        assertTrue(mobileScratchPage.checkIsNotDisplayed(mobileScratchPage.getMapFrame()));
    }

    @BeforeAll
    public static void openPage() {
        random=new Random();
        mobileScratchPage=new MobileScratchPage();
        CommonSteps.openPage(MobileScratchPage.getPageName(), MobileScratchPage.getURL());
    }

    @Test
    @DisplayName("����� ��������� ����� ������� ������-����")
    @Description("���� �� ���������� ������ ������������ ���� � ������")
    @Severity(SeverityLevel.MINOR)
    public void shouldSearchByMapWindow() {
        clickOnRefillByScratch();
        selectCityByScratch();
        String address=selectFromSalePointsTable();
        clickOnClosestSalePointButton();
        checkIsUserAddressTextFieldDisplayed();
        inputAddress(address);
        clickOnFindOnMapButton();
        checkIsMapDisplayed();
        closeMapWindow();
        checkIsMapWindowClosed();
    }
}
