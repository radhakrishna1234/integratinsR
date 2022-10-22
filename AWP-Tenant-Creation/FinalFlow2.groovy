 GroovyShell configReader = new GroovyShell()
 def config = configReader.parse(new File('ConfigFileReader.groovy'))

String bpmPostgresSqlFile = new File('BPM_POSTGRESQL.sql').text
String notifictionPostgresSqlFile = new File('Notification_POSTGRESQL.sql').text
String notificationInitSqlFile = new File('Notification_INIT.sql').text
String ruleRepositorySqlFile = new File('RuleRepository_POSTGRESQL.sql').text
String scheduleRepositorySQlFile = new File('Scheduler_POSTGRESQL.sql').text
String capSchemaRevisionSqlFile = ""

def platformDb= 'appworks'
def tenantDB=config.getProperty('tenantName');
def platformDBHost= config.getProperty('DBEndpoint');
def platformDBPort=config.getProperty('DBport');
def platformDBUser= config.getProperty('DBUsername');
def platformDBPassword= config.getProperty('DBPassword');
def tenantDBUser= config.getProperty('DBUsername');
def tenantDBPassword= config.getProperty('DBPassword');
def tenantDBHost= config.getProperty('DBEndpoint');
def tenantDBPort=config.getProperty('DBport');
def deployList='OpenText Entity Common Models'

//def platformDBHost='http://a52421fa8ae724cf7ab9b86c314b2621-316246436.eu-west-2.elb.amazonaws.com'
//def tenantDBHost='db-prod.cums473anm1l.eu-west-2.rds.amazonaws.com'

def orgName = config.getProperty('platformOrg');
def user = config.getProperty('platformUserName');
def pass =  config.getProperty('platformPassword');
def restartOrgDN = "o="+tenantDB+",cn=cordys,cn=defaultInst,o=opentext.net";
//def platformBaseURL="http://a9b2f0fe4ee0847dc84938fa6565795a-232954245.eu-central-1.elb.amazonaws.com";
def platformBaseURL= config.getProperty('platformBaseURL');
def endPoint =platformBaseURL+"/home/system/com.eibus.web.soap.Gateway.wcp?organization=o=system,cn=cordys,cn=defaultInst,o=opentext.net"

println "endPoint "+endPoint

println "endPoint "+endPoint 
println "restartOrgDN  "+restartOrgDN 

println "user "+user
println "pass  "+pass 
println "orgName "+orgName

	      GroovyShell shell = new GroovyShell()
          def tenantDBScripts = shell.parse(new File('TenantDBScripts.groovy'))
          
		  //tenantDBScripts.createAppworksUser(platformDBHost,platformDBPort,platformDb,platformDBUser,platformDBPassword);
		  
		 // tenantDBScripts.tenantDBScript(bpmPostgresSqlFile,notifictionPostgresSqlFile,notificationInitSqlFile,ruleRepositorySqlFile,scheduleRepositorySQlFile,capSchemaRevisionSqlFile,platformDb,tenantDB,platformDBHost,platformDBPort,platformDBUser,platformDBPassword,tenantDBUser,tenantDBPassword,tenantDBHost,tenantDBPort);
   

         GroovyShell shell2 = new GroovyShell()
         def newOrgCreation = shell2.parse(new File('NewOrgCreation.groovy'))
         newOrgCreation.restartOrganization(orgName,user,pass,restartOrgDN,endPoint)
		 

		 sleep 100000*6

