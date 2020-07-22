package mobileCommunication.mobileCommunication.services;

import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.DataProviderExtension;
import com.tngtech.junit.dataprovider.UseDataProvider;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import common.CommonSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(UseDataProviderExtension.class)
@ExtendWith(DataProviderExtension.class)
public class ServiceTest {

    private ServicePage servicePage = new ServicePage();

    @DataProvider
    public static Object[][] dataLabel() {
        return new Object[][] {
                {"������ ���� �������", "body_0y", "spoiler_0y"},
                {"������ �Telegram - ��� �������", "body_0", "spoiler_0"},
                {"������ ���������� �������", "body_1", "spoiler_1"},
                {"������ ����������� ������", "body_2", "spoiler_2"},
                {"������ ���������� ���", "body_3", "spoiler_3"},
                {"������ ��������� �����", "body_4", "spoiler_4"},
                {"������ ��������������� ������", "body_5", "spoiler_5"},
                {"������ �������������� ������", "body_6", "spoiler_6"},
                {"������ ��������� ������", "body_7", "spoiler_7"},
                {"������ �������ͻ", "body_8", "spoiler_8"}
            };
    }

    @Step("������� �� �����: {0}")
    public void clickLabel(String nameLabel)
    {
        servicePage.setLabel(nameLabel);
        servicePage.getLabel().click();
    }

    @Step("��������, ��������� �� ������ �������� ��� ������")
    public boolean checkBodyOpen(String idBody)
    {
       return servicePage.setBody(idBody).isDisplayed();
    }

    @Step("��������, ��������� �� ������ �������� ��� ������")
    public boolean checkBodyClose(String idBody)
    {
        return servicePage.waitBody(idBody, 1000).isDisplayed();
    }

    @Step("������� �� ������ \"^\" ��� �������� ���������� �� ������")
    public void clickButton(String idButton)
    {
        servicePage.setButton(idButton).scrollTo();
        servicePage.getButton().click();
    }

    @Epic("������")
    @TestTemplate
    @UseDataProvider("dataLabel")
    @DisplayName("�������� ���������� �� ������")
    @Description("���� �� �������� ���������� �� ������ ������ �������� �� ��������")
    @Severity(SeverityLevel.NORMAL)
    public void label(String nameLabel, String idBody, String idButton){
        CommonSteps.openPage(ServicePage.getPageName(), ServicePage.getURL());
        clickLabel(nameLabel);
        assertTrue(checkBodyOpen(idBody));
        clickButton(idButton);
        assertFalse(checkBodyClose(idBody));
    }

}
