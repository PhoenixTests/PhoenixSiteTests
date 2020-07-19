package mobileCommunication.equipment;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.DataProviderExtension;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import lombok.*;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


@Data
public class EquipmentPage {

    @Getter
    private static String pageName = "Оборудование";

    //elements
    private ElementsCollection allButtonInformation;
    private SelenideElement buttonNext;
    private SelenideElement buttonBack;
    private SelenideElement buttonCLoseProduct = $(byClassName("popup_closer"));
    private SelenideElement buttonCLoseBuy = $(byAttribute("for","pupup_where_buy"));
    private SelenideElement buttonCLoseAdvertising = $(byAttribute("for", "popup_checkbox_greeting_internet"));
    private SelenideElement buttonBuy = $(byClassName("where-buy-btn"));
    private SelenideElement buttonSmartphone = $(byId("first-tab-btn"));
    private SelenideElement buttonModem = $(byId("second-tab-btn"));
    private SelenideElement buttonRouters = $(byId("third-tab-btn"));
    private SelenideElement imgActive;
    private SelenideElement imgActiveNew;

    //method

    //search for a product catalog on a page
    public void setAllButtonInformation() {
        allButtonInformation = $$(byText("Подробнее"));
    }

    public void setButtonNext(){
        Selenide.switchTo().frame(0);
        buttonNext = $(byClassName("carousel-control-next-icon"));
    }

    public void setButtonBack() {
        buttonBack = $(byClassName("carousel-control-prev-icon"));
    }

    public void setImgActive(){
        imgActive = $(byClassName("carousel-item active"));
    }

    public void setImgActiveNew(){
        imgActiveNew = $(byClassName("carousel-item active"));
    }

}
