import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.awt.event.ItemListener

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


// Open browser
WebUI.openBrowser('')

// Navigate to a URL
WebUI.navigateToUrl('https://www.webstaurantstore.com/')

WebUI.sendKeys(findTestObject('Object Repository/searchBar'), 'stainless work table')
WebUI.click(findTestObject('Object Repository/crossbutton'))

// Optional: Add a delay to wait for search results to load
WebUI.delay(3)

WebDriver driver = DriverFactory.getWebDriver()

// Find all elements matching the test object using WebDriver's findElements method
List<WebElement> elementsList = driver.findElements(By.xpath('//div[@id="product_listing"]/div/div/a/span'))

// Iterate through the list of elements and perform actions
for (WebElement element : elementsList) {
	// Perform actions on each element
	tabletxt = element.text
	println(tabletxt)
	assert tabletxt.contains("Table") : "Assertion failed: The text should contain 'table'"
	// Add more actions as needed
}

// Assuming 'yourList' is the test object identifier representing the list of items
def yourList = findTestObject('Object Repository/addToCart')

// Find all items in the list
List<WebElement> itemList = WebUiCommonHelper.findWebElements(yourList, 30)

// Check if there are items in the list
if (!itemList.isEmpty()) {
    // Click on the last item
    itemList.last().click()
} else {
    // Log a message or take appropriate action if the list is empty
    println("The list is empty.")
}

WebUI.click(findTestObject('Object Repository/Viewcart'))
WebUI.click(findTestObject('Object Repository/emptycart'))
WebUI.click(findTestObject('Object Repository/EmptyCartPopUp'))

WebUI.delay(3)

// Get the text of the element
String yourCartEmptyText = WebUI.getText(findTestObject('Object Repository/YourCartisEmpty'))

// Assert that the text contains "Your"
WebUI.verifyMatch(yourCartEmptyText, 'Your.*', true)
// Close the browser
WebUI.closeBrowser()
	
