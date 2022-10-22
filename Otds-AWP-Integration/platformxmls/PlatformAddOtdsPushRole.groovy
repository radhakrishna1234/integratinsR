
def addOtdsPushRoleToUser(String orginationName) {

GroovyShell configReader = new GroovyShell()
def config = configReader.parse(new File('ConfigFileReader.groovy'))
def ldaproot = config.getProperty('ldaproot'); 
 
return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
				"   <SOAP:Header>\r\n" + 
				"      <header xmlns=\"http://schemas.cordys.com/General/1.0/\">\r\n" + 
				"         <Logger />\r\n" + 
				"      </header>\r\n" + 
				"      <i18n:international xmlns:i18n=\"http://www.w3.org/2005/09/ws-i18n\">\r\n" + 
				"         <i18n:locale>en-US</i18n:locale>\r\n" + 
				"      </i18n:international>\r\n" + 
				"   </SOAP:Header>\r\n" + 
				"   <SOAP:Body>\r\n" + 
				"      <Update xmlns=\"http://schemas.cordys.com/1.0/ldap\">\r\n" + 
				"         <tuple>\r\n" + 
				"            <old>\r\n" + 
				"               <entry dn=\"cn=sysadmin,cn=organizational users,o="+orginationName+","+ldaproot+"\">\r\n" + 
				"                  <role>\r\n" + 
				"                     <string>cn=everyoneIn"+orginationName+",cn=organizational roles,o="+orginationName+","+ldaproot+"</string>\r\n" + 
				"                     <string>cn=Administrator,cn=Cordys@Work,"+ldaproot+"</string>\r\n" + 
				"                  </role>\r\n" + 
				"                  <description>\r\n" + 
				"                     <string>sysadmin</string>\r\n" + 
				"                  </description>\r\n" + 
				"                  <cn>\r\n" + 
				"                     <string>sysadmin</string>\r\n" + 
				"                  </cn>\r\n" + 
				"                  <objectclass>\r\n" + 
				"                     <string>top</string>\r\n" + 
				"                     <string>busorganizationalobject</string>\r\n" + 
				"                     <string>busorganizationaluser</string>\r\n" + 
				"                  </objectclass>\r\n" + 
				"                  <authenticationuser>\r\n" + 
				"                     <string>cn=sysadmin,cn=authenticated users,"+ldaproot+"</string>\r\n" + 
				"                  </authenticationuser>\r\n" + 
				"               </entry>\r\n" + 
				"            </old>\r\n" + 
				"            <new>\r\n" + 
				"               <entry dn=\"cn=sysadmin,cn=organizational users,o="+orginationName+","+ldaproot+"\">\r\n" + 
				"                  <role>\r\n" + 
				"                     <string>cn=everyoneIn"+orginationName+",cn=organizational roles,o="+orginationName+","+ldaproot+"</string>\r\n" + 
				"                     <string>cn=Administrator,cn=Cordys@Work,"+ldaproot+"</string>\r\n" + 
				"                     <string>cn=OTDS Push Service,cn=OpenText OTDS Platform Push Connector,"+ldaproot+"</string>\r\n" + 
				"                  </role>\r\n" + 
				"                  <description>\r\n" + 
				"                     <string>sysadmin</string>\r\n" + 
				"                  </description>\r\n" + 
				"                  <cn>\r\n" + 
				"                     <string>sysadmin</string>\r\n" + 
				"                  </cn>\r\n" + 
				"                  <objectclass>\r\n" + 
				"                     <string>top</string>\r\n" + 
				"                     <string>busorganizationalobject</string>\r\n" + 
				"                     <string>busorganizationaluser</string>\r\n" + 
				"                  </objectclass>\r\n" + 
				"                  <authenticationuser>\r\n" + 
				"                     <string>cn=sysadmin,cn=authenticated users,"+ldaproot+"</string>\r\n" + 
				"                  </authenticationuser>\r\n" + 
				"               </entry>\r\n" + 
				"            </new>\r\n" + 
				"         </tuple>\r\n" + 
				"      </Update>\r\n" + 
				"   </SOAP:Body>\r\n" + 
				"</SOAP:Envelope>";
}