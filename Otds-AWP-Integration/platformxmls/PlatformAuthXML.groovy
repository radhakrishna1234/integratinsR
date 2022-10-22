def platformAdminLogin(String user, String pass) {
    
return  "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
						"<SOAP:Header>"+
							"<wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">"+
								"<wsse:UsernameToken xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">"+
									"<wsse:Username>"+user+"</wsse:Username>"+
									"<wsse:Password>"+pass+"</wsse:Password>"+
								"</wsse:UsernameToken>"+

								"<i18n:international xmlns:i18n=\"http://www.w3.org/2005/09/ws-i18n\">"+
												  "<locale xmlns=\"http://www.w3.org/2005/09/ws-i18n\">en-US</locale>"+
							"</i18n:international>"+

							"</wsse:Security>"+
						"</SOAP:Header>"+
						"<SOAP:Body>"+
							"<samlp:Request xmlns:samlp=\"urn:oasis:names:tc:SAML:1.0:protocol\" MajorVersion=\"1\" MinorVersion=\"1\">"+
								"<samlp:AuthenticationQuery>"+
									"<saml:Subject xmlns:saml=\"urn:oasis:names:tc:SAML:1.0:assertion\">"+
										"<saml:NameIdentifier Format=\"urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified\">"+user +"</saml:NameIdentifier>"+
									"</saml:Subject>"+
								"</samlp:AuthenticationQuery>"+
							"</samlp:Request>"+
						"</SOAP:Body>"+
					"</SOAP:Envelope>";

}