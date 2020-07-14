import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;

@Epic("Пользовательский интерфейс")
@Feature("Тестирование пользовательского интерфейса")
public class UserInterfaceTest {
    private RatePage ratePage;

    @Step("Переход на страницу с подобранным тарифом")
    public void rateInfo() {
        ratePage.getRateInfo().click();
    }

    @Step("Закрыть всплывающее окно")
    public void closePopup() {
        ratePage.getClosePopupWindow().click();
    }

    @Step("Поменять значение ползунка Звонков внутри сети на {calls}")
    public void setCalls(int calls) {
        ratePage.setCallsRangeVal(calls);
    }

    @Step("Проверить, появилось ли всплывающее окно с тарифом 'Удобный'")
    public void isPopupVisible() {
        ratePage.getRateInfo().shouldNotBe(visible);
    }

    @Attachment(value = "Скриншот страницы тарифов после возврата на предыдущую страницу", type = "image/png")
    public byte[] takeScreenshot() {
        return ratePage.takeScreenshot();
    }

    @BeforeEach
    public void defaultSteps() {
        ratePage= DefaultSteps.openPage();
        DefaultSteps.changeRanges(ratePage,0,50,0,25);
        DefaultSteps.getRateClick(ratePage);
    }

    @Test
    @DisplayName("Возврат на предыдущую страницу")
    @Description("Возврат на предыдущую страницу после подбора тарифа и перехода на страницу тарифа")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldReturnToThePreviousSelectedRate() {
        rateInfo();
        DefaultSteps.returnBack(ratePage);
        setCalls(50);
        isPopupVisible();
    }

    @Test
    @DisplayName("Закрыть окно с тарифом")
    @Description("Закрытие всплывающего окна с подобранным тарифом")
    @Severity(SeverityLevel.NORMAL)
    public void shouldClosePopupWindowWithSelectedRate() {
        closePopup();
    }
}
