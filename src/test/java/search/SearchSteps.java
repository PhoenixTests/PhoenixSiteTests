package search;

import common.CommonSteps;
import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.*;

public abstract class SearchSteps {
    protected static SearchPage searchPage;

    @Step("Нажимаем на значок поиска")
    public static void clickSearch() {
        searchPage.getButtonSearch().click();
    }

    @Step("Ставим курсор в поле ввода")
    public static void clickInput() {
        searchPage.getInputSearch().click();
    }

    @Step("Ввод текста \"{0}\" и осуществляем поиск")
    public static void textInput(String text) {
        searchPage.getInputSearch().setValue(text);
    }

    @Step("Ищем среди найденных результатов ссылку \"{0}\" на нужный раздел")
    public static void checkLink(String text) {
        searchPage.setLinkFind(text);
        searchPage.waitLinks(1000);
        assertTrue(searchPage.getLinkFind().exists());
    }

    @Step("Переходим по найденной ссылке")
    public static void clickLink(String URl) {
        searchPage.getLinkFind().click();
        CommonSteps.checkIsCorrectUrl(URl);
    }

    @Step("Открываем окно статистики")
    public static void clickStatistics() {
        searchPage.getStatisticFind().click();
    }

    @Step("Переходим по ссылке сайта")
    public static void clickLinkWebsite() {
        searchPage.getWebsite().click();
    }

    @Step("Проверяем, доступен ли сайт")
    public static void checkWebsite() {
        assertFalse(searchPage.getExceptionSite().exists());
    }

    @Step("Закрываем статистику")
    public static void closeWebsite() {
        searchPage.getButtonClose().click();
    }

}
