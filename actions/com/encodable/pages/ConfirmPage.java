package com.encodable.pages;

import org.openqa.selenium.WebDriver;

import com.encodable.ui.UploadDemoConfirmUI;

import commons.AbstractPage;

public class ConfirmPage extends AbstractPage {

	WebDriver driver;

	public ConfirmPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verifyEmail(String strMail) {
		waitForControlNotVisible(driver, UploadDemoConfirmUI.PROG_BAR);
		waitForControlVisible(driver, UploadDemoConfirmUI.EMAIL_TEXT, strMail);
		;
		return isControlIsDisplay(driver, UploadDemoConfirmUI.EMAIL_TEXT, strMail);
	}

	public boolean verifyFirstName(String strName) {
		waitForControlVisible(driver, UploadDemoConfirmUI.NAME_TEXT, strName);
		return isControlIsDisplay(driver, UploadDemoConfirmUI.NAME_TEXT, strName);
	}

	public boolean verifyFileName(String strFileName) {
		waitForControlVisible(driver, UploadDemoConfirmUI.FILE_NAME_TEXT, strFileName);
		return isControlIsDisplay(driver, UploadDemoConfirmUI.FILE_NAME_TEXT, strFileName);
	}
}
