package homeInternet.Òonnections.stocks;

import com.codeborne.selenide.SelenideElement;
import lombok.*;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

@Data
public class StocksPage {

    @Getter
    private static String pageName = "¿ÍˆËË";
    @Getter
    private static String URL = "http://phoenix-dnr.ru/internet-actions.php";

    //elements
    private SelenideElement buttonFriends = $(byId("internet-actions-1-button"));
    private SelenideElement buttonRate = $(byId("internet-actions-2-button"));
    private SelenideElement buttonHundred = $(byId("internet-actions-6-button"));
    private SelenideElement buttonConnect = $(byId("button_actions_1"));
    private SelenideElement buttonConnect2 = $(byId("button_actions_2"));
    private SelenideElement buttonClose = $(byAttribute("for", "popup_checkbox_connection"));

}
