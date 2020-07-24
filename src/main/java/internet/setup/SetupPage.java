package internet.setup;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

@Data
public class SetupPage {

    @Getter
    private static String URL = "http://phoenix-dnr.ru/internet-setup.php?";
    @Getter
    private static String pageName = "Настройка интернета";

    private SelenideElement buttonsGroup = $(byId("hexagon_internet_setup"));
    private SelenideElement OSButton;
    private SelenideElement instructionTitle = $(byId("text")).$(By.tagName("h3"));
    private SelenideElement instruction = $(byId("text")).$(By.tagName("ol"));

    public SelenideElement getOSButton(String hrefValue) {
        return buttonsGroup.$(byAttribute("href", hrefValue));
    }

    public void checkIsInstructionVisibleAndNotEmpty() {
        instruction.shouldBe(Condition.visible).shouldNotBe(Condition.empty);
    }
}
