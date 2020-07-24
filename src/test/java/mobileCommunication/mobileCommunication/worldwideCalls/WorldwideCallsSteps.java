package mobileCommunication.mobileCommunication.worldwideCalls;

import io.qameta.allure.Step;

import java.util.Random;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public abstract class WorldwideCallsSteps {
    protected static WorldwideCallsPage worldwideCallsPage;
    protected static Random random;

    @Step("������ �� ��������� �{unitName}�")
    public void openWorldWidePageUnit(String unitName) {
        worldwideCallsPage.getTypeElement(unitName).click();
    }

    @Step("���������, ��� ����� ������� '��� ���������:'")
    public void checkOperatorCodeLabel() {
        assertTrue(worldwideCallsPage.setOperatorCodeLabel().exists());
    }

    @Step("���������, ��� ���� ���������� ������ �� ������� ����� ��������� � ��� ���� ���� �� ���� ���")
    public void checkOperatorCodesList() {
        worldwideCallsPage.setOperatorCodeList();
        worldwideCallsPage.checkOperatorCodesList();
    }

    @Step("������ �� {country} ������/����������� ���� �� ������ �{unitName}�")
    public String clickOnCountry(int country, String unitName) {
        worldwideCallsPage.setCountries(country);
        String countryName = worldwideCallsPage.getCountryNameText();
        worldwideCallsPage.getCountryName().click();
        return countryName;
    }

    @Step("���������, ��� ������� �������� ������ {countryName}")
    public void checkCountryName(String countryName) {
        worldwideCallsPage.setCountries(0);
        assertEquals(countryName, worldwideCallsPage.getCountryNameText());
    }

    @Step("������� ��� ��������� �� ������")
    public String selectCodeOperator() {
        int operatorCodeListSize = worldwideCallsPage.getOperatorCodeList().size();
        return worldwideCallsPage.selectOperatorCode(random.nextInt(operatorCodeListSize));
    }

    @Step("���������, ��� ������������ ����������� �� ���� ��������� {operatorCode}")
    public void checkTariffing(String operatorCode) {
        assertEquals("��� ������ �� �����:", worldwideCallsPage.getLabelFromCostInfo(0));
        assertTrue(checkIsStringMatches(operatorCode));
        assertEquals("C�������� ����� ������ ��������� ��������:", worldwideCallsPage.getLabelFromCostInfo(2));
        assertNotNull(worldwideCallsPage.getLabelFromCostInfo(3));
        assertEquals("* ����������� ����������.", worldwideCallsPage.getCostInfoSubText());
    }

    private boolean checkIsStringMatches(String operatorCode) {
        String pattern = String.format("^\\+%s[\\sX]+", operatorCode);
        return Pattern.matches(pattern, worldwideCallsPage.getLabelFromCostInfo(1));
    }

    @Step("���������, ��� ��� ������� �� '...' � ���������� ������ ����� ��������� ���������� � ����������� �������� ")
    public void checkIsTariffingInfoHidden() {
        worldwideCallsPage.setOperatorCodeListThreeDots();
        worldwideCallsPage.getOperatorCodeListThreeDots().click();
        worldwideCallsPage.checkTariffingInfoHidden();
    }

    @Step("������ �� ����� {letter} �� ��������")
    public void clickOnLetter(String letter) {
        worldwideCallsPage.getLetter().click();
    }

    @Step("������ �� ������ � ������ ��� ���������")
    public void clickOnLetterButton() {
        worldwideCallsPage.getLetterButton().click();
    }
}
