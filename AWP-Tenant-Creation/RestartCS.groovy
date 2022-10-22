GroovyShell configReader = new GroovyShell()
def config = configReader.parse(new File('ConfigFileReader.groovy'))
def orgName = config.getProperty('platformOrg');
def user = config.getProperty('platformUserName');
def pass =  config.getProperty('platformPassword');
def tenantDB =  config.getProperty('tenantName');
def restartOrgDN = "o="+tenantDB+",cn=cordys,cn=defaultInst,o=opentext.net";
//def platformBaseURL="http://a9b2f0fe4ee0847dc84938fa6565795a-232954245.eu-central-1.elb.amazonaws.com";
def platformBaseURL= config.getProperty('platformBaseURL');
def endPoint =platformBaseURL+"/home/system/com.eibus.web.soap.Gateway.wcp?organization=o=system,cn=cordys,cn=defaultInst,o=opentext.net"

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


def startContaine(String nameOfcontainer, String orgName) {

return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
			"<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
			"   <SOAP:Body>\r\n" + 
			"      <multicall:__Cordys_MultipleRequestWrapper xmlns:multicall=\"http://schemas.cordys.com/1.0/monitor\">\r\n" + 
			"         <SOAP:Envelope>\r\n" + 
			"            <SOAP:Body>\r\n" + 
			"               <multicall:Start>\r\n" + 
			"                  <multicall:dn>cn="+nameOfcontainer+",cn=ContentServerWithOTDS,cn=soap nodes,o="+orgName+",cn=cordys,cn=defaultInst,o=opentext.net</multicall:dn>\r\n" + 
			"               </multicall:Start>\r\n" + 
			"            </SOAP:Body>\r\n" + 
			"         </SOAP:Envelope>\r\n" + 
			"         <SOAP:Envelope>\r\n" + 
			"            <SOAP:Body>\r\n" + 
			"               <List xmlns=\"http://schemas.cordys.com/1.0/monitor\" />\r\n" + 
			"            </SOAP:Body>\r\n" + 
			"         </SOAP:Envelope>\r\n" + 
			"      </multicall:__Cordys_MultipleRequestWrapper>\r\n" + 
			"   </SOAP:Body>\r\n" + 
			"</SOAP:Envelope>";

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

    println "Platform saml art getting started .........*****..............."
	def resp3 = firerequest(data,endPoint)	
	def response = new XmlSlurper().parseText(resp3)
	def titles = response.'**'.findAll { node -> node.name() == 'AssertionArtifact' }*.text()
	println "Platform saml art generated successfully."
	
	println "Restarting DocumentStore@appworks-0 pod service container ........................."
	firerequest(startContaine("ContentServerWithOTDS@appworks-0",orgName),endPoint+"&SAMLart="+titles.get(0))	
	sleep(8000)
	println "Started successfully DocumentStore@appworks-0 pod service container"
	
	println "Restarting DocumentStore@appworks-1 pod service container ........................."
	firerequest(startContaine("ContentServerWithOTDS@appworks-1",orgName),endPoint+"&SAMLart="+titles.get(0))
	sleep(8000)
	println "--"+startContaine("DocumentStore@appworks-1",orgName);
	println "Started successfully DocumentStore@appworks-1 pod service container"
	
}

newOrgCreation(orgName,user,pass,restartOrgDN,endPoint)