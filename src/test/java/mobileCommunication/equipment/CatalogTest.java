package mobileCommunication.equipment;

import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.DataProviderExtension;
import com.tngtech.junit.dataprovider.UseDataProvider;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import common.CommonPageActions;
import common.CommonSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(UseDataProviderExtension.class)
@ExtendWith(DataProviderExtension.class)
@Epic("������������")
public class CatalogTest {

    EquipmentPage equipmentPage = new EquipmentPage();

    public void cycleCatalog() {
        for(int i = 0; i < equipmentPage.getAllButtonInformation().size(); i++) {
            openInformation(i);
            CommonSteps.switchFrame();
            equipmentPage.setButtonNext();
            equipmentPage.setButtonBack();
            if (equipmentPage.getButtonNext().isDisplayed()){
                leafImg();
                backImg();
            }
            closeInformation(i);
        }
    }

    public void whereBuy() {
        clickBuy();
        clickBuyClose();
    }

    @Step("������� ��� ������ \"���������\"")
    public void getCatalog() {
        equipmentPage.setAllButtonInformation();

    }

    @Step("��������� ��������� ���������� � �������� {0}")
    public void closeInformation(int index) {
        CommonSteps.exitFrame();
        equipmentPage.setButtonCLoseProduct();
        equipmentPage.getButtonCLoseProduct().click();
    }

    @Step("������� ��������� ��������")
    public void leafImg() {
        equipmentPage.setImgActive();
        String firstImg = equipmentPage.getImgActive().innerHtml();
        equipmentPage.getButtonNext().click();
        equipmentPage.setImgActiveNew();
        String secondImg = equipmentPage.getImgActiveNew().innerHtml();
       assertEquals(false, firstImg.equals(secondImg));
    }

    @Step("������� ���������� ��������")
    public void backImg() {
        equipmentPage.setImgActive();
        String firstImg = equipmentPage.getImgActive().innerHtml();
        equipmentPage.getButtonBack().click();
        equipmentPage.setImgActiveNew();
        String secondImg = equipmentPage.getImgActiveNew().innerHtml();
        assertEquals(false, firstImg.equals(secondImg));
    }

    @Step("��������� �������")
    public void closeAdvertising() {
        if (equipmentPage.getButtonCLoseAdvertising().isDisplayed())
            equipmentPage.getButtonCLoseAdvertising().click();
    }

    @Step("��������� ��������� ���������� � �������� {0}")
    public void openInformation(int index) {
        equipmentPage.getAllButtonInformation().get(index).scrollTo();
        equipmentPage.getAllButtonInformation().get(index).click();
        equipmentPage.setButtonCLoseProduct();
        assertEquals(true, equipmentPage.getButtonCLoseProduct().isDisplayed());
    }

    @Step("�������� �� ������\"��� ������?\"")
    public void clickBuy() {
        equipmentPage.getButtonBuy().click();
    }

    @Step("��������� ����������� ���� � �����������")
    public void clickBuyClose() {
        equipmentPage.getButtonCLoseBuy().click();
    }

    @Step("��������� �������� \"3G/4G ������\" �������")
    public void clickModem(String URL) {
        equipmentPage.getButtonModem().click();
        assertEquals(true, URL.equals(CommonPageActions.getCurrentURL()));
    }

    @Step("��������� �������� \"��������������/IPTV ���������\" �������")
    public void clickRouter(String URL) {
        equipmentPage.getButtonRouters().click();
        assertEquals(true, URL.equals(CommonPageActions.getCurrentURL()));
    }

    @Step("��������� �������� \"��������� ��������\" �������")
    public void clickSmartphone(String URL) {
        equipmentPage.getButtonSmartphone().click();
        assertEquals(true, URL.equals(CommonPageActions.getCurrentURL()));
    }



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
        closeAdvertising();
        getCatalog();
        cycleCatalog();
    }

    @Test
    @Feature("�������")
    @DisplayName("�������� ������")
    @Description("������������ �� ��������� ������� \"������������\" � ������� ������ � �������� ���������� � ������ �������")
    @Severity(SeverityLevel.NORMAL)
    public void slideDepartment(){
        CommonSteps.openPage(URL()[0][0].toString(), URL()[0][1].toString());
        closeAdvertising();
        clickModem(URL()[1][1].toString());
        whereBuy();
        clickRouter(URL()[2][1].toString());
        whereBuy();
        clickSmartphone(URL()[0][1].toString());
        whereBuy();
    }

}
