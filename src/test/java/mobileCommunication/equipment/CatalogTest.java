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
@Epic("Оборудование")
public class CatalogTest {

    @DataProvider
    public static Object[][] URL() {
        return new Object[][] {
                { "Мобильные телефоны", "http://phoenix-dnr.ru/catalog.php?category=1"} ,
                { "3G/4G модемы",  "http://phoenix-dnr.ru/catalog.php?category=2"},
                { "Маршрутизаторы/IPTV приставки",  "http://phoenix-dnr.ru/catalog.php?category=3"},

        };
    }

    @TestTemplate
    @UseDataProvider("URL")
    @Feature("Товары")
    @DisplayName("Проверка товаров")
    @Description("Просмотр информации о каждом товаре")
    @Severity(SeverityLevel.NORMAL)
    public void informationAboutProduct(String namePage, String URL) {
        CommonSteps.openPage(namePage, URL);
        EquipmentSteps.closeAdvertising();
        EquipmentSteps.getCatalog();
        EquipmentSteps.cycleCatalog();
    }

    @Test
    @Feature("Разделы")
    @DisplayName("Проверка кнопок")
    @Description("Переключение по страницам раздела \"Оборудование\" с помощью кнопок и просмотр информации о местах покупки")
    @Severity(SeverityLevel.NORMAL)
    public void slideDepartment(){
        CommonSteps.openPage(URL()[0][0].toString(), URL()[0][1].toString());
        EquipmentSteps.closeAdvertising();
        EquipmentSteps.clickModem(URL()[1][1].toString());
        EquipmentSteps.whereBuy();
        EquipmentSteps.clickRouter(URL()[2][1].toString());
        EquipmentSteps.whereBuy();
        EquipmentSteps.clickSmartphone(URL()[0][1].toString());
        EquipmentSteps.whereBuy();
    }

}
