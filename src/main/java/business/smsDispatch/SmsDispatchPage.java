package business.smsDispatch;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

@Data
public class SmsDispatchPage {
    @Getter
    private static String  URL ="http://phoenix-dnr.ru/business-sms-dispatch.php";
    @Getter
    private static String pageName ="SMS ��������";

    private SelenideElement contractDoc=$(byId("text")).$(byText("������� �� ���-�������� ��� ����������� ���"));
    private SelenideElement requestDoc=$(byId("text")).$(byText("������ �� ���-�������� ��� ����������� ���"));
    private SelenideElement statutoryDoc=$(byId("text")).$(byText("�������� ���������"));

    private SelenideElement closeAdvertisingButton = $(byAttribute("for", "popup_checkbox_greeting_internet"));

    public SelenideElement getDocument(String docName) {
        return $(byId("text")).$(byText(docName));
    }

}
