package mobileCommunication.equipment;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


@Data
public class EquipmentPage {

    @Getter
    private static String pageName = "Оборудование";

    //elements
    private ElementsCollection allButtonInformation;
    @ToString.Exclude
    private ElementsCollection allProductsNames = $(byId("text")).$$(By.tagName("h3"));
    @ToString.Exclude
    private ElementsCollection allProductsPrice = $(byId("text")).$$(By.tagName("p"));
    private SelenideElement productName;
    private SelenideElement productPrice;
    private SelenideElement buttonNext;
    private SelenideElement buttonBack;
    private SelenideElement buttonCLoseProduct;
    private SelenideElement buttonCLoseBuy = $(byAttribute("for","pupup_where_buy"));
    private SelenideElement buttonCLoseAdvertising = $(byAttribute("for", "popup_checkbox_greeting_internet"));
    private SelenideElement buttonBuy = $(byClassName("where-buy-btn"));
    private SelenideElement buttonSmartphone = $(byId("first-tab-btn"));
    private SelenideElement buttonModem = $(byId("second-tab-btn"));
    private SelenideElement buttonRouters = $(byId("third-tab-btn"));
    private SelenideElement imgActive;
    private SelenideElement imgActiveNew;
    private SelenideElement framePopup = $(byId("popup-iframe"));
    //method

    //search for a product catalog on a page
    public void setAllButtonInformation() {
        allButtonInformation = $$(byText("Подробнее"));
    }

    public void setProductNameAndPrice(String name, String price) {
        productName = $(byText(name));
        productPrice = $(byText(price));
    }

    public void setButtonNext() {
        buttonNext = $(byClassName("carousel-control-next-icon"));
    }

    public void setButtonCLoseProduct() {
        buttonCLoseProduct = $(byAttribute("for", "popup_card_page"));
    }

    public void setButtonBack() {
        buttonBack = $(byClassName("carousel-control-prev-icon"));
    }

    public void setImgActive() {
        imgActive = $(byClassName("carousel-inner")).$$(byClassName("carousel-item")).find(Condition.attribute("class", "carousel-item active"));
    }

    public void setImgActiveNew(){
        imgActiveNew = $(byClassName("carousel-inner")).$$(byClassName("carousel-item")).find(Condition.attribute("class", "carousel-item active"));
    }

}
