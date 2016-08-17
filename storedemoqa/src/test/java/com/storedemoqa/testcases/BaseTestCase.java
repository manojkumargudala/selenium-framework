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
import com.storedemoqa.pageobjects.ProductCaterogyPageObject;
import com.storedemoqa.pageobjects.RegisterationPageObject;
import com.storedemoqa.pageobjects.SamplePageObject;
import com.storedemoqa.pageobjects.YourAccountPageObject;

public class BaseTestCase extends BaseDriverInitilization {
  HomePageObject homePageObject;

  @BeforeMethod
  public void SetUp() {
    homePageObject = new HomePageObject(driver, wait);
  }

  @Test
  public void loadUrl() {
    driver.get(readProp.readProperty("baseurl"));
    YourAccountPageObject yourAccountPageObject = homePageObject.clickMyAccount();
    yourAccountPageObject.verifyPageLoaded();
    RegisterationPageObject registrationPageObj = yourAccountPageObject.clickRegisterLink();
    registrationPageObj.verifyPageLoaded();
  }

  // Test case 9
  @Test(enabled = false)
  public void checkCategories() {
    driver.get(readProp.readProperty("baseurl"));
    ImacsCaterogyPageObject imacsCaterogyPageObject = homePageObject.goToImacsCategory();
    imacsCaterogyPageObject.verifyPageLoaded();
    driver.get(readProp.readProperty("baseurl"));
    IPadsCaterogyPageObject ipCaterogyPageObject = homePageObject.goToIPadsCategory();
    ipCaterogyPageObject.verifyPageLoaded();
    driver.get(readProp.readProperty("baseurl"));
    IPodsCaterogyPageObject ipodsCaterogyPageObject = homePageObject.goToIPodsCategory();
    ipodsCaterogyPageObject.verifyPageLoaded();
    driver.get(readProp.readProperty("baseurl"));
    IPhonesCaterogyPageObject iphonesCaterogyPageObject = homePageObject.goToIPhonesCategory();
    iphonesCaterogyPageObject.verifyPageLoaded();
  }

  // Test case 8
  @Test(enabled = false)
  public void goToCheckOutPage() {
    driver.get(readProp.readProperty("baseurl"));
    ImacsCaterogyPageObject imacsCaterogyPageObject = homePageObject.goToImacsCategory();
    imacsCaterogyPageObject.verifyPageLoaded();
    CheckOutPageObject checkoutPage = imacsCaterogyPageObject.goToCheckOutPage();
    // checkoutPage.verifyMagicMouseAddedToCart();
    checkoutPage.verifyIphoneAddedToCart();
  }

  // Test case 10
  @Test(enabled = false)
  public void goToProductDetailsCheckoutPage() {
    driver.get(readProp.readProperty("baseurl"));
    ProductCaterogyPageObject productCategoryObj = homePageObject.gotToProductCategory();
    productCategoryObj.verifyPageLoaded();
    CheckOutPageObject checkoutPage = productCategoryObj.goToCheckOutPage();
    checkoutPage.verifyIphoneAddedToCart();
  }

  // Test case 11
  @Test(enabled = false)
  public void verifyItemsAddedToCartPage() {
    driver.get(readProp.readProperty("baseurl"));
    ProductCaterogyPageObject productCategoryObj = homePageObject.gotToProductCategory();
    productCategoryObj.verifyPageLoaded();
    CheckOutPageObject checkoutPage = productCategoryObj.goToCheckOutPage();
    checkoutPage.verifyIphoneAddedToCart();
    driver.get(readProp.readProperty("baseurl"));
    checkoutPage = homePageObject.goToCheckOutPage();
    checkoutPage.verifyIphoneAddedToCart();
  }

  // Test case 12 -- Part A verify SPHOME links
  @Test(enabled = false)
  public void verifyPageFooterLinks() {
    driver.get(readProp.readProperty("baseurl"));
    ProductCaterogyPageObject productCategoryObj = homePageObject.gotToProductCategory();
    productCategoryObj.verifyPageLoaded();
    productCategoryObj.clickSpHomeLink();
    ImacsCaterogyPageObject imacsCaterogyPageObject = homePageObject.goToImacsCategory();
    imacsCaterogyPageObject.verifyPageLoaded();
    imacsCaterogyPageObject.clickSpHomeLink();
    IPadsCaterogyPageObject ipCaterogyPageObject = homePageObject.goToIPadsCategory();
    ipCaterogyPageObject.verifyPageLoaded();
    ipCaterogyPageObject.clickSpHomeLink();
    IPodsCaterogyPageObject ipodsCaterogyPageObject = homePageObject.goToIPodsCategory();
    ipodsCaterogyPageObject.verifyPageLoaded();
    ipodsCaterogyPageObject.clickSpHomeLink();
    IPhonesCaterogyPageObject iphonesCaterogyPageObject = homePageObject.goToIPhonesCategory();
    iphonesCaterogyPageObject.verifyPageLoaded();
    iphonesCaterogyPageObject.clickSpHomeLink();
  }

  // Test case 12 -- Part B verify Sample Page links
  @Test(enabled = false)
  public void verifySamplePageLinks() {
    driver.get(readProp.readProperty("baseurl"));
    ProductCaterogyPageObject productCategoryObj = homePageObject.gotToProductCategory();
    productCategoryObj.verifyPageLoaded();
    SamplePageObject samplePage = productCategoryObj.clickSamplePageLink();
    samplePage.verifyPageLoaded();
    ImacsCaterogyPageObject imacsCaterogyPageObject = homePageObject.goToImacsCategory();
    imacsCaterogyPageObject.verifyPageLoaded();
    samplePage = imacsCaterogyPageObject.clickSamplePageLink();
    samplePage.verifyPageLoaded();
    IPadsCaterogyPageObject ipCaterogyPageObject = homePageObject.goToIPadsCategory();
    ipCaterogyPageObject.verifyPageLoaded();
    samplePage = ipCaterogyPageObject.clickSamplePageLink();
    samplePage.verifyPageLoaded();
    IPodsCaterogyPageObject ipodsCaterogyPageObject = homePageObject.goToIPodsCategory();
    ipodsCaterogyPageObject.verifyPageLoaded();
    samplePage = ipodsCaterogyPageObject.clickSamplePageLink();
    samplePage.verifyPageLoaded();
    IPhonesCaterogyPageObject iphonesCaterogyPageObject = homePageObject.goToIPhonesCategory();
    iphonesCaterogyPageObject.verifyPageLoaded();
    samplePage = iphonesCaterogyPageObject.clickSamplePageLink();
    samplePage.verifyPageLoaded();
  }
}
