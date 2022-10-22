
        GroovyShell configReader = new GroovyShell()
        def config = configReader.parse(new File('ConfigFileReader.groovy'))

def CSToken = config.getProperty('CSToken');
def CSURL= config.getProperty('CSURL');
def BravaURL = config.getProperty('BravaURL');
def csrootFolder= config.getProperty('csrootFolder');
def orgname=config.getProperty('tenantName');
def deployList='OpenText Entity Common Models'

//def platformDBHost='http://a52421fa8ae724cf7ab9b86c314b2621-316246436.eu-west-2.elb.amazonaws.com'
//def tenantDBHost='db-prod.cums473anm1l.eu-west-2.rds.amazonaws.com'

def orgName = config.getProperty('platformOrg');
def user = config.getProperty('platformUserName');
def pass =  config.getProperty('platformPassword');
def restartOrgDN = "o="+orgName+",cn=cordys,cn=defaultInst,o=opentext.net";
def platformBaseURL= config.getProperty('platformBaseURL');
def endPoint = platformBaseURL+"/home/system/com.eibus.web.soap.Gateway.wcp?organization=o=system,cn=cordys,cn=defaultInst,o=opentext.net"
   
 
        GroovyShell shell1 = new GroovyShell()
        def solutionYmlpreparation = shell1.parse(new File('solutionYmlpreparation.groovy'))
println "orgname"+orgname
        solutionYmlpreparation.solutionymlPreparation(orgName,CSToken,CSURL,BravaURL,csrootFolder);
