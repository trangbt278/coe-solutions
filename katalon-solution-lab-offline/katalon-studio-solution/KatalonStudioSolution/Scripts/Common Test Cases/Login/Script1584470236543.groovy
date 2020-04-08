import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI



//Create variables
//String username = GlobalVariable.userAdmin
//String password = GlobalVariable.passwordAdmin

'Go to login page'
WebUI.click(findTestObject('HomePage/linkLogIn'))
'Enter username'
WebUI.setText(findTestObject('LoginPage/inputEmail'), username)
'Enter password'
WebUI.setText(findTestObject('LoginPage/inputPassword'), password)
'Click on Log In button'
WebUI.click(findTestObject('LoginPage/btnLogIn'))
