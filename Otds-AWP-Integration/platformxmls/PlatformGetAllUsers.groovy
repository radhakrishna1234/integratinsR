def getAllUsers() {
    
return  "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
			"  <SOAP:Body>\r\n" + 
			"    <GetAllUsers xmlns=\"http://schemas/OpenTextEntityIdentityComponents/User/operations\">\r\n" + 
			"      <contains></contains>\r\n" + 
			"      <ns0:Cursor xmlns:ns0=\"http://schemas.opentext.com/bps/entity/core\" offset=\"0\" limit=\"100\" />\r\n" + 
			"    </GetAllUsers>\r\n" + 
			"  </SOAP:Body>\r\n" + 
			"</SOAP:Envelope>\r\n" + 
			"";


}