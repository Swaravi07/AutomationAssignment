package day1;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class AutomationAssignment {
	public static void main(String[] args) throws Exception {
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			
		//Step1: Navigate to the FitPeo Homepage:
			
		    driver.get("https://www.fitpeo.com/home");
		
		//Step2: Navigate to the Revenue Calculator Page:
		    
		    driver.findElement(By.linkText("Revenue Calculator")).click();
		    
		//Step3: Scroll Down to the Slider Section
		
	     	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
	     	WebElement sliderSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/*[contains(text(),\"Patients should be between 0 to 2000\")]")));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sliderSection);
	        Thread.sleep(500);

        //Step 4: Adjust the Slider

			WebElement sliderRange = driver.findElement(By.cssSelector("input[type=\"range\"]"));
			WebElement inputField = driver.findElement(By.cssSelector("input[type=\"number\"]"));
			 Actions actions = new Actions(driver);
			 int targetValue = 820;
			 int sliderRanger = 2000;
			 int stepSize =230;
	     	 int DistanceToMove =(int)((targetValue/(double)sliderRanger)* stepSize);
			 actions.clickAndHold(sliderRange).moveByOffset(DistanceToMove, 0).release().perform();
			 WebElement bottomTextField = driver.findElement(By.cssSelector("input[type=\"number\"]"));
	       String fieldValue = bottomTextField.getAttribute("value");
	       if ("823".equals(fieldValue)) {
	           System.out.println("Slider successfully set to 823.");
	       } else {
	           System.out.println("Failed to set slider to 823.");
	       }
		
		//Step5 - Update the Text Field:
	       Thread.sleep(1000);
	       WebElement input = driver.findElement(By.id(":r0:"));
	       input.sendKeys(Keys.BACK_SPACE);
	       input.sendKeys(Keys.BACK_SPACE);
	       input.sendKeys(Keys.BACK_SPACE);
	       inputField.sendKeys("560");
	       WebElement testSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/*[contains(text(),\"Total Gross Revenue Per Year\")]")));
	       testSection.click();
       
       //Step6 - Validate Slider Value:

	       String style = input.getAttribute("value");
	       if ("560".equals(style)) {
	           System.out.println("Slider updated to 560.");
	       } else {
	           System.out.println("Slider not updated to 560.");
	       }
       
       //Step7 - Select CPT Codes:

	       String[] inputValues= {"CPT-99091","CPT-99453","CPT-99454","CPT-99474"};
	       for(int i=0;i<inputValues.length;i++) {
	       WebElement checkbox = driver.findElement(By.xpath("//div[@class=\"MuiBox-root css-4o8pys\"]/p[contains(text(),\'"+inputValues[i]+"\')]//following::label[1]//input[@type=\"checkbox\"]"));
	       checkbox.click();
	       }

       //Step8- Validate Total Recurring Reimbursement:
       
	     WebElement DisplayElement = driver.findElement(By.xpath("//p[contains(text(),\"Total Recurring Reimbursement for all Patients Per Month:\")]"));
	     if(DisplayElement.isDisplayed()) {
	    	 System.out.println("Total Recurring Reimbursement is visible");
	     }else {
	    	 System.out.println("Total Recurring Reimbursement is not visible");
	     }
    		 
        //Step9 - Verify that the header displaying Total Recurring Reimbursement for all Patients Per Month: shows the value $75600.
	    //NOTE: changed the total price value since calculated the Total Individual Patient/Month as 560 from step 5 
	     
	     WebElement priceCheck = driver.findElement(By.xpath("//p[contains(text(),\"Total Recurring Reimbursement for all Patients Per Month:\")]//following::p[1][contains(text()[2],\"75600\")]"));
	     if(priceCheck.isDisplayed()) {
	    	 System.out.println("Total price displayed is correct");
	     }else {
	    	 System.out.println("Total price displayed is incorrect");
	     }
     
     driver.quit();
}
}