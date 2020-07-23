package search;

import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class SearchSteps {
    protected static SearchPage searchPage;

    @Step("Нажимаем на значок поиска")
    public static void clickSearch() {
        searchPage.getButtonSearch().click();
    }

    @Step("Ставим курсор на поле ввода")
    public static void clickInput() {
        searchPage.getInputSearch().click();
    }

    @Step("Ввод текст - \"{0}\" и осуществляем поиск")
    public static void textInput(String text) {
        searchPage.getInputSearch().setValue(text);
    }

    @Step("Ищем среди найденных результатов ссылку - \"{0}\" на нужный раздел")
    public static void checkLink(String text) {
        searchPage.setLinkFind(text);
        searchPage.waitLinks(700);
        assertTrue(searchPage.getLinkFind().exists());
    }

    @Step("Переходим по найденной ссылке")
    public static void clickLink() {
        searchPage.getLinkFind().click();
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
