package mobileCommunication.mobileCommunication.mobileScratch;

import common.CommonPageActions;
import io.qameta.allure.Step;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class MobileScratchSteps {
    protected static MobileScratchPage mobileScratchPage;
    protected static Random random;

    @Step("Нажать на способ пополнения счета «Пополнить с помощью скретч-карты»")
    public void clickOnRefillByScratch() {
        mobileScratchPage.getRefillByScratch().click();
    }

    @Step("Выбрать город из выпадающего списке (пополнение с помощью скретч-карт)")
    public void selectCityByScratch() {
        int maxByScratchSalePointsSize = mobileScratchPage.getByScratchSalePoints().size();
        mobileScratchPage.byScratchSelectCity(random.nextInt(maxByScratchSalePointsSize));
    }

    @Step("Нажать на способ пополнения счета «Пополнить в почтовом отделении»")
    public void clickOnRefillByPost() {
        mobileScratchPage.getRefillByPost().click();
    }

    @Step("Нажать на кнопку '^' справа от «Пополнить в почтовом отделении»")
    public void clickOnCloseRefillByPostInfoButton() {
        mobileScratchPage.getCloseRefillByPostInfoButton().click();
    }

    @Step("Нажать на кнопку '^' справа от «Пополнить с помощью скретч-карты»")
    public void clickOnCloseRefillByScratchInfoButton() {
        mobileScratchPage.getCloseRefillByScratchInfoButton().click();
    }

    @Step("Проверить, что виден блок информации о пополнении счета в почтовых отделениях")
    public void checkIsRefillByPostInfoDisplayed() {
        assertTrue(mobileScratchPage.checkIsDisplayed(mobileScratchPage.getRefillByPostInfo()));
    }

    @Step("Проверить, что виден блок информации о пополнении счета с помощью скретч-карты")
    public void checkIsRefillByScratchInfoDisplayed() {
        assertTrue(mobileScratchPage.checkIsDisplayed(mobileScratchPage.getRefillByScratchInfo()));
    }

    @Step("Выбрать город из выпадающего списке (пополнение счета в почтовом отделении)")
    public void selectCityByPost() {
        int byPostCitiesSize = mobileScratchPage.getByPostCities().size();
        mobileScratchPage.byPostSelectCity(random.nextInt(byPostCitiesSize));
    }

    @Step("Проверить, что отобразились адреса почтовых отделений в городе {city}")
    public void checkIsCityByPostDisplayed(String city) {
        mobileScratchPage.setByPostAddresses();
        assertTrue(mobileScratchPage.checkIsDisplayed(mobileScratchPage.getByPostAddresses()));
    }

    @Step("Проверить, что отобразились адреса точек продажи скретч-карт в городе {city}")
    public void checkIsCityByScratchDisplayed(String city) {
        mobileScratchPage.setByScratchAddresses();
        assertTrue(mobileScratchPage.checkIsDisplayed(mobileScratchPage.getByScratchAddresses()));
    }

    @Step("Проверить, что блок информации о пополнении счета в почтовых отделениях скрыт")
    public void checkIsRefillByPostInfoNotDisplayed() {
        assertTrue(mobileScratchPage.checkIsNotDisplayed(mobileScratchPage.getRefillByPostInfo()));
    }

    @Step("Проверить, что блок информации о пополнении счета с помощью скретч-карт")
    public void checkIsRefillByScratchInfoNotDisplayed() {
        assertTrue(mobileScratchPage.checkIsNotDisplayed(mobileScratchPage.getRefillByScratchInfo()));
    }

    @Step("Нажать на кнопку 'Найти ближайшую точку продажи'")
    public void clickOnClosestSalePointButton() {
        mobileScratchPage.getFindClosestSalePointButton().click();
    }

    @Step("Выбрать из списка точку продаж скретч-карт")
    public String selectFromSalePointsTable() {
        mobileScratchPage.setByScratchAddresses();
        mobileScratchPage.setByScratchCitySalePoints();
        String address = "Адрес:";
        int byScratchCitySalePointsSize = mobileScratchPage.getByScratchCitySalePoints().size();
        while (address.equals("Адрес:")) {
            address = mobileScratchPage.getByScratchCitySalePointName(random.nextInt(byScratchCitySalePointsSize));
        }
        return address;
    }

    @Step("Ввести в поле адрес '{address}'")
    public void inputAddress(String address) {
        mobileScratchPage.setUserAddressValue(address);
    }

    @Step("Нажать на кнопку 'Найти ближайшую точку продажи'")
    public void checkIsUserAddressTextFieldDisplayed() {
        CommonPageActions.switchToFrame(mobileScratchPage.getMapFrame());
        mobileScratchPage.checkIsDisplayed(mobileScratchPage.getUserAddress());
    }

    @Step("Нажать на кнопку 'Найти'")
    public void clickOnFindOnMapButton() {
        mobileScratchPage.getFindOnMapButton().click();
    }

    @Step("Проверить, что появилась карта")
    public void checkIsMapDisplayed() {
        assertTrue(mobileScratchPage.checkIsDisplayed(mobileScratchPage.getMap()));
    }

    @Step("Нажать на 'x' чтобы закрыть окно с картой")
    public void closeMapWindow() {
        CommonPageActions.exitFromFrame();
        mobileScratchPage.getMapCloser().click();
    }

    @Step("Проверить, что окно с картой закрылось")
    public void checkIsMapWindowClosed() {
        assertTrue(mobileScratchPage.checkIsNotDisplayed(mobileScratchPage.getMapFrame()));
    }
}
