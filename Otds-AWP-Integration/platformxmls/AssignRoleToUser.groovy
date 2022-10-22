 def addRoleToUser(String partitionName,String orgName) {
    
return "{\r\n" + 
				"   \"stringList\":[\r\n" + 
				"      \"cn=Cordys@Work#Administrator,ou=Root,ou="+partitionName+",ou=IdentityProviders,dc=identity,dc="+orgName+",dc=root\",\r\n" + 
				"	  \"cn=Cordys@Work#Developer,ou=Root,ou="+partitionName+",ou=IdentityProviders,dc=identity,dc="+orgName+",dc=root\",\r\n" + 
				"      \"cn=Cordys@Work#Deployer,ou=Root,ou="+partitionName+",ou=IdentityProviders,dc=identity,dc="+orgName+",dc=root\"\r\n" + 
				"   ]\r\n" + 
				"}";
}



def addRoleToUser(String partitionName,String orgName,String roleName) {
    
  if(orgName.equals("opentext"))
     return "{\r\n" + 
				"   \"stringList\":[\r\n" + 
				"      \"cn="+roleName+",ou=Root,ou="+partitionName+",ou=IdentityProviders,dc=identity,dc="+orgName+",dc=net\"\r\n" +
				"   ]\r\n" + 
				"}";
  else
 
   return "{\r\n" + 
				"   \"stringList\":[\r\n" + 
				"      \"cn="+roleName+",ou=Root,ou="+partitionName+",ou=IdentityProviders,dc=identity,dc="+orgName+",dc=root\"\r\n" +
				"   ]\r\n" + 
				"}";
       
}