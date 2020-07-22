package search;

import com.codeborne.selenide.SelenideElement;
import lombok.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.*;

@Data
public class SearchPage {

    @Getter
    private static String namePage = "Поиск";
    @Getter
    private static String URL = "http://phoenix-dnr.ru/";

    //elements
    private SelenideElement buttonSearch = $(byClassName("ph-search__icon-container"));
    private SelenideElement inputSearch = $(byId("inputfind"));
    private SelenideElement linkFind;
    private SelenideElement statisticFind = $(byText("Перейти в статистику запросов"));
    private SelenideElement website = $(byText("http://phoenix-dnr.ru/"));
    private SelenideElement exceptionSite = $(byText("403 Forbidden"));
    private SelenideElement buttonClose = $(byText("Закрыть"));

    public SelenideElement setLinkFind(String text) {
        return linkFind = $(byText(text));
    }

}
