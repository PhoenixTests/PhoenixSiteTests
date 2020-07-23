package search;

import common.CommonSteps;
import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.*;

public abstract class SearchSteps {
    protected static SearchPage searchPage;

    @Step("�������� �� ������ ������")
    public static void clickSearch() {
        searchPage.getButtonSearch().click();
    }

    @Step("������ ������ � ���� �����")
    public static void clickInput() {
        searchPage.getInputSearch().click();
    }

    @Step("���� ������ \"{0}\" � ������������ �����")
    public static void textInput(String text) {
        searchPage.getInputSearch().setValue(text);
    }

    @Step("���� ����� ��������� ����������� ������ \"{0}\" �� ������ ������")
    public static void checkLink(String text) {
        searchPage.setLinkFind(text);
        searchPage.waitLinks(1000);
        assertTrue(searchPage.getLinkFind().exists());
    }

    @Step("��������� �� ��������� ������")
    public static void clickLink(String URl) {
        searchPage.getLinkFind().click();
        CommonSteps.checkIsCorrectUrl(URl);
    }

    @Step("��������� ���� ����������")
    public static void clickStatistics() {
        searchPage.getStatisticFind().click();
    }

    @Step("��������� �� ������ �����")
    public static void clickLinkWebsite() {
        searchPage.getWebsite().click();
    }

    @Step("���������, �������� �� ����")
    public static void checkWebsite() {
        assertFalse(searchPage.getExceptionSite().exists());
    }

    @Step("��������� ����������")
    public static void closeWebsite() {
        searchPage.getButtonClose().click();
    }

}
