import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
def ticket = "";

        GroovyShell configReader = new GroovyShell()
        def config = configReader.parse(new File('ConfigFileReader.groovy'))
        config.getProperty('');
      println "---------------------------------------"
       // GroovyShell configReader1 = new GroovyShell()
       // def config1 = configReader1.parse(new File('setProperties.groovy'))
       // config1.setProperties();
 println "===================================="

/********************************************* parametes need to change *********************************/
//Platform details platform tenant name,base url, username,password,ticket
def platformOrg = config.getProperty('platformOrg');
def platformBaseURL = config.getProperty('platformBaseURL');
def platformUserName = config.getProperty('platformUserName');
def platformPassword = config.getProperty('platformPassword');
def csrootFolder = config.getProperty('csrootFolder');

//def ticket = getAuthTocken(csBaseURL,username,password,ticket);

//Otds details base url, username,password,ticket
def server = config.getProperty('server');
def adminUser = config.getProperty('adminUser');
def adminPass = config.getProperty('adminPass');


//Otds Tenant details to which need to create
def tenantUserName = "";
def tenantPassword = "";
def tenantName = "";
try{
tenantName = config.getProperty('tenantName');
	if("".equals(tenantName.trim())) {
     	   tenantUserName  = adminUser;
           tenantPassword = adminPass;
         } else {

                try { 
                        tenantUserName  = config.getProperty('tenantUserName');
                        tenantPassword = config.getProperty('tenantPassword');
                     }catch(Exception e) {
                       println " Error while retring tenant user , password ........... "
                     }

             }  
         
}catch(Exception e1) {
 tenantUserName = adminUser;
 tenantPassword = adminPass;
}

println "tenantUserName "+tenantUserName
println " tenantPassword "+tenantPassword



//def foderNmae = "Folder1"
def csBaseURL = config.getProperty('CSURL');
def foderNmae = config.getProperty('platformOrg');
def username = tenantUserName;
def password = tenantPassword;



/***************************************/
def getAuthTocken(String endPointURL, String userName, String password) { 

String urlParameters  = "username="+userName+"&password="+password+"";
byte[] postData = urlParameters.getBytes("utf-8");
 def post1 = new URL(endPointURL).openConnection();
 post1.setRequestMethod("POST")
 post1.setDoOutput(true)
 post1.setRequestProperty("Content-Type", "application/x-www-form-urlencoded") 
 
 try {
    DataOutputStream wr = new DataOutputStream(post1.getOutputStream()) 
   wr.write( postData );
 } catch(Exception e) {
 println " ==== >>>> aaa"+e
 }
 def resp1=post1.getInputStream().getText()
 JSONParser parse = new JSONParser();
 JSONObject jobj = (JSONObject)parse.parse(resp1);
 String ticket = (String) jobj.get("ticket") 
 return ticket;

}

 ticket = getAuthTocken(csBaseURL+"/cs/cs/api/v1/auth",username,password);



/***************************************/



def getRoleID(String csBaseURL, String roleName, String userName, String password, String ticket) {

//def ticket = getAuthTocken(csBaseURL+"/cs/cs/api/v1/auth",userName,password);


def getRoleID = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:api.ecm.opentext.com\" xmlns:urn1=\"urn:MemberService.service.livelink.opentext.com\">\n" + 
				"   <soapenv:Header>\n" + 
				"      <urn:OTAuthentication>\n" + 
				"         <!--Optional:-->\n" + 
				"         <urn:AuthenticationToken>"+ticket+"</urn:AuthenticationToken>\n" + 
				"      </urn:OTAuthentication>\n" + 
				"   </soapenv:Header>\n" + 
				"   <soapenv:Body>\n" + 
				"      <urn1:GetGroupByName>\n" + 
				"         <!--Optional:-->\n" + 
				"         <urn1:name>"+roleName+"</urn1:name>\n" + 
				"      </urn1:GetGroupByName>\n" + 
				"   </soapenv:Body>\n" + 
				"</soapenv:Envelope>";
   
 def post = new URL(csBaseURL+"/cws/services/MemberService").openConnection(); 
 post.setRequestMethod("POST") 
 post.setDoOutput(true)
 post.setRequestProperty("Content-Type", "text/xml") 
 post.setRequestProperty("Accept", "text/xml")    
 post.getOutputStream().write(getRoleID.getBytes("UTF-8"));
 def resp=post.getInputStream().getText() 
def id =  getID(resp);
return id;
println "=======>>>>>> Role "+id;

}
def getID(String resp) {
	def response = new XmlSlurper().parseText(resp)
	def titles = response.'**'.findAll { node -> node.name() == 'ID' }*.text()
	 return titles.get(0)
}



/***************************************/



def getFolderID(String csBaseURL, String folderName, String userName, String password, String ticket) {

//def ticket = getAuthTocken(csBaseURL+"/cs/cs/api/v1/auth",userName,password);


def getFolderID = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:api.ecm.opentext.com\" xmlns:urn1=\"urn:DocMan.service.livelink.opentext.com\">\n" + 
				"   <soapenv:Header>\n" + 
				"      <urn:OTAuthentication>\n" + 
				"         <!--Optional:-->\n" + 
				"         <urn:AuthenticationToken>"+ticket+"</urn:AuthenticationToken>\n" + 
				"      </urn:OTAuthentication>\n" + 
				"   </soapenv:Header>\n" + 
				"   <soapenv:Body>\n" + 
				"      <urn1:GetNodeByName>\n" + 
				"         <urn1:parentID>2000</urn1:parentID>\n" + 
				"         <!--Optional:-->\n" + 
				"         <urn1:name>"+folderName+"</urn1:name>\n" + 
				"      </urn1:GetNodeByName>\n" + 
				"   </soapenv:Body>\n" + 
				"</soapenv:Envelope>";
   
 def post = new URL(csBaseURL+"/cws/services/DocumentManagement").openConnection(); 
 post.setRequestMethod("POST") 
 post.setDoOutput(true)
 post.setRequestProperty("Content-Type", "text/xml") 
 post.setRequestProperty("Accept", "text/xml")    
 post.getOutputStream().write(getFolderID.getBytes("UTF-8"));
 def resp=post.getInputStream().getText() 
def id =  getID(resp);
return id;
println "=======>>>>>>Folder ID" +id;



}


/***************************************/



def membership(String csBaseURL, String folderName, String roleName, String userName, String password, String ticket) {


def roleID = getRoleID(csBaseURL,  roleName,  userName,  password , ticket)
def filderID = getFolderID(csBaseURL,  folderName,  userName,  password, ticket) 
println " folder ID "+roleID
//def OTCSTicket = getAuthTocken(csBaseURL+"/cs/cs/api/v1/auth",userName,password);
def OTCSTicket = ticket;

def membership = "body=%7B%22addEmptyAttribute%22%3Atrue%2C%22right_id%22%3A"+roleID+"%2C%22permissions%22%3A%5B%22see%22%2C%22see_contents%22%2C%22modify%22%2C%22edit_attributes%22%2C%22add_items%22%2C%22reserve%22%2C%22add_major_version%22%2C%22delete_versions%22%2C%22delete%22%2C%22edit_permissions%22%5D%2C%22apply_to%22%3A3%2C%22include_sub_types%22%3A%5B204%2C207%2C215%2C298%2C3030202%5D%7D";

//println "ticket ="+OTCSTicket
//println "  reqBody = "+membership 
//println "CS URL" +csBaseURL+"/cs/cs/api/v2/nodes/"+filderID+"/permissions/custom"

 
 def post = new URL(csBaseURL+"/cs/cs/api/v2/nodes/"+filderID+"/permissions/custom").openConnection(); 
 post.setRequestMethod("POST") 
 post.setDoOutput(true)
 post.setRequestProperty("Content-Type", "application/json")
 post.setRequestProperty("OTCSTicket", OTCSTicket) 
 post.getOutputStream().write(membership.getBytes("UTF-8"));
 def resp=post.getInputStream().getText() 

//println "Assign roles" +resp;

}


membership(csBaseURL, csrootFolder,"Cordys@Work#Administrator",username,password,ticket)  

membership(csBaseURL, csrootFolder ,"Cordys@Work#Analyst",username,password,ticket)  

membership(csBaseURL,csrootFolder ,"Cordys@Work#Deployer",username,password,ticket)  

membership(csBaseURL,csrootFolder ,"Cordys@Work#Developer",username,password,ticket)  

//membership(csBaseURL,csrootFolder,OpenText Party Management#Party Administrator",username,password,ticket)
 