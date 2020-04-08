import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper as JsonSlurper

'Send request to search'
response = WS.sendRequest(findTestObject('Web Service/Search with Criteria', [('filter') : '"cushion-size:45cm-x-45cm"']))

responseText = response.getResponseText()

JsonSlurper slurper = new JsonSlurper()

def parsedJson = slurper.parseText(responseText)
'Validate the response with expected result'
for (def product : parsedJson.data.products.edges[0].node ) {
	productName = product.name.toLowerCase()
	if (!productName.contains("cushion"))	{
		KeywordUtil.markFailed(productName + " not search result's expectation") 
	}		
}

