package mobileCommunication.mobileCommunication.worldwideCalls;

import io.qameta.allure.Step;

import java.util.Random;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultWorldwideCallsSteps {
    private static Random random=new Random();

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
}
