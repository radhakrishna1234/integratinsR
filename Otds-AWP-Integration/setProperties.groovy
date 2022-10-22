
def setProperties() {
def newProps = new Properties()
def fileWriter = new OutputStreamWriter(new FileOutputStream("config.properties",false), 'UTF-8')
def lineSeparator = System.getProperty("line.separator")


newProps.setProperty('platformOrg', new File('../OutputData/tenant.txt').getText('UTF-8').trim())
newProps.setProperty('platformBaseURL', 'http://'+new File('../OutputData/awp_host.txt').getText('UTF-8').trim())
newProps.setProperty('platformUserName', 'sysadmin')
newProps.setProperty('platformPassword', 'admin')

newProps.setProperty('server', 'http://'+new File('../OutputData/otds_host.txt').getText('UTF-8').trim())
newProps.setProperty('adminUser', 'otadmin@otds.admin')
newProps.setProperty('adminPass', 'otds')
newProps.setProperty('tenantName', new File('../OutputData/tenant.txt').getText('UTF-8').trim())
newProps.setProperty('tenantUserName', new File('../OutputData/otdsTenantUserName.txt').getText('UTF-8').trim())
newProps.setProperty('tenantPassword', new File('../OutputData/otdsTenantUserPassword.txt').getText('UTF-8').trim())

newProps.store(fileWriter, null)
fileWriter.close()

}