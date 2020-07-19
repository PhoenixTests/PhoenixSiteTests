package mobileCommunication.mobileCommunication.rates;

import io.qameta.allure.Step;

public abstract class DefaultRateSteps {
    protected static RatePage ratePage;

    @Step("���������� �������� ���������: ������ ������ ���� - {calls}, �������� - {gb}, " +
            "SMS - {sms}, ������ � ������ - {rf}")
    public void changeRanges(int calls, int gb, int sms, int rf) {
        ratePage.setCallsRangeVal(calls);
        ratePage.setGbRangeVal(gb);
        ratePage.setSmsRangeVal(sms);
        ratePage.setRfRangeVal(rf);
    }

    @Step("������ �� ������ '��������� �����'")
    public void getRateClick() {
        ratePage.getRateSelection().click();
    }
}
