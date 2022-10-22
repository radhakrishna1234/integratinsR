def updateAccessRoles(String resourceName,String adminUserName,String adminUserPartition, String UserLocation, String partitiontoBeAdd, String PartationLocation,String groupDescription, String groupLocation, String groupFullname,String groupPartition) {
    
return  "{\r\n" + 
	      		"  \"id\": \"Access to "+resourceName+"\",\r\n" + 
	      		"  \"name\": \"Access to "+resourceName+"\",\r\n" + 
	      		"  \"description\": \"Default access role for resource "+resourceName+"\",\r\n" + 
	      		"  \"status\": false,\r\n" + 
	      		"  \"accessRoleMembers\": {\r\n" + 
	      		"    \"userPartitions\": [\r\n" + 
	      		"      {\r\n" + 
	      		"        \"name\": \""+partitiontoBeAdd+"\",\r\n" + 
	      		"        \"displayName\": null,\r\n" + 
	      		"        \"userPartition\": \""+partitiontoBeAdd+"\",\r\n" + 
	      		"        \"location\": \""+PartationLocation+"\",\r\n" + 
	      		"        \"description\": \"null\"\r\n" + 
	      		"      }\r\n" + 
	      		"    ],\r\n" + 
	      		"    \"organizationalUnits\": [],\r\n" + 
	      		"    \"groups\": [\r\n" + 
	      		"      {\r\n" + 
	      		"        \"name\": \""+groupFullname+"\",\r\n" + 
	      		"        \"userPartition\": \""+groupPartition+"\",\r\n" + 
	      		"        \"displayName\": \""+groupDescription+"\",\r\n" + 
	      		"        \"location\": \""+groupLocation+"\",\r\n" + 
	      		"        \"description\": null\r\n" + 
	      		"      }\r\n" + 
	      		"    ],\r\n" + 
	      		"    \"users\": [\r\n" + 
	      		"      {\r\n" + 
	      		"        \"name\": \""+adminUserName+"\",\r\n" + 
	      		"        \"displayName\": \"otadmin\",\r\n" + 
	      		"        \"userPartition\": \""+adminUserPartition+"\",\r\n" + 
	      		"        \"location\": \""+UserLocation+"\",\r\n" + 
	      		"        \"description\": null\r\n" + 
	      		"      }\r\n" + 
	      		"    ],\r\n" + 
	      		"    \"roles\": []\r\n" + 
	      		"  },\r\n" + 
	      		"  \"resources\": [\r\n" + 
	      		"    {\r\n" + 
	      		"      \"resourceName\": \""+resourceName+"\",\r\n" + 
	      		"      \"id\": null,\r\n" + 
	      		"      \"description\": null,\r\n" + 
	      		"      \"displayName\": null,\r\n" + 
	      		"      \"resourceID\": null,\r\n" + 
	      		"      \"resourceState\": 0,\r\n" + 
	      		"      \"userSynchronizationState\": false,\r\n" + 
	      		"      \"resourceDN\": null,\r\n" + 
	      		"      \"resourceType\": null,\r\n" + 
	      		"      \"accessRoleList\": null,\r\n" + 
	      		"      \"impersonateList\": null,\r\n" + 
	      		"      \"impersonateAnonymousList\": null,\r\n" + 
	      		"      \"pcCreatePermissionAllowed\": false,\r\n" + 
	      		"      \"pcModifyPermissionAllowed\": false,\r\n" + 
	      		"      \"pcDeletePermissionAllowed\": false,\r\n" + 
	      		"      \"logoutURL\": null,\r\n" + 
	      		"      \"logoutMethod\": null,\r\n" + 
	      		"      \"allowImpersonation\": null\r\n" + 
	      		"    }\r\n" + 
	      		"  ],\r\n" + 
	      		"  \"attributeList\": [\r\n" + 
	      		"    {\r\n" + 
	      		"      \"name\": \"pushAllGroups\",\r\n" + 
	      		"      \"values\": [\r\n" + 
	      		"        \"True\"\r\n" + 
	      		"      ]\r\n" + 
	      		"    }\r\n" + 
	      		"  ]\r\n" + 
	      		"}";
}