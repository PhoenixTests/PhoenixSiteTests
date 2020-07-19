package mobileCommunication.mobileCommunication.rates;

import io.qameta.allure.Step;

public abstract class DefaultRateSteps {
    protected static RatePage ratePage;

    @Step("Установить значения ползунков: Звонки внутри сети - {calls}, Интернет - {gb}, " +
            "SMS - {sms}, Звонки в Россию - {rf}")
    public void changeRanges(int calls, int gb, int sms, int rf) {
        ratePage.setCallsRangeVal(calls);
        ratePage.setGbRangeVal(gb);
        ratePage.setSmsRangeVal(sms);
        ratePage.setRfRangeVal(rf);
    }

    @Step("Нажать на кнопку 'Подобрать тариф'")
    public void getRateClick() {
        ratePage.getRateSelection().click();
    }
}
