package mobileCommunication.mobileCommunication.mobileScratch;

import common.CommonPageActions;
import common.CommonSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Раздел «Пополнение счета»")
@Feature("Тестирование просмотра информации о способах пополнения счета по городам")
public class MobileScratchInfoTest extends DefaultMobileScratchSteps{

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
            address = mobileScratchPage.getByScratchCitySalePoints()
                    .get(random.nextInt(byScratchCitySalePointsSize))
                    .getText();
        }
        return address;
    }

    @Step("Ввести в поле адрес '{address}'")
    public void inputAddress(String address) {
        mobileScratchPage.getUserAddress().setValue(address);
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

    @BeforeAll
    public static void openPage() {
        random = new Random();
        mobileScratchPage = new MobileScratchPage();
        CommonSteps.openPage(MobileScratchPage.getPageName(), MobileScratchPage.getURL());
    }

    @Test
    @DisplayName("Пополнить в почтовом отделении")
    @Description("Просмотр отделений «Почты Донбасса», где принимаются платежи «ФЕНИКС»")
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
    @DisplayName("Пополнить с помощью скретч-карты")
    @Description("Просмотр точек продажи скретч-карт, поиск по карте")
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
