package mobileCommunication.mobileCommunication.services;

import io.qameta.allure.Step;

public class DefaultStepsService {

    @Step("Открыть страницу Услуги")
    public static ServicePage openPage(){
        return new ServicePage();
    }

}
