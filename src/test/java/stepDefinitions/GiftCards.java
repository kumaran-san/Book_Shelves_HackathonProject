package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.GiftCardsPage;
import utilities.DataReader;

public class GiftCards extends BaseClass{
	GiftCardsPage gp;
	String repName,repEmail,repMobile,yName,yEmail,yMobile,enAddress,pincode;
	@Given("Go to the Gift Cards page")
	public void go_to_the_gift_cards_page() {
		gp = new GiftCardsPage(driver);
		   BaseClass.getLogger().info("Click on the Gift Cards ...");
		   //driver.switchTo().frame(gp.getHead());
		   gp.clickGiftcards();
		   BaseClass.getLogger().info("Going to Gift Cards Page...");
		   
	}

	@When("Check the giftcard page is opened")
	public void check_the_giftcard_page_is_opened() {
		gp = new GiftCardsPage(driver);
		
		
	}

	@When("Click the Birthday or Anniversary card")
	public void click_the_birthday_or_anniversary_card() throws InterruptedException {
		gp = new GiftCardsPage(driver);
		BaseClass.getLogger().info("Scrolling to the BirthDay/Anniversary card...");
		BaseClass.scrolling(gp.ReachBirthday);
		
		BaseClass.getLogger().info("Clicking the BirthDay/Anniversary card...");
		gp.clickBirthday(driver);
		BaseClass.getLogger().info("validating...");
		
	}

	@When("Fill Customize your gift card with valid price & date and click Next button")
	public void fill_customize_your_gift_card_with_valid_price_date_and_click_next_button() throws IOException {
		gp = new GiftCardsPage(driver);
		BaseClass.getLogger().info("Entering price...");
		
		gp.Enteramount(BaseClass.getProperties().getProperty("amount"));
		BaseClass.getLogger().info("Selecting month...");
		gp.Entermonth();;
		BaseClass.getLogger().info("Selecting day...");
		gp.Enterday();
		BaseClass.getLogger().info("Clicking the nextButton...");
		gp.Clicknext();;
		BaseClass.getLogger().info("Opening of the lucky person page...");
		
	}

	@When("Fill To and From details under who is this lucky person with {string} and any one {string} email id invalid and click Submit")
	public void fill_to_and_from_details_under_who_is_this_lucky_person_with_any_one_email_id_invalid_and_click_submit(String sheetName, String rownum) throws IOException, InterruptedException, InvalidFormatException {
		gp = new GiftCardsPage(driver);
		
		BaseClass.getLogger().info("Read the excel file...");

		
		 List<HashMap<String, String>> testData = DataReader.data("C:\\Users\\2318459\\eclipse-workspace\\Hackathon_Demo\\TestData\\FormData.xlsx", sheetName);

	     int index = Integer.parseInt(rownum);
		repName = testData.get(index).get("Recipient's Name");
	    repEmail = testData.get(index).get("Recipient's Email");
	    repMobile = testData.get(index).get("Recipient's Mobile");
	    yName = testData.get(index).get("Your Name");
	    yEmail = testData.get(index).get("Your Email");
	    yMobile = testData.get(index).get("Your Mobile");
	    enAddress = testData.get(index).get("Enter Address");
	    pincode = testData.get(index).get("Pincode");
	    
	    

	   gp.EnterToDetails(repName, repEmail, repMobile);
	    

	   gp.EnterCustomerDetails(yName, yEmail, yMobile, enAddress, pincode);
	    
	}

	@Then("Capture the error message")
	public void capture_the_error_message() {
	    gp = new GiftCardsPage(driver);
	    BaseClass.getLogger().info("Printing the validation message if error occured...");
	    String validationMsg = gp.Email.getAttribute("validationMessage");
		System.out.println(validationMsg);
	    
	}

	@Then("Validate the details in the confirm details section")
	public void validate_the_details_in_the_confirm_details_section() {
		gp = new GiftCardsPage(driver);
	    BaseClass.getLogger().info("Validating details...");
	    
	    if(gp.confirmDetails()==true) {
	    	System.out.println("Details are correct...");
	    	 if(gp.recName().equals(repName)) {
	    		 System.out.println(gp.recName());
	 	    	System.out.println("The Names are correct...");
	 	    }	
	    	 else {
	    		 System.out.println("The Names are not correct...");
	    	 }
	    	 if(gp.recEmail().equals(repEmail)) {
	    		 System.out.println(gp.recEmail());
		 	    	System.out.println("The Emails are correctly...");
		 	    }
	    	 else {
	    		 System.out.println("The Emails are not correct...");
	    	 }
	    	 if(gp.recMobile().equals(repMobile)) {
	    		 System.out.println(gp.recMobile());
		 	    	System.out.println("The Mobile Numbers are correctly...");
		 	    }
	    	 else {
	    		 System.out.println("The Mobile Numbers are not correct...");
	    	 }
	    	 if(gp.toname().equals(yName)) {
	    		 System.out.println(gp.toname());
		 	    System.out.println("The Names are correctly...");
		 	    }
	    	 else {
	    		 System.out.println("The Names are not correct...");
	    	 }
	    	 if(gp.toemail().equals(yEmail)) {
	    		 System.out.println(gp.toemail());
		 	    System.out.println("The Emails are correctly...");
		 	    }
	    	 else {
	    		 System.out.println("The Emails are not correct...");
	    	 }
	    	 if(gp.tomobile().equals(yMobile)) {
	    		 System.out.println(gp.tomobile());
		 	    System.out.println("The Mobile Numbers are correctly...");
		 	    }
	    	 else {
	    		 System.out.println("The Mobile Numbers are not correct...");
	    	 }
	    	 if(gp.toaddress().equals(enAddress)) {
	    		 System.out.println(gp.toaddress());
		 	    System.out.println("The Addresses are correctly...");
		 	    }
	    	 else {
	    		 System.out.println("The Addresses are not correct...");
	    	 }
	    	 if(gp.topincode().equals(pincode)) {
	    		 System.out.println(gp.topincode());
		 	    System.out.println("The Pincodes are correctly...");
		 	    }
	    	 else {
	    		 System.out.println("The Pincodes are not correct...");
	    	 }

	    }
	    else {
	    	System.out.println("Details are wrong..");
	    	
	    }
	    

	}

}
