package mobileCommunication.equipment;

import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import common.CommonPageActions;
import common.CommonSteps;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(UseDataProviderExtension.class)
public class EquipmentSteps {

    static EquipmentPage equipmentPage = new EquipmentPage();

    @Step("������� ��� ������ �� ��������")
    public static void setAllInformation() {
        equipmentPage.setAllButtonInformation();

    }

    @Step("��������� ��������� ���������� � �������� {0}")
    public static void closeInformation(int index) {
        CommonSteps.exitFrame();
        equipmentPage.setButtonCLoseProduct();
        equipmentPage.getButtonCLoseProduct().click();
    }

    @Step("������� ��������� ��������")
    public static void leafImg() {
        equipmentPage.setImgActive();
        String firstImg = equipmentPage.getImgActive().innerHtml();
        equipmentPage.getButtonNext().click();
        equipmentPage.setImgActiveNew();
        String secondImg = equipmentPage.getImgActiveNew().innerHtml();
        assertNotEquals(firstImg, secondImg);
    }

    @Step("������� ���������� ��������")
    public static void backImg() {
        equipmentPage.setImgActive();
        String firstImg = equipmentPage.getImgActive().innerHtml();
        equipmentPage.getButtonBack().click();
        equipmentPage.setImgActiveNew();
        String secondImg = equipmentPage.getImgActiveNew().innerHtml();
        assertNotEquals(firstImg, secondImg);
    }

    @Step("��������� ��������� ���������� � �������� {0}")
    public static void openInformation(int index) {
        equipmentPage.getAllButtonInformation().get(index).scrollTo();
        equipmentPage.getAllButtonInformation().get(index).click();
        equipmentPage.setButtonCLoseProduct();
        assertTrue(equipmentPage.getButtonCLoseProduct().isDisplayed());
    }

    @Step("�������� �� ������ \"��� ������?\"")
    public static void clickBuy() {
        equipmentPage.getButtonBuy().click();
    }

    @Step("��������� ����������� ���� � �����������")
    public static void clickBuyClose() {
        equipmentPage.getButtonCLoseBuy().click();
    }

    @Step("��������� �������� \"3G/4G ������\" �������")
    public static void clickModem(String URL) {
        equipmentPage.getButtonModem().click();
        CommonSteps.checkIsCorrectUrl(URL);
    }

    @Step("��������� �������� \"��������������/IPTV ���������\" �������")
    public static void clickRouter(String URL) {
        equipmentPage.getButtonRouters().click();
        CommonSteps.checkIsCorrectUrl(URL);
    }

    @Step("��������� �������� \"��������� ��������\" �������")
    public static void clickSmartphone(String URL) {
        equipmentPage.getButtonSmartphone().click();
        CommonSteps.checkIsCorrectUrl(URL);
    }

    @Step("���������, ��������� �� ��� � ����")
    public static void checkNameAndPrice(String name, String price) {
        equipmentPage.setProductNameAndPrice(name, price);
        assertTrue(equipmentPage.getProductName().exists() && equipmentPage.getProductPrice().exists());
    }

    public static void whereBuy() {
        clickBuy();
        clickBuyClose();
    }

    public static void cycleCatalog() {
        for(int i = 0; i < equipmentPage.getAllButtonInformation().size(); i++) {
            openInformation(i);
            String name = equipmentPage.getAllProductsNames().get(i).getText();
            String price = equipmentPage.getAllProductsPrice().get(i).getText();
            CommonPageActions.switchToFrame(equipmentPage.getFramePopup());
            checkNameAndPrice(name, price);
            equipmentPage.setButtonNext();
            equipmentPage.setButtonBack();
            if (equipmentPage.getButtonNext().isDisplayed()){
                leafImg();
                backImg();
            }
            closeInformation(i);
        }
    }

}
