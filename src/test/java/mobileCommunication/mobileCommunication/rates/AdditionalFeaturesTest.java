package mobileCommunication.mobileCommunication.rates;

import common.CommonPageActions;
import common.CommonSteps;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Epic("Дополнительные возможности")
@Feature("Тестирование кнопок 'Задать вопрос оператору' и 'Оставить отзыв'")
public class AdditionalFeaturesTest {
    private static RatePage ratePage;

    @Step("Нажать на кнопку 'Задать вопрос оператору' внизу справа страницы")
    public void askOperatorButtonClick() {
        ratePage.getAskOperatorButton().click();
    }

    @Step("Нажать на кнопку 'Оставить отзыв' справа страницы")
    public void feedbackButtonClick() {
        ratePage.getFeedbackButton().click();
    }

    @Step("Закрыть вкладку Личного кабинета")
    public void closeWindow() {
        CommonPageActions.closeCurrentTabAndReturnToMain();
    }

    @BeforeAll
    public static void openRatePage() {
        ratePage=new RatePage();
        CommonSteps.openPage(RatePage.getPageName(), RatePage.getURL());
    }

    @Test
    @Order(1)
    @DisplayName("Обратиться к оператору за помощью")
    @Description("Переход на страницу Личного кабинета, чтобы связаться в онлайн-чате с оператором")
    @Severity(SeverityLevel.NORMAL)
    public void shouldRedirectToAskOperatorPage() {
        askOperatorButtonClick();
        CommonSteps.switchToAnotherWindow(1);
        CommonSteps.checkIsCorrectUrl("https://my.phoenix-dnr.ru/login");
        closeWindow();
    }

    @Test
    @Order(2)
    @DisplayName("Перейти на страницу отзывов")
    @Description("Переход на страницу, где можно заполнить и отослать анкету")
    @Severity(SeverityLevel.NORMAL)
    public void shouldRedirectToFeedbackPage() {
        feedbackButtonClick();
        CommonSteps.checkIsCorrectUrl("http://phoenix-dnr.ru/contacts-questionnaire.php");
        CommonSteps.returnBack();
    }
}
