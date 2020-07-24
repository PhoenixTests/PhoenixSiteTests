package mobileCommunication.mobileCommunication.mobileScratch;

import common.CommonSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

@Epic("Раздел «Пополнение счета»")
@Feature("Тестирование просмотра информации о способах пополнения счета по городам")
public class MobileScratchTest extends MobileScratchSteps {

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
