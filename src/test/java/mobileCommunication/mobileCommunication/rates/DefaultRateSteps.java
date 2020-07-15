package mobileCommunication.mobileCommunication.rates;

import io.qameta.allure.Step;

public class DefaultRateSteps {

    @Step("Установить значения ползунков: Звонки внутри сети - {calls}, Интернет - {gb}, " +
            "SMS - {sms}, Звонки в Россию - {rf}")
    public static void changeRanges(RatePage ratePage, int calls, int gb, int sms, int rf) {
        ratePage.setCallsRangeVal(calls);
        ratePage.setGbRangeVal(gb);
        ratePage.setSmsRangeVal(sms);
        ratePage.setRfRangeVal(rf);
    }

    @Step("Нажать на кнопку 'Подобрать тариф'")
    public static void getRateClick(RatePage ratePage) {
        ratePage.getRateSelection().click();
    }
}
