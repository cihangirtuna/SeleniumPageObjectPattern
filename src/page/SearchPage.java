package page;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import global.BasePage;
import junit.framework.Assert;

public class SearchPage extends BasePage{

	private By query;
	private By searchButton;
	private By zeroResults;
	private By resultCount;
	private By searchResults;
	private By loadMoreButton;
	private By narrowLink;
	private By closeNarrow;
	private By productName;
	
	public SearchPage() {
		query = By.cssSelector(".search");
		searchButton = By.cssSelector(".btn-search");
		zeroResults = By.cssSelector(".zero-results");
		resultCount = By.cssSelector(".bold-dark.p3-bold");
		searchResults = By.cssSelector(".invisible-link");
		loadMoreButton = By.cssSelector(".search-more");
		narrowLink = By.cssSelector("div.result > a.primary-link");
		closeNarrow = By.cssSelector(".label-close");
		productName = By.cssSelector("div.results-p > h3.lt4");
	    }
	
	public void openQueryPage(){		
		driver.get("http://www.sony.com/search?query=");
	}
	
	public void writeTextToSearchField(String text){
		driver.findElement(query).sendKeys(text);
	}
	
	public void tabSearchButton(){
		driver.findElement(searchButton).click();
	}
	
	public void tabEnterButton(){
		driver.findElement(searchButton).sendKeys(Keys.RETURN);
	}
	
	public void getZeroResults(String expected){
		String actual = driver.findElement(zeroResults).getText();
		Assert.assertEquals("Your search for \"" + expected + "\" returned 0 results", actual);
	}
	
	public String getResultCount(){		
		return driver.findElement(resultCount).getText();	
	}
	
	public void checkResultCount(String expected){		
		String count = driver.findElement(resultCount).getText();
		Assert.assertEquals(expected, count);	
	}
	
	public List<WebElement> getResultLinks(){
		List<WebElement> elemList = driver.findElements(searchResults);
		return elemList;	
	}
	
	public void clickMoreButton(){
		driver.findElement(loadMoreButton).click();
	}
	
	public void isClickable(List<WebElement> elem) {

		if (elem.isEmpty()) {
			Assert.assertTrue(false);
		}
		try {
			for (WebElement el : elem) {
				ExpectedConditions.elementToBeClickable(el);
			}
			Assert.assertTrue(true);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	public String getFirstNarrowLinksAndClick(){
		List<WebElement> elemList = driver.findElements(narrowLink);	
		// can not click with WebElement click method
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", elemList.get(0));

		return elemList.get(0).getText().replaceAll("[^\\d.]", "");
	}
	
	public void closeNarrow(){
		driver.findElement(closeNarrow).click();
	}
	
	public void checkCurrentUrl(String searched){	
		String currentUrl = driver.getCurrentUrl();
		String expected = "https://www.sony.com/search?query=" + searched;
		Assert.assertEquals(expected, currentUrl);	
	}
	
	public void checkElemListSize(List<WebElement> l1, List<WebElement> l2){	
		Assert.assertTrue(l1.size() < l2.size());
	}
	
	public void checkProductName(String text){
		String headerText = driver.findElements(productName).get(0).getText();		
		Assert.assertTrue(headerText.contains(text));	
	}
}