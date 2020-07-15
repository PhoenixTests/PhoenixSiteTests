package mobileCommunication.mobileCommunication.rates;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import lombok.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

@Data
public class RatePage {
    @Getter
    private static String pageName = "Тарифы";
    @Getter
    private static String URL = "http://phoenix-dnr.ru/rates/";
    //select rate
    private SelenideElement calls = $(byId("calls"));
    private SelenideElement gbRange = $(byId("gbRange"));
    private SelenideElement smsRange = $(byId("smsRange"));
    private SelenideElement rfRange = $(byId("rfRange"));
    private SelenideElement popup = $(byClassName("ph-tariff__container"));
    private SelenideElement rateSelection = $(byText("Подобрать тариф"));
    private SelenideElement rateInfo = $(byClassName("ph-tariff__button")).$(byText("Подробнее"));
    private SelenideElement rateName = $(byId("ph-tariff-popup")).$(byClassName("ph-tariff__title-name"));
    private SelenideElement ratePrice = $(byId("ph-tariff-popup")).$(byClassName("ph-tariff--info-month-price"));
    private SelenideElement closePopupWindow = $(byId("ph-tariff-popup")).$(byClassName("popup_closer"));

    //carousel
    private SelenideElement carousel = $(byId("tariff-carousel"));
    private SelenideElement owlStage = carousel.$(byClassName("owl-stage"));
    @ToString.Exclude
    private ElementsCollection activeElements = owlStage.$$(".active");
    private SelenideElement carouselBackButton = carousel.$(byText("<"));
    private SelenideElement carouselForwardButton = carousel.$(byText(">"));
    private SelenideElement carouselRate;
    @Setter(AccessLevel.NONE)
    private SelenideElement carouselRateButton;

    //additional features
    private SelenideElement feedbackButton = $(byId("questionnaire_button"));
    private SelenideElement askOperatorButton = $(byText("Задать вопрос оператору"));

    //socials
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    private ElementsCollection socialIcons = $(byId("footer")).$$(By.tagName("a"));

    public SelenideElement getChangeRatesLink(String linkText) {
        return $(byLinkText(linkText));
    }

    public SelenideElement getSocialIcon(String url) {
        for (SelenideElement icon : socialIcons) {
            if (icon.getAttribute("href").contains(url))
                return icon;
        }
        return null;
    }

    public void isRateVisible(String rateName) {
        carouselRate = activeElements.findBy(text(rateName));
    }

    public void setCarouselRateButton() {
        carouselRateButton = carouselRate.$(byText("Подробнее"));
    }

   /* public void changeRanges(int calls, int gb, int sms, int rf) {
        Selenide.executeJavaScript("$('#calls').val(arguments[0])", calls);
        Selenide.executeJavaScript("$('#gbRange').val(arguments[0])", gb);
        Selenide.executeJavaScript("$('#smsRange').val(arguments[0])", sms);
        Selenide.executeJavaScript("$('#rfRange').val(arguments[0])",rf);
    }*/

    public void setCallsRangeVal(int calls) {
        Selenide.executeJavaScript("$('#calls').val(arguments[0])", calls);
//        this.calls.val(String.valueOf(calls));
    }

    public void setGbRangeVal(int gb) {
        Selenide.executeJavaScript("$('#gbRange').val(arguments[0])", gb);
//        gbRange.val(String.valueOf(gb));
    }

    public void setSmsRangeVal(int sms) {
        Selenide.executeJavaScript("$('#smsRange').val(arguments[0])", sms);
//        smsRange.val(String.valueOf(sms));
    }

    public void setRfRangeVal(int rf) {
        Selenide.executeJavaScript("$('#rfRange').val(arguments[0])", rf);
//        rfRange.val(String.valueOf(rf));
    }

    public String getPageTitle() {
        return WebDriverRunner.getWebDriver().getTitle().split("\\|")[0].trim();
    }

}
