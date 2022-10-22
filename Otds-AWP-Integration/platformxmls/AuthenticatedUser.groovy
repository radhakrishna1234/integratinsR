

def authenticatedUser(String orgUserNmae,String orgUserNmaePass, String orgName) {

GroovyShell configReader = new GroovyShell()
def config = configReader.parse(new File('ConfigFileReader.groovy'))
def ldaproot = config.getProperty('ldaproot');
    
return  "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
				"            <SOAP:Body>\r\n" + 
				"                <Update xmlns=\"http://schemas.cordys.com/1.0/ldap\" xmlns:clientattr=\"http://schemas.cordys.com/General/ClientAttributes/\" clientattr:sync_id=\"0\">\r\n" + 
				"                    <tuple clientattr:sync_id=\"8\">\r\n" + 
				"                        <new>\r\n" + 
				"                            <entry xmlns=\"http://schemas.cordys.com/1.0/ldap\" dn=\"cn="+orgUserNmae+",cn=authenticated users,"+ldaproot+"\">\r\n" + 
				"                                <objectclass>\r\n" + 
				"                                    <string>top</string>\r\n" + 
				"                                    <string>busauthenticationuser</string>\r\n" + 
				"                                </objectclass>\r\n" + 
				"                                <cn>\r\n" + 
				"                                    <string>"+orgUserNmae+"</string>\r\n" + 
				"                                </cn>\r\n" + 
				"                                <description>\r\n" + 
				"                                    <string>"+orgUserNmae+"</string>\r\n" + 
				"                                </description>\r\n" + 
				"                                <osidentity>\r\n" + 
				"                                    <string>"+orgUserNmae+"</string>\r\n" + 
				"                                </osidentity>\r\n" + 
				"                                <defaultcontext>\r\n" + 
				"                                    <string>o="+orgName+","+ldaproot+"</string>\r\n" + 
				"                                </defaultcontext>\r\n" + 
				"                                <userPassword>\r\n" + 
				"                                    <string>"+orgUserNmaePass+"</string>\r\n" + 
				"                                </userPassword><o>\r\n" + 
				"                                    <string/>\r\n" + 
				"                                </o>\r\n" + 
				"                                <registeredAddress>\r\n" + 
				"                                    <string/>\r\n" + 
				"                                </registeredAddress>\r\n" + 
				"                                <telephoneNumber>\r\n" + 
				"                                    <string/>\r\n" + 
				"                                    <string/>\r\n" + 
				"                                </telephoneNumber>\r\n" + 
				"                                <facsimileTelephoneNumber>\r\n" + 
				"                                    <string/>\r\n" + 
				"                                </facsimileTelephoneNumber>\r\n" + 
				"                                <mail>\r\n" + 
				"                                    <string>"+orgUserNmae+"@cordys.com</string>\r\n" + 
				"                                </mail>\r\n" + 
				"                                <labeleduri>\r\n" + 
				"                                    <string/>\r\n" + 
				"                                </labeleduri>\r\n" + 
				"                                <authenticationtype>\r\n" + 
				"                                    <string>custom</string>\r\n" + 
				"                                </authenticationtype>\r\n" + 
				"                            </entry>\r\n" + 
				"                        </new>\r\n" + 
				"                    </tuple>\r\n" + 
				"                </Update>\r\n" + 
				"            </SOAP:Body>\r\n" + 
				"        </SOAP:Envelope>";


}