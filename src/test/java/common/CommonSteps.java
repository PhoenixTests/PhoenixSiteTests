package common;

import io.qameta.allure.Step;

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
        assertEquals(url, CommonPageActions.getCurrentURL());
    }

    @Step("����� �� ������")
    public static void exitFrame() {
        CommonPageActions.exitFromFrame();
    }
}
