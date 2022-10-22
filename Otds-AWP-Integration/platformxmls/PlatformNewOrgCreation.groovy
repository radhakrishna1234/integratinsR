def pushNewOrganization(String orgName, String user) {
    
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
		"      <CreateOrganization xmlns=\"http://schemas.cordys.com/1.0/organization\">\r\n" + 
		"         <Name>"+orgName+"</Name>\r\n" + 
		"         <DisplayName>"+orgName+"</DisplayName>\r\n" + 
		"         <Administrators>\r\n" + 
		"            <UserName>"+user+"</UserName>\r\n" + 
		"         </Administrators>\r\n" + 
		"      </CreateOrganization>\r\n" + 
		"   </SOAP:Body>\r\n" + 
		"</SOAP:Envelope>";

}