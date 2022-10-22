
def platformEndPoint = "";

def getSamlTocken(String endPoint, String platformUserNmae, String platformPassword )  {

	GroovyShell shell = new GroovyShell()
	def platformAuth = shell.parse(new File('./platformxmls/PlatformAuthXML.groovy'))
	def platformAuthPayload = platformAuth.platformAdminLogin(platformUserNmae, platformPassword);
	
	def resp = fireRequest(endPoint,"POST",platformAuthPayload);
	
	//println " resp  "+resp
	def samlArt = getSamlTocken(resp);
	platformEndPoint= endPoint+"&SAMLart="+samlArt;
}

//update org trust
def upateTrust() {
        println "INFO : AWP -> Adding platform URL to Trust store ..............";
	GroovyShell shell = new GroovyShell()
	def platformUpdateTrust = shell.parse(new File('./platformxmls/PlatformUpdateOrgTrustXML.groovy'))
   	def platformUpdateTrustPayload = platformUpdateTrust.updateOrgTrust();
   	fireRequest(platformEndPoint,"POST",platformUpdateTrustPayload);
        println "INFO : AWP -> Added platform URL to Trust store \n";
}

// Update Resoure
def updateResource(String otdsBaseURL, String resourceID) {
        println "INFO : AWP -> Updating Resource ID and otds Base URL in Platform ..............";
	GroovyShell shell = new GroovyShell()
	def platformUpdateRes = shell.parse(new File('./platformxmls/PlatformUpdateOrgResXML.groovy'))
	def platformUpdateResPayload = platformUpdateRes.updateOrgRes(otdsBaseURL, resourceID);        
	fireRequest(platformEndPoint,"POST",platformUpdateResPayload );
        println "INFO : AWP -> Updated Resource ID and otds Base URL in Platform \n";

}

// Update Authenticator
def authenticator(String authenticatorName, String otdsBaseURL) {
        println "INFO : AWP -> Updating Authenticators in Platform ..............";
	GroovyShell shell = new GroovyShell()
	def platformUpdateAuthenticator = shell.parse(new File('./platformxmls/PlatformUpdateOrgAuthenticatorXML.groovy'))
	def platformUpdateAuthenticatorPayload = platformUpdateAuthenticator.updateOrgAuth(authenticatorName, otdsBaseURL);        
	fireRequest(platformEndPoint,"POST",platformUpdateAuthenticatorPayload);
        println "INFO : AWP -> Updated Authenticators in Platform \n";
 
}

// Update platform URL
def updatePlatformURL(String platforBaseURL) {
        println "INFO : AWP -> Updating Authenticators in Platform ..............";
	GroovyShell shell = new GroovyShell()
	def platformorgVariable = shell.parse(new File('./platformxmls/PlatformOrgVaraile.groovy'))
	def platformorgVariablePayload = platformorgVariable.orgVariable(platforBaseURL);        
	fireRequest(platformEndPoint,"POST", platformorgVariablePayload);
        println "INFO : AWP -> Updated Authenticators in Platform \n";

}

// Actiate Res
def activateRes() {
        println "INFO : AWP -> Activating resource ..............";
	GroovyShell shell = new GroovyShell()
	def platformActivateRes = shell.parse(new File('./platformxmls/PlatformActivateRes.groovy'))
	def platformActivateteResPayload = platformActivateRes.activateteRes();        
	fireRequest(platformEndPoint,"POST", platformActivateteResPayload);
        println "INFO : AWP -> Activated resource .............. \n ";

}

// add otds Push Roles
def AddotdsPushRoletoUser(String platformOrg) {   
        println "INFO : AWP -> Adding otds push role to User sysadmin .............."
	GroovyShell shell = new GroovyShell()
	def platformAddOtdsPushRole = shell.parse(new File('./platformxmls/PlatformAddOtdsPushRole.groovy'))
	def platformAddOtdsPushRolePayload = platformAddOtdsPushRole.addOtdsPushRoleToUser(platformOrg);       
	fireRequest(platformEndPoint,"POST", platformAddOtdsPushRolePayload);  
        println "INFO : AWP -> Added otds push role to User sysadmin \n"      
}

def addPartitionInPlatform(String partitionName) {
        println "INFO : AWP -> Adding partition to push roles ..............";
	GroovyShell shell = new GroovyShell()
	def platformAddPartition = shell.parse(new File('./platformxmls/PlatformAddPartition.groovy'))
	def platformAddPartitionPayload = platformAddPartition.platformAddPartition(partitionName);       
	fireRequest(platformEndPoint,"POST", platformAddPartitionPayload);
        println "INFO : AWP -> Added partition to push roles \n";
		try {
    Thread.sleep(30 * 1000);
	 }catch(Exception e) {
	 }

}

def pushRolestoOtds() {
        println "INFO : AWP -> Pushing roles to OTDS ..............";
	GroovyShell shell = new GroovyShell()
	def platformPushRoles = shell.parse(new File('./platformxmls/PlatformPushRoles.groovy'))
	def platformPushRolesPayload = platformPushRoles.pushRolesToOtds();	
	println "platformPushRolesPayload "+platformPushRolesPayload
	println "platformEndPoint "+platformEndPoint 
	fireRequest(platformEndPoint,"POST", platformPushRolesPayload);
        println "INFO : AWP -> Pushed roles to OTDS .............. \n";
}


def pushRolestoOtds(String partitioName) {
        println "INFO : AWP -> Pushing roles to OTDS ..............";	
	GroovyShell shell = new GroovyShell()
	def platformUpdatePartition = shell.parse(new File('./platformxmls/PlatformUpdatePartition.groovy'))
	def platformUpdatePartitionPayload = platformUpdatePartition.updatePartition(partitioName);        
	fireRequest(platformEndPoint,"POST", platformUpdatePartitionPayload );
        println "INFO : AWP -> Pushed roles to OTDS .............. \n";	

}


//Base utilities 
def fireRequest(String endPoint, String method, String request) {

try{
	def post = new URL(endPoint).openConnection();	
	post.setRequestMethod(method)
	post.setDoOutput(true)
	post.setRequestProperty("Content-Type", "application/json")
	post.getOutputStream().write(request.getBytes("UTF-8"));
	def postRC = post.getResponseCode();
   // println " postRC "+postRC	
	return post.getInputStream().getText()
}catch(Exception e) {
	System.err.println(" ****************************************** ERRROR ********************************************************");
	System.err.println("ERROR : AWP -> end point "+endPoint);
	System.err.println("ERROR : AWP -> Method  "+method);
	System.err.println("ERROR : AWP -> Request  "+request);	
	println " Failure details in  Platform config -- "+ e.printStackTrace(System.err)
	throw new Exception(" Failed in appworks ");

}    
}


def fireRequest1(String endPoint, String method, String request) {

try{
	def post = new URL(endPoint).openConnection();	
	post.setRequestMethod(method)
	post.setDoOutput(true)
	post.setRequestProperty("Content-Type", "application/json")
	post.getOutputStream().write(request.getBytes("UTF-8"));
	def postRC = post.getResponseCode();
	
	try {
    Thread.sleep(60 * 1000);
	 }catch(Exception e) {
	 }
    
	//return post.getInputStream().getText()   

}catch(Exception e) {
	System.err.println(" ****************************************** ERRROR ********************************************************");
	System.err.println("ERROR : AWP -> end point "+endPoint);
	System.err.println("ERROR : AWP -> Method  "+Method);
	System.err.println("ERROR : AWP -> Request  "+request);	
	println " Failure details in  Platform config -- "+ e.printStackTrace(System.err)
	throw new Exception(" Failed in appworks ");

}	
}


def getSamlTocken(String resp) {
	def response = new XmlSlurper().parseText(resp)
	def titles = response.'**'.findAll { node -> node.name() == 'AssertionArtifact' }*.text()
	 return titles.get(0)
}

def platformSideConfig(String endPoint, String platformUserNmae,String platformPassword, String otdsBaseURL,String resourceID, String authenticatorName, String platforBaseURL, String partitionName) {
  println "platform side config"
  getSamlTocken(endPoint, platformUserNmae, platformPassword); 
  upateTrust();
  updateResource(otdsBaseURL, resourceID);
  authenticator(authenticatorName, otdsBaseURL);
  updatePlatformURL(platforBaseURL)
  activateRes();
  try {
  addPartitionInPlatform(partitionName); 
  }catch(Exception e) {
  println "New request of update partition failed, so updating partition name."
  pushRolestoOtds(partitionName)
  println "Updated partition successfully"
  
  }
   pushRolestoOtds();
}


def platformSideConfig1(String endPoint, String platformUserNmae,String platformPassword,  String partitionName) {
  println "platform side config"
  getSamlTocken(endPoint, platformUserNmae, platformPassword); 
  
  try {
  addPartitionInPlatform(partitionName); 
  }catch(Exception e) {
  println "New request of update partition failed, so updating partition name."
  pushRolestoOtds(partitionName)
  println "Updated partition successfully"
  
  }
   pushRolestoOtds();
}


def addOtdsPushRoletoUser(String endPoint, String platformUserNmae,String platformPassword, String platformOrg) {
  
  getSamlTocken(endPoint, platformUserNmae, platformPassword);
  AddotdsPushRoletoUser(platformOrg);
  
}

def addOtdsPushRoletoOrgUser(String endPoint, String platformUserNmae,String platformPassword, String platformOrg) {
  getSamlTocken(endPoint, platformUserNmae, platformPassword);
  AddotdsPushRoletoOrgUser(platformOrg);
}


def AddotdsPushRoletoOrgUser(String orgName) {       
        String orgUserNmae = orgName+"user2";
        String orgUserNmaePass = "Opentext1!" 
         println "INFO : AWP -> Created push user with name "+orgUserNmae +" password "+orgUserNmaePass +" and added otdspush role"       
        println "INFO : AWP -> Adding orgUser .............."
	GroovyShell shell = new GroovyShell()
	def orgUser = shell.parse(new File('./platformxmls/orgUser.groovy'))
	def platformAddOtdsPushRolePayload = orgUser.orgUser(orgUserNmae,orgName);       
	fireRequest(platformEndPoint,"POST", platformAddOtdsPushRolePayload);  
        println "INFO : AWP -> Added orgUser" 

        println "INFO : AWP -> Adding authenticated user and adding push roles to him .............."
	GroovyShell shell1 = new GroovyShell()
	def AuthenticatedUser = shell1.parse(new File('./platformxmls/AuthenticatedUser.groovy'))
	platformAddOtdsPushRolePayload = AuthenticatedUser.authenticatedUser(orgUserNmae,orgUserNmaePass,orgName);       
	fireRequest(platformEndPoint,"POST", platformAddOtdsPushRolePayload);  
        println "INFO : AWP -> Added authenticated user and added push role to him" 

        println "INFO : AWP -> Set password for user.............."
	GroovyShell shell2 = new GroovyShell()
	def setpass= shell2.parse(new File('./platformxmls/SetPassWord.groovy'))
	platformAddOtdsPushRolePayload = setpass.setpass(orgUserNmae,orgUserNmaePass);       
	fireRequest(platformEndPoint,"POST", platformAddOtdsPushRolePayload);  
        println "INFO : AWP -> Added password to user \n"
}

platformSideConfig(endPoint, platformUserNmae, platformPassword, otdsBaseURL, resourceID, authenticatorName, platforBaseURL);


def ihubConnManager(String endPoint, String platformUserName,String platformPassword, String platformOrg,String ihubhost,String ihubOauthClientName,String ihubOauthClientID){
	println "INFO : APPWORKS -> iHUB  Connection  Manager ..............";
	GroovyShell shell2 = new GroovyShell()
	def ihubConn = shell2.parse(new File('./platformxmls/IHUB_REQUEST.groovy'))
	def ihubConnPayload = ihubConn.ihubConnManager(ihubhost,ihubOauthClientName,ihubOauthClientID,platformOrg)
	//println " *APPWORKS -> iHUB  Connection req  \n"+ihubConnPayload
	getSamlTocken(endPoint,platformUserName,platformPassword)
	fireRequest(platformEndPoint,"POST", ihubConnPayload);
	println "APPWORKS -> iHUB  Connection  Manager Successfull.............. \n";

}

def ihubConnManagerOTDS(String endPoint, String platformUserName,String platformPassword, String platformOrg,String ihubhost,String ihubOauthClientName,String ihubOauthClientID){
	println "INFO : APPWORKS -> iHUB  Connection  Manager ..............";
	GroovyShell shell2 = new GroovyShell()
	def ihubConn = shell2.parse(new File('./platformxmls/IHUB_REQUESTOTDS.groovy'))
	def ihubConnPayload = ihubConn.ihubConnManager(ihubhost,ihubOauthClientName,ihubOauthClientID,platformOrg)
	//println " *APPWORKS -> iHUB  Connection req  \n"+ihubConnPayload
	getSamlTocken(endPoint,platformUserName,platformPassword)
	fireRequest(platformEndPoint,"POST", ihubConnPayload);
	println "APPWORKS -> iHUB  Connection  Manager Successfull.............. \n";

}

def clearIhubPage(String endPoint, String platformUserName,String platformPassword){
	println "INFO : APPWORKS -> iHUB  Connection  Manager ..............";
	GroovyShell shell2 = new GroovyShell()
	def ihubConn = shell2.parse(new File('./platformxmls/IHUB_REQUEST.groovy'))
	def ihubConnPayload = ihubConn.clearIhubPage ()
	//println " ********************* Req **************************  \n"+ihubConnPayload
	getSamlTocken(endPoint,platformUserName,platformPassword)
	fireRequest(platformEndPoint,"POST", ihubConnPayload);
	println "APPWORKS -> iHUB  Connection  Manager Successfull.............. \n";

}


def createOrg(String orgendPoint,String endPoint, String platformUserName,String platformPassword, String orgName){
	println "INFO : APPWORKS ->  APPWORKS New Organization creation started .............. ";
	System.err.println(" organization creation started");
	//println " end point "+endPoint
	GroovyShell shell2 = new GroovyShell()
	def orgCreation = shell2.parse(new File('./platformxmls/PlatformNewOrgCreation.groovy'))
	def orgCreationPayload = orgCreation.pushNewOrganization(orgName, platformUserName)
	//println " ********************* Req **************************  \n"+orgCreationPayload	
	getSamlTocken(endPoint,platformUserName,platformPassword)
	//println "saml art generated"
	fireRequest1(platformEndPoint,"POST", orgCreationPayload);
	println "INFO : APPWORKS -> Waiting for organization creation on platform ........... "
	verifyOrg(orgendPoint,platformUserName,platformPassword,orgName)
	
	println "APPWORKS -> APPWORKS New Organization creation Ended.............. \n";

}

def verifyOrg(String endPoint, String platformUserName,String platformPassword, String orgName){
	println "INFO : APPWORKS ->  Verifying org cretion start.............. ";
	for(int i=1 ; i<600;i++) {
	try{
	getSamlTocken(endPoint,platformUserName,platformPassword)
	 sleep(30*1000)
	 break;
	}catch(Exception e) {
	sleep(30*1000)
	println "INFO : APPWORKS -> AWP organization creaton inprogress ........... "
	}
	}
	
	
	
	println "APPWORKS -> Verifying org cretion  Ended.............. \n";

}


def csResourceInSecAdmin(String endPoint, String platformUserName,String platformPassword,String Resourcename, String Space, String otdsBaseURL, String resourceID){
	println "INFO : APPWORKS ->  APPWORKS Creating resource in security admin .............. ";
	println " end point "+endPoint
	GroovyShell shell2 = new GroovyShell()
	def orgCreation = shell2.parse(new File('./platformxmls/CsResourceInSecAdmin.groovy'))
	def orgCreationPayload = orgCreation.csResourceInSecAdmin(Resourcename, Space, otdsBaseURL, resourceID)
	println " ********************* Req **************************  \n"+orgCreationPayload
	getSamlTocken(endPoint,platformUserName,platformPassword)
	fireRequest1(platformEndPoint,"POST", orgCreationPayload);
	
	println "APPWORKS -> APPWORKS APPWORKS Created resource in security admin .............. \n";

}



def csServiceContainerCreation(String endPoint, String platformUserName,String platformPassword,String BravaserverURL, String Bravacapabilityset, String PrepublishdocumentstBravaonupload, String OrganizationalAdministrator, String Resourcename, String Space, String EndpointURL, String serviceName, String csrootFolder){
	println "INFO : APPWORKS ->  APPWORKS Creating cs service container .............. ";
	//println " end point "+endPoint
	GroovyShell shell2 = new GroovyShell()
	def orgCreation = shell2.parse(new File('./platformxmls/CsServiceContainerCreation.groovy'))
	def orgCreationPayload = orgCreation.csServiceContainerCreation(BravaserverURL, Bravacapabilityset, PrepublishdocumentstBravaonupload, OrganizationalAdministrator, Resourcename, Space, EndpointURL, serviceName,csrootFolder)
	//println " ********************* Req **************************  \n"+orgCreationPayload
	getSamlTocken(endPoint,platformUserName,platformPassword)
	fireRequest1(platformEndPoint,"POST", orgCreationPayload);
	
	println "APPWORKS -> APPWORKS Created cs service container .............. \n";

}

def ihubServiceContainerCreation(String endPoint, String platformUserName,String platformPassword, String Sgroup, String Scontainer, String PlatformOrg){
	println "INFO : APPWORKS ->  APPWORKS Creating ihub service container .............. ";
	//println " end point "+endPoint
	GroovyShell shell2 = new GroovyShell()
	def orgCreation = shell2.parse(new File('./platformxmls/ihubServiceContainerCreation.groovy'))
	def orgCreationPayload = orgCreation.ihubServiceContainerCreation(Sgroup,Scontainer,PlatformOrg,)
	//println " ********************* Req **************************  \n"+orgCreationPayload
	getSamlTocken(endPoint,platformUserName,platformPassword)
	fireRequest1(platformEndPoint,"POST", orgCreationPayload);
	
	println "APPWORKS -> APPWORKS Created ihub service container .............. \n";

}


def getAllUsers(String endPoint, String platformUserName, String platformPassword) {
        println "INFO : AWP -> get All users  ..............";
	GroovyShell shell = new GroovyShell()
	def platformUpdateRes = shell.parse(new File('./platformxmls/PlatformGetAllUsers.groovy'))
	def platformUpdateResPayload = platformUpdateRes.getAllUsers(); 
    getSamlTocken(endPoint,platformUserName,platformPassword)	
	def resp = fireRequest(platformEndPoint,"POST",platformUpdateResPayload );
        println "INFO : AWP -> Listed all the users \n";
	return resp;
}

def getAllRoles(String endPoint, String platformUserName, String platformPassword) {

        println "INFO : AWP -> get All roles ..............";
	GroovyShell shell = new GroovyShell()
	def platformUpdateRes = shell.parse(new File('./platformxmls/PlatformGetAllRoles.groovy'))
	def platformUpdateResPayload = platformUpdateRes.getAllRoles();   
    getSamlTocken(endPoint,platformUserName,platformPassword)
	
	def resp = fireRequest(platformEndPoint,"POST",platformUpdateResPayload );
        println "INFO : AWP -> Listed all the roles \n";
	return resp;
}

def getUsers(String resp) {
	def response = new XmlSlurper().parseText(resp)
	def titles = response.'**'.findAll { node -> node.name() == 'UserId' }*.text()
	 return titles;
}

def getRoles(String resp) {
	def response = new XmlSlurper().parseText(resp)
	def titles = response.'**'.findAll { node -> node.name() == 'Name' }*.text()
	 return titles;
}


def createOauthAuthenticatorForIV(String endPoint, String platformUserName,String platformPassword, String name){
	println "INFO : APPWORKS ->  APPWORKS New Organization creation started .............. ";

	
	 println "INFO : AWP -> Adding OAuth client to platform ..............";
	GroovyShell shell = new GroovyShell()
	def ivOauth = shell.parse(new File('./platformxmls/IV.groovy'))
	def ivOauthPaylod = ivOauth.addOuthAuthenticatorforIV(name);
    getSamlTocken(endPoint,platformUserName,platformPassword)
    println "------------------------"+ platformEndPoint	
	fireRequest(platformEndPoint,"POST", ivOauthPaylod);
    println "INFO : AWP ->  Added OAuth client to platform \n";
}