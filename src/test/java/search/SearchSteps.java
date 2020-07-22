package search;

import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SearchSteps {

    @Step("�������� �� ������ ������")
    public static void clickSearch(SearchPage searchPage) {
        searchPage.getButtonSearch().click();
    }

    @Step("������ ������ �� ���� �����")
    public static void clickInput(SearchPage searchPage) {
        searchPage.getInputSearch().click();
    }

    @Step("���� ����� - \"{1}\" � ������������ �����")
    public static void textInput(SearchPage searchPage, String text) {
        searchPage.getInputSearch().setValue(text);
    }

    @Step("���� ����� ��������� ����������� ������ - \"{1}\" �� ������ ������")
    public static void checkLink(SearchPage searchPage, String text) {
        searchPage.setLinkFind(text);
        assertEquals(true, searchPage.getLinkFind().exists());
    }

    @Step("��������� �� ��������� ������")
    public static void clickLink(SearchPage searchPage) {
        searchPage.getLinkFind().click();
    }

    @Step("��������� ���� ����������")
    public static void clickStatistics(SearchPage searchPage) {
        searchPage.getStatisticFind().click();
    }

    @Step("��������� �� ������ �����")
    public static void clickLinkWebsite(SearchPage searchPage) {
        searchPage.getWebsite().click();
    }

    @Step("���������, �������� �� ����")
    public static void checkWebsite(SearchPage searchPage) {
        assertEquals(false, searchPage.getExceptionSite().exists());
    }

    @Step("��������� ����������")
    public static void closeWebsite(SearchPage searchPage) {
        searchPage.getButtonClose().click();
    }

}
