        GroovyShell configReader = new GroovyShell()
        def config = configReader.parse(new File('ConfigFileReader.groovy'))
        config.getProperty('');
      println "---------------------------------------"
       // GroovyShell configReader1 = new GroovyShell()
       // def config1 = configReader1.parse(new File('setProperties.groovy'))
       // config1.setProperties();
 println "===================================="

/********************************************* parametes need to change *********************************/
//Platform details platform tenant name,base url, username,password
def platformOrg = config.getProperty('platformOrg');
def platformBaseURL = config.getProperty('platformBaseURL');
def platformUserName = config.getProperty('platformUserName');
def platformPassword = config.getProperty('platformPassword');
def ldaproot = config.getProperty('ldaproot');

//Otds details base url, username,password
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

/************************************************* no need  to change any parametes in below code *******************************************************/
Random rnd = new Random()
def randomNumber=rnd.next(20)
def endPoint = platformBaseURL+"/home/"+platformOrg+"/com.eibus.web.soap.Gateway.wcp?organization=o="+platformOrg+","+ldaproot+"";
def authenticatorName = "otds"
def platformOtdsPushURL = platformBaseURL+"/home/"+platformOrg+"/app/otdspush";
def adminAuthendPoint = server +"/otdsws/rest/authentication/credentials";
def method ="";

def otdsBaseURL = "";
def createTenantEndpointURL = server +"/otdsws/tenants";
def getTenantEndpointURL = server +"/otdsws/tenants/"+tenantName;
def resourceName = "AppworksResource";
def partitionName = "AWPPartition";
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
def resourceImpersonate = orgBaseURI+"/otdsws/rest/resources/"+resourceName+"/impersonation";
def partition1="otds.admin";
     
	 
  	if("".equals(tenantName.trim()))
     	   otdsBaseURL=server;
         else
           otdsBaseURL = server +"/otdstenant/"+tenantName;           
        
       
	 GroovyShell shell = new GroovyShell()
         def PlatformConfig = shell.parse(new File('PlatformConfig.groovy'))
         def platformAuthPayload = PlatformConfig.addOtdsPushRoletoOrgUser(endPoint, platformUserName, platformPassword,platformOrg);
 
        
       GroovyShell shell1 = new GroovyShell()
       def otdsConfig = shell1.parse(new File('OtdsConfig.groovy'))
       def orgAdminAuthPayload = otdsConfig.orgAdminPlayLoad(adminUser,adminPass)
       def isOrgCreated = otdsConfig.checkOrgAvailability(getTenantEndpointURL,"GET",adminAuthendPoint,orgAdminAuthPayload);
       println "==>>"+isOrgCreated 
       if(!isOrgCreated)
       otdsConfig.createORG(createTenantEndpointURL,method,adminAuthendPoint,tenantUserName,tenantPassword,tenantName,adminUser,adminPass);

       def resourceID  = otdsConfig.CreateOtdsOrg(orgCreatePartitionURL, method, orgAdminAuthendPoint, partitionName, orgCreateResourceURL,resourceName, platformOtdsPushURL, platformOrg+"user2", "Opentext1!",getOrgPartitionURL, getOrgUserURL,getOrgResourceURL, orgCreateAccessRolesURL, tenantUserName, partition1, orgCreateUserURL, tenantName, tenantPassword,orgConsoloditaeURL,orgTrustSiteURL,platformBaseURL,assignRoleToUser);
       sleep(2000);
       
       def platformAuthPayload1 = PlatformConfig.platformSideConfig(endPoint, platformUserName, platformPassword, otdsBaseURL, resourceID, authenticatorName, platformBaseURL,partitionName );;
       sleep(20000); 

        
      createUser(orgCreateUserURL,method, orgAdminAuthendPoint,tenantUserName,"appworksUser","Opentext1!",tenantPassword,partitionName,getOrgPartitionURL);
      assignRole(otdsBaseURL,partitionName ,tenantName,tenantUserName ,tenantPassword, orgAdminAuthendPoint,"appworksUser","Cordys@Work#Developer");

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
     orgAdminAuthPayload = otdsConfig.orgAdminPlayLoad(tenantUserName,tenantPassword);
     otdsConfig.allowImpersonate(resourceImpersonate ,"PUT",  orgAdminAuthendPoint, orgAdminAuthPayload); 
   