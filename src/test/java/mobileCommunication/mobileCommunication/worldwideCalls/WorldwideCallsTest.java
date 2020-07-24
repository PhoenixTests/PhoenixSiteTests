package mobileCommunication.mobileCommunication.worldwideCalls;

import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.DataProviderExtension;
import com.tngtech.junit.dataprovider.UseDataProvider;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import common.CommonSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Random;

@ExtendWith(UseDataProviderExtension.class)
@ExtendWith(DataProviderExtension.class)
@Epic("Раздел «Звонки по всему миру»")
@Feature("Тестирование выбора страны/спутниковой сети, просмотра тарификации")
public class WorldwideCallsTest extends WorldwideCallsSteps {
    @BeforeAll
    public static void openPage() {
        random = new Random();
        worldwideCallsPage = new WorldwideCallsPage();
        CommonSteps.openPage(WorldwideCallsPage.getPageName(), WorldwideCallsPage.getURL());
    }

    @DataProvider
    public static Object[] unitNames() {
        return new Object[]{
                "Популярные",
                "Прочее"
        };
    }

    private void defaultOperationsWithCountry(int country, String unitName) {
        String countryName = clickOnCountry(country, unitName);
        checkCountryName(countryName);
        checkOperatorCodeLabel();
        checkOperatorCodesList();
        String operatorCode = selectCodeOperator();
        checkTariffing(operatorCode);
        checkIsTariffingInfoHidden();
    }

    @TestTemplate
    @UseDataProvider("unitNames")
    @DisplayName("Просмотр тарификации у популярной страны/спутниковой сети")
    @Description("Выбор страны/спутниковой сети из списка в подразделе Популярные/Прочее, просмотр тарификации")
    @Severity(SeverityLevel.MINOR)
    public void shouldRedirectPopularCountryOrOthers(String unitName) {
        openWorldWidePageUnit(unitName);
        int countriesSize = worldwideCallsPage.getCountries().size();
        defaultOperationsWithCountry(random.nextInt(countriesSize), unitName);
    }

    @Test
    @DisplayName("Просмотр тарификации у страны в подразделе 'Все страны'")
    @Description("Выбор страны из списка, просмотр тарификации")
    @Severity(SeverityLevel.MINOR)
    public void shouldRedirectToTheCountry() {
        openWorldWidePageUnit("Все страны");
        int alphabetSize = worldwideCallsPage.getAlphabet().size();
        worldwideCallsPage.setLetter(random.nextInt(alphabetSize));
        clickOnLetter(worldwideCallsPage.getLetterText());
        defaultOperationsWithCountry(0, "Все страны");
        clickOnLetterButton();
    }
}
