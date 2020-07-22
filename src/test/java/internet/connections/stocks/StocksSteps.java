package internet.connections.stocks;

import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StocksSteps {

    @Step("Нажать на кнопку акции")
    public static void clickButtonFriends(StocksPage stocksPage, int key) {
        switch (key)
        {
            case 0:
                stocksPage.getButtonFriends().scrollTo();
                stocksPage.getButtonFriends().click();
                break;
            case 1:
                stocksPage.getButtonRate().scrollTo();
                stocksPage.getButtonRate().click();
                break;
            case 2:
                stocksPage.getButtonHundred().scrollTo();
                stocksPage.getButtonHundred().click();
                break;
        }
    }

    @Step("Нажать на кнопку «ПОДКЛЮЧИТЬ»")
    public static void clickButtonConnect(StocksPage stocksPage) {
        if(stocksPage.getButtonConnect().exists())
            stocksPage.getButtonConnect().click();
        else
            stocksPage.getButtonConnect2().click();
    }

    @Step("Проверка, открылось ли окно заявки")
    public static void checkWindow(StocksPage stocksPage) {
        assertTrue(stocksPage.getButtonClose().isDisplayed());
    }

    @Step("Закрываем окно заявки")
    public static void clickClose(StocksPage stocksPage) {
        stocksPage.getButtonClose().click();
    }

}
