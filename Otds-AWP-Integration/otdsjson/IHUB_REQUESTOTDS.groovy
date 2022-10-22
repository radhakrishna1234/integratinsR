 def iHUBResCreation(String resName) {
    
return "{\r\n" + 
		  		"   \"resourceName\":\""+resName+"\",\r\n" + 
		  		"   \"id\":\""+resName+"\",\r\n" + 
		  		"   \"description\":null,\r\n" + 
		  		"   \"displayName\":\""+resName+"\",  \r\n" + 
		  		"   \"resourceState\":0,\r\n" + 
		  		"   \"userSynchronizationState\":true,   \r\n" + 
		  		"   \"resourceType\":null,\r\n" + 
		  		"   \"accessRoleList\":[\r\n" + 
		  		"      {\r\n" + 
		  		"         \"id\":\"Access to "+resName+"\",\r\n" + 
		  		"         \"name\":\"Access to "+resName+"\",\r\n" + 
		  		"         \"description\":\"Default access role for resource "+resName+"\",\r\n" + 
		  		"         \"status\":false\r\n" + 
		  		"      }\r\n" + 
		  		"   ],\r\n" + 
		  		"   \"impersonateList\":null,\r\n" + 
		  		"   \"impersonateAnonymousList\":null,\r\n" + 
		  		"   \"pcCreatePermissionAllowed\":true,\r\n" + 
		  		"   \"pcModifyPermissionAllowed\":true,\r\n" + 
		  		"   \"pcDeletePermissionAllowed\":false,\r\n" + 
		  		"   \"logoutURL\":null,\r\n" + 
		  		"   \"logoutMethod\":null,\r\n" + 
		  		"   \"allowImpersonation\":false,\r\n" + 
		  		"   \"connectorName\":null,\r\n" + 
		  		"   \"connectorid\":null,\r\n" + 
		  		"   \"userAttributeMapping\":[\r\n" + 
		  		"      {\r\n" + 
		  		"         \"sourceAttr\":[\r\n" + 
		  		"            \"oTExternalID3\"\r\n" + 
		  		"         ],\r\n" + 
		  		"         \"destAttr\":\"__NAME__\",\r\n" + 
		  		"         \"mappingFormat\":\"%s\"\r\n" + 
		  		"      }\r\n" + 
		  		"   ],\r\n" + 
		  		"   \"groupAttributeMapping\":[\r\n" + 
		  		"      {\r\n" + 
		  		"         \"sourceAttr\":[\r\n" + 
		  		"            \"cn\"\r\n" + 
		  		"         ],\r\n" + 
		  		"         \"destAttr\":\"__NAME__\",\r\n" + 
		  		"         \"mappingFormat\":\"%s\"\r\n" + 
		  		"      }\r\n" + 
		  		"   ],\r\n" + 
		  		"   \"connectionParamInfo\":null,\r\n" + 
		  		"   \"logonStyle\":null,\r\n" + 
		  		"   \"logonUXVersion\":0\r\n" + 
		  		"}";

}



def allowImpersonation(String resName) {

	return "{\"allowImpersonation\":true,\"impersonateList\":[]}";

}


def oAuthClientCreation(String ihubOAuthName,String iHubHost) {

/*return "{\r\n" + 
        		"   \"customAttributes\":[\r\n" + 
        		"      \r\n" + 
        		"   ],\r\n" + 
        		"   \"name\":\""+ihubOAuthName+"\",\r\n" + 
        		"   \"description\":\"\",\r\n" + 
				"   \"secret\":\"123456789\",\r\n" +
        		"   \"confidential\":true,\r\n" + 
        		"   \"redirectURLs\":[\r\n" + 
        		"      \""+iHubHost+"\"\r\n" + 
        		"   ],\r\n" + 
        		"   \"logoutURL\":\"\",\r\n" + 
        		"   \"logoutMethod\":\"\",\r\n" + 
        		"   \"accessTokenLifeTime\":\"\",\r\n" + 
        		"   \"refreshTokenLifeTime\":\"\",\r\n" + 
        		"   \"allowRefreshToken\":true,\r\n" + 
        		"   \"useSessionRefreshTokenLifeTime\":false,\r\n" + 
        		"   \"allowedScopes\":[\r\n" + 
        		"      \r\n" + 
        		"   ],\r\n" + 
        		"   \"defaultScopes\":[\r\n" + 
        		"      \r\n" + 
        		"   ],\r\n" + 
        		"   \"authScopes\":null\r\n" + 
        		"}";

				*/
return "{\r\n" + 
  		"   \"customAttributes\":[\r\n" + 
  		"      \r\n" + 
  		"   ],\r\n" + 
  		"   \"name\":\""+ihubOAuthName+"\",\r\n" + 
  		"   \"description\":\"\",\r\n" + 
  		"   \"confidential\":true,\r\n" + 
  		"   \"redirectURLs\":[\r\n" + 
  		"      \""+iHubHost+"\"\r\n" + 
  		"   ],\r\n" + 
  		"   \"logoutURL\":\"\",\r\n" + 
		"   \"secret\":\"123456789\",\r\n" +
  		"   \"logoutMethod\":\"\",\r\n" + 
  		"   \"accessTokenLifeTime\":\"\",\r\n" + 
  		"   \"refreshTokenLifeTime\":\"\",\r\n" + 
  		"   \"allowRefreshToken\":true,\r\n" + 
  		"   \"useSessionRefreshTokenLifeTime\":false,\r\n" + 
  		"   \"allowedScopes\":[\r\n" + 
  		"      \r\n" + 
  		"   ],\r\n" + 
  		"   \"defaultScopes\":[\r\n" + 
  		"      \r\n" + 
  		"   ],\r\n" + 
  		"   \"authScopes\":null\r\n" + 
  		"}";

}


def oAuthClientImpersonate() {

return "{\"allowImpersonation\":true,\"impersonateList\":[]}";

}



def addRoleToUser(String partitionName,String orgName,String roleName) {
    
  if(orgName.equals("opentext"))
     return "{\r\n" + 
				"   \"stringList\":[\r\n" + 
				"      \"cn="+roleName+",ou=Root,ou="+partitionName+",ou=IdentityProviders,dc=identity,dc="+orgName+",dc=net\"\r\n" +
				"   ]\r\n" + 
				"}";
  else
 
   return "{\r\n" + 
				"   \"stringList\":[\r\n" + 
				"      \"cn="+roleName+",ou=Root,ou="+partitionName+",ou=IdentityProviders,dc=identity,dc="+orgName+",dc=root\"\r\n" +
				"   ]\r\n" + 
				"}";
       
}

def ihubConnManager(String ihubhost,String ihubOauthClientName,String ihubOauthClientID,String platformOrg){
	return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
			"<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" +
			"   <SOAP:Header>\r\n" +
			"      <header xmlns=\"http://schemas.cordys.com/General/1.0/\">\r\n" +
			"         <Logger />\r\n" +
			"      </header>\r\n" +
			"      <i18n:international xmlns:i18n=\"http://www.w3.org/2005/09/ws-i18n\">\r\n" +
			"         <i18n:locale>en-US</i18n:locale>\r\n" +
			"      </i18n:international>\r\n" +
			"   </SOAP:Header>\r\n" +
			"   <SOAP:Body>\r\n" +
			"      <UpdateXMLObject xmlns=\"http://schemas.cordys.com/1.0/xmlstore\" xmlns:xfr=\"http://schemas.cordys.com/1.0/xforms/runtime\">\r\n" +
			"         <tuple key=\"/OpenText/configuration/iHubConnection.xml\" level=\"\" version=\"organization\" name=\"\" isFolder=\"false\" unconditional=\"true\" recursive=\"false\" original=\"\">\r\n" +
			"            <old />\r\n" +
			"            <new>\r\n" +
			"               <configuration xmlns=\"http://ihubconnection.opentext.com/1.0/configuration\">\r\n" +
			"                  <connect>true</connect>\r\n" +
			"                  <overrideshared>1</overrideshared>\r\n" +
			"                  <hostname>"+ihubhost+"</hostname>\r\n" +
			"                  <protocol>http</protocol>\r\n" +
			"                  <iportalport>8700</iportalport>\r\n" +
			"                  <restport>8000</restport>\r\n" +
			"                  <volume>"+platformOrg+"</volume>\r\n" +
			"                  <authType>OTDS</authType>\r\n" +
			"               </configuration>\r\n" +
			"            </new>\r\n" +
			"         </tuple>\r\n" +
			"      </UpdateXMLObject>\r\n" +
			"   </SOAP:Body>\r\n" +
			"</SOAP:Envelope>"
}


def clearIhubPage () {

return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
  		"<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
  		"   <SOAP:Header>\r\n" + 
  		"      <header xmlns=\"http://schemas.cordys.com/General/1.0/\">\r\n" + 
  		"         <Logger />\r\n" + 
  		"      </header>\r\n" + 
  		"      <i18n:international xmlns:i18n=\"http://www.w3.org/2005/09/ws-i18n\">\r\n" + 
  		"         <i18n:locale>en-US</i18n:locale>\r\n" + 
  		"      </i18n:international>\r\n" + 
  		"   </SOAP:Header>\r\n" + 
  		"   <SOAP:Body>\r\n" + 
  		"      <UpdateXMLObject xmlns=\"http://schemas.cordys.com/1.0/xmlstore\" xmlns:xfr=\"http://schemas.cordys.com/1.0/xforms/runtime\">\r\n" + 
  		"         <tuple key=\"/OpenText/configuration/iHubConnection.xml\" level=\"\" version=\"organization\" name=\"\" isFolder=\"false\" unconditional=\"true\" recursive=\"false\" original=\"\">\r\n" + 
  		"            <old />\r\n" + 
  		"         </tuple>\r\n" + 
  		"      </UpdateXMLObject>\r\n" + 
  		"   </SOAP:Body>\r\n" + 
  		"</SOAP:Envelope>";


}

