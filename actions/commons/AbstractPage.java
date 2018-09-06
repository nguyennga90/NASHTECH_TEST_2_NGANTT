package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AbstractPage {

	private int timeout = 30;

	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void clickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void clickToElement(WebDriver driver, String locator, String value) {
		String specialLocate = String.format(locator, value);
		WebElement element = driver.findElement(By.xpath(specialLocate));
		element.click();
	}

	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (!element.getText().isEmpty() || !element.getAttribute("value").isEmpty()) {
			element.clear();
		}
		element.sendKeys(value);
	}

	public void sendKeyToElementCSS(WebDriver driver, String locator, String value) {
		WebElement element = driver.findElement(By.cssSelector(locator));
		if (!element.getText().isEmpty() || !element.getAttribute("value").isEmpty()) {
			element.clear();
		}
		element.sendKeys(value);
	}

	public void sendKeyToElement(WebDriver driver, String locator, Keys key) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(key);
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String value) {
		Select select = new Select(driver.findElement(By.xpath(locator)));
		select.selectByVisibleText(value);
	}

	public String getFirstItemSelected(WebDriver driver, String locator) {
		Select select = new Select(driver.findElement(By.xpath(locator)));
		return select.getFirstSelectedOption().getText();
	}

	public String getAttribute(WebDriver driver, String locator, String attribute) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attribute);
	}

	public String getTextElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}

	public String getTextElement(WebDriver driver, String locate, String value) {
		String specialLocate = String.format(locate, value);
		WebElement element = driver.findElement(By.xpath(specialLocate));
		return element.getText();
	}

	public int getSize(WebDriver driver, String locator) {
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		return elements.size();
	}
	
	public void uncheckTheCheckbox(WebDriver driver, String locator) {
		WebElement checkbox = driver.findElement(By.xpath(locator));
		if (checkbox.isSelected()) {
			checkbox.click();
		}
	}

	public boolean isControlIsDisplay(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlIsDisplay(WebDriver driver, String locator, String value) {
		String specialLocate = String.format(locator, value);
		WebElement element = driver.findElement(By.xpath(specialLocate));
		return element.isDisplayed();
	}

	public boolean isControlIsSelected(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

	public boolean isControlIsEnable(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}

	public Alert switchToAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		return alert;
	}

	public void acceptAlert(Alert alert) {
		alert.accept();
	}

	public void cancelAlert(Alert alert) {
		alert.dismiss();
	}

	public String getTextAlert(Alert alert) {
		return alert.getText();
	}

	public String getJsAlertText(WebDriver driver) {
		Object txt = ((JavascriptExecutor) driver).executeScript("return window.alert.myAlertText;");
		return (String) txt;
	}

	public void sendKeyToAlert(WebDriver driver, String msg, Alert alert) {
		alert.sendKeys(msg);
		alert.accept();
	}

	public void switchToWindowByID(WebDriver driver, String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String childWindows : allWindows) {
			if (!childWindows.equals(parent)) {
				driver.switchTo().window(childWindows);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String childWindows : allWindows) {
			driver.switchTo().window(childWindows);
			String childTitle = driver.getTitle();
			if (childTitle.equals(title)) {
				break;
			}
		}
	}

	public void switchToIframe(WebDriver driver, String locator) {
		WebElement frame = driver.findElement(By.xpath(locator));
		driver.switchTo().frame(frame);
	}

	public void backDefaultWindow(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public Actions setAction(WebDriver driver) {
		Actions action = new Actions(driver);
		return action;
	}

	public void singleClick(WebDriver driver, String locator, Actions action) {
		WebElement element = driver.findElement(By.xpath(locator));
		action.click(element).build().perform();
	}

	public void doubleClick(WebDriver driver, String locator, Actions action) {
		WebElement element = driver.findElement(By.xpath(locator));
		action.doubleClick(element).build().perform();
	}

	public void hoverMouse(WebDriver driver, String locator, Actions action) {
		WebElement element = driver.findElement(By.xpath(locator));
		action.moveToElement(element).build().perform();
	}

	public void rightClick(WebDriver driver, String locator, Actions action) {
		WebElement element = driver.findElement(By.xpath(locator));
		action.contextClick(element).build().perform();
	}

	public void dragAndDrop(WebDriver driver, String source, String target, Actions action) {
		WebElement eSource = driver.findElement(By.xpath(source));
		WebElement eTarget = driver.findElement(By.xpath(target));
		action.dragAndDrop(eSource, eTarget).build().perform();
	}

	public void keyPress(WebDriver driver, String locator, String key, Actions action) {
		WebElement element = driver.findElement(By.xpath(locator));
		action.sendKeys(element, key).build().perform();
	}

	public void uploadFile(WebDriver driver, String locator, String filePath) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(filePath);
	}

	public Object executeJavascriptToBrowser(WebDriver driver, String javaSript) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(javaSript);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object executeJavascriptToElement(WebDriver driver, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public void scrollToBottomPage(WebDriver driver) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void scrollToElement(WebDriver driver, String locator) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.id(locator));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void setTextToElementByTagName(WebDriver driver, String tagName, String content) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementsByTagName('" + tagName + "')[0].innerHTML = '" + content + "'");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void setTextToElement(WebDriver driver, String locator, String content) {
		WebElement element = driver.findElement(By.xpath(locator));
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].innerHTML = '" + content + "'", element);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void waitForControlPresence(WebDriver driver, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		By by = By.xpath(locator);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public void waitForControlVisible(WebDriver driver, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		By by = By.xpath(locator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForControlVisible(WebDriver driver, String locator, String value) {
		String specialLocate = String.format(locator, value);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		By by = By.xpath(specialLocate);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForControlClickable(WebDriver driver, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		By by = By.xpath(locator);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void waitForControlNotVisible(WebDriver driver, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		By by = By.xpath(locator);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public void waitForControlAlertPresence(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (TimeoutException eTO) {
			eTO.printStackTrace();
		}
	}

	public void assertTextEqual(WebDriver driver, String locate, String value) {
		WebElement msg = driver.findElement(By.xpath(locate));
		Assert.assertEquals(value, msg.getText());
	}

	public void assertTextEqual(String strActual, String strExpect) {
		Assert.assertEquals(strActual, strExpect);
	}

	public void assertMsgDisplay(WebDriver driver, String locate) {
		WebElement msg = driver.findElement(By.xpath(locate));
		Assert.assertTrue(msg.isDisplayed());
	}

}
