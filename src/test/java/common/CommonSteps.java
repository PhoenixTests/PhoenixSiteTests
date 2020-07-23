package common;

import io.qameta.allure.Step;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonSteps {
    @Step("������� �������� {pageName}")
    public static void openPage(String pageName, String URL) {
        CommonPageActions.openPage(URL);
    }

    @Step("������� �� ����������� ��������")
    public static void switchToAnotherWindow(int window) {
        CommonPageActions.switchToAnotherWindow(window);
    }

    @Step("������� �� ���������� ��������")
    public static void returnBack() {
        CommonPageActions.returnToPreviousPage();
    }

    @Step("��������� url ������ ��� �������� ������� � {url}")
    public static void checkIsCorrectUrl(String url) {
        try {
            String currentURL = CommonPageActions.getCurrentURL();
            assertEquals(URLDecoder.decode(url, "UTF-8"), URLDecoder.decode(currentURL, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Step("����� �� ������")
    public static void exitFrame() {
        CommonPageActions.exitFromFrame();
    }
}
