        GroovyShell configReader = new   GroovyShell()
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
def BUILD_NUMBER = config.getProperty('BUILD_NUMBER');
def resourceID=config.getProperty('CSToken');
def csRootFolder=config.getProperty('csrootFolder');
def ldaproot = config.getProperty('ldaproot');
//Otds details base url, username,password
def server = config.getProperty('server');
def adminUser = config.getProperty('adminUser');
def adminPass = config.getProperty('adminPass');

def space = "organization";
def resourceName="cs";


def BravaserverURL = config.getProperty('BravaURL');
def Bravacapabilityset =config.getProperty('Bravacapabilityset');
def prepublishdocumentstBravaonupload = config.getProperty('prepublishdocumentstBravaonupload');

if("".equals(BravaserverURL.trim())) {
BravaserverURL="http://google.com"
}
if("".equals(Bravacapabilityset.trim())) {
Bravacapabilityset="PS_BV"
}

if("".equals(prepublishdocumentstBravaonupload.trim())) {
prepublishdocumentstBravaonupload="false"
}



def OrganizationalAdministrator= config.getProperty('OrganizationalAdministrator');
def CSURL = config.getProperty('CSURL');

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
def endPointforOrgCreation = platformBaseURL+"/home/system/com.eibus.web.soap.Gateway.wcp?organization=o=system,"+ldaproot+"";
def authenticatorName = "otds"
def platformOtdsPushURL = platformBaseURL+"/home/"+platformOrg+"/app/otdspush";
def adminAuthendPoint = server +"/otdsws/rest/authentication/credentials";
def method ="";

def otdsBaseURL = "";
def createTenantEndpointURL = server +"/otdsws/tenants";
def getTenantEndpointURL = "";
//def resourceName = platformOrg;
def partitionName = config.getProperty('partitionName');
String partitionName1 = partitionName.replace(" ", "%20");
//def partitionName = "AWPPartition";
def orgURI = "/otdstenant/"+ tenantName;
def orgBaseURI = "";

	if("".equals(tenantName.trim()))
     	   orgBaseURI = server ;
         else
           orgBaseURI = server + orgURI; 


	if("".equals(tenantName.trim()))
     	   getTenantEndpointURL = server
         else
           getTenantEndpointURL = server +"/otdsws/tenants/"+tenantName;  		   
       


def orgCreatePartitionURL = orgBaseURI + "/otdsws/rest/partition"
def orgCreateUserURL = orgBaseURI + "/otdsws/rest/users"
def orgCreateResourceURL = orgBaseURI + "/otdsws/rest/resources"
def orgCreateAccessRolesURL = orgBaseURI + "/otdsws/rest/accessroles/Access%20to%20"+resourceName
def orgAdminAuthendPoint = orgBaseURI + "/otdsws/rest/authentication/credentials";
def getOrgPartitionURL = orgBaseURI +"/otdsws/rest/partitions/"+partitionName1
def getOrgUserURL = orgBaseURI +"/otdsws/rest/users/"+tenantUserName
def getOrgResourceURL = orgBaseURI +"/otdsws/rest/resources/"+resourceName
def orgConsoloditaeURL = orgBaseURI + "/otdsws/rest/consolidation";
def orgTrustSiteURL = orgBaseURI + "/otdsws/rest/systemconfig/whitelist"
def assignRoleToUser = orgBaseURI + "/otdsws/rest/users/"+tenantName +"@"+partitionName1+"/memberof";
def resourceImpersonate = orgBaseURI+"/otdsws/rest/resources/"+resourceName+"/impersonation";
def partition1="otds.admin";
     
	 def arr = new String[2]
arr [0]=config.getProperty("platformBaseURL");
//arr[1] =config.getProperty("CSURL"); 
	 
  	if("".equals(tenantName.trim()))
     	   otdsBaseURL=server;
         else
           otdsBaseURL = server +"/otdstenant/"+tenantName;           
        
       
	     GroovyShell shell = new GroovyShell()
         def PlatformConfig = shell.parse(new File('PlatformConfig.groovy'))
		 
		 	   PlatformConfig.csResourceInSecAdmin(endPoint, platformUserName, platformPassword, resourceName, space, otdsBaseURL, resourceID);
			   
			    PlatformConfig.csServiceContainerCreation(endPoint, platformUserName, platformPassword, BravaserverURL, Bravacapabilityset, prepublishdocumentstBravaonupload, OrganizationalAdministrator, resourceName, space, CSURL+"/cws/services/DocumentManagement", platformOrg,csRootFolder);
			 
		 
         //def platformAuthPayload = PlatformConfig.addOtdsPushRoletoOrgUser(endPoint, platformUserName, platformPassword,platformOrg);
		 
		