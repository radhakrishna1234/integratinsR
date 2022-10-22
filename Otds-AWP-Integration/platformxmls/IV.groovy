


def addOuthAuthenticatorforIV(String name) {


return  "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
				"   <SOAP:Header>\r\n" + 
				"      <header xmlns=\"http://schemas.cordys.com/General/1.0/\">\r\n" + 
				"         <Logger />\r\n" + 
				"      </header>\r\n" + 
				"      <i18n:international xmlns:i18n=\"http://www.w3.org/2005/09/ws-i18n\">\r\n" + 
				"         <i18n:locale>en-US</i18n:locale>\r\n" + 
				"      </i18n:international>\r\n" + 
				"   </SOAP:Header>\r\n" + 
				"   <SOAP:Body>\r\n" + 
				"      <UpdateOrganizationAuthenticator xmlns=\"http://schemas.cordys.com/SSO/Configuration/1.0\">\r\n" + 
				"         <Id>"+name+"</Id>\r\n" + 
				"         <Type>oauth</Type>\r\n" + 
				"         <Description />\r\n" + 
				"         <Default>true</Default>\r\n" + 
				"         <SigningDefault>false</SigningDefault>\r\n" + 
				"         <TestOnly>false</TestOnly>\r\n" + 
				"         <FrameWidth />\r\n" + 
				"         <FrameHeight />\r\n" + 
				"         <FrameTarget />\r\n" + 
				"         <NoFrame>true</NoFrame>\r\n" + 
				"         <MaximizedFrame>false</MaximizedFrame>\r\n" + 
				"         <ChangePasswordUrl />\r\n" + 
				"         <Path>/wcp/sso/login.htm</Path>\r\n" + 
				"         <ServerConfiguration xmlns=\"http://schemas.cordys.com/security/2.0/otds\">\r\n" + 
				"            <PublicURL/>\r\n" + 
				"         </ServerConfiguration>\r\n" + 
				"      </UpdateOrganizationAuthenticator>\r\n" + 
				"   </SOAP:Body>\r\n" + 
				"</SOAP:Envelope>";



}