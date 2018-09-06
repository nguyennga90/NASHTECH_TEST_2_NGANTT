package com.encodable.upload;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.encodable.pages.ConfirmPage;
import com.encodable.pages.PageFactoryPage;
import com.encodable.pages.UploadPage;

import commons.AbstractTest;
import commons.ExcelUtils;

public class UploadFile extends AbstractTest {

	WebDriver driver;

	private String workingDir, fileUpload, strUploadTo, strSubFolder, strEmail, strFirstName, fileUploadDir;
	private UploadPage uploadPage;
	private ConfirmPage confirmPage;

	@Parameters({ "browser", "url", "version" })
	@BeforeClass
	public void beforeClass(String browser, String url, String version) {
		driver = openMultiBrowser(browser, url, version);

		workingDir = System.getProperty("user.dir");
		try {
			ExcelUtils.setExcelFile(workingDir + "\\Data\\TestData.xlsx", "Sheet1");
			fileUpload = ExcelUtils.getCellData(1, 0);
			strUploadTo = ExcelUtils.getCellData(1, 1);
			strSubFolder = ExcelUtils.getCellData(1, 2);
			strEmail = ExcelUtils.getCellData(1, 3);
			strFirstName = ExcelUtils.getCellData(1, 4);
			
			fileUploadDir = workingDir + "\\file_upload\\" + fileUpload;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void uploadFile() {
		uploadPage = PageFactoryPage.getUploadPage(driver);
		
		uploadPage.attachFile(fileUploadDir);
		uploadPage.chooseDirToUpload(strUploadTo);
		uploadPage.inputSubfolderName(strSubFolder);
		uploadPage.inputEmail(strEmail);
		uploadPage.inputFirstName(strFirstName);
		
		confirmPage = uploadPage.clickUploadBtn();
		
		verifyTrue(confirmPage.verifyEmail(strEmail));
		verifyTrue(confirmPage.verifyFirstName(strFirstName));
		verifyTrue(confirmPage.verifyFileName(fileUpload));
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
