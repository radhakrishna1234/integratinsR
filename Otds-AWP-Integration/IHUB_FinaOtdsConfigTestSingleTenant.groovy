import groovy.json.JsonSlurper
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

GroovyShell configReader = new GroovyShell()
def config = configReader.parse(new File('ConfigFileReader.groovy'))
config.getProperty('');
def ldaproot = config.getProperty('ldaproot');
/********************************************* parametes need to change *********************************/
//iHUB details tenant name,base url, username,password
def iHUB_Org = config.getProperty('platformOrg');
def iHUB_URL = config.getProperty('iHUB_URL');
def iHUB_UserName = config.getProperty('iHUB_UserName');
def customer_name = config.getProperty('customer_name');
def platformOrg = config.getProperty('platformOrg');
def ihubtenantNameRandom = platformOrg
def platformBaseURL = config.getProperty('platformBaseURL');
def platformUserName = config.getProperty('platformUserName');
def platformPassword = config.getProperty('platformPassword');
def partitionName = config.getProperty('partitionName');
def isClassicIHUB = config.getProperty('isClassicIHUB');

def dbUser = config.getProperty('DBUsername');
def dbPassword = config.getProperty('DBPassword');
//def TenantNo = config.getProperty('TenantNo');
def port =  config.getProperty('DBport');
def dbServerName =  config.getProperty('DBEndpoint');
def dbName = config.getProperty('platformOrg');
//def dbPassword = "postgres";
def sysCon_URL = config.getProperty('sysCon_URL').replace("sysconsole","");
		String s = sysCon_URL;
		String ipOfMachine = s.split("//")[1];
		ipOfMachine= ipOfMachine.split(":")[0];
def sysCon_UserName = config.getProperty('sysCon_UserName');
def sysCon_Password = config.getProperty('sysCon_Password');


//Otds details base url, username,password
//def server = config.getProperty('server');
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
println "tenantPassword "+tenantPassword 
println "-------------------------------------------------------------------"+java.time.LocalDateTime.now()
/************************************************* no need  to change any parametes in below code *******************************************************/
println "===>>>"+server
Random rnd = new Random()
def randomNumber=rnd.next(20)
def authenticatorName = "otds"
def adminAuthendPoint = server +"/otdsws/rest/authentication/credentials";
def method ="";

def otdsBaseURL = "";
def createTenantEndpointURL = server +"/otdsws/tenants";
def resourceName = "res";
def appworksRes = platformOrg;
//def partitionName = platformOrg;
def orgURI = "/otdstenant/"+ tenantName;
def orgBaseURI = "";
def randomPIN = platformOrg;
      def user = "user_"+randomPIN;
	  println "======>>>>> Ceated user "+user;

   

    def adminGroupName1 = "otgroup_"+randomPIN;

	if("".equals(tenantName.trim()))
     	   orgBaseURI = server ;
         else
           orgBaseURI = server + orgURI;           
       
def oauthClientName="auth";
def oauthBasicClientName="auth1";
def orgCreatePartitionURL = orgBaseURI + "/otdsws/rest/partition"
def orgCreateUserURL = orgBaseURI + "/otdsws/rest/users"
def orgCreateResourceURL = orgBaseURI + "/otdsws/rest/resources"
def orgCreateAccessRolesURL = orgBaseURI + "/otdsws/rest/accessroles/Access%20to%20"+resourceName
def orgAdminAuthendPoint = orgBaseURI + "/otdsws/rest/authentication/credentials";
def getOrgPartitionURL = orgBaseURI +"/otdsws/rest/partitions/"+partitionName
def getOrgUserURL = orgBaseURI +"/otdsws/rest/users/"+tenantUserName
def getOrgGroupURL = orgBaseURI +"/otdsws/rest/groups/"+adminGroupName1
def getOrgResourceURL = orgBaseURI +"/otdsws/rest/resources/"+resourceName
def getAppworksResourceURL = orgBaseURI +"/otdsws/rest/resources/"+appworksRes
def orgConsoloditaeURL = orgBaseURI + "/otdsws/rest/consolidation";
def orgTrustSiteURL = orgBaseURI + "/otdsws/rest/systemconfig/whitelist"
def assignRoleToUser = orgBaseURI + "/otdsws/rest/users/"+tenantName +"@"+partitionName+"/memberof";
def allowImpersonate = orgBaseURI + "/otdsws/rest/resources/"+resourceName+"/impersonation";
def oAuthClientCreationURL = orgBaseURI + "/otdsws/rest/oauthclients";
//def oAuthClientURL = server + "/otdsws/rest/oauthclients";
def oAuthClientUpdateWithImpersonate = orgBaseURI + "/otdsws/rest/oauthclients/"+oauthClientName+"/impersonation";
//def oAuthClientUpdateWithImp = server + "/otdsws/rest/oauthclients/"+oauthClientName+"/impersonation";
def groupcreationURL = orgBaseURI+"/otdsws/rest/groups";
def oAuthClientURL = server + "/otdsws/rest/oauthclients";
def oAuthClientUpdateWithImp = server + "/otdsws/rest/oauthclients/"+oauthBasicClientName+"/impersonation";
def getOauthClient= server + "/otdsws/rest/oauthclients/"+oauthBasicClientName;
def partition1="otds.admin";
def resourceImpersonate = orgBaseURI+"/otdsws/rest/resources/"+appworksRes+"/impersonation";


println "************************************************************************************************************"
/*************TENANT ORG CREATION ********/
  	if("".equals(tenantName.trim()))
     	   otdsBaseURL=server;
         else
           otdsBaseURL = server +"/otdstenant/"+tenantName;
    def getTenantEndpointURL = server +"/otdsws/tenants/"+tenantName;
    def getDummyTenantEndpointURL = server +"/otdsws/tenants/dummy";
    GroovyShell shell1 = new GroovyShell()
    def otdsConfig = shell1.parse(new File('OtdsConfig.groovy'))
    def superAdminAuthPayload = otdsConfig.orgAdminPlayLoad(adminUser,adminPass);
    def orgAdminAuthPayload = otdsConfig.orgAdminPlayLoad(tenantUserName,tenantPassword)

   if(!"".equals(tenantName.trim())) {
        def isOrgCreated = otdsConfig.checkOrgAvailability(getTenantEndpointURL,"GET",adminAuthendPoint,superAdminAuthPayload);
        println "==>>"+isOrgCreated
        if(!isOrgCreated)
            otdsConfig.createORG(createTenantEndpointURL,method,adminAuthendPoint,tenantUserName,tenantPassword,tenantName,adminUser,adminPass);
    }
	
  
        def isDummyOrgCreated = otdsConfig.checkOrgAvailability(getDummyTenantEndpointURL,"GET",adminAuthendPoint,superAdminAuthPayload);
        println "==>> dummy org is not reated "+isDummyOrgCreated;
        if(!isDummyOrgCreated){
            otdsConfig.createORG(createTenantEndpointURL,method,adminAuthendPoint,tenantUserName,tenantPassword,"dummy",adminUser,adminPass);
            println "dummy org created";
        }
  

	//int randomPIN = (int)(Math.random()*9000)+1000;
	



		
     createUser(orgCreateUserURL,method, orgAdminAuthendPoint,tenantUserName,user,"Opentext1!",tenantPassword,partitionName,getOrgPartitionURL);      
	  println "--------"
    otdsConfig.createGroup(groupcreationURL,"POST",partitionName,adminGroupName1,orgAdminAuthendPoint, orgAdminAuthPayload)  
    println "--------......"
	 assignRole(otdsBaseURL,partitionName ,tenantName,tenantUserName ,tenantPassword, orgAdminAuthendPoint,user,adminGroupName1);
    assignRole(otdsBaseURL,partitionName ,tenantName,tenantUserName ,tenantPassword, orgAdminAuthendPoint,user,"Cordys@Work#Developer");
    assignRole(otdsBaseURL,partitionName ,tenantName,tenantUserName ,tenantPassword, orgAdminAuthendPoint,user,"Cordys@Work#Administrator");
    assignRole(otdsBaseURL,partitionName ,tenantName,tenantUserName ,tenantPassword, orgAdminAuthendPoint,user,"Cordys@Work#Deployer");
   
    
   
   
   def oauthID1 = otdsConfig.getOauthRespByClientID(getOauthClient, "GET",adminAuthendPoint,superAdminAuthPayload)
   println " Default Org  auth Tocken ID : "+oauthID1
   if ("".equals(oauthID1)) {
   otdsConfig.createIhubOauthClient(oAuthClientURL,"POST",adminAuthendPoint,superAdminAuthPayload,oauthBasicClientName,iHUB_URL);
   otdsConfig.allowImpersonate(oAuthClientUpdateWithImp,"PUT",adminAuthendPoint,superAdminAuthPayload,oauthBasicClientName);
   }



    otdsConfig.createIhubOauthClient(oAuthClientCreationURL,"POST",orgAdminAuthendPoint,orgAdminAuthPayload,oauthClientName,iHUB_URL);
    otdsConfig.allowImpersonate(oAuthClientUpdateWithImpersonate,"PUT",orgAdminAuthendPoint,orgAdminAuthPayload,oauthClientName);

    //otdsConfig.cratePartition(orgCreatePartitionURL, "POST",  orgAdminAuthendPoint, orgAdminAuthPayload,partitionName)

    def ihubResID=otdsConfig.createIHUBResource(orgCreateResourceURL , "POST", orgAdminAuthendPoint,orgAdminAuthPayload,resourceName);
    println "==>>"+ihubResID
    File ResID = new File("./ihubResID.txt")
    ResID.write(ihubResID)

    def orgResourceResp = otdsConfig.getResourceResp(getOrgResourceURL, "get",  orgAdminAuthendPoint, orgAdminAuthPayload);    
    otdsConfig.consolidateRes(orgResourceResp, orgConsoloditaeURL, "", orgAdminAuthendPoint, orgAdminAuthPayload)

    def orgResourceResp1 = otdsConfig.getResourceResp(getAppworksResourceURL, "get",  orgAdminAuthendPoint, orgAdminAuthPayload);    
    otdsConfig.consolidateRes(orgResourceResp1, orgConsoloditaeURL, "", orgAdminAuthendPoint, orgAdminAuthPayload)

    otdsConfig.allowImpersonate(allowImpersonate , "PUT", orgAdminAuthendPoint,orgAdminAuthPayload,resourceName);

    def orgPartitionLocation = otdsConfig.getPartitionLocation(getOrgPartitionURL, "GET",  orgAdminAuthendPoint, orgAdminAuthPayload)
    def orgUserLocation = otdsConfig.getUserLocation(getOrgUserURL, "GET",  orgAdminAuthendPoint, orgAdminAuthPayload)
	
	def groupLocation = otdsConfig.getUserLocation(getOrgGroupURL, "GET",  orgAdminAuthendPoint, orgAdminAuthPayload)
	
	
	

    otdsConfig.updateAccessResourceIhub(orgCreateAccessRolesURL, "POST",  orgAdminAuthendPoint, orgAdminAuthPayload,resourceName, "otadmin@otds.admin", partition1, orgUserLocation, partitionName, orgPartitionLocation, adminGroupName1,  groupLocation,  adminGroupName1+"@"+partitionName, partitionName)

     otdsConfig.allowImpersonate(resourceImpersonate ,"PUT",  orgAdminAuthendPoint, orgAdminAuthPayload); 
	


    File oauthID = new File("./ihubOauthClientID.txt")
    oauthID.write("123456789");
	
	def createUser(String orgCreateUserURL,String method, String orgAdminAuthendPoint, String tenantUserName ,String user,String userPass, String tenantPassword,String partitionName,String getOrgPartitionURL) {
       GroovyShell shell2 = new GroovyShell()
       def otdsConfig2 = shell2.parse(new File('OtdsConfig.groovy'))
       otdsConfig2.createUserInOTDS(orgCreateUserURL,method, orgAdminAuthendPoint,tenantUserName,user, userPass,tenantPassword,partitionName,getOrgPartitionURL);
     }   

     def assignRole(String orgBaseURI, String partitionName ,String tenantName,String tenantUserName ,String tenantPassword, String orgAdminAuthendPoint,String user, String role) {
       def assignRoleToUser1 = orgBaseURI + "/otdsws/rest/users/"+user+"@"+partitionName+"/memberof";
       GroovyShell shell2 = new GroovyShell()
       def otdsConfig2 = shell2.parse(new File('OtdsConfig.groovy'))
	
        if("".equals(tenantName.trim()))                            
       otdsConfig2.addRolesToUser(assignRoleToUser1 ,partitionName ,"opentext",tenantUserName ,tenantPassword, orgAdminAuthendPoint,role);
        else
       otdsConfig2.addRolesToUser(assignRoleToUser1 ,partitionName ,tenantName,tenantUserName ,tenantPassword, orgAdminAuthendPoint,role);
     }


//*********************************************************** IHUB SIDE *********************************************/


def sysCon_Auth_URL = sysCon_URL + "api/login"
def sysCon_getCluster_URL = sysCon_URL + "api/clusters"
def sysCon_CreateNode_URL = sysCon_URL + "api/nodes/"
def sysCon_createTenant_URL = sysCon_URL +"api/multitenant-otds/"
def sysCon_createVolume_URL = sysCon_URL +"api/volumes/"
def sysCon_OTDS_URL = sysCon_URL +"api/otds/"
def sysCon_UpdateVolume_URL = sysCon_URL +"api/volumes/"
def sysCon_Updatenode_URL = sysCon_URL+"api/nodes/config-file/"




def otdsLoginURL = orgBaseURI +"/otds-admin";
def oauth2_URL = orgBaseURI + "/otdsws/oauth2";
def otdsRestEndPoint = orgBaseURI +"/otdsws/rest";
def resourceID = new File('./ihubResID.txt').getText('UTF-8');
def oauthSecretKey = new File('./ihubOauthClientID.txt').getText('UTF-8');

//def ihubtenantNameRandom = ihubtenantName+
def ihubtenantName = config.getProperty('platformOrg');
//def ihubtenantNameRandom = ihubtenantName+randomPIN;
//def adminGroupName = adminGroupName1+"@CCPartition3";
def groupDescriptionAttribute =  "oTExternalID3";
def homeFolderAttribute = "/home/"+platformOrg;


def defaultOTDSAuthenticationURL = server + "/otdsws/oauth2";
def defaultOAuth2Client = oauthClientName;
def defaultOAuth2SecretKey = "123456789";


public String executeProcess(String curlRequest, int killConnection) {
def sout = new StringBuilder(), serr = new StringBuilder()
try{
def proc = "$curlRequest".execute()
proc.consumeProcessOutput(sout, serr)
proc.waitForOrKill(1000*killConnection)
}catch(Exception e) {
  //println " ddd "+e.getMessage();
}
return sout;
}



AuthToken = getAuthToken(sysCon_Auth_URL,sysCon_UserName,sysCon_Password)

    JSONParser parse = new JSONParser();
    JSONObject jobj = (JSONObject) parse.parse(AuthToken);
    AuthToken = (String) jobj.get("authToken");  
     
     println "Auth Tocken is "+AuthToken;



println "==============================>>>>>>> AuthToken tocken =="+AuthToken
ClusterID = getClusterID(sysCon_getCluster_URL,AuthToken,sysCon_UserName,sysCon_Password)
def TenantNo = getdummyClusterStatus(sysCon_URL, ClusterID, AuthToken);
 

def sysCon_Deletenode_URL = sysCon_URL+"api/nodes/"+ClusterID+"/"+ipOfMachine+""
println "==============================>>>>>>> Default ClusterID = "+AuthToken
def nodeUL = sysCon_Updatenode_URL+ClusterID+"/"+ipOfMachine+"";
//deleteihub0Pod(sysCon_Deletenode_URL,AuthToken)
def resp = checkPodAvail(nodeUL,AuthToken)
if("".equals(resp.trim()) ||  !resp.contains("DaemonSOAPSSLPort")) {
createNode(sysCon_CreateNode_URL,ClusterID,AuthToken,sysCon_UserName,sysCon_Password, ipOfMachine)
println "==============================>>>>>>> Noad created"
}
def createVolumnURL = sysCon_UpdateVolume_URL+ClusterID;
sysCon_UpdateVolume_URL=sysCon_UpdateVolume_URL+ClusterID+"/Default%20Volume";
createtenanturl = sysCon_createTenant_URL+ClusterID
def defaulttenanturl = sysCon_createTenant_URL+ClusterID+"/{tenantName}";
def enableVolumeURL = sysCon_URL+"api/volumes/"+ClusterID+"/"+ihubtenantNameRandom+"/status";
def otdsTicketURL = sysCon_URL+"api/otds/"+ClusterID;
String groupNameAttribute = "otExternalID3"
String adminGroupName = adminGroupName1+"%40"+partitionName;  //in curl @ means %40 , : mean %3A
String defaultHomeFolder="/home/"+ihubtenantNameRandom;
//String OTDSBaseURL =otdsBaseURL;
//String resourceID =resourceID;
String baseURL1 = otdsBaseURL.replace(":","%3A");
baseURL1 = baseURL1.replace("/","%2F");

defaultHomeFolder=defaultHomeFolder.replace("/","%2F");

otdsTicketTenant(otdsTicketURL, AuthToken, resourceID, baseURL1, defaultHomeFolder, groupNameAttribute, adminGroupName)

sleep 1000;
CreateVolumn(createVolumnURL, dbUser, dbPassword, ihubtenantName,AuthToken,ihubtenantNameRandom, isClassicIHUB);
 enableVolumn(enableVolumeURL,AuthToken);


println "============"+java.time.LocalDateTime.now()
stopServer(sysCon_URL,AuthToken,ipOfMachine );
checkNodeStatus(sysCon_URL,AuthToken,"Offline")
startServer(sysCon_URL,AuthToken,ipOfMachine);
checkNodeStatus(sysCon_URL,AuthToken,"Running")
println "-------------"+java.time.LocalDateTime.now()
sleep 1000;

/* *************************************** implementation ****************************************************/



def getdummyClusterStatus(String sysCon_URL, String ClusterID, String AuthToken) {
  println "============= checking Ihub tenant ount ========"
  String dummyTenantStatus =  "curl -k -X GET "+sysCon_URL+"api/multitenant-otds/"+ClusterID+" -H  \"accept: application/json\" -H  AuthToken:"+AuthToken+"";  
  def dummyTenantStatusResp = executeProcess(dummyTenantStatus,120);
  
  println "============= Req of getdummyClusterStatus " +dummyTenantStatus
  
  println "============= Resp of getdummyClusterStatus " +dummyTenantStatusResp
  
  if(dummyTenantStatusResp.contains("dummy") && dummyTenantStatusResp.contains("11111111111111111111111111") ) {
   println "============= This was not an Ihub first tenant ========"
  return "2";
 
  }
  else {
  println "============= This was Ihub first tenant ========"
  return "1";
  
  }
}



def checkStatus(String sysCon_URL,String AuthToken){  
		String s = sysCon_URL;
		String ipOfMachine = s.split("//")[1];
		ipOfMachine= ipOfMachine.split(":")[0];
		
   String checkNodeStatus = "curl -k -X GET "+sysCon_URL+"api/nodes/"+ipOfMachine+" -H  \"accept: application/json\" -H  AuthToken:"+AuthToken+"";
	 //println "\n\n\nprintln *************************************** cheking status of "+ipOfMachine+" ***************************************";
	//println "println  ====>>>> get status of "+ipOfMachine+" req : "+checkNodeStatus;  
  def checkNodeStatusresp = executeProcess(checkNodeStatus,45);  
	//println "println  ====>>>> get status of "+ipOfMachine+" resp : "+checkNodeStatusresp;
  //println "println --------------------------------------- cheking status of Ihub-0 end ---------------------------------------";
  return checkNodeStatusresp;
}

 //Offline
 //


def checkNodeStatus( String sysCon_URL, String AuthToken, String status) {

  long millis=System.currentTimeMillis();  
  //println "millis  "+millis
  String input="notRunning";

  for(int i=0;input.equals("notRunning");i++) {
    //println "before"
    sleep 15000;
    //println "After"
    long millis1=System.currentTimeMillis();

    long diff = millis1-millis;

     if(diff>=300000){
     input="Running";
     println "Timeout"
     }
     String response =  checkStatus(sysCon_URL,AuthToken);
    String[] runningNode = response.split(status);
	
    println "Waiting for status : "+ status +" currently "+status+" nodes are :" +runningNode.length-1 +" expected  6 nodes with status "+status
    if(runningNode.length-1==6) {
     input="Running";
     println "IHub-0 pod running with all services"
     }
  }	 
}	 

def stopServer(String nodeUL,String AuthToken, String ipOfMachine ){  
def nodeURLreq
  for(int i =1;i<=10;i++) {   
  def nodeURL = "curl -k -X PATCH "+nodeUL+"api/nodes/"+ipOfMachine+" -H  \"accept: application/json\" -H  AuthToken:"+AuthToken+" -H  \"Content-Type: application/x-www-form-urlencoded\" -d action=stopserver";
	 nodeURLreq = executeProcess(nodeURL,120);
 
  println "\n\n\nprintln *************************************** Stop server "+ipOfMachine+" start ***************************************";
	println "println  ====>>>> Delete Ihub-0 req : "+nodeURL;
	println "println  ====>>>> Delete Ihub-0 resp : "+nodeURLreq;
  println "println --------------------------------------- Stop server Ihub-0 end ---------------------------------------";
   if(nodeURLreq.contains("success")) {    
     println " --------------Server stopped at "+i+"th iteration"
      i=12;
     }
   }
   return nodeURLreq;

}


def startServer(String nodeUL,String AuthToken,String ipOfMachine){  

  def nodeURLreq;  
  for(int i=0 ;i<10;i++)  {
  def nodeURL = "curl -k -X PATCH "+nodeUL+"api/nodes/"+ipOfMachine+" -H  \"accept: application/json\" -H  AuthToken:"+AuthToken+" -H  \"Content-Type: application/x-www-form-urlencoded\" -d action=startserver";
	nodeURLreq = executeProcess(nodeURL,120);
 
  println "\n\n\nprintln *************************************** Start server "+ipOfMachine+" start ***************************************";
	println "println  ====>>>> Delete Ihub-0 req : "+nodeURL;
	println "println  ====>>>> Delete Ihub-0 resp : "+nodeURLreq;
  println "println --------------------------------------- Start server Ihub-0 end ---------------------------------------";
   if(nodeURLreq.contains("success")) {    
     println " --------------Server stopped at "+i +"th iteration";
      i=12;
     }
    }
       return nodeURLreq;
  }







def deleteihub0Pod(String nodeUL,String AuthToken){     
  def nodeURL = "curl -k -X DELETE "+nodeUL+" -H  \"accept: application/json\" -H  AuthToken:"+AuthToken+"";	
	def nodeURLreq = executeProcess(nodeURL,120);
 
  println "\n\n\nprintln *************************************** Delete Ihub-0 start ***************************************";
	println "println  ====>>>> Delete Ihub-0 req : "+nodeURL;
	println "println  ====>>>> Delete Ihub-0 resp : "+nodeURLreq;
  println "println --------------------------------------- Delete Ihub-0 end ---------------------------------------";
  return nodeURLreq;

}


def checkPodAvail(String nodeUL,String AuthToken){     
  def nodeURL = "curl -k -X GET "+nodeUL+" -H  \"accept: application/json\" -H  AuthToken:"+AuthToken+"";	
	def nodeURLreq = executeProcess(nodeURL,120);
 
  println "\n\n\nprintln *************************************** Check node availability start ***************************************";
	//println "println  ====>>>> node URL req : "+nodeURL;
	//println "println  ====>>>> node URL resp : "+nodeURLreq;
  println "println --------------------------------------- Check node availability end ---------------------------------------";
  return nodeURLreq;

}

def CreateVolumn(String createVolumeurl, String dbUser, String dbPassword, String ihubtenantName, String AuthToken, String ihubtenantNameRandom, String isClassicIHUB) {

      def storagelocation = "";
	  if(isClassicIHUB.equals("true")) {	  
	  storagelocation = "C%3A%5COpenText%5CInformationHub%5Cmodules%5CBIRTiHub%5CiHub%5Cshared%5Cstorage%5C";
	  } else {	  
	  storagelocation =  "%2FOpenText%2FInformationHub%2Fmodules%2FBIRTiHub%2FiHub%2Fshared%2F";
	  }
	  

 	def createVolCurlCommand ="curl -k -X POST "+createVolumeurl+" -H  \"accept: application/json\" -H  AuthToken:"+AuthToken+" -H  \"Content-Type: application/x-www-form-urlencoded\" -d volumeName="+ihubtenantNameRandom+"&volumeAdministratorEmail=&schemaName="+ihubtenantNameRandom+"&organizationId="+ihubtenantName+"&DBAUser="+dbUser+"&encryptionKeyForStorage=&storageLocation="+storagelocation+ihubtenantNameRandom+"&isOverrideDefaultPwd=&tablespace=&isCreateNewSchema=true&confirmSchemaPassword=&schemaPassword=&description=&DBAPassword="+dbPassword+"";
	def createVolCurlresp = executeProcess(createVolCurlCommand,120);
   println "\n\n\nprintln *************************************** Create Volumn start ***************************************";
	 println "println  ====>>>> Ceate Volume req : "+createVolCurlCommand;
	 println "println ====>>>> Ceate Volume  resp : "+createVolCurlresp;
   println "println ====>>> volumn name : "+ihubtenantNameRandom + " organization name :  "+ihubtenantName;

   println "println ---------------------------------------  Create Volumn end ---------------------------------------";
}

def updateDefaultVolume(String sysCon_UpdateVolume_URL, String ihubtenantName,String AuthToken) {
	def UpdateVolCurlCommand = "curl -k -X PATCH "+sysCon_UpdateVolume_URL+" -H  \"accept: application/json\" -H  AuthToken:"+AuthToken+" -H  \"Content-Type: application/json\" -d {\\\"newOrganizationId\\\":\\\""+ihubtenantName+"\\\"}";
	//def UpdatedVolume = ['bash', '-c', "$UpdateVolCurlCommand"].execute().text
    println "\n\n\nprintln *************************************** updateDefaultVolume start ***************************************";
	println "println ====>>>> Update updateDefaultVolume   req : "+UpdateVolCurlCommand;
	def UpdatedVolume = executeProcess(UpdateVolCurlCommand,120);

	println "println ====>>>> Update  updateDefaultVolume  resp : "+UpdatedVolume;
  println "println --------------------------------------- updateDefaultVolume end ---------------------------------------";
}

def updateDefaultTenant(String defaulttenanturl, String defaultOTDSAuthenticationURL, String defaultOAuth2Client, String defaultOAuth2SecretKey, String  AuthToken) {
   def updateDefaultTenant = "curl -k -X PUT ${defaulttenanturl} -H  \"accept: application/json\" -H  AuthToken:"+AuthToken+" -H  \"Content-Type: application/x-www-form-urlencoded\" -d OTDSAuthenticationURL=${defaultOTDSAuthenticationURL}&OAuth2Client=${defaultOAuth2Client}&searchCacheOnly=&resourceID=&userDescriptionAttribute=&userEmailAttribute=&defaultHomeFolder=&homeFolderAttribute=&groupNameAttribute=&OAuth2SecretKey=${defaultOAuth2SecretKey}&tenantName=Default%20Tenant&adminGroupName=&OTDSRestEndpoint=&groupEmailAttribute=&groupDescriptionAttribute=&cacheTimeOut=1440"
  println "\n\n\nprintln *************************************** updateDefaultTenant start ***************************************";
  println "println ====>>>> Default tenant update req : "+updateDefaultTenant;
   updateDefaultTenant1 = executeProcess(updateDefaultTenant,120);
    
    println "println ====>>>> Default tenant update resp : "+updateDefaultTenant1;
    println "println --------------------------------------- updateDefaultTenant end  ---------------------------------------";
}

def createtenant(String createtenanturl,String ihubtenantName, String oauth2_URL, String otdsRestEndPoint, String oauthClientName, String oauthSecretKey, String resourceID, String homeFolderAttribute, String groupDescriptionAttribute, String adminGroupName, String AuthToken) {
   def curlcommand = "curl -k -X POST ${createtenanturl} -H  \"accept: application/json\" -H  AuthToken:"+AuthToken+" -H  \"Content-Type: application/x-www-form-urlencoded\" -d tenantName=${ihubtenantName}&cacheTimeOut=1440&OTDSAuthenticationURL=${oauth2_URL}&OTDSRestEndpoint=${otdsRestEndPoint}&OAuth2Client=${oauthClientName}&OAuth2SecretKey=${oauthSecretKey}&resourceID=${resourceID}&userEmailAttribute=mail&defaultHomeFolder=${homeFolderAttribute}&adminGroupName=${adminGroupName}&groupNameAttribute=${groupDescriptionAttribute}"
   //def addTenant = ['bash', '-c', "$curlcommand"].execute().text
   println "\n\n\nprintln *************************************** createtenant start ***************************************";
		println "println ====>>>> Add tenant req : "+curlcommand;
	 def addTenant = executeProcess(curlcommand,120);
    
	  println "println ====>>>> Add tenant resp : "+addTenant;
    println "println --------------------------------------- createtenant end  ---------------------------------------";

}

def getAuthToken(String sysCon_Auth_URL,String sysCon_UserName,String sysCon_Password) {   
    String url = "curl -k -L -X POST ${sysCon_Auth_URL} -H  \"accept: application/json\" -H  \"Content-Type: application/x-www-form-urlencoded\" -d username=${sysCon_UserName}&password=${sysCon_Password}"
    def authToken = executeProcess(url,60);  
    println "\n\n\nprintln *************************************** getAuthToken start ***************************************";
    println "println ====>>>> getAuthToken REQ : "+url;
    println "println ====>>>> getAuthToken RESP : "+authToken;
    println "println --------------------------------------- getAuthToken end  ---------------------------------------";
    return authToken;
}

def getClusterID(String sysCon_getCluster_URL,String AuthToken,String sysCon_UserName,String sysCon_Password) {    
    String url = "curl -k -X GET ${sysCon_getCluster_URL}?search=DefaultCluster -H  \"accept: application/json\" -H  AuthToken:"+AuthToken+""
    //def clusterIDReq = ['bash', '-c', "curl -k -X GET \"${sysCon_getCluster_URL}?search=DefaultCluster\" -H  \"accept: application/json\" -H  AuthToken:AuthToken"].execute().text
    //def clusterIDReq = ['bash', '-c', "$url"].execute().text
	  //def clusterID1 = "$url".execute();
    def clusterIDReq = executeProcess(url,120);
    println "\n\n\nprintln *************************************** getClusterID start ***************************************";
    println "println ====>>>> getClusterID REQ : "+url;
    println "println ====>>>> getClusterID RESP : "+clusterIDReq;
    println "println --------------------------------------- getClusterID end  ---------------------------------------";
    JSONParser parse = new JSONParser();
    JSONObject jobj = (JSONObject) parse.parse(clusterIDReq);
    JSONObject jsonObject = new JSONObject(jobj);
    JSONObject getSth = jsonObject.get("clusters");
    Object clusterID = getSth.get("id");
  
   return clusterID.toString()
}

def createNode(String sysCon_CreateNode_URL, String ClusterID,String AuthToken,String sysCon_UserName,String sysCon_Password , String ipOfMachine) {   
    nodeurl = sysCon_CreateNode_URL+ClusterID
      String url = "curl -k -X POST ${nodeurl} -H  \"accept: application/json\" -H  AuthToken:"+AuthToken+" -H  \"Content-Type: application/x-www-form-urlencoded\" -d nodeName="+ipOfMachine+"&nodeDescription=ihub%20default%20cluster&clusterConfigurationPath=/OpenText/InformationHub/modules/BIRTiHub/iHub/shared/config"
    //def createNode = ['bash', '-c', "curl -k -X POST \"${nodeurl}\" -H  \"accept: application/json\" -H  AuthToken:AuthToken -H  \"Content-Type: application/x-www-form-urlencoded\" -d \"nodeName="+ipOfMachine+"&nodeDescription=ihub%20default%20cluster&clusterConfigurationPath=/OpenText/InformationHub/modules/BIRTiHub/iHub/shared/config\""].execute().text
    //def createNode = ['bash', '-c', "$url"].execute().text
	  def createNode = executeProcess(url,120);   
    println "\n\n\nprintln *************************************** createNode start ***************************************";
    println "println ====>>>> createNode REQ : "+url;
    println "println ====>>>> createNode RESP : "+createNode;
    println "println --------------------------------------- createNode end  ---------------------------------------";
    JSONParser parse = new JSONParser();
    JSONObject jobj = (JSONObject) parse.parse(createNode);

}



def enableVolumn(String enableVolumn, String AuthToken) {
   def curlcommand = "curl -k -X PATCH "+enableVolumn+" -H  \"accept: */*\" -H  AuthToken:"+AuthToken+" -H  \"Content-Type: application/x-www-form-urlencoded\" -d status=enable";
   //def addTenant = ['bash', '-c', "$curlcommand"].execute().text
	 def status = executeProcess(curlcommand,120);
    println "\n\n\nprintln *************************************** enableVolumn start ***************************************";
		println "println ====>>>> Add tenant req : "+curlcommand;
	  println "println ====>>>> Add tenant resp : "+status;
    println "println --------------------------------------- enableVolumn end  ---------------------------------------";

}


def postDefaultTenant(String postDefaultTenantURL, String AuthToken, String OTDSAuthenticationURL, String OAuth2Client,String OAuth2SecretKey) {
   def curlcommand = data = "curl -k -X POST "+postDefaultTenantURL+" -H  \"accept: application/json\" -H  AuthToken:"+AuthToken+" -H  \"Content-Type: application/x-www-form-urlencoded\" -d OTDSAuthenticationURL="+OTDSAuthenticationURL+"&OAuth2Client="+OAuth2Client+"&OAuth2SecretKey="+OAuth2SecretKey+"&tenantName=DEFAULT%20TENANT&cacheTimeOut=1440";
   //def addTenant = ['bash', '-c', "$curlcommand"].execute().text
	 def resp = executeProcess(curlcommand,120);
    println "\n\n\nprintln *************************************** Post Default Tenant start ***************************************";
		println "println ====>>>> Add tenant req : "+curlcommand;
	  println "println ====>>>> Add tenant resp : "+resp;
    println "println --------------------------------------- ost Default Tenant end  ---------------------------------------";

}


def otdsTicketTenant(String otdsTicketURL, String AuthToken, String resourceID, String OTDSBaseURL, String defaultHomeFolder, String groupNameAttribute, String adminGroupName) {
   def curlcommand = "\r\n" + 
	      		"curl -k -X POST ${otdsTicketURL} -H  \"accept: application/json\" -H  AuthToken:"+AuthToken+" -H  \"Content-Type: application/x-www-form-urlencoded\" -d OTDSAuthenticationURL=&OAuth2Client=&searchCacheOnly=&resourceID=${resourceID}&OTDSBaseURL=${OTDSBaseURL}&userDescriptionAttribute=&userEmailAttribute=mail&integrationType=OTDSTicket&defaultHomeFolder=${defaultHomeFolder}&homeFolderAttribute=&groupNameAttribute=${groupNameAttribute}&OAuth2SecretKey=&adminGroupName=${adminGroupName}&oTDSConfigurationType=CommonURL&OTDSRestEndpoint=&groupEmailAttribute=&groupDescriptionAttribute=&cacheTimeOut=1440";
   //def addTenant = ['bash', '-c', "$curlcommand"].execute().text
	 def status = executeProcess(curlcommand,120);
    println "\n\n\nprintln *************************************** Createing otds ticket tenant ***************************************";
		println "println ====>>>> Add tenant req : "+curlcommand;
	  println "println ====>>>> Add tenant resp : "+status;
    println "println --------------------------------------- Created otds ticket tenant  ---------------------------------------";

}



 iHUB_URL = iHUB_URL.replace("http://","");
 iHUB_URL = iHUB_URL.replace(":8700/","");

	def endPoint = platformBaseURL+"/home/"+platformOrg+"/com.eibus.web.soap.Gateway.wcp?organization=o="+platformOrg+","+ldaproot+"";
 GroovyShell shell = new GroovyShell()
 def PlatformConfig = shell.parse(new File('PlatformConfig.groovy'))
 PlatformConfig.ihubConnManagerOTDS( endPoint, platformUserName, platformPassword, ihubtenantNameRandom, iHUB_URL, oauthClientName, "123456789");
 if(isClassicIHUB.equals("true")) {
    PlatformConfig.ihubServiceContainerCreation(endPoint, platformUserName, platformPassword, "ihubG","ihubS",platformOrg)
}

