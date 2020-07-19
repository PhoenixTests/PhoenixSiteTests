package mobileCommunication.mobileCommunication.mobileScratch;

import io.qameta.allure.Step;

import java.util.Random;

public abstract class DefaultMobileScratchSteps {
    protected static MobileScratchPage mobileScratchPage;
    protected static Random random;

    @Step("Нажать на способ пополнения счета «Пополнить с помощью скретч-карты»")
    public void clickOnRefillByScratch() {
        mobileScratchPage.getRefillByScratch().click();
    }

    @Step("Выбрать город из выпадающего списке (пополнение с помощью скретч-карт)")
    public void selectCityByScratch() {
        mobileScratchPage.getByScratchSelectCity().selectOption(random.nextInt(mobileScratchPage.getByScratchSalePoints().size()));
    }
}
