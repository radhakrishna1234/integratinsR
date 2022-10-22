def getAllRoles() {
    
return  "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
			"  <SOAP:Body>\r\n" + 
			"    <GetAllRoles xmlns=\"http://schemas/OpenTextEntityIdentityComponents/Role/operations\">\r\n" + 
			"      <contains></contains>\r\n" + 
			"      <ns0:Cursor xmlns:ns0=\"http://schemas.opentext.com/bps/entity/core\" offset=\"0\" limit=\"100\" />\r\n" + 
			"    </GetAllRoles>\r\n" + 
			"  </SOAP:Body>\r\n" + 
			"</SOAP:Envelope>";


}