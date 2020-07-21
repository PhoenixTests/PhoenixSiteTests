package internet.setup;

import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.DataProviderExtension;
import com.tngtech.junit.dataprovider.UseDataProvider;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import common.CommonSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(UseDataProviderExtension.class)
@ExtendWith(DataProviderExtension.class)
@Epic("������ '��������� ���������'")
@Feature("������������ ������, �������������� ��� ����������� ���������� �� ��������� ��������� �� ��������� ��")
public class SetupPageTest {
    private SetupPage setupPage;

    @Step("������ �� ������ '{buttonName}'")
    public void clickOnOSButton(String buttonName, String hrefValue) {
        setupPage.getOSButton(hrefValue).click();
    }

    @Step("���������, ��� ������������ ����������, � ��������� ������� ���� �� {OS}")
    public void checkIsInstructionDisplayed(String OS) {
        assertTrue(setupPage.getInstructionTitle().getText().contains(OS));
    }

    @DataProvider
    public static Object[][] setup() {
        return new Object[][]{
                {"?setup=1", "Windows XP"},
                {"?setup=2", "Windows Vista"},
                {"?setup=3", "Windows 7"},
                {"?setup=4", "Windows 8"},
                {"?setup=5", "Windows 10"},
                {"?setup=6", "Mac OS"},
                {"?setup=7", "Ubuntu"},
        };
    }

    @TestTemplate
    @UseDataProvider("setup")
    @DisplayName("��������� ��������� �� ��������� ��")
    @Description("���� �� ����������������� ������ ��� ����������� ���������� �� ��������� ���������")
    @Severity(SeverityLevel.MINOR)
    public void shouldShowSetupInstruction(String url, String OS) {
        setupPage=new SetupPage();
        CommonSteps.openPage(SetupPage.getPageName(),SetupPage.getURL());
        clickOnOSButton(OS, url);
        checkIsInstructionDisplayed(OS);
    }

}