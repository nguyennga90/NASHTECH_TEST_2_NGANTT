package com.encodable.pages;

import org.openqa.selenium.WebDriver;

import com.encodable.ui.UploadDemoUI;

import commons.AbstractPage;

public class UploadPage extends AbstractPage{

	WebDriver driver;

	public UploadPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void attachFile(String strFile) {
		waitForControlVisible(driver, UploadDemoUI.UPLOAD_TXT);
		sendKeyToElement(driver, UploadDemoUI.UPLOAD_TXT, strFile);
	}
	
	public void chooseDirToUpload(String strDir) {
		waitForControlVisible(driver, UploadDemoUI.UPLOAD_TO_PDN);
		selectItemInDropdown(driver, UploadDemoUI.UPLOAD_TO_PDN, strDir);
	}
	
	public void inputSubfolderName(String strName) {
		waitForControlVisible(driver, UploadDemoUI.SUB_FOLDER_TXT);
		sendKeyToElement(driver, UploadDemoUI.SUB_FOLDER_TXT, strName);
	}
	
	public void inputEmail(String strMail) {
		waitForControlVisible(driver, UploadDemoUI.EMAIL_TXT);
		sendKeyToElement(driver, UploadDemoUI.EMAIL_TXT, strMail);
	}

	public void inputFirstName(String strFirstName) {
		waitForControlVisible(driver, UploadDemoUI.FIRST_NAME_TXT);
		sendKeyToElement(driver, UploadDemoUI.FIRST_NAME_TXT, strFirstName);
	}
	
	public ConfirmPage clickUploadBtn() {
		waitForControlVisible(driver, UploadDemoUI.UPLOAD_BTN);
		clickToElement(driver, UploadDemoUI.UPLOAD_BTN);
		return PageFactoryPage.getConfirmPage(driver);
	}
}
