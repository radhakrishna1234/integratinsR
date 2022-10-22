

def resp1=''
def firerequest(String data, String endPoint) {
def resp=''
	def post = new URL(endPoint).openConnection();
	def message = data
	post.setRequestMethod("POST")
	post.setDoOutput(true)
	post.setRequestProperty("Content-Type", "application/json")
	post.getOutputStream().write(message.getBytes("UTF-8"));
	def postRC = post.getResponseCode();
	if(postRC.equals(200)) {
        resp=post.getInputStream().getText()    
	}
    return resp;
}

def newOrgCreation(String orgName,String user,String pass,String restartOrgDN,String endPoint) {

def data =   "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
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



def lines="<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
  "<SOAP:Body>"+
    "<CreateOrganization xmlns=\"http://schemas.cordys.com/1.0/organization\">"+
      "<Name>"+orgName +"</Name>"+
      "<DisplayName>"+orgName +"</DisplayName>"+
      "<Administrators>"+
        "<UserName>sysadmin</UserName>"+
      "</Administrators>"+
    "</CreateOrganization>"+
  "</SOAP:Body>"+
"</SOAP:Envelope>";


def reStartOrganization = "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
  "<SOAP:Body>"+
    "<TriggerOrganizationActions xmlns=\"http://schemas.cordys.com/1.0/organization\">"+
      "<dn>"+restartOrgDN+"</dn>"+
    "</TriggerOrganizationActions>"+
  "</SOAP:Body>"+
"</SOAP:Envelope>";

    	println "Platform saml art getting started .........*****..............."
	def resp3 = firerequest(data,endPoint)	
	def response = new XmlSlurper().parseText(resp3)
	def titles = response.'**'.findAll { node -> node.name() == 'AssertionArtifact' }*.text()
	println "Platform saml art generated successfully."
	
	println "Platform New Organization Creation Started ........................."
	firerequest(lines,endPoint+"&SAMLart="+titles.get(0))
	sleep(60000)
	println "Platform New Organization Creation ended"

	 println "Platform restart organization started ........................."
	 firerequest(reStartOrganization,endPoint+"&SAMLart="+titles.get(0))
	 sleep(30000)
	 println "Platform restart organization ended"

}


def restartOrganization(String orgName,String user,String pass,String restartOrgDN,String endPoint) {

def data =   "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
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



def lines="<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
  "<SOAP:Body>"+
    "<CreateOrganization xmlns=\"http://schemas.cordys.com/1.0/organization\">"+
      "<Name>"+orgName +"</Name>"+
      "<DisplayName>"+orgName +"</DisplayName>"+
      "<Administrators>"+
        "<UserName>sysadmin</UserName>"+
      "</Administrators>"+
    "</CreateOrganization>"+
  "</SOAP:Body>"+
"</SOAP:Envelope>";


def reStartOrganization = "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
  "<SOAP:Body>"+
    "<TriggerOrganizationActions xmlns=\"http://schemas.cordys.com/1.0/organization\">"+
      "<dn>"+restartOrgDN+"</dn>"+
    "</TriggerOrganizationActions>"+
  "</SOAP:Body>"+
"</SOAP:Envelope>";

    	println "Platform saml art getting started .........*****..............."
	def resp3 = firerequest(data,endPoint)	
	def response = new XmlSlurper().parseText(resp3)
	def titles = response.'**'.findAll { node -> node.name() == 'AssertionArtifact' }*.text()
	println "Platform saml art generated successfully."
	
	 //println "Platform New Organization Creation Started ........................."
	 //firerequest(lines,endPoint+"&SAMLart="+titles.get(0))
	 //sleep(60000)
	 //println "Platform New Organization Creation ended"

	println "Platform restart organization started ........................."
	firerequest(reStartOrganization,endPoint+"&SAMLart="+titles.get(0))
	sleep(30000)
	println "Platform restart organization ended"



}