package com.hm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hm.utils.logs.Log;

public class LandingPage extends Base{
	
	private WebDriver driver;
	
	public final String expLandingPageTitle = "The Home Depot";
	
	public final String[] expectedShopplingListLabels = {
															"All Departments",
															"Home Decor, Furniture & Kitchenware",
															"DIY Projects & Ideas",
															"Project Calculators",
															"Installation & Services",
															"Specials & Offers",
															"Local Ad & Catalog" 
															};
	
	//SVG icon
	// .//*[@class="ml-2 cursor-pointer z-[1000]"]
	@FindBy(xpath="//body/div[@id='__nuxt']/div[@id='__layout']/div[1]/div[1]/div[1]/div[1]/div[1]/*[2]")
	WebElement gridIcon;
	
	@FindBy(css=".ShoppingLinks")
	WebElement shoppingLinks;
	
	//   placeholder "YOUR LETTERS"   .//*[@class="block mobile:block"]
	
	public LandingPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		
	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	public boolean validateGridIcon() {
		return gridIcon.isDisplayed();
	}
	
	public void clickValidateGridIcon() {
		gridIcon.click();
	}

	public WebElement getShoppingLinks() {
		return shoppingLinks;
	}
	
	public List<WebElement> getShoppingLinksList() {
		return shoppingLinks.findElements(By.tagName("li"));
	}

	public String getLandingPageTitle() {
		Log.debug("getLandingPageTitle...");
		return driver.getTitle();
	}
}
