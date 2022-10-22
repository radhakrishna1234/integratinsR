def consolidateResource(String resourceDN) {
    
return  "{\r\n" + 
	        		"   \"cleanupUsersInResource\":true,\r\n" + 
	        		"   \"cleanupGroupsInResource\":true,\r\n" + 
	        		"   \"resourceList\":[\r\n" + 
	        		"      \""+resourceDN+"\"\r\n" + 
	        		"   ],\r\n" + 
	        		"   \"objectToConsolidate\":\""+resourceDN+"\"\r\n" + 
	        		"}";

}