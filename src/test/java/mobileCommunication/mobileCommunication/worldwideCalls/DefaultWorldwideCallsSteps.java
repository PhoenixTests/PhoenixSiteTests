package mobileCommunication.mobileCommunication.worldwideCalls;

import io.qameta.allure.Step;

import java.util.Random;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultWorldwideCallsSteps {
    private static Random random=new Random();

    @Step("������ �� ��������� �{unitName}�")
    public static void openWorldWidePageUnit(WorldwideCallsPage worldwideCallsPage, String unitName) {
        worldwideCallsPage.getTypeElement(unitName).click();
    }

    @Step("���������, ��� ����� ������� '��� ���������:'")
    public static void checkOperatorCodeLabel(WorldwideCallsPage worldwideCallsPage) {
        assertTrue(worldwideCallsPage.setOperatorCodeLabel().exists());
    }

    @Step("���������, ��� ���� ���������� ������ �� ������� ����� ��������� � ��� ���� ���� �� ���� ���")
    public static void checkOperatorCodesList(WorldwideCallsPage worldwideCallsPage) {
        worldwideCallsPage.setOperatorCodeList();
        worldwideCallsPage.checkOperatorCodesList();
    }

    @Step("������ �� {++country} ������/����������� ���� �� ������ �{unitName}�")
    public static String clickOnCountry(WorldwideCallsPage worldwideCallsPage, int country,  String unitName) {
        worldwideCallsPage.setCountries(country);
        String countryName = worldwideCallsPage.getCountryName().getText();
        worldwideCallsPage.getCountryName().click();
        return countryName;
    }

    @Step("���������, ��� ������� �������� ������ {countryName}")
    public static void checkCountryName(WorldwideCallsPage worldwideCallsPage, String countryName) {
        worldwideCallsPage.setCountries(0);
        assertEquals(countryName, worldwideCallsPage.getCountryName().getText());
    }

    @Step("������� ��� ��������� �� ������")
    public static String selectCodeOperator(WorldwideCallsPage worldwideCallsPage) {
        return worldwideCallsPage.selectOperatorCode(random);
    }

    @Step("���������, ��� ������������ ����������� �� ���� ��������� {operatorCode}")
    public static void checkTariffing (WorldwideCallsPage worldwideCallsPage, String operatorCode) {
        assertEquals("��� ������ �� �����:", worldwideCallsPage.getCostInfo().get(0).getText());
        assertTrue(Pattern.matches(String.format("^\\+%s[\\sX]+", operatorCode), worldwideCallsPage.getCostInfo().get(1).getText()));
        assertEquals("C�������� ����� ������ ��������� ��������:", worldwideCallsPage.getCostInfo().get(2).getText());
        assertNotNull(worldwideCallsPage.getCostInfo().get(3).getText());
        assertEquals("* ����������� ����������.", worldwideCallsPage.getCostInfoSub().getText());
    }

    @Step("���������, ��� ��� ������� �� '...' � ���������� ������ ����� ��������� ���������� � ����������� �������� ")
    public static void checkIsTariffingInfoHidden(WorldwideCallsPage worldwideCallsPage) {
        worldwideCallsPage.setOperatorCodeListThreeDots();
        worldwideCallsPage.getOperatorCodeListThreeDots().click();
        worldwideCallsPage.checkTariffingInfoHidden();
    }
}
