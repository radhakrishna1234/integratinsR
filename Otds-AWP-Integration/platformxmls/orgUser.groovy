
def orgUser(String orgUserNmae,String orgName) {

GroovyShell configReader = new GroovyShell()
def config = configReader.parse(new File('ConfigFileReader.groovy'))
def ldaproot = config.getProperty('ldaproot');
    
return  "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
				"            <SOAP:Body>\r\n" + 
				"                <Update xmlns=\"http://schemas.cordys.com/1.0/ldap\" xmlns:clientattr=\"http://schemas.cordys.com/General/ClientAttributes/\" clientattr:sync_id=\"0\">\r\n" + 
				"                    <tuple clientattr:sync_id=\"4\">\r\n" + 
				"                        <new>\r\n" + 
				"                            <entry dn=\"cn="+orgUserNmae+",cn=organizational users,o="+orgName+","+ldaproot+"\">\r\n" + 
				"                                <authenticationuser>\r\n" + 
				"                                    <string>cn="+orgUserNmae+",cn=authenticated users,"+ldaproot+"</string>\r\n" + 
				"                                </authenticationuser>\r\n" + 
				"                                <description>\r\n" + 
				"                                    <string>"+orgUserNmae+"</string>\r\n" + 
				"                                </description>\r\n" + 
				"                                <role>\r\n" + 
				"                                    <string>cn=OTDS Push Service,cn=OpenText OTDS Platform Push Connector,"+ldaproot+"</string>\r\n" + 
				"									<string>cn=everyoneIn"+orgName+",cn=organizational roles,o="+orgName+","+ldaproot+"</string>\r\n" + 
				"                                </role>\r\n" + 
				"                                <cn>\r\n" + 
				"                                    <string>"+orgUserNmae+"</string>\r\n" + 
				"                                </cn>\r\n" + 
				"                                <objectclass>\r\n" + 
				"                                    <string>top</string>\r\n" + 
				"                                    <string>busorganizationaluser</string>\r\n" + 
				"                                    <string>busorganizationalobject</string>\r\n" + 
				"                                </objectclass>\r\n" + 
				"                            </entry>\r\n" + 
				"                        </new>\r\n" + 
				"                    </tuple>\r\n" + 
				"                </Update>\r\n" + 
				"            </SOAP:Body>\r\n" + 
				"        </SOAP:Envelope>";


}