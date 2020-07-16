package mobileCommunication.mobileCommunication.worldwideCalls;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.By;

import java.util.Random;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

@Data
public class WorldwideCallsPage {
    @Getter
    static String pageName="Звонки по всему миру";
    @Getter
    static String URL = "http://phoenix-dnr.ru/world_cost/";

    private SelenideElement types=$(byId("types"));
    @ToString.Exclude
    private ElementsCollection operatorCodeList;
    private SelenideElement operatorCodeListThreeDots;
    private SelenideElement operatorCodeLabel;
    private SelenideElement cost=$(byName("Cost"));
    @ToString.Exclude
    private ElementsCollection costInfo=cost.$$("p");
    private SelenideElement costInfoSub=cost.$("sub");

    private SelenideElement countriesTable = $(byId("country"));
    @ToString.Exclude
    private ElementsCollection countries=countriesTable.$$(By.tagName("tr"));
    private SelenideElement countryName;
//    private SelenideElement countryImage;


    //all countries
    private SelenideElement abc=$(byId("abc"));
    @ToString.Exclude
    private ElementsCollection alphabet=abc.$$("a");
    private SelenideElement letter=$(byClassName("letter"));

    public void setCountries(int element) {
        ElementsCollection popularCountriesRowFromTable=countries.get(element).$$(By.tagName("td"));
        if(!popularCountriesRowFromTable.isEmpty()) {
            for (SelenideElement row : popularCountriesRowFromTable) {
                if (!row.$(By.tagName("a")).$(By.tagName("img")).exists())
                    countryName = row.$(By.tagName("a"));
//                if (row.$(By.tagName("a")).$(By.tagName("img")).exists())
//                    countryImage = row.$(By.tagName("a"));
//                else
//                    countryName = row.$(By.tagName("a"));
            }
        }
    }

    public SelenideElement getTypeElement(String typeName){
        return types.$(byText(typeName));
    }

    public SelenideElement setOperatorCodeLabel() {
        operatorCodeLabel=$(byName("selectCode")).$(byText("Код оператора:")).waitUntil(Condition.appear, 2000);
        return operatorCodeLabel;
    }

    public void checkOperatorCodesList() {
        operatorCodeList.shouldBe(CollectionCondition.sizeGreaterThanOrEqual(1));
    }

    public void setOperatorCodeList() {
        operatorCodeList=$(byName("selectCode")).$(byName("prefix")).$("optgroup").$$("option");
    }

    public void setOperatorCodeListThreeDots() {
        operatorCodeListThreeDots=$(byName("selectCode")).$(byName("prefix")).$("option");
    }

    public String selectOperatorCode(Random random) {
        int operatorCodeListSize=operatorCodeList.size();
        SelenideElement operatorCode = operatorCodeList.get(random.nextInt(operatorCodeListSize));
        operatorCode.click();
        return operatorCode.getValue();
    }

    public void checkTariffingInfoHidden() {
        cost.shouldNotBe(Condition.visible);
    }

    public boolean checkCountryName(String selectedCountry) {
        return countryName.has(Condition.text(selectedCountry));
    }
}
