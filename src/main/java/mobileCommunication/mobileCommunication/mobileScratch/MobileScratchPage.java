package mobileCommunication.mobileCommunication.mobileScratch;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

@Data
public class MobileScratchPage {

    @Getter
    private static String URL="http://phoenix-dnr.ru/mobile-scratch.php";

    @Getter
    private static String pageName="Пополнение счета";

    private SelenideElement refillByPost=$(byId("text")).$(byAttribute("for", "spoiler_1"));
    private SelenideElement refillByScratch=$(byId("text")).$(byAttribute("for", "spoiler_2"));

    private SelenideElement refillByPostInfo=$(byId("body_1"));
    private SelenideElement refillByScratchInfo=$(byId("body_2"));

    private SelenideElement closeRefillByPostInfoButton=$(byId("spoiler_1"));
    private SelenideElement closeRefillByScratchInfoButton=$(byId("spoiler_2"));

    private SelenideElement byPostSelectCity=$(byId("city-post"));
    @ToString.Exclude
    private ElementsCollection byPostCities=byPostSelectCity.$$("option");
    private SelenideElement byPostAddresses;
    private SelenideElement byScratchSelectCity=$(byId("city-scratch"));
    @ToString.Exclude
    private ElementsCollection byScratchSalePoints=byScratchSelectCity.$$("option");
    private SelenideElement byScratchAddresses;
    @ToString.Exclude
    private ElementsCollection byScratchCitySalePoints;

    private SelenideElement findClosestSalePointButton=refillByScratchInfo
            .$(byAttribute("for", "popup_checkbox_scratch_map"));

    private SelenideElement mapFrame=$(byAttribute("src", "mobile-scratch-map.php"));
    private SelenideElement userAddress=$(byId("useradress"));
    private SelenideElement findOnMapButton = $(byClassName("button"));
    private SelenideElement map = $(byId("map")).$(byClassName("ymaps-2-1-77-map"));
    private SelenideElement mapCloser = $(byClassName("popup_closer"));

    public SelenideElement getMap() {
        return map.waitUntil(Condition.visible, 10000);
    }

    public boolean checkIsDisplayed(SelenideElement element) {
        return element.waitWhile(Condition.not(Condition.visible), 1000).isDisplayed();
    }

    public boolean checkIsNotDisplayed(SelenideElement element) {
        return !element.waitWhile(Condition.visible, 3000).isDisplayed();
    }

    public void setByPostAddresses() {
        byPostAddresses = $(byId(String.format("city-post_%s", byPostSelectCity.getSelectedValue())));
    }

    public void setByScratchAddresses() {
        byScratchAddresses = $(byId(String.format("city-scratch_%s", byScratchSelectCity.getSelectedValue())));
    }

    public void setByScratchCitySalePoints() {
        byScratchCitySalePoints = byScratchAddresses.$$(By.tagName("tr"));
    }

    public void byScratchSelectCity(int option) {
        byScratchSelectCity.selectOption(option);
    }

    public void byPostSelectCity(int option) {
        byPostSelectCity.selectOption(option);
    }

    public String getByScratchCitySalePointName(int salePointNum) {
        return byScratchCitySalePoints.get(salePointNum).getText();
    }

    public void setUserAddressValue(String address) {
        userAddress.setValue(address);
    }
}
