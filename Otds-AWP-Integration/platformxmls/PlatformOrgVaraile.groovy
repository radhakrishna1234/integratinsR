def orgVariable(String platformBaseURL) {
    
return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
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
        		"      <CreateOrganizationVariable xmlns=\"http://schemas.cordys.com/SSO/Configuration/1.0\">\r\n" + 
        		"         <Variable>\r\n" + 
        		"            <Name>BASE_URL</Name>\r\n" + 
        		"            <Value>"+platformBaseURL+"</Value>\r\n" + 
        		"            <AuthenticatorId>otds</AuthenticatorId>\r\n" + 
        		"         </Variable>\r\n" + 
        		"      </CreateOrganizationVariable>\r\n" + 
        		"   </SOAP:Body>\r\n" + 
        		"</SOAP:Envelope>";


}