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
//def newLicenseServer = config.getProperty('NewLicenseServer');
//newLicenseServer = newLicenseServer.trim();

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
def ldaproot = config.getProperty('ldaproot');

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
def partitionName = "CCPartition3";
def orgURI = "/otdstenant/"+ tenantName;
def orgBaseURI = "";

	 def arr = new String[2]
arr [0]=config.getProperty("platformBaseURL");
arr[1] =config.getProperty("CSURL"); 

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
        
 
        
      GroovyShell shell1 = new GroovyShell()
      def otdsConfig = shell1.parse(new File('OtdsConfig.groovy'))
      def orgAdminAuthPayload = otdsConfig.orgAdminPlayLoad(tenantUserName,tenantPassword)
      
     File file1 = new File("resource.txt");
     def String resResponse = file1.getText();
     otdsConfig.consolidateRes(resResponse, orgConsoloditaeURL, "POST", orgAdminAuthendPoint,orgAdminAuthPayload)
	  otdsConfig.addTrust(orgTrustSiteURL, arr, "POST", orgAdminAuthendPoint,orgAdminAuthPayload);
     String resID = otdsConfig.getResID(resResponse);	 
    //otdsConfig.updateLicense(tenantName,resourceName,resID,orgAdminAuthendPoint,orgAdminAuthPayload,orgBaseURI,"true");

     //otdsConfig.updateLicense("test970","cserver3","8ca7eed7-67b8-4f39-81c0-5a44b357fb5f",orgAdminAuthendPoint,orgAdminAuthPayload,orgBaseURI);

   