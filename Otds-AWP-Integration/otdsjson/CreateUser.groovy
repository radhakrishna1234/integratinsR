def createUser(String userName, String userPassword, String partitionName, String partitionLocation) {
    
return  "{\r\n" + 
				"  \"values\": [\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"accountDisabled\",\r\n" + 
				"      \"values\": [\r\n" + 
				"        \"false\"\r\n" + 
				"      ]\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"UserMustChangePasswordAtNextSignIn\",\r\n" + 
				"      \"values\": [\r\n" + 
				"        \"false\"\r\n" + 
				"      ]\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"UserCannotChangePassword\",\r\n" + 
				"      \"values\": [\r\n" + 
				"        \"true\"\r\n" + 
				"      ]\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"PasswordNeverExpires\",\r\n" + 
				"      \"values\": [\r\n" + 
				"        \"true\"\r\n" + 
				"      ]\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"userPassword\",\r\n" + 
				"      \"values\": [\r\n" + 
				"        \""+userPassword+"\"\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"customAttributes\": [],\r\n" + 
				"  \"userPartitionID\": \""+partitionName+"\",\r\n" + 
				"  \"location\": \""+partitionLocation+"\",\r\n" + 
				"  \"name\": \""+userName+"\",\r\n" + 
				"  \"description\": \"\"\r\n" + 
				"}";}