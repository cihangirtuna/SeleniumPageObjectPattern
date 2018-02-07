package test;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import page.SearchPage;

public class SearchTest {

	@Test
	public void checkSearchForNoResult() {

		SearchPage searchPage = new SearchPage();

		searchPage.openQueryPage();
		searchPage.writeTextToSearchField("noFound");
		searchPage.tabSearchButton();

		searchPage.waitTime(4000);
		searchPage.getZeroResults("noFound");

		searchPage.stopDriver();
	}

	@Test
	public void checkResultLinkIsClickable() {

		SearchPage searchPage = new SearchPage();

		searchPage.openQueryPage();
		searchPage.writeTextToSearchField("television");
		searchPage.tabSearchButton();

		searchPage.waitTime(4000);

		List<WebElement> elemList = searchPage.getResultLinks();
		searchPage.isClickable(elemList);

		searchPage.stopDriver();
	}

	@Test
	public void checkCurrentUrl() {

		SearchPage searchPage = new SearchPage();

		searchPage.openQueryPage();
		searchPage.writeTextToSearchField("television");
		searchPage.tabSearchButton();

		searchPage.waitTime(4000);
		searchPage.checkCurrentUrl("television");

		searchPage.stopDriver();
	}

	@Test
	public void checkSearchMoreThanOneWord() {

		SearchPage searchPage = new SearchPage();

		searchPage.openQueryPage();
		searchPage.writeTextToSearchField("4K TV");
		searchPage.tabSearchButton();

		searchPage.waitTime(4000);

		List<WebElement> elemList = searchPage.getResultLinks();
		searchPage.isClickable(elemList);
		searchPage.checkCurrentUrl("4K+TV");

		searchPage.stopDriver();
	}
	
	@Test
	public void checkSearchMoreThanOneTime() {

		SearchPage searchPage = new SearchPage();

		searchPage.openQueryPage();
		searchPage.writeTextToSearchField("phone");
		searchPage.tabSearchButton();

		searchPage.waitTime(4000);

		List<WebElement> elemList = searchPage.getResultLinks();
		searchPage.isClickable(elemList);
		searchPage.checkCurrentUrl("phone");
		
		searchPage.writeTextToSearchField("radio");
		searchPage.tabSearchButton();

		searchPage.waitTime(4000);

		List<WebElement> elemList2 = searchPage.getResultLinks();
		searchPage.isClickable(elemList2);
		searchPage.checkCurrentUrl("radio");

		searchPage.stopDriver();
	}

	@Test
	public void checkNarrowedResultCount() {

		SearchPage searchPage = new SearchPage();

		searchPage.openQueryPage();
		searchPage.writeTextToSearchField("television");
		searchPage.tabSearchButton();

		searchPage.waitTime(4000);
		String resultCount = searchPage.getResultCount();
		String countNarrow = searchPage.getFirstNarrowLinksAndClick();

		searchPage.waitTime(4000);
		searchPage.checkResultCount(countNarrow);
		searchPage.closeNarrow();
		searchPage.waitTime(4000);
		searchPage.checkResultCount(resultCount);

		searchPage.stopDriver();
	}

	@Test
	public void checkLoadMoreButton() {

		SearchPage searchPage = new SearchPage();

		searchPage.openQueryPage();
		searchPage.writeTextToSearchField("television");
		searchPage.tabSearchButton();

		searchPage.waitTime(4000);
		List<WebElement> elemList = searchPage.getResultLinks();
		searchPage.clickMoreButton();
		searchPage.waitTime(4000);
		List<WebElement> elemList2 = searchPage.getResultLinks();
		searchPage.checkElemListSize(elemList, elemList2);

		searchPage.stopDriver();
	}

	@Test
	public void checkEnterKeyIsSearching() {

		SearchPage searchPage = new SearchPage();

		searchPage.openQueryPage();
		searchPage.writeTextToSearchField("television");
		searchPage.tabEnterButton();

		searchPage.waitTime(4000);
		List<WebElement> elemList = searchPage.getResultLinks();
		searchPage.isClickable(elemList);

		searchPage.stopDriver();
	}
	
	@Test
	public void checkSearchWithProductName() {

		SearchPage searchPage = new SearchPage();

		searchPage.openQueryPage();
		searchPage.writeTextToSearchField("X850D");
		searchPage.tabSearchButton();

		searchPage.waitTime(4000);
		searchPage.checkProductName("X850D");

		searchPage.stopDriver();
	}
}
