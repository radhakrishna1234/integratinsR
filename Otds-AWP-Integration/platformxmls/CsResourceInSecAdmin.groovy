 def csResourceInSecAdmin( String Resourcename, String Space, String otdsBaseURL, String resourceID) {
 
 
 
 s1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
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
    		"      <UpdateOrganizationalResource xmlns=\"http://schemas.cordys.com/security/2.0/otds\" xmlns:xfr=\"http://schemas.cordys.com/1.0/xforms/runtime\">\r\n" + 
    		"         <tuple xmlns:clientattr=\"http://schemas.cordys.com/General/ClientAttributes/\" clientattr:sync_id=\"5\">\r\n" + 
    		"            <new>\r\n" + 
    		"               <Resource>\r\n" + 
    		"                  <Space>"+Space+"</Space>\r\n" + 
    		"                  <Name>"+Resourcename+"</Name>\r\n" + 
    		"                  <Server>"+otdsBaseURL+"</Server>\r\n" + 
    		"                  <ResourceID>"+resourceID+"</ResourceID>\r\n" + 
    		"               </Resource>\r\n" + 
    		"            </new>\r\n" + 
    		"         </tuple>\r\n" + 
    		"      </UpdateOrganizationalResource>\r\n" + 
    		"   </SOAP:Body>\r\n" + 
    		"</SOAP:Envelope>";
 
 
 
 }
 