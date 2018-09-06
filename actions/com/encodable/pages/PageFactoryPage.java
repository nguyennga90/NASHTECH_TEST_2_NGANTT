package com.encodable.pages;

import org.openqa.selenium.WebDriver;

public class PageFactoryPage {

	private static UploadPage uploadPage;
	private static ConfirmPage confirmPage;

	public static UploadPage getUploadPage(WebDriver driver) {
		if (uploadPage == null) {
			return new UploadPage(driver);
		} else {
			return uploadPage;
		}
	}

	public static ConfirmPage getConfirmPage(WebDriver driver) {
		if (confirmPage == null) {
			return new ConfirmPage(driver);
		} else {
			return confirmPage;
		}
	}

}
