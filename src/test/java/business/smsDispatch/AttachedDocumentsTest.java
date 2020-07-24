package business.smsDispatch;

import com.tngtech.junit.dataprovider.DataProviderExtension;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import common.CommonPageActions;
import common.CommonSteps;
import io.qameta.allure.*;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(UseDataProviderExtension.class)
@ExtendWith(DataProviderExtension.class)
@Epic("������ '������' - ����������� ���������")
@Feature("������������ ���������� ����������� ���������� � ���������� 'SMS ��������'")
public class AttachedDocumentsTest {
    private static SmsDispatchPage smsDispatchPage;

    @Step("������ �� ������ '������ �� ���-�������� ��� ����������� ���'")
    public void clickOnLink() {
        smsDispatchPage.getRequestDoc().click();
    }

    @Step("���������, ��� ���� ������ � ������")
    public void checkIsFileDownloaded(File file) {
        assertNotNull(file);
    }

    @Step("������� �������� '������� �� ���-�������� ��� ����������� ���.doc'")
    public File downloadDocDocument() {
        try {
            return smsDispatchPage.getContractDoc().download();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Step("������� �������� '������ �� ���-�������� ��� ����������� ���.pdf'")
    public File downloadPdfDocument() {
        try {
            return smsDispatchPage.getRequestDoc().download();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Step("������� �������� '��������_���������.rar'")
    public File downloadRarDocument() {
        try {
            return smsDispatchPage.getStatutoryDoc().download();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Step("���������, ��� ��� ������ ������ '{docNameExpected}'")
    public void cheIsDocNameAsExpected(String docNameExpected, String docNameActual) {
        try {
            assertEquals(docNameExpected, URLDecoder.decode(docNameActual, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Step("������� ���� �������")
    public static void closeAdvertisingPopup() {
        smsDispatchPage.getCloseAdvertisingButton().click();
        smsDispatchPage.waitWhile();
    }

    @BeforeAll
    public static void openPage() {
        smsDispatchPage=new SmsDispatchPage();
        CommonSteps.openPage(SmsDispatchPage.getPageName(),SmsDispatchPage.getURL());
        closeAdvertisingPopup();
    }

    @Test
    @DisplayName("���������� doc ���������")
    @Description("���������� ��������� '������� �� ���-�������� ��� ����������� ���.doc'")
    @Severity(SeverityLevel.MINOR)
    public void shouldDownloadDocDocument() {
        File contract=downloadDocDocument();
        checkIsFileDownloaded(contract);
        cheIsDocNameAsExpected("������� �� ���-�������� ��� ����������� ���.doc", contract.getName());
    }

    @Test
    @DisplayName("���������� pdf ���������")
    @Description("���������� ��������� '������ �� ���-�������� ��� ����������� ���.pdf'")
    @Severity(SeverityLevel.MINOR)
    public void shouldDownloadPdfDocument() {
        File request = downloadPdfDocument();
        checkIsFileDownloaded(request);
        cheIsDocNameAsExpected("������ �� ���-�������� ��� ����������� ���.pdf", request.getName());
        clickOnLink();
        CommonPageActions.switchToAnotherWindow(1);
        CommonSteps.checkIsCorrectUrl("http://phoenix-dnr.ru/documents/%D0%A1%D0%9C%D0%A1-%D1%80%D0%B0%D1%81%D1%81%D" +
                "1%8B%D0%BB%D0%BA%D0%B0/%D0%97%D0%90%D0%AF%D0%92%D0%9A%D0%90%20%D0%9D%D0%90%20%D0%A1%D0%9C%D0%A1-%D1" +
                "%80%D0%B0%D1%81%D1%81%D1%8B%D0%BB%D0%BA%D1%83%20%D0%94%D0%9B%D0%AF%20%D0%AE%D0%A0%D0%98%D0%94%D0%98" +
                "%D0%A7%D0%95%D0%A1%D0%9A%D0%98%D0%A5%20%D0%9B%D0%98%D0%A6.pdf");
        CommonPageActions.closeCurrentTab();
        CommonPageActions.returnToMain();
    }

    @Test
    @DisplayName("���������� rar ���������")
    @Description("���������� ��������� '��������_���������.rar'")
    @Severity(SeverityLevel.MINOR)
    public void shouldDownloadRarDocument() {
        File statutory = downloadRarDocument();
        checkIsFileDownloaded(statutory);
        cheIsDocNameAsExpected("��������_���������.rar", statutory.getName());
    }

    @AfterAll
    public static void deleteDirectory() {
        try {
            FileUtils.deleteDirectory(new File("build/downloads"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}