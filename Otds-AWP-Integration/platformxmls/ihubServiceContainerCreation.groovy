

def ihubServiceContainerCreation(String Sgroup, String Scontainer, String PlatformOrg) {

GroovyShell configReader = new GroovyShell()
def config = configReader.parse(new File('ConfigFileReader.groovy'))
def ldaproot = config.getProperty('ldaproot');
def cmputerName = config.getProperty('cmputerName');

return 		 "<SOAP:Envelope\r\n" + 
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
				"						<entry dn=\"cn="+Sgroup+",cn=soap nodes,o="+PlatformOrg+","+ldaproot+"\">\r\n" + 
				"							<objectclass>\r\n" + 
				"								<string>top</string>\r\n" + 
				"								<string>bussoapnode</string>\r\n" + 
				"							</objectclass>\r\n" + 
				"							<cn>\r\n" + 
				"								<string>"+Sgroup+"</string>\r\n" + 
				"							</cn>\r\n" + 
				"							<description>\r\n" + 
				"								<string>"+Sgroup+"</string>\r\n" + 
				"							</description>\r\n" + 
				"							<busmethodsets>\r\n" + 
				"								<string>cn=iHubConnector.iHubConnectorInterface,cn=OpenText iHub Connector,"+ldaproot+"</string>\r\n" + 
				"							</busmethodsets>\r\n" + 
				"							<labeleduri>\r\n" + 
				"								<string>http://schemas.opentext.com/ihubconnector/1.0</string>\r\n" + 
				"							</labeleduri>\r\n" + 
				"							<bussoapnodeconfiguration>\r\n" + 
				"								<string>&lt;configuration&gt;&lt;routing ui_algorithm=\"failover\" ui_type=\"loadbalancing\"&gt;&lt;numprocessors&gt;100000&lt;/numprocessors&gt;&lt;algorithm&gt;com.eibus.transport.routing.DynamicRouting&lt;/algorithm&gt;&lt;/routing&gt;&lt;validation&gt;&lt;protocol&gt;false&lt;/protocol&gt;&lt;payload&gt;false&lt;/payload&gt;&lt;/validation&gt;&lt;IgnoreWhiteSpaces&gt;false&lt;/IgnoreWhiteSpaces&gt;&lt;/configuration&gt;</string>\r\n" + 
				"							</bussoapnodeconfiguration>\r\n" + 
				"						</entry>\r\n" + 
				"					</new>\r\n" + 
				"				</tuple>\r\n" + 
				"				<tuple>\r\n" + 
				"					<new>\r\n" + 
				"						<entry dn=\"cn="+Scontainer+",cn="+Sgroup+",cn=soap nodes,o="+PlatformOrg+","+ldaproot+"\">\r\n" + 
				"							<objectclass>\r\n" + 
				"								<string>top</string>\r\n" + 
				"								<string>bussoapprocessor</string>\r\n" + 
				"							</objectclass>\r\n" + 
				"							<cn>\r\n" + 
				"								<string>"+Scontainer+"</string>\r\n" + 
				"							</cn>\r\n" + 
				"							<computer>\r\n" + 
				"								<string>"+cmputerName+"</string>\r\n" + 
				"							</computer>\r\n" + 
				"							<busosprocesshost>\r\n" + 
				"								<string>cn=Application Server,cn=monitor@"+cmputerName+",cn=monitorsoapnode@"+cmputerName+",cn=soap nodes,o=system,"+ldaproot+"</string>\r\n" + 
				"							</busosprocesshost>\r\n" + 
				"							<bussoapprocessorconfiguration>\r\n" + 
				"								<string>&lt;configurations autoStartCount=\"3\"&gt;&lt;cancelReplyInterval&gt;30000&lt;/cancelReplyInterval&gt;&lt;gracefulCompleteTime&gt;15&lt;/gracefulCompleteTime&gt;&lt;abortTime&gt;5&lt;/abortTime&gt;&lt;mustSuspendContainerManagedTransaction&gt;false&lt;/mustSuspendContainerManagedTransaction&gt;&lt;routing ui_type=\"loadbalancing\" ui_algorithm=\"failover\"&gt;&lt;preference&gt;1&lt;/preference&gt;&lt;/routing&gt;&lt;configuration implementation=\"com.opentext.ihub.IHubConnector\" htmfile=\"ihubconnector/applicationconnector/iHubConnectorConfiguration.caf\"&gt;&lt;classpath&gt;&lt;location&gt;components/ihubconnector/ihubconnector.jar&lt;/location&gt;&lt;location&gt;ext/jackson-databind.jar&lt;/location&gt;&lt;location&gt;ext/jackson-core.jar&lt;/location&gt;&lt;location&gt;ext/jackson-annotations.jar&lt;/location&gt;&lt;/classpath&gt;&lt;startupDependency/&gt;&lt;/configuration&gt;&lt;/configurations&gt;</string>\r\n" + 
				"							</bussoapprocessorconfiguration>\r\n" + 
				"							<automaticstart>\r\n" + 
				"								<string>true</string>\r\n" + 
				"							</automaticstart>\r\n" + 
				"						</entry>\r\n" + 
				"					</new>\r\n" + 
				"				</tuple>\r\n" + 
				"				<tuple>\r\n" + 
				"					<new>\r\n" + 
				"						<entry dn=\"cn=connectionpoint-"+Scontainer+",cn="+Scontainer+",cn="+Sgroup+",cn=soap nodes,o="+PlatformOrg+","+ldaproot+"\">\r\n" + 
				"							<objectclass>\r\n" + 
				"								<string>top</string>\r\n" + 
				"								<string>busconnectionpoint</string>\r\n" + 
				"							</objectclass>\r\n" + 
				"							<cn>\r\n" + 
				"								<string>connectionpoint-"+Scontainer+"</string>\r\n" + 
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
 