package mobileCommunication.mobileCommunication.rates;

import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultSteps {

    @Step("������� �������� �������")
    public static RatePage openPage() {
        return new RatePage();
    }

    @Step("������� �� ����������� ��������")
    public static void switchToAnotherWindow(RatePage ratePage, int window) {
        ratePage.switchToAnotherWindow(window);
    }

    @Step("��������� url ������ ��� �������� ������� � {url}")
    public static void checkIsCorrectUrl(RatePage ratePage, String url) {
       /* if (url.equals("mailto:Info@phoenix-dnr.ru"))
            assertEquals(2, ratePage.getCountOfOpenedWindows());
        else*/
            assertEquals(url, ratePage.getCurrentURL());
    }

    @Step("������� �� �������� �������")
    public static void returnBack(RatePage ratePage) {
        ratePage.returnToPreviousPage();
    }

    @Step("���������� �������� ���������: ������ ������ ���� - {calls}, �������� - {gb}, " +
            "SMS - {sms}, ������ � ������ - {rf}")
    public static void changeRanges(RatePage ratePage, int calls, int gb, int sms, int rf) {
        ratePage.setCallsRangeVal(calls);
        ratePage.setGbRangeVal(gb);
        ratePage.setSmsRangeVal(sms);
        ratePage.setRfRangeVal(rf);

//        ratePage.changeRanges(calls, gb, sms, rf);
    }

    @Step("������ �� ������ '��������� �����'")
    public static void getRateClick(RatePage ratePage) {
        ratePage.getRateSelection().click();
    }
}
