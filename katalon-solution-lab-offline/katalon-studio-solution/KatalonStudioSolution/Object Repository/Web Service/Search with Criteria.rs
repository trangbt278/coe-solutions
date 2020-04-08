<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Search with Criteria</name>
   <tag></tag>
   <elementGuidId>88b7a900-07f2-454c-92db-f90ec3422fb5</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;[\n    {\n        \&quot;operationName\&quot;: \&quot;Category\&quot;,\n        \&quot;variables\&quot;: {\n            \&quot;attributes\&quot;: [\n               ${filter}\n            ],\n            \&quot;pageSize\&quot;: 20,\n            \&quot;id\&quot;: ${id}\n        },\n        \&quot;query\&quot;: \&quot;query Category($id: ID!, $attributes: [AttributeScalar], $pageSize: Int) { products( attributes: $attributes, first: $pageSize, filter: {categories: [$id]}) { edges { node { id name purchaseCost { start { amount currency localized } } } } } }\&quot;\n    }\n]\n&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>JWT eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFkbWluQGV4YW1wbGUuY29tIiwiZXhwIjoxNTYwMTk0ODI0LCJvcmlnSWF0IjoxNTYwMTk0NTI0fQ.WUuy7s_sBniFTH9j7_K8wevF48OPtcbBcLf3BFTuOfg</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${url}/graphql/</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>'&quot;cushion-size:45cm-x-45cm&quot;'</defaultValue>
      <description></description>
      <id>a3ab153f-9273-4a9a-93c6-dda43fbce1c3</id>
      <masked>false</masked>
      <name>filter</name>
   </variables>
   <variables>
      <defaultValue>'&quot;Q2F0ZWdvcnk6Nw==&quot;'</defaultValue>
      <description></description>
      <id>cfc2d921-16a5-436e-8e66-cfee65861316</id>
      <masked>false</masked>
      <name>id</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.url</defaultValue>
      <description></description>
      <id>e840d0e4-12e6-443e-a4ae-553bc256b8d3</id>
      <masked>false</masked>
      <name>url</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
