package mobileCommunication.mobileCommunication.rates;

import io.qameta.allure.Step;

public class DefaultRateSteps {

    @Step("���������� �������� ���������: ������ ������ ���� - {calls}, �������� - {gb}, " +
            "SMS - {sms}, ������ � ������ - {rf}")
    public static void changeRanges(RatePage ratePage, int calls, int gb, int sms, int rf) {
        ratePage.setCallsRangeVal(calls);
        ratePage.setGbRangeVal(gb);
        ratePage.setSmsRangeVal(sms);
        ratePage.setRfRangeVal(rf);
    }

    @Step("������ �� ������ '��������� �����'")
    public static void getRateClick(RatePage ratePage) {
        ratePage.getRateSelection().click();
    }
}
