package internet.connections.stocks;

import common.CommonPageActions;
import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class StocksSteps {
    protected static StocksPage stocksPage;

    @Step("Нажать на кнопку акции {stockName}")
    public static void clickButtonFriends(String stockName, int key) {
        switch (key) {
            case 0:
                CommonPageActions.scrollIntoView(stocksPage.getButtonFriends());
                stocksPage.getButtonFriends().click();
                break;
            case 1:
                CommonPageActions.scrollIntoView(stocksPage.getButtonRate());
                stocksPage.getButtonRate().click();
                break;
            case 2:
                CommonPageActions.scrollIntoView(stocksPage.getButtonHundred());
                stocksPage.getButtonHundred().click();
                break;
        }
    }

    @Step("Нажать на кнопку «ПОДКЛЮЧИТЬ»")
    public static void clickButtonConnect() {
        if (stocksPage.getButtonConnect().exists())
            stocksPage.getButtonConnect().click();
        else
            stocksPage.getButtonConnect2().click();
    }

    @Step("Проверка, открылось ли окно заявки")
    public static void checkWindow() {
        assertTrue(stocksPage.getButtonClose().isDisplayed());
    }

    @Step("Закрываем окно заявки")
    public static void clickClose() {
        stocksPage.getButtonClose().click();
    }

}
