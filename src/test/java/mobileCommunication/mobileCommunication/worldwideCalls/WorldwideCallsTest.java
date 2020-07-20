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
    public static void openWorldWidePageUnit(WorldwideCallsPage worldwideCallsPage, String unitName) {
        worldwideCallsPage.getTypeElement(unitName).click();
    }

    @Step("Проверить, что видна надпись 'Код оператора:'")
    public static void checkOperatorCodeLabel(WorldwideCallsPage worldwideCallsPage) {
        assertTrue(worldwideCallsPage.setOperatorCodeLabel().exists());
    }

    @Step("Проверить, что есть выпадающий список со списком кодов оператора и там есть хотя бы один код")
    public static void checkOperatorCodesList(WorldwideCallsPage worldwideCallsPage) {
        worldwideCallsPage.setOperatorCodeList();
        worldwideCallsPage.checkOperatorCodesList();
    }

    @Step("Нажать на {++country} страну/спутниковую сеть из списка «{unitName}»")
    public static String clickOnCountry(WorldwideCallsPage worldwideCallsPage, int country,  String unitName) {
        worldwideCallsPage.setCountries(country);
        String countryName = worldwideCallsPage.getCountryName().getText();
        worldwideCallsPage.getCountryName().click();
        return countryName;
    }

    @Step("Проверить, что открыта страница страны {countryName}")
    public static void checkCountryName(WorldwideCallsPage worldwideCallsPage, String countryName) {
        worldwideCallsPage.setCountries(0);
        assertEquals(countryName, worldwideCallsPage.getCountryName().getText());
    }

    @Step("Выбрать код оператора из списка")
    public static String selectCodeOperator(WorldwideCallsPage worldwideCallsPage) {
        return worldwideCallsPage.selectOperatorCode(random);
    }

    @Step("Проверить, что отображается тарификация по коду оператора {operatorCode}")
    public static void checkTariffing (WorldwideCallsPage worldwideCallsPage, String operatorCode) {
        assertEquals("При звонке на номер:", worldwideCallsPage.getCostInfo().get(0).getText());
        assertTrue(Pattern.matches(String.format("^\\+%s[\\sX]+", operatorCode), worldwideCallsPage.getCostInfo().get(1).getText()));
        assertEquals("Cтоимость одной минуты разговора составит:", worldwideCallsPage.getCostInfo().get(2).getText());
        assertNotNull(worldwideCallsPage.getCostInfo().get(3).getText());
        assertEquals("* Тарификация поминутная.", worldwideCallsPage.getCostInfoSub().getText());
    }

    @Step("Проверить, что при нажатии на '...' в выпадающем списке кодов оператора информация о тарификации скроется ")
    public static void checkIsTariffingInfoHidden(WorldwideCallsPage worldwideCallsPage) {
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
//        worldwideCallsPage.getAlphabet().get(letterNum).click();
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
        String countryName=clickOnCountry(worldwideCallsPage, country,
                unitName);
        checkCountryName(worldwideCallsPage, countryName);
        checkOperatorCodeLabel(worldwideCallsPage);
        checkOperatorCodesList(worldwideCallsPage);
        String operatorCode = selectCodeOperator(worldwideCallsPage);
        checkTariffing(worldwideCallsPage, operatorCode);
        checkIsTariffingInfoHidden(worldwideCallsPage);
    }

    @TestTemplate
    @UseDataProvider("unitNames")
    @DisplayName("Просмотр тарификации у популярной страны/спутниковой сети")
    @Description("Выбор страны/спутниковой сети из списка в подразделе Популярные/Прочее, просмотр тарификации")
    @Severity(SeverityLevel.MINOR)
    public void shouldRedirectPopularCountryOrOthers(String unitName) {
        openWorldWidePageUnit(worldwideCallsPage, unitName);
        defaultOperationsWithCountry(random.nextInt(worldwideCallsPage.getCountries().size()), unitName);
    }

    @Test
    @DisplayName("Просмотр тарификации у страны в подразделе 'Все страны'")
    @Description("Выбор страны из списка, просмотр тарификации")
    @Severity(SeverityLevel.MINOR)
    public void shouldRedirectToTheCountry() {
        openWorldWidePageUnit(worldwideCallsPage, "Все страны");
        worldwideCallsPage.setLetter(random.nextInt(worldwideCallsPage.getAlphabet().size()));
        clickOnLetter(worldwideCallsPage.getLetter().getText());
        defaultOperationsWithCountry(0, "Все страны");
        clickOnLetterButton();
    }
}
