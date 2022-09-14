package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private  SelenideElement messError = $("[data-test-id='error-notification'] .notification__content");

    public void isVisibleVerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        getAndSendVerificationCode(verificationCode);
        return new DashboardPage();
    }

    public void getAndSendVerificationCode(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
    }

    public void errorMessInvalidVerificationCode() {
        messError.shouldBe(visible);
    }
}
