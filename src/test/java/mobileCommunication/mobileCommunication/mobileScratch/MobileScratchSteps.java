package mobileCommunication.mobileCommunication.mobileScratch;

import common.CommonPageActions;
import io.qameta.allure.Step;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class MobileScratchSteps {
    protected static MobileScratchPage mobileScratchPage;
    protected static Random random;

    @Step("������ �� ������ ���������� ����� ���������� � ������� ������-������")
    public void clickOnRefillByScratch() {
        mobileScratchPage.getRefillByScratch().click();
    }

    @Step("������� ����� �� ����������� ������ (���������� � ������� ������-����)")
    public void selectCityByScratch() {
        int maxByScratchSalePointsSize = mobileScratchPage.getByScratchSalePoints().size();
        mobileScratchPage.byScratchSelectCity(random.nextInt(maxByScratchSalePointsSize));
    }

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
        int byPostCitiesSize = mobileScratchPage.getByPostCities().size();
        mobileScratchPage.byPostSelectCity(random.nextInt(byPostCitiesSize));
    }

    @Step("���������, ��� ������������ ������ �������� ��������� � ������ {city}")
    public void checkIsCityByPostDisplayed(String city) {
        mobileScratchPage.setByPostAddresses();
        assertTrue(mobileScratchPage.checkIsDisplayed(mobileScratchPage.getByPostAddresses()));
    }

    @Step("���������, ��� ������������ ������ ����� ������� ������-���� � ������ {city}")
    public void checkIsCityByScratchDisplayed(String city) {
        mobileScratchPage.setByScratchAddresses();
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

    @Step("������ �� ������ '����� ��������� ����� �������'")
    public void clickOnClosestSalePointButton() {
        mobileScratchPage.getFindClosestSalePointButton().click();
    }

    @Step("������� �� ������ ����� ������ ������-����")
    public String selectFromSalePointsTable() {
        mobileScratchPage.setByScratchAddresses();
        mobileScratchPage.setByScratchCitySalePoints();
        String address = "�����:";
        int byScratchCitySalePointsSize = mobileScratchPage.getByScratchCitySalePoints().size();
        while (address.equals("�����:")) {
            address = mobileScratchPage.getByScratchCitySalePointName(random.nextInt(byScratchCitySalePointsSize));
        }
        return address;
    }

    @Step("������ � ���� ����� '{address}'")
    public void inputAddress(String address) {
        mobileScratchPage.setUserAddressValue(address);
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
}
