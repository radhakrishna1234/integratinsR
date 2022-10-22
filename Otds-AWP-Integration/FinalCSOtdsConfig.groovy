        GroovyShell configReader = new GroovyShell()
        def config = configReader.parse(new File('ConfigFileReader.groovy'))
        config.getProperty('');

/********************************************* parametes need to change *********************************/
//Platform details platform tenant name,base url, username,password
//def platformOrg = config.getProperty('platformOrg');
//def platformBaseURL = config.getProperty('platformBaseURL');
//def platformUserName = config.getProperty('platformUserName');
//def platformPassword = config.getProperty('platformPassword');


//Otds details base url, username,password
def server = config.getProperty('server');
def adminUser = config.getProperty('adminUser');
def adminPass = config.getProperty('adminPass');
def partitionName = config.getProperty('partitionName');
def ldaproot = config.getProperty('ldaproot');

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

def csBaseURL = config.getProperty('CSURL');
def csAdmin = config.getProperty('csAdmin');
def cspass = config.getProperty('cspass');
def csWebPass = config.getProperty('csWebPass');

/************************************************* no need  to change any parametes in below code *******************************************************/
Random rnd = new Random()
def randomNumber=rnd.next(20)
//def endPoint = platformBaseURL+"/home/"+platformOrg+"/com.eibus.web.soap.Gateway.wcp?organization=o="+platformOrg+","+ldaproot+"";
def authenticatorName = "otds"
//def platformOtdsPushURL = platformBaseURL+"/home/"+platformOrg+"/app/otdspush";
def adminAuthendPoint = server +"/otdsws/rest/authentication/credentials";
def method ="";

def otdsBaseURL = "";
def createTenantEndpointURL = server +"/otdsws/tenants";
def resourceName = "cserver3";
//def partitionName = "CCPartition3";
def orgURI = "/otdstenant/"+ tenantName;
def orgBaseURI = "";

	if("".equals(tenantName.trim()))
     	   orgBaseURI = server ;
         else
           orgBaseURI = server + orgURI;           
       


def orgCreatePartitionURL = orgBaseURI + "/otdsws/rest/partition"
def orgCreateUserURL = orgBaseURI + "/otdsws/rest/users"
def orgCreateResourceURL = orgBaseURI + "/otdsws/rest/resources"
def orgCreateAccessRolesURL = orgBaseURI + "/otdsws/rest/accessroles/Access%20to%20"+resourceName
def orgAdminAuthendPoint = orgBaseURI + "/otdsws/rest/authentication/credentials";
def getOrgPartitionURL = orgBaseURI +"/otdsws/rest/partitions/"+partitionName
def getOrgUserURL = orgBaseURI +"/otdsws/rest/users/"+tenantUserName
def getOrgResourceURL = orgBaseURI +"/otdsws/rest/resources/"+resourceName
def orgConsoloditaeURL = orgBaseURI + "/otdsws/rest/consolidation";
def orgTrustSiteURL = orgBaseURI + "/otdsws/rest/systemconfig/whitelist"
def assignRoleToUser = orgBaseURI + "/otdsws/rest/users/"+tenantName +"@"+partitionName+"/memberof";
def partition1="otds.admin";
//def orgTrustSiteURL = "http://a24d55b58f1c044c4b8bfb278c5be41c-318151494.eu-west-2.elb.amazonaws.com";
     
	 
  	if("".equals(tenantName.trim()))
     	   otdsBaseURL=server;
         else
           otdsBaseURL = server +"/otdstenant/"+tenantName;           
  def getTenantEndpointURL = server +"/otdsws/tenants/"+tenantName;      
 
        
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
      //otdsConfig.cratePartition(orgCreatePartitionURL, "POST",  orgAdminAuthendPoint, orgAdminAuthPayload,partitionName)
      def resp = otdsConfig.createCSResource(orgCreateResourceURL ,"POST", orgAdminAuthendPoint,orgAdminAuthPayload,tenantUserName,tenantPassword,csBaseURL,resourceName,"3b81f0b5-147c-46e7-ae63-4f6aaf471339")  
	   
	  println "Resource ID -------- >>>>>>> "+ otdsConfig.getResID(resp.toString());
	   
     
 	def orgPartitionLOcation = otdsConfig.getPartitionLocation(getOrgPartitionURL, "GET",  orgAdminAuthendPoint, orgAdminAuthPayload)
	def orgUserLOcation = otdsConfig.getUserLocation(getOrgUserURL, "GET",  orgAdminAuthendPoint, orgAdminAuthPayload)
        def orgResourceResp = otdsConfig.getResourceResp(getOrgResourceURL, "GET",  orgAdminAuthendPoint, orgAdminAuthPayload);
        def resID = otdsConfig.getResID(orgResourceResp);
        println "============ "+ resID;
		
		
	  
	  File fileSystemId = new File('./key.txt')
      fileSystemId.write(resID)
	  
	   
       /* new File(path).withWriter('utf-8') { 
         writer -> writer.writeLine resID 
        }
		*/
        new File('./','resource.txt').withWriter('utf-8') { 
         writer -> writer.writeLine orgResourceResp 
        }    
        otdsConfig.updateAccessResource(orgCreateAccessRolesURL, "POST",  orgAdminAuthendPoint, orgAdminAuthPayload,resourceName, "otadmin@otds.admin", partition1, orgUserLOcation, partitionName, orgPartitionLOcation);
        otdsConfig.addTrust(orgTrustSiteURL, csBaseURL, "POST", orgAdminAuthendPoint,orgAdminAuthPayload);

    

createUser(orgCreateUserURL,method, orgAdminAuthendPoint,tenantUserName,"appworksUser111","Opentext1!",tenantPassword,partitionName,getOrgPartitionURL);

def createUser(String orgCreateUserURL,String method, String orgAdminAuthendPoint, String tenantUserName ,String user,String userPass, String tenantPassword,String partitionName,String getOrgPartitionURL) {
       GroovyShell shell2 = new GroovyShell()
       def otdsConfig2 = shell2.parse(new File('OtdsConfig.groovy'))
       otdsConfig2.createUserInOTDS(orgCreateUserURL,method, orgAdminAuthendPoint,"otadmin@otds.admin",user, userPass,tenantPassword,partitionName,getOrgPartitionURL);
     } 

   