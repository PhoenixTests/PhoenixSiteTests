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
@Feature("Тестирование поиска по карте")
public class SearchByMapTest extends DefaultMobileScratchSteps{
    @Step("Нажать на кнопку 'Найти ближайшую точку продажи'")
    public void clickOnClosestSalePointButton() {
        mobileScratchPage.getFindClosestSalePointButton().click();
    }

    @Step("Выбрать из списка точку продаж скретч-карт")
    public String selectFromSalePointsTable() {
        mobileScratchPage.setByScratchAddresses(mobileScratchPage.getByScratchSelectCity().getSelectedValue());
        mobileScratchPage.setByScratchCitySalePoints();
        return mobileScratchPage.getByScratchCitySalePoints()
                .get(random.nextInt(mobileScratchPage.getByScratchCitySalePoints().size()))
                .getText();
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
        random=new Random();
        mobileScratchPage=new MobileScratchPage();
        CommonSteps.openPage(MobileScratchPage.getPageName(), MobileScratchPage.getURL());
    }

    @Test
    @DisplayName("Поиск ближайшей точки продажи скретч-карт")
    @Description("Тест на корректную работу всплывающего окна с картой")
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
