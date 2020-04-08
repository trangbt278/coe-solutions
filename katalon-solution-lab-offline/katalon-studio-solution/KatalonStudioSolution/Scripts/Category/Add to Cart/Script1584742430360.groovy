import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable


'Open the VOX SUT'
WebUI.openBrowser(GlobalVariable.url)

'Search items and add an item to cart'
WebUI.callTestCase(findTestCase('Common Test Cases/Add an Item to Cart'), [:], FailureHandling.STOP_ON_FAILURE)

'Verify the cart is not empty'
WebUI.verifyElementVisible(findTestObject('AccountPage/objOneItemCart'))


@TearDown
def tearDown() {
	WebUI.closeBrowser()
}

