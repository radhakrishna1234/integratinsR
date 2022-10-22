	
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// ********************** Start of creating tenant in otds ********************


def createORG(String createTenantEndpointURL, String method, String adminAuthendPoint, String tenantUserNmae, String tenantPassword, String tenantName, String adminUser, String adminPass) {
	GroovyShell shell = new GroovyShell()
	def tools = shell.parse(new File('TenantCreation.groovy'))
	def tenantCreationPayload = tools.tenantCreation(tenantName, tenantUserNmae, tenantPassword);
	def adminAuth = shell.parse(new File('authentication.groovy'))
	def adminAuthPayload = adminAuth.adminAuth(adminUser, adminPass);
	fireRequest(createTenantEndpointURL,"POST", tenantCreationPayload, adminAuthendPoint, adminAuthPayload);
}

createORG(createTenantEndpointURL,method,adminAuthendPoint,tenantUserNmae,tenantPassword,tenantName,adminUser,adminPass)
// ********************** Start of tenant configuration in otds ********************





GroovyShell shell1 = new GroovyShell()

def orgAdminPlayLoad(String tenantUserNmae,String tenantPassword) {
	GroovyShell shell = new GroovyShell()
	def orgAdminAuth = shell.parse(new File('authentication.groovy'))
	return orgAdminAuth.adminAuth(tenantUserNmae, tenantPassword);
}

//def resourceID = CreateOtdsOrg(orgCreatePartitionURL, method, orgAdminAuthendPoint, partitionName, orgCreateResourceURL,resourceName, platformURL, plarformUser, platformPass, getOrgPartitionURL, getOrgUserURL,getOrgResourceURL, orgCreateAccessRolesURL, tenantUserNmae, partition1, orgCreateUserURL, tenantName, tenantPassword,orgConsoloditaeURL,orgTrustSiteURL,platformBaseURL,assignRoleToUser);

println "resource key "+resourceID

def CreateOtdsOrg(String orgCreatePartitionURL, String method, String orgAdminAuthendPoint, String partitionName,String orgCreateResourceURL,String resourceName, String platformURL, String plarformUser, String platformPass, String getOrgPartitionURL, String getOrgUserURL, String getOrgResourceURL, String orgCreateAccessRolesURL, String tenantUserNmae, String partition1, String orgCreateUserURL,String tenantName,String tenantPassword, String orgConsoloditaeURL,String orgTrustSiteURL, String platformBaseURL, String assignRoleToUser) {
  def orgAdminAuthPayload = orgAdminPlayLoad(tenantUserNmae,tenantPassword)
  cratePartition(orgCreatePartitionURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload,partitionName)
  //createResource(orgCreateResourceURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload, resourceName, platformURL, plarformUser, platformPass)
  //def orgPartitionLOcation = getPartitionLocation(getOrgPartitionURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload)
 // def orgUserLOcation = getUserLocation(getOrgUserURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload)
 // def orgResourceResp = getResourceResp(getOrgResourceURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload);
  //updateAccessResource(orgCreateAccessRolesURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload,resourceName, tenantUserNmae, partition1, orgUserLOcation, partitionName, orgPartitionLOcation);
  //createUser(orgCreateUserURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload,tenantName,tenantPassword,partitionName,orgPartitionLOcation )
 // consolidateRes(orgResourceResp, orgConsoloditaeURL, method, orgAdminAuthendPoint, orgAdminAuthPayload)
  //addTrust(orgTrustSiteURL, platformBaseURL, method, orgAdminAuthendPoint,orgAdminAuthPayload);  
  return getResID(orgResourceResp);
}

//Create partition
def cratePartition(String orgCreatePartitionURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload, String partitionName ) {
	println "INFO : OTDS -> Creating partition ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgPartition = shell1.parse(new File('Createpartition.groovy'))
	def orgPartitionPayload = orgPartition.createPartition(partitionName , partitionName);        	
	fireRequest(orgCreatePartitionURL ,"POST", orgPartitionPayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Created partition";
}

//Create Resource
def createResource(String orgCreateResourceURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload, String resourceName, String platformURL, String plarformUser, String platformPass) {
	println "INFO : OTDS -> Creating Resource ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgResource = shell1.parse(new File('createResource.groovy'))
	def orgResourcePayload = orgResource.createResource(resourceName, platformURL, plarformUser, platformPass);        
	fireRequest(orgCreateResourceURL ,"POST", orgResourcePayload, orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Created Resource";

}

// get partition location
def getPartitionLocation(String getOrgPartitionURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload) {
        println "INFO : OTDS -> Getting partition lcation ..............";
	def orgPartitionResp = fireRequest1(getOrgPartitionURL,"GET","",orgAdminAuthendPoint,orgAdminAuthPayload);
	orgPartitionLOcation = parseResp(orgPartitionResp ,"location")
       // println "INFO : OTDS -> Fetched partition lcation ";
}

// get user location
def getUserLocation(String getOrgUserURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload) {
        println "INFO : OTDS -> Getting User lcation ..............";
	def orgUserResp = fireRequest1(getOrgUserURL ,"GET","",orgAdminAuthendPoint,orgAdminAuthPayload);
	orgUserLOcation = parseResp(orgUserResp, "location")
       // println "INFO : OTDS -> Fetched user lcation ";

}
getOrgResourceURL
// get user Resource
def getResourceResp(String getOrgResourceURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload){
	orgResourceResp = fireRequest1(getOrgResourceURL ,"GET","",orgAdminAuthendPoint,orgAdminAuthPayload);
}



//Update access resource
def updateAccessResource(String orgCreateAccessRolesURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload,resourceName, tenantUserNmae, partition1, orgUserLOcation, partitionName, orgPartitionLOcation){
        println "INFO : OTDS -> Updating Access Roles ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgAccessResource = shell1.parse(new File('updateAccessRoles.groovy'))
	def orgAccessResourcePayload = orgAccessResource.updateAccessRoles(resourceName, tenantUserNmae, partition1, orgUserLOcation, partitionName, orgPartitionLOcation );        
	fireRequest(orgCreateAccessRolesURL,"PUT", orgAccessResourcePayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Updated Access Roles";

}

//Create user
def createUser(String orgCreateUserURL, String method,  String orgAdminAuthendPoint, String orgAdminAuthPayload,tenantName,tenantPassword,partitionName,orgPartitionLOcation ) {
        println "INFO : OTDS -> Creating User ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgCreateUser = shell1.parse(new File('CreateUsers.groovy'))
	def orgCreateUserPayload = orgCreateUser.createUser(tenantName,"","","","","",tenantPassword,partitionName ,orgPartitionLOcation);  
	fireRequest(orgCreateUserURL,"POST", orgCreateUserPayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Created User ";

}

def createUser1(String orgCreateUserURL, String method,  String orgAdminAuthendPoint, String tenantUserNmae,String userName,String tenantPassword,String partitionName,String getOrgPartitionURL){
        def orgAdminAuthPayload = orgAdminPlayLoad(tenantUserNmae,tenantPassword)
        def orgPartitionLOcation = getPartitionLocation(getOrgPartitionURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload)       
        println "INFO : OTDS -> Creating User ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgCreateUser = shell1.parse(new File('CreateUsers.groovy'))
	def orgCreateUserPayload = orgCreateUser.createUser(userName,"","","","","",tenantPassword,partitionName ,orgPartitionLOcation);  
	fireRequest(orgCreateUserURL,"POST", orgCreateUserPayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Created User ";

}

def createUserInOTDS(String orgCreateUserURL, String method,  String orgAdminAuthendPoint, String tenantUserNmae,String userName,String userPass,String tenantPassword,String partitionName,String getOrgPartitionURL){
        def orgAdminAuthPayload = orgAdminPlayLoad(tenantUserNmae,tenantPassword)
        def orgPartitionLOcation = getPartitionLocation(getOrgPartitionURL, method,  orgAdminAuthendPoint, orgAdminAuthPayload)       
        println "INFO : OTDS -> Creating User ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgCreateUser = shell1.parse(new File('CreateUsers.groovy'))
	def orgCreateUserPayload = orgCreateUser.createUser(userName,"","","","","",userPass,partitionName ,orgPartitionLOcation);  
	fireRequest(orgCreateUserURL,"POST", orgCreateUserPayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Created User ";

}



def consolidateRes(String resResponse, String orgConsoloditaeURL, String method, String orgAdminAuthendPoint, String orgAdminAuthPayload) {
//consolidate Resource
        println "INFO : OTDS -> Consolidating Resource ..............";
        GroovyShell shell1 = new GroovyShell()
	def orgResourceLOcation = parseResp(resResponse ,"resourceDN");
	def consolidateResource= shell1.parse(new File('ConsolidateResource.groovy'))
	def consolidateResourcePayload = consolidateResource.consolidateResource(orgResourceLOcation);        
	fireRequest(orgConsoloditaeURL ,"POST", consolidateResourcePayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Consolidated Resource";

}

//consolidate Resource
def getResID(String resResponse) {
    GroovyShell shell1 = new GroovyShell()
	def orgResourceID = parseResp(resResponse ,"resourceID");
	return orgResourceID;
}



def addTrust(String orgTrustSiteURL, String platformBaseURL, String method, String orgAdminAuthendPoint, String orgAdminAuthPayload) {
        println "INFO : OTDS -> Adding Trust store platform URL ..............";
        GroovyShell shell1 = new GroovyShell()	
	def trustedSites= shell1.parse(new File('TrustedSites.groovy'))
	def trustedSitesPayload = trustedSites.trustedSites(platformBaseURL);
        fireRequest(orgTrustSiteURL ,"PUT", trustedSitesPayload , orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Added Trust store platform URL";

}


def addRolesToUser(String assignRoleToUser, String partitionName, String tenantName , String tenantUserNmae, String tenantPassword, String orgAdminAuthendPoint) {
	println "INFO : OTDS -> Adding roles to User ..............";	   
	def orgAdminAuthPayload = orgAdminPlayLoad(tenantUserNmae,tenantPassword)
   	GroovyShell shell1 = new GroovyShell()	   
	def assignRoleToUser1 = shell1.parse(new File('AssignRoleToUser.groovy'))        
	def assignRoleToUserPayload = assignRoleToUser1.addRoleToUser(partitionName,tenantName );       
	fireRequest(assignRoleToUser ,"POST", assignRoleToUserPayload, orgAdminAuthendPoint, orgAdminAuthPayload);
        println "INFO : OTDS -> Added roles to User";
}


def addRolesToUser(String assignRoleToUser, String partitionName, String tenantName , String tenantUserNmae, String tenantPassword, String orgAdminAuthendPoint,String roleName) {
	println "INFO : OTDS -> Adding roles to User ******* ..............";	    
	def orgAdminAuthPayload = orgAdminPlayLoad(tenantUserNmae,tenantPassword)
   	 GroovyShell shell1 = new GroovyShell()	
	def assignRoleToUser1 = shell1.parse(new File('AssignRoleToUser.groovy'))
	def assignRoleToUserPayload = assignRoleToUser1.addRoleToUser(partitionName,tenantName,roleName);       
	fireRequest(assignRoleToUser ,"POST", assignRoleToUserPayload, orgAdminAuthendPoint, orgAdminAuthPayload);
         
        println "INFO : OTDS -> Added roles to User";
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
 //println  ticket;
 def post = new URL(endpointURL).openConnection();
 post.setRequestMethod(Method) 
 post.setDoOutput(true)
 post.setRequestProperty("Content-Type", "application/json")
 post.setRequestProperty("OTDSTicket", ticket)
 post.getOutputStream().write(request.getBytes("UTF-8"));
 def resp=post.getInputStream().getText() 
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
