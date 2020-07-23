package search;

import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class SearchSteps {
    protected static SearchPage searchPage;

    @Step("�������� �� ������ ������")
    public static void clickSearch() {
        searchPage.getButtonSearch().click();
    }

    @Step("������ ������ �� ���� �����")
    public static void clickInput() {
        searchPage.getInputSearch().click();
    }

    @Step("���� ����� - \"{0}\" � ������������ �����")
    public static void textInput(String text) {
        searchPage.getInputSearch().setValue(text);
    }

    @Step("���� ����� ��������� ����������� ������ - \"{0}\" �� ������ ������")
    public static void checkLink(String text) {
        searchPage.setLinkFind(text);
        searchPage.waitLinks(700);
        assertTrue(searchPage.getLinkFind().exists());
    }

    @Step("��������� �� ��������� ������")
    public static void clickLink() {
        searchPage.getLinkFind().click();
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
