import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser; 


// ********************** Start of creating tenant in otds ********************


def createORG(String createTenantEndpointURL, String method, String adminAuthendPoint, String tenantUserNmae, String tenantPassword, String tenantName, String adminUser, String adminPass) {
	GroovyShell shell = new GroovyShell()
	def tools = shell.parse(new File('./otdsjson/TenantCreation.groovy'))
	def tenantCreationPayload = tools.tenantCreation(tenantName, tenantUserNmae, tenantPassword);
	def adminAuth = shell.parse(new File('./otdsjson/authentication.groovy'))
	def adminAuthPayload = adminAuth.adminAuth(adminUser, adminPass);
	fireRequest(createTenantEndpointURL,"POST", tenantCreationPayload, adminAuthendPoint, adminAuthPayload);
}

createORG(createTenantEndpointURL,method,adminAuthendPoint,tenantUserNmae,tenantPassword,tenantName,adminUser,adminPass)
// ********************** Start of tenant configuration in otds ********************





GroovyShell shell1 = new GroovyShell()

def orgAdminPlayLoad(String tenantUserNmae,String tenantPassword) {
	GroovyShell shell = new GroovyShell()
	def orgAdminAuth = shell.parse(new File('./otdsjson/authentication.groovy'))
	return orgAdminAuth.adminAuth(tenantUserNmae, tenantPassword);
}

//def resourceID = CreateOtdsOrg(orgCreatePartitionURL, method, orgAdminAuthendPoint, partitionName, orgCreateResourceURL,resourceName, platformURL, plarformUser, platformPass, getOrgPartitionURL, getOrgUserURL,getOrgResourceURL, orgCreateAccessRolesURL, tenantUserNmae, partition1, orgCreateUserURL, tenantName, tenantPassword,orgConsoloditaeURL,orgTrustSiteURL,platformBaseURL,assignRoleToUser);

println "resource key "+resourceID

def CreateOtdsOrg(String orgCreatePartitionURL, String method, String orgAdminAuthendPoint, String partitionName,String orgCreateResourceURL,String resourceName, String platformURL, String plarformUser, String platformPass, String getOrgPartitionURL, String getOrgUserURL, String getOrgResourceURL, String orgCreateAccessRolesURL, String tenantUserNmae, String partition1, String orgCreateUserURL,String tenantName,String tenantPassword, String orgConsoloditaeURL,String orgTrustSiteURL, String platformBaseURL, String assignRoleToUser) {
  def orgAdminAuthPayload = orgAdminPlayLoad(tenantUserNmae,tenantPassword)
  cratePartition(orgCreatePartitionURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload,partitionName)
  createResource(orgCreateResourceURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload, resourceName, platformURL, plarformUser, platformPass)
  def orgPartitionLOcation = getPartitionLocation(getOrgPartitionURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload)
  def orgUserLOcation = getUserLocation(getOrgUserURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload)
  def orgResourceResp = getResourceResp(getOrgResourceURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload);
  updateAccessResource(orgCreateAccessRolesURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload,resourceName, tenantUserNmae, partition1, orgUserLOcation, partitionName, orgPartitionLOcation);
  //createUser(orgCreateUserURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload,tenantName,tenantPassword,partitionName,orgPartitionLOcation )
  consolidateRes(orgResourceResp, orgConsoloditaeURL, method, orgAdminAuthendPoint, orgAdminAuthPayload)
  addTrust(orgTrustSiteURL, platformBaseURL, method, orgAdminAuthendPoint,orgAdminAuthPayload);
  //addRolesToUser(assignRoleToUser, partitionName, tenantName , orgAdminAuthendPoint, orgAdminAuthPayload)
  return getResID(orgResourceResp);
}

//Create partition
def cratePartition(String orgCreatePartitionURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload, String partitionName ) {
	println "INFO : OTDS -> Creating partition ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgPartition = shell1.parse(new File('./otdsjson/Createpartition.groovy'))
	def orgPartitionPayload = orgPartition.createPartition(partitionName , partitionName);        	
	fireRequest(orgCreatePartitionURL ,"POST", orgPartitionPayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Created partition \n";
}

//Create Resource
def createResource(String orgCreateResourceURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload, String resourceName, String platformURL, String plarformUser, String platformPass) {
	println "INFO : OTDS -> Creating Resource ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgResource = shell1.parse(new File('./otdsjson/createResource.groovy'))
	def orgResourcePayload = orgResource.createResource(resourceName, platformURL, plarformUser, platformPass);        
	fireRequest(orgCreateResourceURL ,"POST", orgResourcePayload, orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Created Resource \n";

}

// get partition location
def getPartitionLocation(String getOrgPartitionURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload) {
        println "INFO : OTDS -> Getting partition location ..............";
	def orgPartitionResp = fireRequest1(getOrgPartitionURL,"GET","",orgAdminAuthendPoint,orgAdminAuthPayload);
	orgPartitionLOcation = parseResp(orgPartitionResp ,"location")
       // println "INFO : OTDS -> Fetched partition location \n ";
}

// get user location
def getUserLocation(String getOrgUserURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload) {
        println "INFO : OTDS -> Getting User location .............."+getOrgUserURL;
println "INFO : OTDS -> Getting User location .............."+orgAdminAuthendPoint;
println "INFO : OTDS -> Getting User location .............."+orgAdminAuthPayload;
	def orgUserResp = fireRequest1(getOrgUserURL ,"GET","",orgAdminAuthendPoint,orgAdminAuthPayload);
	orgUserLOcation = parseResp(orgUserResp, "location")
       // println "INFO : OTDS -> Fetched user location \n";

}
getOrgResourceURL
// get user Resource
def getResourceResp(String getOrgResourceURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload){
	orgResourceResp = fireRequest1(getOrgResourceURL ,"GET","",orgAdminAuthendPoint,orgAdminAuthPayload);
}

def checkOrgAvailability(String getOrgUserURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload) {
def orgUserResp;       
 try{
        println "INFO : OTDS -> Getting User location ..............";
	orgUserResp = fireRequest1(getOrgUserURL ,"GET","",orgAdminAuthendPoint,orgAdminAuthPayload);	
        println " Resp "+orgUserResp;
        return true;
        }catch(IOException e) {
          println "=====> "+e
        return false;
        }
}

//Update access resource
def updateAccessResource(String orgCreateAccessRolesURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload,resourceName, tenantUserNmae, partition1, orgUserLOcation, partitionName, orgPartitionLOcation){
        println "INFO : OTDS -> Updating Access Roles ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgAccessResource = shell1.parse(new File('./otdsjson/updateAccessRoles.groovy'))
	def orgAccessResourcePayload = orgAccessResource.updateAccessRoles(resourceName, tenantUserNmae, partition1, orgUserLOcation, partitionName, orgPartitionLOcation );        
	fireRequest(orgCreateAccessRolesURL,"PUT", orgAccessResourcePayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Updated Access Roles \n";

}

//Update access resource
def updateAccessResourceIhub(String orgCreateAccessRolesURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload,resourceName, tenantUserNmae, partition1, orgUserLOcation, partitionName, orgPartitionLOcation,String groupDescription, String groupLocation, String groupFullname,String groupPartition){
        println "INFO : OTDS -> Updating Access Roles ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgAccessResource = shell1.parse(new File('./otdsjson/updateAccessRolesForIHUB.groovy'))
	def orgAccessResourcePayload = orgAccessResource.updateAccessRoles(resourceName, tenantUserNmae, partition1, orgUserLOcation, partitionName, orgPartitionLOcation, groupDescription, groupLocation, groupFullname,groupPartition);        
	println " update access roles "+orgAccessResourcePayload
	fireRequest(orgCreateAccessRolesURL,"PUT", orgAccessResourcePayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Updated Access Roles \n";

}

//Update access resource
def updateAccessResource1(String orgCreateAccessRolesURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload,resourceName, tenantUserNmae, partition1, orgUserLOcation, partitionName, orgPartitionLOcation){
        println "INFO : OTDS -> Updating Access Roles ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgAccessResource = shell1.parse(new File('./otdsjson/updateAccessRoles.groovy'))
	def orgAccessResourcePayload = 
"{\r\n" + 
        		"  \"userPartitions\": [\r\n" + 
        		"    {\r\n" + 
        		"      \"name\": \"e20\",\r\n" + 
        		"      \"displayName\": \"e20\",\r\n" + 
        		"      \"userPartition\": \"e20\",\r\n" + 
        		"       \"location\": \""+orgPartitionLOcation+"\",\r\n" + 
        		"      \"description\": \"e20\"\r\n" + 
        		"    }\r\n" + 
        		"  ]\r\n" + 
        		"}";	

  
	fireRequest(orgCreateAccessRolesURL,"POST", orgAccessResourcePayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Updated Access Roles";

}

//Create user
def createUser(String orgCreateUserURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload,tenantName,tenantPassword,partitionName,orgPartitionLOcation ) {
        println "INFO : OTDS -> Creating User ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgCreateUser = shell1.parse(new File('./otdsjson/CreateUsers.groovy'))
	def orgCreateUserPayload = orgCreateUser.createUser(tenantName,"","","","","",tenantPassword,partitionName ,orgPartitionLOcation);  
	fireRequest(orgCreateUserURL,"POST", orgCreateUserPayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Created User \n ";

}

def createUser1(String orgCreateUserURL, String method,  String orgAdminAuthendPoint, String tenantUserNmae,String userName,String tenantPassword,String partitionName,String getOrgPartitionURL){
        def orgAdminAuthPayload = orgAdminPlayLoad(tenantUserNmae,tenantPassword)
        def orgPartitionLOcation = getPartitionLocation(getOrgPartitionURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload)       
        println "INFO : OTDS -> Creating User ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgCreateUser = shell1.parse(new File('./otdsjson/CreateUsers.groovy'))
	def orgCreateUserPayload = orgCreateUser.createUser(userName,"","","","","",tenantPassword,partitionName ,orgPartitionLOcation);  
	fireRequest(orgCreateUserURL,"POST", orgCreateUserPayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Created User \n";

}

def createUserInOTDS(String orgCreateUserURL, String method,  String orgAdminAuthendPoint, String tenantUserNmae,String userName,String userPass,String tenantPassword,String partitionName,String getOrgPartitionURL){
        def orgAdminAuthPayload = orgAdminPlayLoad(tenantUserNmae,tenantPassword)
        def orgPartitionLOcation = getPartitionLocation(getOrgPartitionURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload)       
        println "INFO : OTDS -> Creating User ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgCreateUser = shell1.parse(new File('./otdsjson/CreateUsers.groovy'))
	def orgCreateUserPayload = orgCreateUser.createUser(userName,"","","","","",userPass,partitionName ,orgPartitionLOcation);  
	fireRequest(orgCreateUserURL,"POST", orgCreateUserPayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Created User \n";

}



def consolidateRes(String resResponse, String orgConsoloditaeURL, String method, String orgAdminAuthendPoint, String orgAdminAuthPayload) {
//consolidate Resource
        println "INFO : OTDS -> Consolidating Resource ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgResourceLOcation = parseResp(resResponse ,"resourceDN");
	def consolidateResource= shell1.parse(new File('./otdsjson/ConsolidateResource.groovy'))
	def consolidateResourcePayload = consolidateResource.consolidateResource(orgResourceLOcation);        
	fireRequest(orgConsoloditaeURL ,"POST", consolidateResourcePayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Consolidated Resource \n";

}

//consolidate Resource
def getResID(String resResponse) {
    GroovyShell shell1 = new GroovyShell()
	def orgResourceID = parseResp(resResponse ,"resourceID");
	return orgResourceID;
}

def parseJson(String resResponse,String key) {
    GroovyShell shell1 = new GroovyShell()
	def orgResourceID = parseResp(resResponse ,"resourceID");
	return orgResourceID;
}

def parseAnyJson(String resResponse,String key) {
    GroovyShell shell1 = new GroovyShell()
	def orgResourceID = parseResp(resResponse ,key);
	return orgResourceID;
}

def addTrust(String orgTrustSiteURL, String platformBaseURL, String method, String orgAdminAuthendPoint, String orgAdminAuthPayload) {
        println "INFO : OTDS -> Adding Trust store platform URL ..............";
        GroovyShell shell1 = new GroovyShell()	
	def trustedSites= shell1.parse(new File('./otdsjson/TrustedSites.groovy'))
	def trustedSitesPayload = trustedSites.trustedSites(platformBaseURL);
        fireRequest(orgTrustSiteURL ,"PUT", trustedSitesPayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Added Trust store platform URL \n";

}


def addRolesToUser(String assignRoleToUser, String partitionName, String tenantName , String tenantUserNmae, String tenantPassword, String orgAdminAuthendPoint) {
	println "INFO : OTDS -> Adding roles to User ..............";	   
	def orgAdminAuthPayload = orgAdminPlayLoad(tenantUserNmae,tenantPassword)
   	GroovyShell shell1 = new GroovyShell()	   
	def assignRoleToUser1 = shell1.parse(new File('./otdsjson/AssignRoleToUser.groovy'))        
	def assignRoleToUserPayload = assignRoleToUser1.addRoleToUser(partitionName,tenantName );       
	fireRequest(assignRoleToUser ,"POST", assignRoleToUserPayload, orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Added roles to User \n";
}


def addRolesToUser(String assignRoleToUser, String partitionName, String tenantName , String tenantUserNmae, String tenantPassword, String orgAdminAuthendPoint,String roleName) {
	println "INFO : OTDS -> Adding roles to User ******* ..............";	    
	def orgAdminAuthPayload = orgAdminPlayLoad(tenantUserNmae,tenantPassword)
   	 GroovyShell shell1 = new GroovyShell()	
	def assignRoleToUser1 = shell1.parse(new File('./otdsjson/AssignRoleToUser.groovy'))
	def assignRoleToUserPayload = assignRoleToUser1.addRoleToUser(partitionName,tenantName,roleName);       
	fireRequest(assignRoleToUser ,"POST", assignRoleToUserPayload, orgAdminAuthendPoint, orgAdminAuthPayload);
         
        println "INFO : OTDS -> Added roles to User \n";
}


// ********************** Start of Reusable methods in otds ********************

def getAuthTocken(String endPointURL, String adminAuthPayload ) { 
 def post1 = new URL(endPointURL).openConnection();
 post1.setRequestMethod("POST")
 post1.setDoOutput(true)
 post1.setRequestProperty("Content-Type", "application/json") 
 post1.getOutputStream().write(adminAuthPayload.getBytes("UTF-8"));
 def resp1=post1.getInputStream().getText()
 JSONParser parse = new JSONParser();
 JSONObject jobj = (JSONObject)parse.parse(resp1);
 String ticket = (String) jobj.get("ticket") 
 return ticket;

}

def fireRequest(String endpointURL, String Method, String request, String adminAuthendPoint, String adminAuthPayload) {
 def ticket = getAuthTocken(adminAuthendPoint, adminAuthPayload );
 //println "ticker"+ ticket;
 //println "end point "+endpointURL
 def post = new URL(endpointURL).openConnection();
 //println "connection success "+Method
 

 //post.setRequestProperty("X-HTTP-Method-Override", "PATCH");
 post.setRequestMethod(Method) 
 post.setDoOutput(true)
 post.setRequestProperty("Content-Type", "application/json")
 post.setRequestProperty("OTDSTicket", ticket)
 //println "ticket"+ticket; 
  //println "request"+request;
   post.getOutputStream().write(request.getBytes("UTF-8"));
   int status = post.getResponseCode();
 def resp=post.getInputStream().getText()
   // println " -------- "+resp
 return resp;
}

def fireRequest2(String endpointURL, String Method, String request, String adminAuthendPoint, String adminAuthPayload) {
 def ticket = getAuthTocken(adminAuthendPoint, adminAuthPayload );
 //println "ticker"+ ticket;
 //println "end point "+endpointURL
 def post = new URL(endpointURL).openConnection();
 //println "connection success "+Method
 post.setRequestMethod(Method) 
 post.setDoOutput(true)
 post.setRequestProperty("Content-Type", "application/json")
 post.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
 post.setRequestProperty("Accept","*/*");
 println "OTDSTicket " +ticket
 post.setRequestProperty("OTDSTicket", ticket)
 //println "ticket"+ticket; 
  //println "request"+request;
  
   File file = new File("out12.txt")
    String fileContent = file.text
 post.getOutputStream().write(fileContent.getBytes("UTF-8"));
 
 //int status = post.getResponseCode();
 
 //def inputStream = post.getErrorStream().getText();
 
 //println "status :: "+status
 
 //println "inputStream :: "+inputStream
 
 
def resp=post.getInputStream().getText()
//println "***** response *****\n "+ resp
return resp;
}




def fireRequest1(String endpointURL, String Method, String request, String adminAuthendPoint, String adminAuthPayload) {
 def ticket = getAuthTocken(adminAuthendPoint, adminAuthPayload );
 def post = new URL(endpointURL).openConnection();
 post.setRequestMethod("GET")
 post.setDoOutput(true)
 post.setRequestProperty("Content-Type", "application/json")
 post.setRequestProperty("OTDSTicket", ticket)
 //post.getOutputStream().write("");
 def resp=post.getInputStream().getText() 
 return resp;
}


def parseResp(String jsonString, String key) {
 JSONParser parse1 = new JSONParser();
 JSONObject jobj1 = (JSONObject)parse1.parse(jsonString);
 String value1 = (String) jobj1.get(key)
 return value1;
}


// Cs specific things

def createCSResource(String orgCreateResourceURL ,String method, String orgAdminAuthendPoint,String orgAdminAuthPayload,String csUserName, String cdpassword, String csHost,String resourceName,String resourceID) {
	println "INFO : OTDS -> Creating  CS Resource ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgResource = shell1.parse(new File('./otdsjson/Cs_ResourceCreationInOtds_res.groovy'))
	def orgResourcePayload = orgResource.createCsResource(csUserName, cdpassword, csHost, resourceName, resourceID);
    //println " req "+orgResourcePayload	
    def resp = fireRequest(orgCreateResourceURL ,"POST", orgResourcePayload, orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Created CS Resource \n";
    return resp;
}

def createIHUBResource(String orgCreateResourceURL ,String method, String orgAdminAuthendPoint,String orgAdminAuthPayload,String resourceName) {
        println "INFO : OTDS -> Creating  iHUB Resource ..............";
             GroovyShell shell2 = new GroovyShell()
        def ihubRes = shell2.parse(new File('./otdsjson/IHUB_REQUEST.groovy'))
        def ihubResPayload = ihubRes.iHUBResCreation(resourceName)
        println " req "+ihubResPayload
        def resID = fireRequest(orgCreateResourceURL ,"POST", ihubResPayload, orgAdminAuthendPoint, orgAdminAuthPayload);
        println resID
        resID = parseResp(resID ,"resourceID")
        println "IHUB resource ID is is  : \n" + resID;
        println "INFO : OTDS -> Created iHUB Resource \n";
        return resID;

}

def allowImpersonate(String orgCreateResourceURL ,String method, String orgAdminAuthendPoint,String orgAdminAuthPayload,String resourceName) {
	println "INFO : OTDS -> Adding allow Impersonate ..............";
             GroovyShell shell3 = new GroovyShell()
        def allowImp = shell3.parse(new File('./otdsjson/IHUB_REQUEST.groovy'))
        def allowImpPayload = allowImp.allowImpersonation(resourceName)
        //println " req "+allowImpPayload
        fireRequest(orgCreateResourceURL ,"PUT", allowImpPayload, orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Added allow Impersonate \n";

}

def createIhubOauthClient(String oAuthClientCreationURL,String method,String orgAdminAuthendPoint,String orgAdminAuthPayload,String oauthClientName,iHubHost) {

    println "INFO : OTDS -> OAuth client creation ..............";
    GroovyShell shell2 = new GroovyShell()
    def ihubRes = shell2.parse(new File('./otdsjson/IHUB_REQUEST.groovy'))
    def ihubOauthPayload = ihubRes.oAuthClientCreation(oauthClientName,iHubHost)
    println " ********************* Req **************************  \n"+ihubOauthPayload
    fireRequest(oAuthClientCreationURL ,"POST", ihubOauthPayload, orgAdminAuthendPoint, orgAdminAuthPayload);
    println "INFO : OTDS -> Created OAUTH Client \n";

}

def getIhubOauthClientID(String oAuthClientCreationURL,String method,String orgAdminAuthendPoint,String orgAdminAuthPayload){
    println "INFO : OTDS -> Getting Oauth ID  ..............";
    def oauthClientID = fireRequest1(oAuthClientCreationURL,"GET","",orgAdminAuthendPoint,orgAdminAuthPayload);
    println oauthClientID
    oauthID = parseResp(oauthClientID ,"uuid")
    println "oauth ID is  : \n" + oauthID;
    return oauthID;
}

def allowImpersonate(String orgCreateResourceURL ,String method, String orgAdminAuthendPoint,String orgAdminAuthPayload) {
	println "INFO : OTDS -> Adding allow Impersonate ..............";
             GroovyShell shell3 = new GroovyShell()
      def allowImp = shell3.parse(new File('./otdsjson/Impersonate.groovy'))
      def allowImpPayload = allowImp.impersonate()
    println " req "+allowImpPayload
	fireRequest(orgCreateResourceURL ,"PUT", allowImpPayload, orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Added allow Impersonate \n";

}

def addTrust(String orgTrustSiteURL, String[] urls, String method, String orgAdminAuthendPoint, String orgAdminAuthPayload) {
        println "INFO : OTDS -> Adding Trust store platform URL ..............";
        GroovyShell shell1 = new GroovyShell()	
	def trustedSites= shell1.parse(new File('./otdsjson/TrustedSites.groovy'))
	def trustedSitesPayload = trustedSites.trustedSites(urls);
        fireRequest(orgTrustSiteURL ,"PUT", trustedSitesPayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Added Trust store platform URL \n";

}

def createGroup(String orgGroupURL,  String method, String partitionName, String groupName, String orgAdminAuthendPoint, String orgAdminAuthPayload) {
        println "INFO : OTDS -> Crete Group start ..............";
        GroovyShell shell1 = new GroovyShell()	
	def createGroup= shell1.parse(new File('./otdsjson/CreateGroup.groovy'))
	def createGroupPayload = createGroup.createGroup(partitionName,groupName);
        fireRequest(orgGroupURL ,"POST", createGroupPayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Crete Group start end \n";

}

def getOauthRespByClientID(String getOauthendPointURL,String method,String orgAdminAuthendPoint,String orgAdminAuthPayload){
    println "INFO : OTDS -> Getting Oauth ID  ..............";
    def oauthID = "";
    try{
    def oauthClientID = fireRequest1(getOauthendPointURL,"GET","",orgAdminAuthendPoint,orgAdminAuthPayload);
    println oauthClientID;    
    oauthID = parseResp(oauthClientID ,"id")
    println " OAUTH ID :: "+oauthID
    }catch (Exception e) {

    }
    return oauthID;
}

def updateLicense(String tenantName, String resName, String resID, String orgAdminAuthendPoint, String orgAdminAuthPayload, String serverName, String newLicenseServer){
        println "INFO : OTDS -> Updating licese start ..............";
		
        GroovyShell shell1 = new GroovyShell()
		def orgaddLicense = "";
		
		if("false".equals(newLicenseServer))
		orgaddLicense = shell1.parse(new File('./otdsjson/AddLicense2.groovy'))
		else
	    orgaddLicense = shell1.parse(new File('./otdsjson/AddLicense2.groovy'))
	  
	String orgaddLicensePayload = orgaddLicense.addLicense(tenantName, resName,resID);  
        println " URL :: "+serverName+"/otdsws/rest/licensemanagement/licenses/cn%3DContent_Server%C2%B9"+resID+"%2Cou%3DLicenses%2Cdc%3Didentity%2Cdc%3D"+tenantName+"%2Cdc%3Droot"
      
	  File file = new File("out12.txt")
      file.write orgaddLicensePayload
	   
	fireRequest2(serverName+"/otdsws/rest/licensemanagement/licenses/cn%3DContent_Server%C2%B9"+resID+"%2Cou%3DLicenses%2Cdc%3Didentity%2Cdc%3D"+tenantName+"%2Cdc%3Droot","PUT", orgaddLicensePayload, orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Updating licese end ";

}

//Create empty Resource
def createEmptyResource(String orgCreateResourceURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload, String resourceName) {
	println "INFO : OTDS -> Creating Resource ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgResource = shell1.parse(new File('./otdsjson/createEmptyResource.groovy'))
	def orgResourcePayload = orgResource.createResource(resourceName);        
	fireRequest(orgCreateResourceURL ,"POST", orgResourcePayload, orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Created Resource";

}

//Create empty Resource
def updateEmptyResource(String orgCreateResourceURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload, String resourceName, String resID, String platformURL, String plarformUser, String platformPass, String tenantName) {
	println "INFO : OTDS -> Creating Resource ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgResource = shell1.parse(new File('./otdsjson/updateEmptyResource.groovy'))
	def orgResourcePayload = orgResource.updateResource(resourceName, resID, platformURL, plarformUser, platformPass,tenantName);        
	fireRequest(orgCreateResourceURL ,"PUT", orgResourcePayload, orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Created Resource";

}


def consolidateResourceDN(String resourceDN, String orgConsoloditaeURL, String method, String orgAdminAuthendPoint, String orgAdminAuthPayload) {
//consolidate Resource
        println "INFO : OTDS -> Consolidating Resource ..............";
        GroovyShell shell1 = new GroovyShell()
	def consolidateResource= shell1.parse(new File('./otdsjson/ConsolidateResource.groovy'))
	def consolidateResourcePayload = consolidateResource.consolidateResource(resourceDN); 
  //println "INFO : OTDS -> Consolidating Resource .............."+consolidateResourcePayload;	
	return fireRequest(orgConsoloditaeURL ,"POST", consolidateResourcePayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Consolidated Resource";

}



def updateIVPublisherOauthClient(String orgConsoloditaeURL, String method, String orgAdminAuthendPoint, String orgAdminAuthPayload, String id, String redirectURL) {

println "INFO : OTDS -> Updating IV Publisher Oauth client with Redirect URL ..............";

    GroovyShell shell1 = new GroovyShell()
	def iv = shell1.parse(new File('./otdsjson/IV.groovy'))
	def ivpublisherUpdatePayload = iv.addingRedirectURLToPublisher(id,redirectURL); 
    fireRequest(orgConsoloditaeURL ,method, ivpublisherUpdatePayload , orgAdminAuthendPoint, orgAdminAuthPayload);
 println "INFO : OTDS -> Updated IV Publisher Oauth client with Redirect URL";

}


def createIVAppworksOauthClient(String orgConsoloditaeURL, String method, String orgAdminAuthendPoint, String orgAdminAuthPayload) {

println "INFO : OTDS -> Creating IV Appworks Oauth client with Redirect URL ..............";

    GroovyShell shell1 = new GroovyShell()
	def iv = shell1.parse(new File('./otdsjson/IV.groovy'))
	def ivAppworksCreatePayload = iv.ivAppworscreation(); 
    return fireRequest(orgConsoloditaeURL ,method, ivAppworksCreatePayload , orgAdminAuthendPoint, orgAdminAuthPayload);
    println "INFO : OTDS -> Created IV Appworks Oauth client with Redirect URL";

}




