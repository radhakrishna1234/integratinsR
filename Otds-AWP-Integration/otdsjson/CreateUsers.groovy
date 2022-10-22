def createUser(String userID, String fristNamr, String lastName, String initial, String displayname, String email, String password, String partition, String partitionDN) {
    
return  " {\r\n" + 
				"   \"values\":[\r\n" + 
				"      {\r\n" + 
				"         \"name\":\"accountDisabled\",\r\n" + 
				"         \"values\":[\r\n" + 
				"            \"false\"\r\n" + 
				"         ]\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"         \"name\":\"UserMustChangePasswordAtNextSignIn\",\r\n" + 
				"         \"values\":[\r\n" + 
				"            \"false\"\r\n" + 
				"         ]\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"         \"name\":\"UserCannotChangePassword\",\r\n" + 
				"         \"values\":[\r\n" + 
				"            \"true\"\r\n" + 
				"         ]\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"         \"name\":\"PasswordNeverExpires\",\r\n" + 
				"         \"values\":[\r\n" + 
				"            \"true\"\r\n" + 
				"         ]\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"         \"name\":\"userPassword\",\r\n" + 
				"         \"values\":[\r\n" + 
				"            \""+password+"\"\r\n" + 
				"         ]\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"         \"name\":\"displayName\",\r\n" + 
				"         \"values\":[\r\n" + 
				"            \""+displayname+"\"\r\n" + 
				"         ]\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"         \"name\":\"givenName\",\r\n" + 
				"         \"values\":[\r\n" + 
				"            \""+fristNamr+"\"\r\n" + 
				"         ]\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"         \"name\":\"initials\",\r\n" + 
				"         \"values\":[\r\n" + 
				"            \""+initial+"\"\r\n" + 
				"         ]\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"         \"name\":\"mail\",\r\n" + 
				"         \"values\":[\r\n" + 
				"            \""+email+"\"\r\n" + 
				"         ]\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"         \"name\":\"sn\",\r\n" + 
				"         \"values\":[\r\n" + 
				"            \""+lastName+"\"\r\n" + 
				"         ]\r\n" + 
				"      }\r\n" + 
				"   ],\r\n" + 
				"   \"customAttributes\":[\r\n" + 
				"      \r\n" + 
				"   ],\r\n" + 
				"   \"userPartitionID\":\""+partition+"\",\r\n" + 
				"   \"location\":\""+partitionDN+"\",\r\n" + 
				"   \"name\":\""+userID+"\"\r\n" + 
				"}";


}