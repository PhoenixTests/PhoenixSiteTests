package mobileCommunication.mobileCommunication.worldwideCalls;

import io.qameta.allure.Step;

import java.util.Random;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public abstract class WorldwideCallsSteps {
    protected static WorldwideCallsPage worldwideCallsPage;
    protected static Random random;

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
        String countryName = worldwideCallsPage.getCountryNameText();
        worldwideCallsPage.getCountryName().click();
        return countryName;
    }

    @Step("Проверить, что открыта страница страны {countryName}")
    public void checkCountryName(String countryName) {
        worldwideCallsPage.setCountries(0);
        assertEquals(countryName, worldwideCallsPage.getCountryNameText());
    }

    @Step("Выбрать код оператора из списка")
    public String selectCodeOperator() {
        int operatorCodeListSize = worldwideCallsPage.getOperatorCodeList().size();
        return worldwideCallsPage.selectOperatorCode(random.nextInt(operatorCodeListSize));
    }

    @Step("Проверить, что отображается тарификация по коду оператора {operatorCode}")
    public void checkTariffing(String operatorCode) {
        assertEquals("При звонке на номер:", worldwideCallsPage.getLabelFromCostInfo(0));
        assertTrue(checkIsStringMatches(operatorCode));
        assertEquals("Cтоимость одной минуты разговора составит:", worldwideCallsPage.getLabelFromCostInfo(2));
        assertNotNull(worldwideCallsPage.getLabelFromCostInfo(3));
        assertEquals("* Тарификация поминутная.", worldwideCallsPage.getCostInfoSubText());
    }

    private boolean checkIsStringMatches(String operatorCode) {
        String pattern = String.format("^\\+%s[\\sX]+", operatorCode);
        return Pattern.matches(pattern, worldwideCallsPage.getLabelFromCostInfo(1));
    }

    @Step("Проверить, что при нажатии на '...' в выпадающем списке кодов оператора информация о тарификации скроется ")
    public void checkIsTariffingInfoHidden() {
        worldwideCallsPage.setOperatorCodeListThreeDots();
        worldwideCallsPage.getOperatorCodeListThreeDots().click();
        worldwideCallsPage.checkTariffingInfoHidden();
    }

    @Step("Нажать на букву {letter} из алфавита")
    public void clickOnLetter(String letter) {
        worldwideCallsPage.getLetter().click();
    }

    @Step("Нажать на кнопку с буквой под алфавитом")
    public void clickOnLetterButton() {
        worldwideCallsPage.getLetterButton().click();
    }
}
