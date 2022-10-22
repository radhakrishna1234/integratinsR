def setpass(String orgUserNmae,String orgUserNmaePass) {
    
return  "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
				"            <SOAP:Header xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
				"                <header xmlns=\"http://schemas.cordys.com/General/1.0/\">\r\n" + 
				"                    <Logger xmlns=\"http://schemas.cordys.com/General/1.0/\"/>\r\n" + 
				"                </header>\r\n" + 
				"                <i18n:international xmlns:i18n=\"http://www.w3.org/2005/09/ws-i18n\">\r\n" + 
				"                    <i18n:locale>en-US</i18n:locale>\r\n" + 
				"                </i18n:international>\r\n" + 
				"            </SOAP:Header>\r\n" + 
				"            <SOAP:Body>\r\n" + 
				"                <SetPasswordForUser xmlns=\"http://schemas.cordys.com/user/password/1.0\" xmlns:xfr=\"http://schemas.cordys.com/1.0/xforms/runtime\">\r\n" + 
				"                    <Username xmlns=\"http://schemas.cordys.com/user/password/1.0\">"+orgUserNmae+"</Username>\r\n" + 
				"                    <NewPassword xmlns=\"http://schemas.cordys.com/user/password/1.0\">"+orgUserNmaePass+"</NewPassword>\r\n" + 
				"                </SetPasswordForUser>\r\n" + 
				"            </SOAP:Body>\r\n" + 
				"        </SOAP:Envelope>";


}