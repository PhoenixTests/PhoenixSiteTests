package mobileCommunication.mobileCommunication.rates;

import common.CommonPageActions;
import common.CommonSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;

@Epic("Пользовательский интерфейс")
@Feature("Тестирование пользовательского интерфейса")
public class UserInterfaceTest {
    private static RatePage ratePage;

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
        return CommonPageActions.takeScreenshot();
    }

    /*@BeforeAll
    public static void openPage() {
        ratePage= new RatePage();
        CommonSteps.openPage(ratePage.getPageName(), ratePage.getURL());
    }*/

    //вообще желательно бы разбить это на @BeforeAll, где было бы открытие вкладки, но т.к. щас
    //если подобрать тариф, перейти на страницу тарифа, вернуться назад, изменить ползунки и снова нажать на
    //кнопку подбора тарифа,нихрена не будет, то тест shouldClosePopupWindowWithSelectedRate провалится
    //и вообще не хочу щас этим заниматься
    @BeforeEach
    public void defaultSteps() {
        ratePage= new RatePage();
        CommonSteps.openPage(RatePage.getPageName(), RatePage.getURL());
        DefaultRateSteps.changeRanges(ratePage,0,50,0,25);
        DefaultRateSteps.getRateClick(ratePage);
    }

    @Test
    @DisplayName("Возврат на предыдущую страницу")
    @Description("Возврат на предыдущую страницу после подбора тарифа и перехода на страницу тарифа")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldReturnToThePreviousSelectedRate() {
        rateInfo();
        CommonSteps.returnBack();
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
