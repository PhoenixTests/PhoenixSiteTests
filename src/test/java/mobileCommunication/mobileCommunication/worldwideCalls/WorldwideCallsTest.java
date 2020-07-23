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
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(UseDataProviderExtension.class)
@ExtendWith(DataProviderExtension.class)
@Epic("Раздел «Звонки по всему миру»")
@Feature("Тестирование выбора страны/спутниковой сети, просмотра тарификации")
public class WorldwideCallsTest {
    private static WorldwideCallsPage worldwideCallsPage;
    private static Random random;

    @Step("Нажать на подраздел «{unitName}»")
    public void openWorldWidePageUnit(String unitName) {
        worldwideCallsPage.getTypeElement(unitName).click();
    }

    @Step("Проверить, что видна надпись 'Код оператора:'")
    public void checkOperatorCodeLabel() {
        assertTrue(worldwideCallsPage.setOperatorCodeLabel().exists());
    }

    @Step("Проверить, что есть выпадающий список со списком кодов оператора и там есть хотя бы один код")
    public void checkOperatorCodesList() {
        worldwideCallsPage.setOperatorCodeList();
        worldwideCallsPage.checkOperatorCodesList();
    }

    @Step("Нажать на {country} страну/спутниковую сеть из списка «{unitName}»")
    public String clickOnCountry(int country, String unitName) {
        worldwideCallsPage.setCountries(country);
        String countryName = worldwideCallsPage.getCountryName().getText();
        worldwideCallsPage.getCountryName().click();
        return countryName;
    }

    @Step("Проверить, что открыта страница страны {countryName}")
    public void checkCountryName(String countryName) {
        worldwideCallsPage.setCountries(0);
        assertEquals(countryName, worldwideCallsPage.getCountryName().getText());
    }

    @Step("Выбрать код оператора из списка")
    public String selectCodeOperator() {
        return worldwideCallsPage.selectOperatorCode(random);
    }

    @Step("Проверить, что отображается тарификация по коду оператора {operatorCode}")
    public void checkTariffing(String operatorCode) {
        assertEquals("При звонке на номер:", worldwideCallsPage.getCostInfo().get(0).getText());
        assertTrue(Pattern.matches(String.format("^\\+%s[\\sX]+", operatorCode), worldwideCallsPage.getCostInfo().get(1).getText()));
        assertEquals("Cтоимость одной минуты разговора составит:", worldwideCallsPage.getCostInfo().get(2).getText());
        assertNotNull(worldwideCallsPage.getCostInfo().get(3).getText());
        assertEquals("* Тарификация поминутная.", worldwideCallsPage.getCostInfoSub().getText());
    }

    @Step("Проверить, что при нажатии на '...' в выпадающем списке кодов оператора информация о тарификации скроется ")
    public void checkIsTariffingInfoHidden() {
        worldwideCallsPage.setOperatorCodeListThreeDots();
        worldwideCallsPage.getOperatorCodeListThreeDots().click();
        worldwideCallsPage.checkTariffingInfoHidden();
    }

    @BeforeAll
    public static void openPage() {
        random=new Random();
        worldwideCallsPage=new WorldwideCallsPage();
        CommonSteps.openPage(WorldwideCallsPage.getPageName(), WorldwideCallsPage.getURL());
    }

    @Step("Нажать на букву {letter} из алфавита")
    public void clickOnLetter(String letter) {
        worldwideCallsPage.getLetter().click();
    }

    @Step("Нажать на кнопку с буквой под алфавитом")
    public void clickOnLetterButton() {
        worldwideCallsPage.getLetterButton().click();
    }

    @DataProvider
    public static Object[] unitNames() {
        return new Object[] {
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
        defaultOperationsWithCountry(random.nextInt(worldwideCallsPage.getCountries().size()), unitName);
    }

    @Test
    @DisplayName("Просмотр тарификации у страны в подразделе 'Все страны'")
    @Description("Выбор страны из списка, просмотр тарификации")
    @Severity(SeverityLevel.MINOR)
    public void shouldRedirectToTheCountry() {
        openWorldWidePageUnit("Все страны");
        worldwideCallsPage.setLetter(random.nextInt(worldwideCallsPage.getAlphabet().size()));
        clickOnLetter(worldwideCallsPage.getLetter().getText());
        defaultOperationsWithCountry(0, "Все страны");
        clickOnLetterButton();
    }
}
