package mobileCommunication.mobileCommunication.services;

import com.codeborne.selenide.Condition;
import com.tngtech.junit.dataprovider.*;
import common.CommonSteps;
import io.qameta.allure.*;

import mobileCommunication.mobileCommunication.rates.RatePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(UseDataProviderExtension.class)
@ExtendWith(DataProviderExtension.class)
public class TestLabel {

    private ServicePage servicePage = new ServicePage();

    @DataProvider
    public static Object[][] dataLabel() {
        return new Object[][] {
                {"Услуга «Вам звонили»", "body_0y", "spoiler_0y"},
                {"Услуга «Telegram - Вам звонили»", "body_0", "spoiler_0"},
                {"Услуга «Мобильный перевод»", "body_1", "spoiler_1"},
                {"Услуга «Отложенный платеж»", "body_2", "spoiler_2"},
                {"Услуга «Перезвони мне»", "body_3", "spoiler_3"},
                {"Услуга «Красивый номер»", "body_4", "spoiler_4"},
                {"Услуга «Восстановление номера»", "body_5", "spoiler_5"},
                {"Услуга «Переадресация вызова»", "body_6", "spoiler_6"},
                {"Услуга «Ожидание вызова»", "body_7", "spoiler_7"},
                {"Услуга «АнтиАОН»", "body_8", "spoiler_8"}
            };
    }

    @Step("Нажатие на метку: {0}")
    public void clickLabel(String nameLabel)
    {
        servicePage.setLabel(nameLabel);
        servicePage.getLabel().click();
    }

    @Step("Проверка, открылось ли нужное описание для услуги")
    public boolean checkBodyOpen(String idBody)
    {
       return servicePage.setBody(idBody).isDisplayed();
    }

    @Step("Проверка, закрылось ли нужное описание для услуги")
    public boolean checkBodyClose(String idBody)
    {
        return servicePage.setBody(idBody).waitWhile(Condition.visible, 1000).isDisplayed();
    }

    @Step("Нажатие на кнопку \"^\" для закрытия информации об услуге")
    public void clickButton(String idButton)
    {
        servicePage.setButton(idButton).scrollTo();
        servicePage.getButton().click();
    }

    @Epic("Услуги")
    @TestTemplate
    @UseDataProvider("dataLabel")
    @DisplayName("Просмотр информации об услуге")
    @Description("Тест на просмотр информации по каждой услуге нажатием на название")
    @Severity(SeverityLevel.NORMAL)
    public void label(String nameLabel, String idBody, String idButton){
        CommonSteps.openPage(servicePage.getPageName(), servicePage.getURL());
        clickLabel(nameLabel);
        assertEquals(true, checkBodyOpen(idBody));
        clickButton(idButton);
        assertEquals(false, checkBodyClose(idBody));
    }

}
