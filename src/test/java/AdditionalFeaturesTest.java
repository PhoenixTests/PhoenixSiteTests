import io.qameta.allure.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Дополнительные возможности")
@Feature("Тестирование кнопок 'Задать вопрос оператору' и 'Оставить отзыв'")
public class AdditionalFeaturesTest {
    private RatePage ratePage;

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
        ratePage.closeCurrentTabAndReturnToMain();
    }

    @BeforeEach
    public void openRatePage() {
        ratePage= DefaultSteps.openPage();
    }

    @Test
    @DisplayName("Обратиться к оператору за помощью")
    @Description("Переход на страницу Личного кабинета, чтобы связаться в онлайн-чате с оператором")
    @Severity(SeverityLevel.NORMAL)
    public void shouldRedirectToAskOperatorPage() {
        askOperatorButtonClick();
        DefaultSteps.switchToAnotherWindow(ratePage, 1);
        DefaultSteps.checkIsCorrectUrl(ratePage,"https://my.phoenix-dnr.ru/login");
        closeWindow();
    }

    @Test
    @DisplayName("Перейти на страницу отзывов")
    @Description("Переход на страницу, где можно заполнить и отослать анкету")
    @Severity(SeverityLevel.NORMAL)
    public void shouldRedirectToFeedbackPage() {
        feedbackButtonClick();
        DefaultSteps.checkIsCorrectUrl(ratePage, "http://phoenix-dnr.ru/contacts-questionnaire.php");
    }
}
