package mobileCommunication.equipment;

import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import common.CommonPageActions;
import common.CommonSteps;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(UseDataProviderExtension.class)
public class EquipmentSteps {

    static EquipmentPage equipmentPage = new EquipmentPage();

    @Step("Находим все кнопки \"Подробнее\"")
    public static void getCatalog() {
        equipmentPage.setAllButtonInformation();
    }

    @Step("Закрываем подробную информацию о продукте {0}")
    public static void closeInformation(int index) {
        CommonSteps.exitFrame();
        equipmentPage.setButtonCLoseProduct();
        equipmentPage.getButtonCLoseProduct().click();
    }

    @Step("Смотрим следующую картинку")
    public static void leafImg() {
        equipmentPage.setImgActive();
        String firstImg = equipmentPage.getImgActive().innerHtml();
        equipmentPage.getButtonNext().click();
        equipmentPage.setImgActiveNew();
        String secondImg = equipmentPage.getImgActiveNew().innerHtml();
        assertNotEquals(firstImg, secondImg);
    }

    @Step("Смотрим предыдущую картинку")
    public static void backImg() {
        equipmentPage.setImgActive();
        String firstImg = equipmentPage.getImgActive().innerHtml();
        equipmentPage.getButtonBack().click();
        equipmentPage.setImgActiveNew();
        String secondImg = equipmentPage.getImgActiveNew().innerHtml();
        assertNotEquals(firstImg, secondImg);
    }

    @Step("Закрываем рекламу")
    public static void closeAdvertising() {
        if (equipmentPage.getButtonCLoseAdvertising().isDisplayed())
            equipmentPage.getButtonCLoseAdvertising().click();
    }

    @Step("Открываем подробную информацию о продукте {0}")
    public static void openInformation(int index) {
        equipmentPage.getAllButtonInformation().get(index).scrollTo();
        equipmentPage.getAllButtonInformation().get(index).click();
        equipmentPage.setButtonCLoseProduct();
        assertTrue(equipmentPage.getButtonCLoseProduct().isDisplayed());
    }

    @Step("Нажимаем на кнопку\"Где купить?\"")
    public static void clickBuy() {
        equipmentPage.getButtonBuy().click();
    }

    @Step("Закрываем открывшееся окно с информацией")
    public static void clickBuyClose() {
        equipmentPage.getButtonCLoseBuy().click();
    }

    @Step("Открываем страницу \"3G/4G модемы\" кнопкой")
    public static void clickModem(String URL) {
        equipmentPage.getButtonModem().click();
        assertEquals(URL, CommonPageActions.getCurrentURL());
    }

    @Step("Открываем страницу \"Маршрутизаторы/IPTV приставки\" кнопкой")
    public static void clickRouter(String URL) {
        equipmentPage.getButtonRouters().click();
        assertEquals(URL, CommonPageActions.getCurrentURL());
    }

    @Step("Открываем страницу \"Мобильные телефоны\" кнопкой")
    public static void clickSmartphone(String URL) {
        equipmentPage.getButtonSmartphone().click();
        assertEquals(URL, CommonPageActions.getCurrentURL());
    }

    public static void whereBuy() {
        clickBuy();
        clickBuyClose();
    }

    public static void cycleCatalog() {
        for(int i = 0; i < equipmentPage.getAllButtonInformation().size(); i++) {
            EquipmentSteps.openInformation(i);
            CommonSteps.switchFrame();
            equipmentPage.setButtonNext();
            equipmentPage.setButtonBack();
            if (equipmentPage.getButtonNext().isDisplayed()){
                EquipmentSteps.leafImg();
                EquipmentSteps.backImg();
            }
            EquipmentSteps.closeInformation(i);
        }
    }

}
