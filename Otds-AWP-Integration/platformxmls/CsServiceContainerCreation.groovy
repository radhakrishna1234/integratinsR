

def csServiceContainerCreation(String BravaserverURL, String Bravacapabilityset, String PrepublishdocumentstBravaonupload, String OrganizationalAdministrator, String Resourcename, String Space, String EndpointURL, String serviceName, String csRootFolder) {

GroovyShell configReader = new GroovyShell()
def config = configReader.parse(new File('ConfigFileReader.groovy'))
def ldaproot = config.getProperty('ldaproot');
def cmputerName = config.getProperty('cmputerName');

cmputerName=cmputerName.toLowerCase();

def serviceGroup=csRootFolder;
def ServiceContainer=csRootFolder;
def csStore=csRootFolder;
def csRoot=csRootFolder;

String request = "<SOAP:Envelope\r\n" + 
    		"	xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" +  
    		"	<SOAP:Header\r\n" + 
    		"		xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
    		"		<header\r\n" + 
    		"			xmlns=\"http://schemas.cordys.com/General/1.0/\">\r\n" + 
    		"			<Logger\r\n" + 
    		"				xmlns=\"http://schemas.cordys.com/General/1.0/\"/>\r\n" + 
    		"			</header>\r\n" + 
    		"			<i18n:international\r\n" + 
    		"				xmlns:i18n=\"http://www.w3.org/2005/09/ws-i18n\">\r\n" + 
    		"				<i18n:locale>en-US</i18n:locale>\r\n" + 
    		"			</i18n:international>\r\n" + 
    		"		</SOAP:Header>\r\n" + 
    		"		<SOAP:Body>\r\n" + 
    		"			<Update\r\n" + 
    		"				xmlns=\"http://schemas.cordys.com/1.0/ldap\">\r\n" + 
    		"				<tuple>\r\n" + 
    		"					<new>\r\n" + 
    		"						<entry dn=\"cn="+serviceGroup+",cn=soap nodes,o="+serviceName+","+ldaproot+"\">\r\n" + 
    		"							<objectclass>\r\n" + 
    		"								<string>top</string>\r\n" + 
    		"								<string>bussoapnode</string>\r\n" + 
    		"							</objectclass>\r\n" + 
    		"							<cn>\r\n" + 
    		"								<string>"+serviceGroup+"</string>\r\n" + 
    		"							</cn>\r\n" + 
    		"							<description>\r\n" + 
    		"								<string>"+serviceName+"</string>\r\n" + 
    		"							</description>\r\n" + 
    		"							<busmethodsets>\r\n" + 
    		"								<string>cn=Method Set Document Store,cn=Cordys Document Store,"+ldaproot+"</string>\r\n" + 
    		"								<string>cn=Method Set Document Store 2.0,cn=Cordys Document Store,"+ldaproot+"</string>\r\n" + 
    		"								<string>cn=Method Set Document Store AX 1.0,cn=Cordys Document Store,"+ldaproot+"</string>\r\n" + 
    		"								<string>cn=Method Set Document Store Schemas,cn=Cordys Document Store,"+ldaproot+"</string>\r\n" + 
    		"								<string>cn=Method Set Document Store Core 1.0,cn=Cordys Document Store,"+ldaproot+"</string>\r\n" + 
    		"								<string>cn=Method Set Document Store Audit 1.0,cn=Cordys Document Store,"+ldaproot+"</string>\r\n" + 
    		"								<string>cn=Method Set Document Store Search 1.0,cn=Cordys Document Store,"+ldaproot+"</string>\r\n" + 
    		"								<string>cn=Method Set Document Store Content Server,cn=Cordys Document Store,"+ldaproot+"</string>\r\n" + 
    		"							</busmethodsets>\r\n" + 
    		"							<labeleduri>\r\n" + 
    		"								<string>http://schemas.cordys.com/documentstore/default/1.0</string>\r\n" + 
    		"								<string>http://schemas.cordys.com/documentstore/default/2.0</string>\r\n" + 
    		"								<string>http://schemas.cordys.com/documentstore/ax/1.0</string>\r\n" + 
    		"								<string>http://schemas.cordys.com/documentstore/general/1.0</string>\r\n" + 
    		"								<string>http://schemas.cordys.com/documentstore/core/1.0</string>\r\n" + 
    		"								<string>http://schemas.cordys.com/documentstore/audit/1.0</string>\r\n" + 
    		"								<string>http://schemas.cordys.com/documentstore/search/1.0</string>\r\n" + 
    		"								<string>http://schemas.cordys.com/documentstore/contentserver/1.0</string>\r\n" + 
    		"							</labeleduri>\r\n" + 
    		"							<bussoapnodeconfiguration>\r\n" + 
    		"								<string>&lt;configuration&gt;&lt;routing ui_algorithm=\"failover\" ui_type=\"loadbalancing\"&gt;&lt;numprocessors&gt;100000&lt;/numprocessors&gt;&lt;algorithm&gt;com.eibus.transport.routing.DynamicRouting&lt;/algorithm&gt;&lt;/routing&gt;&lt;validation&gt;&lt;protocol&gt;false&lt;/protocol&gt;&lt;payload&gt;false&lt;/payload&gt;&lt;/validation&gt;&lt;IgnoreWhiteSpaces&gt;false&lt;/IgnoreWhiteSpaces&gt;&lt;/configuration&gt;</string>\r\n" + 
    		"							</bussoapnodeconfiguration>\r\n" + 
    		"						</entry>\r\n" + 
    		"					</new>\r\n" + 
    		"				</tuple>\r\n" + 
    		"				<tuple>\r\n" + 
    		"					<new>\r\n" + 
    		"						<entry dn=\"cn="+ServiceContainer+",cn="+serviceGroup+",cn=soap nodes,o="+serviceName+","+ldaproot+"\">\r\n" + 
    		"							<objectclass>\r\n" + 
    		"								<string>top</string>\r\n" + 
    		"								<string>bussoapprocessor</string>\r\n" + 
    		"							</objectclass>\r\n" + 
    		"							<cn>\r\n" + 
    		"								<string>"+ServiceContainer+"</string>\r\n" + 
    		"							</cn>\r\n" + 
    		"							<computer>\r\n" + 
    		"								<string>"+cmputerName+"</string>\r\n" + 
    		"							</computer>\r\n" + 
    		"							<busosprocesshost>\r\n" + 
    		"								<string>cn=Application Server,cn=monitor@"+cmputerName+",cn=monitorsoapnode@"+cmputerName+",cn=soap nodes,o=system,"+ldaproot+"</string>\r\n" + 
    		"							</busosprocesshost>\r\n" + 
    		"							<bussoapprocessorconfiguration>\r\n" + 
    		"								<string>&lt;configurations autoStartCount=\"3\"&gt;&lt;cancelReplyInterval&gt;30000&lt;/cancelReplyInterval&gt;&lt;gracefulCompleteTime&gt;15&lt;/gracefulCompleteTime&gt;&lt;abortTime&gt;5&lt;/abortTime&gt;&lt;mustSuspendContainerManagedTransaction&gt;false&lt;/mustSuspendContainerManagedTransaction&gt;&lt;routing ui_type=\"loadbalancing\" ui_algorithm=\"failover\"&gt;&lt;preference&gt;1&lt;/preference&gt;&lt;/routing&gt;&lt;configuration implementation=\"com.cordys.documentstore.applicationconnector.DocumentStoreConnector\" htmfile=\"/cordys/com/cordys/documentstore/configuration/documentstoreconnector.caf\"&gt;&lt;classpath&gt;&lt;location&gt;components/documentstore/documentstore.jar&lt;/location&gt;&lt;location&gt;ext/chemistry-opencmis-client-api.jar&lt;/location&gt;&lt;location&gt;ext/chemistry-opencmis-client-bindings.jar&lt;/location&gt;&lt;location&gt;ext/chemistry-opencmis-client-impl.jar&lt;/location&gt;&lt;location&gt;ext/chemistry-opencmis-commons-api.jar&lt;/location&gt;&lt;location&gt;ext/chemistry-opencmis-commons-impl.jar&lt;/location&gt;&lt;location&gt;ext/otcs-client-stubs-16.0.3.1197.jar&lt;/location&gt;&lt;location&gt;ext/ecmlink-client-stubs-16.0.0.938.jar&lt;/location&gt;&lt;location&gt;ext/jackson-annotations.jar&lt;/location&gt;&lt;location&gt;ext/jackson-jaxrs-json-provider.jar&lt;/location&gt;&lt;location&gt;ext/jackson-jaxrs-base.jar&lt;/location&gt;&lt;location&gt;ext/jackson-core.jar&lt;/location&gt;&lt;location&gt;ext/jackson-databind.jar&lt;/location&gt;&lt;location&gt;ext/jackson-dataformat-xml.jar&lt;/location&gt;&lt;/classpath&gt;&lt;startupDependency&gt;&lt;namespace&gt;http://schemas.cordys.com/security/2.0/otds&lt;/namespace&gt;&lt;namespace&gt;http://schemas.cordys.com/1.0/secadmin&lt;/namespace&gt;&lt;/startupDependency&gt;&lt;documentstores\r\n" + 
    		"									xmlns=\"\"&gt;&lt;store&gt;&lt;storename&gt;"+csStore+"&lt;/storename&gt;&lt;storetype&gt;OTCS&lt;/storetype&gt;&lt;storeconfiguration&gt;&lt;otcsEndPointUrl&gt;"+EndpointURL+"&lt;/otcsEndPointUrl&gt;&lt;authentication&gt;&lt;ContentServerResource&gt;&lt;ResourceName&gt;"+Resourcename+"&lt;/ResourceName&gt;&lt;Space&gt;"+Space+"&lt;/Space&gt;&lt;/ContentServerResource&gt;&lt;OAuthClient&gt;&lt;clientID/&gt;&lt;clientSecret/&gt;&lt;/OAuthClient&gt;&lt;authenticationHandler/&gt;&lt;organizationalUserCN&gt;"+OrganizationalAdministrator+"&lt;/organizationalUserCN&gt;&lt;/authentication&gt;&lt;workspaceName&gt;EnterpriseWS&lt;/workspaceName&gt;&lt;cordysroot&gt;"+csRoot+"&lt;/cordysroot&gt;&lt;xECMDetails&gt;&lt;WorkSpaceId/&gt;&lt;APIServiceURL/&gt;&lt;CGIPath/&gt;&lt;SupportPath/&gt;&lt;SynchronizeWSManually&gt;false&lt;/SynchronizeWSManually&gt;&lt;ViewerFromOTCS/&gt;&lt;/xECMDetails&gt;&lt;/storeconfiguration&gt;&lt;viewerType&gt;Brava&lt;/viewerType&gt;&lt;BravaConfig&gt;&lt;BravaServerUrl&gt;"+BravaserverURL+"&lt;/BravaServerUrl&gt;&lt;BravaServerPrePublish&gt;"+PrepublishdocumentstBravaonupload+"&lt;/BravaServerPrePublish&gt;&lt;LicenseInfo&gt;&lt;CapabilitySet&gt;"+Bravacapabilityset+"&lt;/CapabilitySet&gt;&lt;/LicenseInfo&gt;&lt;/BravaConfig&gt;&lt;/store&gt;&lt;/documentstores&gt;&lt;/configuration&gt;&lt;/configurations&gt;\r\n" + 
    		"								</string>\r\n" + 
    		"							</bussoapprocessorconfiguration>\r\n" + 
    		"							<automaticstart>\r\n" + 
    		"								<string>true</string>\r\n" + 
    		"							</automaticstart>\r\n" + 
    		"						</entry>\r\n" + 
    		"					</new>\r\n" + 
    		"				</tuple>\r\n" + 
    		"				<tuple>\r\n" + 
    		"					<new>\r\n" + 
    		"						<entry dn=\"cn=connectionpoint-"+ServiceContainer+",cn="+ServiceContainer+",cn="+serviceGroup+",cn=soap nodes,o="+serviceName+","+ldaproot+"\">\r\n" + 
    		"							<objectclass>\r\n" + 
    		"								<string>top</string>\r\n" + 
    		"								<string>busconnectionpoint</string>\r\n" + 
    		"							</objectclass>\r\n" + 
    		"							<cn>\r\n" + 
    		"								<string>connectionpoint-"+ServiceContainer+"</string>\r\n" + 
    		"							</cn>\r\n" + 
    		"							<labeleduri>\r\n" + 
    		"								<string>socket://"+cmputerName+":0</string>\r\n" + 
    		"							</labeleduri>\r\n" + 
    		"							<description>\r\n" + 
    		"								<string>&lt;socket-configuration/&gt;</string>\r\n" + 
    		"							</description>\r\n" + 
    		"							<clientconnectionpoint>\r\n" + 
    		"								<string>false</string>\r\n" + 
    		"							</clientconnectionpoint>\r\n" + 
    		"						</entry>\r\n" + 
    		"					</new>\r\n" + 
    		"				</tuple>\r\n" + 
    		"			</Update>\r\n" + 
    		"		</SOAP:Body>\r\n" + 
    		"	</SOAP:Envelope>";


}
 