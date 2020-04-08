import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable


//Declare variables
Random rand = new Random()
int index = rand.nextInt(Integer.valueOf(numRandom.toString().trim()))
chkFilter = new TestObject()
String filterName
String filterValue
String apiID

'Open the VOX SUT'
WebUI.openBrowser(GlobalVariable.url)

'Go to Accessories, Groceries, Apparel page'
switch(category){
	case('Accessories'):
		WebUI.click(findTestObject('SaleorPage/linkAccessories'))
		apiID = GlobalVariable.apiAccessoriesID
		break;
	case('Groceries'):
		WebUI.click(findTestObject('SaleorPage/linkGroceries'))
		apiID = GlobalVariable.apiGroceriesID
		break;
	case('Apparel'):
		WebUI.click(findTestObject('SaleorPage/linkApparel'))
		apiID = GlobalVariable.apiApparelID
		break;
}

//Make filter object and get attribute
chkFilter = CustomKeywords.'utilities.WebUtils.parameterizedFilterLabel'(preXpath, index)
filterValue = (WebUI.getAttribute(chkFilter, "outerText").trim()).toLowerCase()
filterValue = filterValue.replace('.', '')
filterValue = filterValue.replace('%', '')
filterValue = filterValue.replace(', ','-')
filterValue = filterValue.replace(' ','-')

chkFilter = CustomKeywords.'utilities.WebUtils.parameterizedFilterInput'(preXpath, index)
filterName = WebUI.getAttribute(chkFilter, "name").toLowerCase()

'Search with filter'
CustomKeywords.'utilities.WebUtils.filterOneCriteria'(chkFilter)
//Handle dynamic object on product list
WebDriver driver = DriverFactory.getWebDriver()
List<WebElement> list = driver.findElements(By.cssSelector(".product-list__header .row .product-list .product-list-item-name"))
countList = list.size()

if (countList == 0) {
	'Verify no result returned'
	WebUI.verifyElementVisible(findTestObject('CategoryPage/objNoResults'))
} else {
	def listProductUI = ["product"]
	for (def i : (0..countList-1)) {
		listProductUI = listProductUI << (list.get(i).getText()).toLowerCase()
	}
	
	'Build the API attribute and send a request'
	filterAttribute = '"' + filterName + ":" + filterValue + '"'
	response = WS.sendRequest(findTestObject('Web Service/Search with Criteria', [('filter') : filterAttribute, ('id') : apiID]))
	//Handle API response
	responseText = response.getResponseText()
	def listProductAPI = ["product"]
	JsonSlurper slurper = new JsonSlurper()
	def parsedJson = slurper.parseText(responseText)
	for (def product : parsedJson.data.products.edges[0].node ) {
		listProductAPI << product.name.toLowerCase()
		}
	
	'Compare API response and UI Product list'
	assert (listProductUI.sort()  == listProductAPI.sort()) == true
}

@TearDown
def tearDown() {
	WebUI.closeBrowser()
}

