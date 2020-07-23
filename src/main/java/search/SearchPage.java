package search;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

@Data
public class SearchPage {

    @Getter
    private static String namePage = "�����";
    @Getter
    private static String URL = "http://phoenix-dnr.ru/";

    //elements
    private SelenideElement buttonSearch = $(byClassName("ph-search__icon-container"));
    private SelenideElement inputSearch = $(byId("inputfind"));
    private SelenideElement linkFind;
    private SelenideElement statisticFind = $(byText("������� � ���������� ��������"));
    private SelenideElement website = $(byText("http://phoenix-dnr.ru/"));
    private SelenideElement exceptionSite = $(byText("403 Forbidden"));
    private SelenideElement buttonClose = $(byText("�������"));

    public void setLinkFind(String text) {
        linkFind = $(byText(text));
    }

    @SneakyThrows
    public void waitLinks(int milliseconds) {
        linkFind.waitUntil(Condition.exist, milliseconds);
    }

}
