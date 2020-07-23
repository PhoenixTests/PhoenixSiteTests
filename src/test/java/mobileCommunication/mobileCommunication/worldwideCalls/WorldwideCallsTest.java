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
@Epic("������ ������� �� ����� ����")
@Feature("������������ ������ ������/����������� ����, ��������� �����������")
public class WorldwideCallsTest {
    private static WorldwideCallsPage worldwideCallsPage;
    private static Random random;

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
        String countryName = worldwideCallsPage.getCountryName().getText();
        worldwideCallsPage.getCountryName().click();
        return countryName;
    }

    @Step("���������, ��� ������� �������� ������ {countryName}")
    public void checkCountryName(String countryName) {
        worldwideCallsPage.setCountries(0);
        assertEquals(countryName, worldwideCallsPage.getCountryName().getText());
    }

    @Step("������� ��� ��������� �� ������")
    public String selectCodeOperator() {
        return worldwideCallsPage.selectOperatorCode(random);
    }

    @Step("���������, ��� ������������ ����������� �� ���� ��������� {operatorCode}")
    public void checkTariffing(String operatorCode) {
        assertEquals("��� ������ �� �����:", worldwideCallsPage.getCostInfo().get(0).getText());
        assertTrue(Pattern.matches(String.format("^\\+%s[\\sX]+", operatorCode), worldwideCallsPage.getCostInfo().get(1).getText()));
        assertEquals("C�������� ����� ������ ��������� ��������:", worldwideCallsPage.getCostInfo().get(2).getText());
        assertNotNull(worldwideCallsPage.getCostInfo().get(3).getText());
        assertEquals("* ����������� ����������.", worldwideCallsPage.getCostInfoSub().getText());
    }

    @Step("���������, ��� ��� ������� �� '...' � ���������� ������ ����� ��������� ���������� � ����������� �������� ")
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

    @Step("������ �� ����� {letter} �� ��������")
    public void clickOnLetter(String letter) {
        worldwideCallsPage.getLetter().click();
    }

    @Step("������ �� ������ � ������ ��� ���������")
    public void clickOnLetterButton() {
        worldwideCallsPage.getLetterButton().click();
    }

    @DataProvider
    public static Object[] unitNames() {
        return new Object[] {
                "����������",
                "������"
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
    @DisplayName("�������� ����������� � ���������� ������/����������� ����")
    @Description("����� ������/����������� ���� �� ������ � ���������� ����������/������, �������� �����������")
    @Severity(SeverityLevel.MINOR)
    public void shouldRedirectPopularCountryOrOthers(String unitName) {
        openWorldWidePageUnit(unitName);
        defaultOperationsWithCountry(random.nextInt(worldwideCallsPage.getCountries().size()), unitName);
    }

    @Test
    @DisplayName("�������� ����������� � ������ � ���������� '��� ������'")
    @Description("����� ������ �� ������, �������� �����������")
    @Severity(SeverityLevel.MINOR)
    public void shouldRedirectToTheCountry() {
        openWorldWidePageUnit("��� ������");
        worldwideCallsPage.setLetter(random.nextInt(worldwideCallsPage.getAlphabet().size()));
        clickOnLetter(worldwideCallsPage.getLetter().getText());
        defaultOperationsWithCountry(0, "��� ������");
        clickOnLetterButton();
    }
}
