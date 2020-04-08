import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


//Declare variables
Random rand = new Random()
String category
String preXpath
String numRandom
chkFilter = new TestObject()
listItem = new TestObject()
int countList = 0
int rowCount
int rowNum
int index

//Get test data
TestData listCategory = findTestData('Data Files/extdata_categoryfilter')
rowCount = listCategory.getRowNumbers()

//'Open the VOX SUT'
//WebUI.openBrowser(GlobalVariable.url)

while (countList == 0) {
	
	rowNum = rand.nextInt(rowCount -1) + 1
	category = listCategory.getValue('category', rowNum)
	preXpath = listCategory.getValue('preXpath', rowNum)
	numRandom = (listCategory.getValue('numRandom', rowNum))
	index = rand.nextInt(Integer.valueOf(numRandom.toString().trim()))
	
	'Go to Accessories, Groceries, Apparel page'
	switch(category){
		case('Accessories'):
			WebUI.click(findTestObject('SaleorPage/linkAccessories'))
			break;
		case('Groceries'):
			WebUI.click(findTestObject('SaleorPage/linkGroceries'))
			break;
		case('Apparel'):
			WebUI.click(findTestObject('SaleorPage/linkApparel'))
			break;
	}

	'Search with filter'
	chkFilter = CustomKeywords.'utilities.WebUtils.parameterizedFilterInput'(preXpath, index)
	CustomKeywords.'utilities.WebUtils.filterOneCriteria'(chkFilter)

	//Handle dynamic object on product list
	WebDriver driver = DriverFactory.getWebDriver()
	List<WebElement> list = driver.findElements(By.cssSelector(".product-list__header .row .product-list"))
	countList = list.size()
	
	if (countList !=0) {
		index = rand.nextInt(Integer.valueOf(countList.toString().trim()))
		//listItem.addProperty('cssSelector', ConditionType.EQUALS, "(//IMG[@class='img-responsive lazypreload ls-is-cached lazyloaded'])[" + index + ']')

		'Click on a Product item'
		list.get(index).click()

		'Add to cart'
		WebUI.click(findTestObject('ProductPage/btnAddtoCart'))
	}
}
