        GroovyShell configReader = new GroovyShell()
        def config = configReader.parse(new File('ConfigFileReader.groovy'))
        config.getProperty('');
      println "---------------------------------------"
       // GroovyShell configReader1 = new GroovyShell()
       // def config1 = configReader1.parse(new File('setProperties.groovy'))
       // config1.setProperties();
 println "===================================="
def ldaproot = config.getProperty('ldaproot');
/********************************************* parametes need to change *********************************/
//Platform details platform tenant name,base url, username,password
def platformOrg = config.getProperty('platformOrg');
def platformBaseURL = config.getProperty('platformBaseURL');
def platformUserName = config.getProperty('platformUserName');
def platformPassword = config.getProperty('platformPassword');


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
def endPoint = platformBaseURL+"/home/"+platformOrg+"/com.eibus.web.soap.Gateway.wcp?organization=o="+platformOrg+",cn=cordys,cn=defaultInst,o=opentext."+ldaproot+"";
def authenticatorName = "otds"
def platformOtdsPushURL = platformBaseURL+"/home/"+platformOrg+"/app/otdspush";
def adminAuthendPoint = server +"/otdsws/rest/authentication/credentials";
def method ="";

def otdsBaseURL = "";
def createTenantEndpointURL = server +"/otdsws/tenants";
def getTenantEndpointURL = server +"/otdsws/tenants/"+tenantName;
def resourceName = platformOrg;
def csresourceName = "cserver3";
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
def csresourceImpersonate = orgBaseURI+"/otdsws/rest/resources/"+csresourceName+"/impersonation";
def partition1="otds.admin";
     
	 
  	if("".equals(tenantName.trim()))
     	   otdsBaseURL=server;
         else
           otdsBaseURL = server +"/otdstenant/"+tenantName;           
        

        
       GroovyShell shell1 = new GroovyShell()
       def otdsConfig = shell1.parse(new File('OtdsConfig.groovy'))
       def orgAdminAuthPayload = otdsConfig.orgAdminPlayLoad(adminUser,adminPass)
       def isOrgCreated = otdsConfig.checkOrgAvailability(getTenantEndpointURL,"GET",adminAuthendPoint,orgAdminAuthPayload);
       println "==>>"+isOrgCreated 
       if(!isOrgCreated)
       otdsConfig.createORG(createTenantEndpointURL,method,adminAuthendPoint,tenantUserName,tenantPassword,tenantName,adminUser,adminPass);

     
     orgAdminAuthPayload = otdsConfig.orgAdminPlayLoad(tenantUserName,tenantPassword);
     otdsConfig.allowImpersonate(resourceImpersonate ,"PUT",  orgAdminAuthendPoint, orgAdminAuthPayload); 
	 otdsConfig.allowImpersonate(csresourceImpersonate ,"PUT",  orgAdminAuthendPoint, orgAdminAuthPayload); 
   