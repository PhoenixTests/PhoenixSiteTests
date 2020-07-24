package mobileCommunication.equipment;

import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.DataProviderExtension;
import com.tngtech.junit.dataprovider.UseDataProvider;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import common.CommonSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(UseDataProviderExtension.class)
@ExtendWith(DataProviderExtension.class)
@Epic("������������")
public class EquipmentTest {

    @DataProvider
    public static Object[][] URL() {
        return new Object[][] {
                { "��������� ��������", "http://phoenix-dnr.ru/catalog.php?category=1"} ,
                { "3G/4G ������",  "http://phoenix-dnr.ru/catalog.php?category=2"},
                { "��������������/IPTV ���������",  "http://phoenix-dnr.ru/catalog.php?category=3"},

        };
    }

    @TestTemplate
    @UseDataProvider("URL")
    @Feature("������")
    @DisplayName("�������� �������")
    @Description("�������� ���������� � ������ ������")
    @Severity(SeverityLevel.NORMAL)
    public void informationAboutProduct(String namePage, String URL) {
        CommonSteps.openPage(namePage, URL);
        EquipmentSteps.closeAdvertising();
        EquipmentSteps.setAllInformation();
        EquipmentSteps.cycleCatalog();
    }

    @Test
    @Feature("�������")
    @DisplayName("�������� ������")
    @Description("������������ �� ��������� ������� \"������������\" � ������� ������ � �������� ���������� � ������ �������")
    @Severity(SeverityLevel.NORMAL)
    public void slideDepartment(){
        CommonSteps.openPage("��������� ��������", "http://phoenix-dnr.ru/catalog.php?category=1");
        EquipmentSteps.closeAdvertising();
        EquipmentSteps.clickModem("http://phoenix-dnr.ru/catalog.php?category=2");
        EquipmentSteps.whereBuy();
        EquipmentSteps.clickRouter("http://phoenix-dnr.ru/catalog.php?category=3");
        EquipmentSteps.whereBuy();
        EquipmentSteps.clickSmartphone("http://phoenix-dnr.ru/catalog.php?category=1");
        EquipmentSteps.whereBuy();
    }

}
