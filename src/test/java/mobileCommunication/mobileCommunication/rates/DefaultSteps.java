package mobileCommunication.mobileCommunication.rates;

import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultSteps {

    @Step("Открыть страницу Тарифов")
    public static RatePage openPage() {
        return new RatePage();
    }

    @Step("Перейти на открывшуюся страницу")
    public static void switchToAnotherWindow(RatePage ratePage, int window) {
        ratePage.switchToAnotherWindow(window);
    }

    @Step("Сравнение url только что открытой вкладки с {url}")
    public static void checkIsCorrectUrl(RatePage ratePage, String url) {
       /* if (url.equals("mailto:Info@phoenix-dnr.ru"))
            assertEquals(2, ratePage.getCountOfOpenedWindows());
        else*/
            assertEquals(url, ratePage.getCurrentURL());
    }

    @Step("Возврат на страницу Тарифов")
    public static void returnBack(RatePage ratePage) {
        ratePage.returnToPreviousPage();
    }

    @Step("Установить значения ползунков: Звонки внутри сети - {calls}, Интернет - {gb}, " +
            "SMS - {sms}, Звонки в Россию - {rf}")
    public static void changeRanges(RatePage ratePage, int calls, int gb, int sms, int rf) {
        ratePage.setCallsRangeVal(calls);
        ratePage.setGbRangeVal(gb);
        ratePage.setSmsRangeVal(sms);
        ratePage.setRfRangeVal(rf);

//        ratePage.changeRanges(calls, gb, sms, rf);
    }

    @Step("Нажать на кнопку 'Подобрать тариф'")
    public static void getRateClick(RatePage ratePage) {
        ratePage.getRateSelection().click();
    }
}
