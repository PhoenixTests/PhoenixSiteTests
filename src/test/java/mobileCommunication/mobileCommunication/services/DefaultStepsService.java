package mobileCommunication.mobileCommunication.services;

import io.qameta.allure.Step;

public class DefaultStepsService {

    @Step("������� �������� ������")
    public static ServicePage openPage(){
        return new ServicePage();
    }

}
