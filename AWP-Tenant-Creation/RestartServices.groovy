

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

def newOrgCreation() {
 GroovyShell configReader = new GroovyShell()
 def config = configReader.parse(new File('ConfigFileReader.groovy'))
def orgName = config.getProperty('platformOrg');
def user = config.getProperty('platformUserName');
def pass =  config.getProperty('platformPassword');
def platformBaseURL = config.getProperty('platformBaseURL');
String endPoint = platformBaseURL+"/home/system/com.eibus.web.soap.Gateway.wcp?organization=o=system,cn=cordys,cn=defaultInst,o=opentext.net"


String bpm0URL = platformBaseURL+"/home/"+orgName+"/com.eibus.web.soap.Gateway.wcp?receiver=cn=monitorsoapnode@appworks-0,cn=soap%20nodes,o=system,cn=cordys,cn=defaultInst,o=opentext.net&organizationalcontext=o="+orgName+",cn=cordys,cn=defaultInst,o=opentext.net";

String bpm1URL = platformBaseURL+"/home/"+orgName+"/com.eibus.web.soap.Gateway.wcp?receiver=cn=monitorsoapnode@appworks-1,cn=soap%20nodes,o=system,cn=cordys,cn=defaultInst,o=opentext.net&organizationalcontext=o="+orgName+",cn=cordys,cn=defaultInst,o=opentext.net";

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


def bpmSoapProcessor0 =   "<SOAP:Envelope\n" + 
   		"	xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
   		"	<SOAP:Body>\n" + 
   		"		<multicall:__Cordys_MultipleRequestWrapper\n" + 
   		"			xmlns:multicall=\"http://schemas.cordys.com/1.0/monitor\">\n" + 
   		"			<SOAP:Envelope\n" + 
   		"				xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
   		"				<SOAP:Body>\n" + 
   		"					<multicall:Restart>\n" + 
   		"						<multicall:dn>cn=BPM@appworks-0,cn=BPM,cn=soap nodes,o="+orgName+",cn=cordys,cn=defaultInst,o=opentext.net</multicall:dn>\n" + 
   		"					</multicall:Restart>\n" + 
   		"				</SOAP:Body>\n" + 
   		"			</SOAP:Envelope>\n" + 
   		"			<SOAP:Envelope\n" + 
   		"				xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
   		"				<SOAP:Body>\n" + 
   		"					<List\n" + 
   		"						xmlns=\"http://schemas.cordys.com/1.0/monitor\"/>\n" + 
   		"					</SOAP:Body>\n" + 
   		"				</SOAP:Envelope>\n" + 
   		"			</multicall:__Cordys_MultipleRequestWrapper>\n" + 
   		"		</SOAP:Body>\n" + 
   		"	</SOAP:Envelope>";



def bpmSoapProcessor1 ="<SOAP:Envelope\n" + 
   		"	xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
   		"	<SOAP:Body>\n" + 
   		"		<multicall:__Cordys_MultipleRequestWrapper\n" + 
   		"			xmlns:multicall=\"http://schemas.cordys.com/1.0/monitor\">\n" + 
   		"			<SOAP:Envelope\n" + 
   		"				xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
   		"				<SOAP:Body>\n" + 
   		"					<multicall:Restart>\n" + 
   		"						<multicall:dn>cn=BPM@appworks-1,cn=BPM,cn=soap nodes,o="+orgName+",cn=cordys,cn=defaultInst,o=opentext.net</multicall:dn>\n" + 
   		"					</multicall:Restart>\n" + 
   		"				</SOAP:Body>\n" + 
   		"			</SOAP:Envelope>\n" + 
   		"			<SOAP:Envelope\n" + 
   		"				xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
   		"				<SOAP:Body>\n" + 
   		"					<List\n" + 
   		"						xmlns=\"http://schemas.cordys.com/1.0/monitor\"/>\n" + 
   		"					</SOAP:Body>\n" + 
   		"				</SOAP:Envelope>\n" + 
   		"			</multicall:__Cordys_MultipleRequestWrapper>\n" + 
   		"		</SOAP:Body>\n" + 
   		"	</SOAP:Envelope>";
		
	

   def wsapp0 = "<SOAP:Envelope\n" + 
   		"	xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
   		"	<SOAP:Body>\n" + 
   		"		<multicall:__Cordys_MultipleRequestWrapper\n" + 
   		"			xmlns:multicall=\"http://schemas.cordys.com/1.0/monitor\">\n" + 
   		"			<SOAP:Envelope\n" + 
   		"				xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
   		"				<SOAP:Body>\n" + 
   		"					<multicall:Restart>\n" + 
   		"						<multicall:dn>cn=WS-AppServer@appworks-0,cn=WS-AppServer,cn=soap nodes,o="+orgName+",cn=cordys,cn=defaultInst,o=opentext.net</multicall:dn>\n" + 
   		"					</multicall:Restart>\n" + 
   		"				</SOAP:Body>\n" + 
   		"			</SOAP:Envelope>\n" + 
   		"			<SOAP:Envelope\n" + 
   		"				xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
   		"				<SOAP:Body>\n" + 
   		"					<List\n" + 
   		"						xmlns=\"http://schemas.cordys.com/1.0/monitor\"/>\n" + 
   		"					</SOAP:Body>\n" + 
   		"				</SOAP:Envelope>\n" + 
   		"			</multicall:__Cordys_MultipleRequestWrapper>\n" + 
   		"		</SOAP:Body>\n" + 
   		"	</SOAP:Envelope>";
		
   def wsapp1 = "<SOAP:Envelope\n" + 
   		"	xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
   		"	<SOAP:Body>\n" + 
   		"		<multicall:__Cordys_MultipleRequestWrapper\n" + 
   		"			xmlns:multicall=\"http://schemas.cordys.com/1.0/monitor\">\n" + 
   		"			<SOAP:Envelope\n" + 
   		"				xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
   		"				<SOAP:Body>\n" + 
   		"					<multicall:Restart>\n" + 
   		"						<multicall:dn>cn=WS-AppServer@appworks-1,cn=WS-AppServer,cn=soap nodes,o="+orgName+",cn=cordys,cn=defaultInst,o=opentext.net</multicall:dn>\n" + 
   		"					</multicall:Restart>\n" + 
   		"				</SOAP:Body>\n" + 
   		"			</SOAP:Envelope>\n" + 
   		"			<SOAP:Envelope\n" + 
   		"				xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
   		"				<SOAP:Body>\n" + 
   		"					<List\n" + 
   		"						xmlns=\"http://schemas.cordys.com/1.0/monitor\"/>\n" + 
   		"					</SOAP:Body>\n" + 
   		"				</SOAP:Envelope>\n" + 
   		"			</multicall:__Cordys_MultipleRequestWrapper>\n" + 
   		"		</SOAP:Body>\n" + 
   		"	</SOAP:Envelope>";


    println "Platform saml art getting started .........*****..............."
	def resp3 = firerequest(data,endPoint)	
	def response = new XmlSlurper().parseText(resp3)
	def titles = response.'**'.findAll { node -> node.name() == 'AssertionArtifact' }*.text()
	println "Platform saml art generated successfully."
	
	println " ....................... BPM SOAP PROCESSOR-0 Restart Start ........................."
	println "BPM-0 Restart Request "+bpmSoapProcessor0;
	def resp = firerequest(bpmSoapProcessor0,bpm0URL+"&SAMLart="+titles.get(0))
	sleep(100)
	println resp;
	println " ------------------------ BPM SOAP PROCESSOR-0 Restart Start -------------------------"
	
	
	println " ....................... BPM SOAP PROCESSOR-1 Restart Start ........................."
	println "BPM-0 Restart Request "+bpmSoapProcessor1;
	def resp1 = firerequest(bpmSoapProcessor1,bpm1URL+"&SAMLart="+titles.get(0))
	sleep(100)
	println resp1;
	println " ------------------------ BPM SOAP PROCESSOR-1 Restart Start -------------------------"
	
	
	
	println " ....................... WSAPP SOAP PROCESSOR-0 Restart Start ........................."
	println "BPM-0 Restart Request "+wsapp0;
	def resp2 = firerequest(wsapp0,bpm0URL+"&SAMLart="+titles.get(0))
	sleep(100)
	println resp2;
	println " ------------------------ WSAPP SOAP PROCESSOR-0 Restart Start -------------------------"
	
	
	println " ....................... WSAPP SOAP PROCESSOR-1 Restart Start ........................."
	println "BPM-0 Restart Request "+wsapp1;
	def resp4 = firerequest(wsapp1,bpm1URL+"&SAMLart="+titles.get(0))
	sleep(100)
	println resp4;
	println " ------------------------ WSAPP SOAP PROCESSOR-1 Restart Start -------------------------"

	 

}
newOrgCreation();

