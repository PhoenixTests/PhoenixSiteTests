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
@Epic("Оборудование")
public class CatalogTest {

    EquipmentPage equipmentPage = new EquipmentPage();

    public void cycleCatalog() {
        for(int i = 1; i < equipmentPage.getAllButtonInformation().size(); i++) {
            openInformation(i);
            equipmentPage.setButtonNext();
            if (equipmentPage.getButtonNext().isDisplayed()){
                leafImg();
            }
            closeInformation(i);
        }
    }

    public void whereBuy() {
        clickBuy();
        clickBuyClose();
    }

    @Step("Находим все кнопки \"Подробнее\"")
    public void getCatalog() {
        equipmentPage.setAllButtonInformation();

    }

    @Step("Закрываем подробную информацию о продукте {0}")
    public void closeInformation(int index) {
        equipmentPage.getButtonCLoseProduct().click();
    }

    @Step("Смотрим следующую картинку")
    public void leafImg() {
        equipmentPage.setImgActive();
        String firstImg = equipmentPage.getImgActive().attr("scr");
        equipmentPage.getButtonNext().click();
        equipmentPage.setImgActiveNew();
        String secondImg = equipmentPage.getImgActiveNew().attr("scr");
        assertEquals(false,firstImg.equals(secondImg));
    }

    @Step("Закрываем рекламу")
    public void closeAdvertising() {
        if (equipmentPage.getButtonCLoseAdvertising().isDisplayed())
            equipmentPage.getButtonCLoseAdvertising().click();
    }

    @Step("Открываем подробную информацию о продукте {0}")
    public void openInformation(int index) {
        equipmentPage.getAllButtonInformation().get(index).scrollTo();
        equipmentPage.getAllButtonInformation().get(index).click();
        assertEquals(true, equipmentPage.getButtonCLoseProduct().isDisplayed());
    }

    @Step("Нажимаем на кнопку\"Где купить?\"")
    public void clickBuy() {
        equipmentPage.getButtonBuy().click();
    }

    @Step("Закрываем открывшееся окно с информацией")
    public void clickBuyClose() {
        equipmentPage.getButtonCLoseBuy().click();
    }

    @Step("Открываем страницу \"3G/4G модемы\" кнопкой")
    public void clickModem(String URL) {
        equipmentPage.getButtonModem().click();
        assertEquals(true, URL.equals(CommonPageActions.getCurrentURL()));
    }

    @Step("Открываем страницу \"Маршрутизаторы/IPTV приставки\" кнопкой")
    public void clicRouter(String URL) {
        equipmentPage.getButtonRouters().click();
        assertEquals(true, URL.equals(CommonPageActions.getCurrentURL()));
    }

    @Step("Открываем страницу \"Мобильные телефоны\" кнопкой")
    public void clickSmartphone(String URL) {
        equipmentPage.getButtonSmartphone().click();
        assertEquals(true, URL.equals(CommonPageActions.getCurrentURL()));
    }

    @DataProvider
    public static Object[][] URL() {
        return new Object[][] {
                { "Мобильные телефоны", "http://phoenix-dnr.ru/catalog.php?category=1"} ,
                { "3G/4G модемы",  "http://phoenix-dnr.ru/catalog.php?category=2"},
                { "Маршрутизаторы/IPTV приставки",  "http://phoenix-dnr.ru/catalog.php?category=3"},

        };
    }


    @Feature("Товары")
    @DisplayName("Проверка товаров")
    @Description("Просмотр информации о каждом товаре")
    @Severity(SeverityLevel.NORMAL)
    @TestTemplate
    @UseDataProvider("URL")
    public void informationAboutProduct(String namePage, String URL) {
        CommonSteps.openPage(namePage, URL);
        closeAdvertising();
        getCatalog();
        cycleCatalog();
    }

    @Test
    @Feature("Разделы")
    @DisplayName("Проверка кнопок")
    @Description("Переключение по страницам раздела \"Оборудование\" с помощью кнопок и просмотр информации о местах покупки")
    @Severity(SeverityLevel.NORMAL)
    public void slideDepartment(){
        CommonSteps.openPage(URL()[0][0].toString(), URL()[0][1].toString());
        closeAdvertising();
        clickModem(URL()[1][1].toString());
        whereBuy();
        clicRouter(URL()[2][1].toString());
        whereBuy();
        clickSmartphone(URL()[0][1].toString());
        whereBuy();
    }

}
