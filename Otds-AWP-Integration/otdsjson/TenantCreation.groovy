def tenantCreation(String tenantID, String tenantUser, String tenantPassword) {
    
return  "{\r\n" + 
				"\"id\":\""+tenantID+"\",\r\n" + 
				"\"displayName\": \""+tenantID+"\",\r\n" + 
				"\"adminUserName\": \""+tenantUser+"\",\r\n" + 
				"\"adminPassword\": \""+tenantPassword+"\"\r\n" + 
				"}";
}