package search;

import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SearchSteps {

    @Step("Нажимаем на значок поиска")
    public static void clickSearch(SearchPage searchPage) {
        searchPage.getButtonSearch().click();
    }

    @Step("Ставим курсор на поле ввода")
    public static void clickInput(SearchPage searchPage) {
        searchPage.getInputSearch().click();
    }

    @Step("Ввод текст - \"{1}\" и осуществляем поиск")
    public static void textInput(SearchPage searchPage, String text) {
        searchPage.getInputSearch().setValue(text);
    }

    @Step("Ищем среди найденных результатов ссылку - \"{1}\" на нужный раздел")
    public static void checkLink(SearchPage searchPage, String text) {
        searchPage.setLinkFind(text);
        assertEquals(true, searchPage.getLinkFind().exists());
    }

    @Step("Переходим по найденной ссылке")
    public static void clickLink(SearchPage searchPage) {
        searchPage.getLinkFind().click();
    }

    @Step("Открываем окно статистики")
    public static void clickStatistics(SearchPage searchPage) {
        searchPage.getStatisticFind().click();
    }

    @Step("Переходим по ссылке сайта")
    public static void clickLinkWebsite(SearchPage searchPage) {
        searchPage.getWebsite().click();
    }

    @Step("Проверяем, доступен ли сайт")
    public static void checkWebsite(SearchPage searchPage) {
        assertEquals(false, searchPage.getExceptionSite().exists());
    }

    @Step("Закрываем статистику")
    public static void closeWebsite(SearchPage searchPage) {
        searchPage.getButtonClose().click();
    }

}
