package com.storedemoqa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demoqa.selenium.BaseDriverInitilization;
import com.storedemoqa.pageobjects.CheckOutPageObject;
import com.storedemoqa.pageobjects.HomePageObject;
import com.storedemoqa.pageobjects.IPadsCaterogyPageObject;
import com.storedemoqa.pageobjects.IPhonesCaterogyPageObject;
import com.storedemoqa.pageobjects.IPodsCaterogyPageObject;
import com.storedemoqa.pageobjects.ImacsCaterogyPageObject;
import com.storedemoqa.pageobjects.PageFooter;
import com.storedemoqa.pageobjects.ProductCaterogyPageObject;
import com.storedemoqa.pageobjects.SamplePageObject;
import com.storedemoqa.pageobjects.YourAccountPageObject;

public class BaseTestCase extends BaseDriverInitilization {
	HomePageObject homePageObject;
	PageFooter pageFooter;

	@BeforeMethod
	public void SetUp() {
		pageFooter = new PageFooter(driver, wait);
		homePageObject = new HomePageObject(driver, wait, pageFooter);
	}

	@Test
	public void loadUrl() {
		driver.get(readProp.readProperty("baseurl"));
		YourAccountPageObject yourAccountPageObject = homePageObject
				.clickMyAccount();
		yourAccountPageObject.verifyPageLoaded();
		yourAccountPageObject.clickRegisterLink();
	}

	// Test case 9
	@Test
	public void checkCategories() {
		driver.get(readProp.readProperty("baseurl"));
		ImacsCaterogyPageObject imacsCaterogyPageObject = homePageObject
				.goToImacsCategory();
		imacsCaterogyPageObject.verifyPageLoaded();
		driver.get(readProp.readProperty("baseurl"));
		IPadsCaterogyPageObject ipCaterogyPageObject = homePageObject
				.goToIPadsCategory();
		ipCaterogyPageObject.verifyPageLoaded();
		driver.get(readProp.readProperty("baseurl"));
		IPodsCaterogyPageObject ipodsCaterogyPageObject = homePageObject
				.goToIPodsCategory();
		ipodsCaterogyPageObject.verifyPageLoaded();
		driver.get(readProp.readProperty("baseurl"));
		IPhonesCaterogyPageObject iphonesCaterogyPageObject = homePageObject
				.goToIPhonesCategory();
		iphonesCaterogyPageObject.verifyPageLoaded();
	}

	// Test case 8
	@Test
	public void goToCheckOutPage() {
		driver.get(readProp.readProperty("baseurl"));
		ImacsCaterogyPageObject imacsCaterogyPageObject = homePageObject
				.goToImacsCategory();
		imacsCaterogyPageObject.verifyPageLoaded();
		CheckOutPageObject checkoutPage = imacsCaterogyPageObject
				.goToCheckOutPage();
		// checkoutPage.verifyMagicMouseAddedToCart();
		checkoutPage.verifyIphoneAddedToCart();
	}

	// Test case 10
	@Test
	public void goToProductDetailsCheckoutPage() {
		driver.get(readProp.readProperty("baseurl"));
		ProductCaterogyPageObject productCategoryObj = homePageObject
				.gotToProductCategory();
		productCategoryObj.verifyPageLoaded();
		CheckOutPageObject checkoutPage = productCategoryObj.goToCheckOutPage();
		checkoutPage.verifyIphoneAddedToCart();
	}

	// Test case 11
	@Test
	public void verifyItemsAddedToCartPage() {
		driver.get(readProp.readProperty("baseurl"));
		ProductCaterogyPageObject productCategoryObj = homePageObject
				.gotToProductCategory();
		productCategoryObj.verifyPageLoaded();
		CheckOutPageObject checkoutPage = productCategoryObj.goToCheckOutPage();
		checkoutPage.verifyIphoneAddedToCart();
		driver.get(readProp.readProperty("baseurl"));
		checkoutPage = homePageObject.goToCheckOutPage();
		checkoutPage.verifyIphoneAddedToCart();
	}

	// Test case 12 -- Part A verify SPHOME links
	@Test
	public void verifyPageFooterLinks() {
		driver.get(readProp.readProperty("baseurl"));
		ProductCaterogyPageObject productCategoryObj = homePageObject
				.gotToProductCategory();
		productCategoryObj.verifyPageLoaded();
		productCategoryObj.clickSpHomeLink();
		ImacsCaterogyPageObject imacsCaterogyPageObject = homePageObject
				.goToImacsCategory();
		imacsCaterogyPageObject.verifyPageLoaded();
		imacsCaterogyPageObject.clickSpHomeLink();
		IPadsCaterogyPageObject ipCaterogyPageObject = homePageObject
				.goToIPadsCategory();
		ipCaterogyPageObject.verifyPageLoaded();
		ipCaterogyPageObject.clickSpHomeLink();
		IPodsCaterogyPageObject ipodsCaterogyPageObject = homePageObject
				.goToIPodsCategory();
		ipodsCaterogyPageObject.verifyPageLoaded();
		ipodsCaterogyPageObject.clickSpHomeLink();
		IPhonesCaterogyPageObject iphonesCaterogyPageObject = homePageObject
				.goToIPhonesCategory();
		iphonesCaterogyPageObject.verifyPageLoaded();
		iphonesCaterogyPageObject.clickSpHomeLink();
	}

	// Test case 12 -- Part B verify Sample Page links
	@Test
	public void verifySamplePageLinks() {
		driver.get(readProp.readProperty("baseurl"));
		ProductCaterogyPageObject productCategoryObj = homePageObject
				.gotToProductCategory();
		productCategoryObj.verifyPageLoaded();
		SamplePageObject samplePage = productCategoryObj.clickSamplePageLink();
		samplePage.verifyPageLoaded();
		ImacsCaterogyPageObject imacsCaterogyPageObject = homePageObject
				.goToImacsCategory();
		imacsCaterogyPageObject.verifyPageLoaded();
		samplePage = imacsCaterogyPageObject.clickSamplePageLink();
		samplePage.verifyPageLoaded();
		IPadsCaterogyPageObject ipCaterogyPageObject = homePageObject
				.goToIPadsCategory();
		ipCaterogyPageObject.verifyPageLoaded();
		samplePage = ipCaterogyPageObject.clickSamplePageLink();
		samplePage.verifyPageLoaded();
		IPodsCaterogyPageObject ipodsCaterogyPageObject = homePageObject
				.goToIPodsCategory();
		ipodsCaterogyPageObject.verifyPageLoaded();
		samplePage = ipodsCaterogyPageObject.clickSamplePageLink();
		samplePage.verifyPageLoaded();
		IPhonesCaterogyPageObject iphonesCaterogyPageObject = homePageObject
				.goToIPhonesCategory();
		iphonesCaterogyPageObject.verifyPageLoaded();
		samplePage = iphonesCaterogyPageObject.clickSamplePageLink();
		samplePage.verifyPageLoaded();
	}
}
