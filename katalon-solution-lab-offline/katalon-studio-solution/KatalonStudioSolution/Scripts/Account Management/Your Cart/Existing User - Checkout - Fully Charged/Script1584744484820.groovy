import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.TearDown as TearDown
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable


'Open the VOX SUT'
WebUI.openBrowser(GlobalVariable.url)

'Login'
WebUI.callTestCase(findTestCase('Common Test Cases/Login'), ['username':GlobalVariable.userAdmin, 'password':GlobalVariable.passwordAdmin], FailureHandling.STOP_ON_FAILURE)

'Search items and add an item to cart'
WebUI.callTestCase(findTestCase('Common Test Cases/Add an Item to Cart'), [:], FailureHandling.STOP_ON_FAILURE)

'Go to My Cart'
WebUI.click(findTestObject('AccountPage/linkYourCart'))

'Check Out Step 1'
WebUI.click(findTestObject('AccountPage/btnCheckOut'))

'Check Out - Shipping Address'
WebUI.click(findTestObject('AccountPage/btnContinue'))

'Check Out - Shipping Method'
WebUI.click(findTestObject('AccountPage/btnContinue'))

'Check Out - Billing Address'
WebUI.click(findTestObject('AccountPage/btnOrder'))

'Check Out - Payment'
WebUI.click(findTestObject('AccountPage/chkPaymentMethod'))
WebUI.click(findTestObject('AccountPage/btnProceedPayment'))

'Check Out - Payment Fully Charged'
WebUI.click(findTestObject('AccountPage/chkFullChargeOption'))
WebUI.click(findTestObject('AccountPage/btnMakePayment'))

'Verify Checkout Page'
WebUI.verifyElementVisible(findTestObject('AccountPage/imgCheckOutPirate'))


@TearDown
def tearDown() {
	WebUI.closeBrowser()
}