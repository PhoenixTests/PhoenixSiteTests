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

@ExtendWith(UseDataProviderExtension.class)
@ExtendWith(DataProviderExtension.class)
@Epic("������ ������� �� ����� ����")
@Feature("������������ ������ ������/����������� ����, ��������� �����������")
public class WorldwideCallsTest extends DefaultWorldwideCallsSteps{
    private static WorldwideCallsPage worldwideCallsPage;

    @BeforeAll
    public static void openPage() {
        worldwideCallsPage=new WorldwideCallsPage();
        CommonSteps.openPage(WorldwideCallsPage.getPageName(), WorldwideCallsPage.getURL());
    }

    @Step("������ �� ����� {letter} �� ��������")
    public void clickOnLetter(String letter, int letterNum) {
        worldwideCallsPage.getAlphabet().get(letterNum).click();
    }

    @Step("������ �� ������ � ������ ��� ���������")
    public void clickOnLetterButton() {
        worldwideCallsPage.getLetter().click();
    }

    @DataProvider
    public static Object[] unitNames() {
        return new Object[] {
                "����������",
                "������"
        };
    }

    private void defaultOperationsWithCountry(int country, String unitName) {
        String countryName=DefaultWorldwideCallsSteps.clickOnCountry(worldwideCallsPage, country,
                unitName);
        DefaultWorldwideCallsSteps.checkCountryName(worldwideCallsPage, countryName);
        DefaultWorldwideCallsSteps.checkOperatorCodeLabel(worldwideCallsPage);
        DefaultWorldwideCallsSteps.checkOperatorCodesList(worldwideCallsPage);
        String operatorCode = DefaultWorldwideCallsSteps.selectCodeOperator(worldwideCallsPage);
        DefaultWorldwideCallsSteps.checkTariffing(worldwideCallsPage, operatorCode);
        DefaultWorldwideCallsSteps.checkIsTariffingInfoHidden(worldwideCallsPage);
    }

    @TestTemplate
    @UseDataProvider("unitNames")
    @DisplayName("�������� ����������� � ���������� ������/����������� ����")
    @Description("����� ������/����������� ���� �� ������ � ���������� ����������/������, �������� �����������")
    @Severity(SeverityLevel.MINOR)
    public void shouldRedirectPopularCountryOrOthers(String unitName) {
        DefaultWorldwideCallsSteps.openWorldWidePageUnit(worldwideCallsPage, unitName);
        int countriesCount=worldwideCallsPage.getCountries().size();
        for (int country=0; country<countriesCount; country++) {
            defaultOperationsWithCountry(country,unitName);
            CommonSteps.returnBack();
        }
    }

    @Test
    @DisplayName("�������� ����������� � ������ � ���������� '��� ������'")
    @Description("����� ������ �� ������, �������� �����������")
    @Severity(SeverityLevel.MINOR)
    public void shouldRedirectToTheCountry() {
        DefaultWorldwideCallsSteps.openWorldWidePageUnit(worldwideCallsPage, "��� ������");
        int alphabetSize = worldwideCallsPage.getAlphabet().size();
        for (int letter = 0; letter < alphabetSize; letter++) {
            clickOnLetter(worldwideCallsPage.getAlphabet().get(letter).getText(), letter);
            defaultOperationsWithCountry(0, "��� ������");
            clickOnLetterButton();
        }
    }

//    @Test
//    @DisplayName("����� ������ � ��������� '����������'")
//    @Description("������������ ����� ������ �� ������ ���� �����, ����� ��� ��������� � ���������� '����������'")
//    @Severity(SeverityLevel.MINOR)
//    public void shouldAddToPopularCountries() {
//        String selectedCountry=null;
//        boolean isAdded=false;
//        DefaultWorldwideCallsSteps.openWorldWidePageUnit(worldwideCallsPage, "��� ������");
//        worldwideCallsPage.getAlphabet().get(0).click();
//        for(int i=0; i<40; i++) {
//            selectedCountry = DefaultWorldwideCallsSteps.clickOnCountry(worldwideCallsPage, 1, "��� ������");
//            worldwideCallsPage.getLetter().click();
//        }
//        DefaultWorldwideCallsSteps.openWorldWidePageUnit(worldwideCallsPage, "����������");
//        int countriesCount=worldwideCallsPage.getCountries().size();
//        for (int country=0; country<countriesCount; country++) {
//            worldwideCallsPage.setCountries(country);
//            isAdded=worldwideCallsPage.checkCountryName(selectedCountry);
//        }
//        assertTrue(isAdded);
//    }

//    @Test
//    @DisplayName("�������� ����������� � ������ � ���������� '��� ������'")
//    @Description("����� ������ �� ������, �������� �����������")
//    @Severity(SeverityLevel.MINOR)
//    public void shouldRedirectToTheCountry() {
//        DefaultWorldwideCallsSteps.openWorldWidePageUnit(worldwideCallsPage, "��� ������");
//        int alphabetSize=worldwideCallsPage.getAlphabet().size();
//        for(int letter=0; letter<alphabetSize; letter++) {
//            worldwideCallsPage.getAlphabet().get(letter).click();
//            int countriesCount=worldwideCallsPage.getCountries().size();
//            for (int country=0; country<countriesCount; country++) {
//                defaultOperationsWithCountry(country,"��� ������");
//                worldwideCallsPage.getLetter().click();
//            }
//        }
//    }
}
