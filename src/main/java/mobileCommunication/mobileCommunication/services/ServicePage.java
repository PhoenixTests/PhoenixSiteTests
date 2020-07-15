package mobileCommunication.mobileCommunication.services;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.*;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

@Data
public class ServicePage {

    @Getter
    static String pageName = "Услуги";

    @Getter
    static String URL = "http://phoenix-dnr.ru/mobile-services.php";

    //elements page
    private SelenideElement label;
    private SelenideElement body;
    private SelenideElement button;


    public SelenideElement setLabel(String nameLabel){
        label = $(byText(nameLabel));
        return label;
    }

    public SelenideElement setBody(String idBody){
        body = $(byId(idBody));
        return body;
    }

    public SelenideElement setButton(String idButton){
        button = $(byId(idButton));
        return button;
    }

}
