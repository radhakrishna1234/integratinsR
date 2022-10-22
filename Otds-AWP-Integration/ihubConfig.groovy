import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// ********************** Start of Reusable methods in ihub ********************

def getAuthToken(String endPointURL, String adminAuthPayload ) { 
 def post = new URL(endPointURL).openConnection();
 println post
 post.setRequestMethod("POST")
 post.setDoOutput(true)
 post.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
 post.getOutputStream().write(adminAuthPayload.getBytes("UTF-8"));
 def resp=post.getInputStream().getText()
    println resp
 JSONParser parse = new JSONParser();
 JSONObject jobj = (JSONObject)parse.parse(resp1);
 String ticket = (String) jobj.get("ticket") 
 println ticket
 return ticket;

}

def AdminPlayLoad(String sysconUserName,String sysconPassword) {
	GroovyShell shell = new GroovyShell()
	def orgAdminAuth = shell.parse(new File('authentication.groovy'))
	return orgAdminAuth.adminAuth(sysconUserName, sysconPassword);
}


def parseJson(String resResponse,String key) {
    GroovyShell shell1 = new GroovyShell()
	def orgResourceID = parseResp(resResponse ,"resourceID");
	return orgResourceID;
}


def firePOSTRequest(String endpointURL, String Method, String request, String adminAuthendPoint, String adminAuthPayload) {
 def ticket = getAuthToken(adminAuthendPoint, adminAuthPayload );
 //println "ticker"+ ticket;
 println "end point "+endpointURL
 def post = new URL(endpointURL).openConnection();
 println "connection success "+Method
 post.setRequestMethod(Method) 
 post.setDoOutput(true)
 post.setRequestProperty("Content-Type", "application/json")
 post.setRequestProperty("OTDSTicket", ticket)
 println "ticket"+ticket;
 println "request"+request;
 post.getOutputStream().write(request.getBytes("UTF-8"));
 def resp=post.getInputStream().getText()
 println "***** response *****\n "+ resp
}


def fireGETRequest(String endpointURL, String Method, String request, String adminAuthendPoint, String adminAuthPayload) {
 def ticket = getAuthToken(adminAuthendPoint, adminAuthPayload );
 def post = new URL(endpointURL).openConnection();
 post.setRequestMethod("GET")
 post.setDoOutput(true)
 post.setRequestProperty("Content-Type", "application/json")
 post.setRequestProperty("OTDSTicket", ticket)
 def resp=post.getInputStream().getText() 
 return resp;
}


def parseResp(String jsonString, String key) {
 JSONParser parse1 = new JSONParser();
 JSONObject jobj1 = (JSONObject)parse1.parse(jsonString);
 String value1 = (String) jobj1.get(key)
 return value1;
}


def getClusterInfo(String endpointURL2,String endpointURL,String sysconUserName,String sysconPassword){
	GroovyShell shell = new GroovyShell()
	println endpointURL
	def adminAuth = shell.parse(new File('authentication.groovy'))
	def adminAuthPayload = adminAuth.adminAuth(sysconUserName, sysconPassword);
	println adminAuthPayload
	//getAuthToken(endpointURL,adminAuthPayload)
	firePOSTRequest(endpointURL2,"POST","" , endpointURL, adminAuthPayload);
}
def parseAnyJson(String resResponse,String key) {
    GroovyShell shell1 = new GroovyShell()
    def orgResourceID = parseResp(resResponse ,key);
    return orgResourceID;
}
