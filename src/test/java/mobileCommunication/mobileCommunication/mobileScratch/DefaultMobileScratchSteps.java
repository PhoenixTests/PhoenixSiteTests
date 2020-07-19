package mobileCommunication.mobileCommunication.mobileScratch;

import io.qameta.allure.Step;

import java.util.Random;

public abstract class DefaultMobileScratchSteps {
    protected static MobileScratchPage mobileScratchPage;
    protected static Random random;

    @Step("������ �� ������ ���������� ����� ���������� � ������� ������-������")
    public void clickOnRefillByScratch() {
        mobileScratchPage.getRefillByScratch().click();
    }

    @Step("������� ����� �� ����������� ������ (���������� � ������� ������-����)")
    public void selectCityByScratch() {
        mobileScratchPage.getByScratchSelectCity().selectOption(random.nextInt(mobileScratchPage.getByScratchSalePoints().size()));
    }
}
