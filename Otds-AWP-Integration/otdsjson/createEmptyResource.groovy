def createResource(String resolurceName ) {
    
return  "{\r\n" + 
			"  \"resourceName\": \""+resolurceName+"\",\r\n" + 
			"  \"description\": \""+resolurceName+"\",\r\n" + 
			"  \"displayName\": \""+resolurceName+"\",\r\n" + 
			"  \"resourceID\": null,\r\n" + 
			"  \"logonStyle\": \"\",\r\n" + 
			"  \"logonUXVersion\": \"0\",\r\n" + 
			"  \"logoutURL\": \"\",\r\n" + 
			"  \"logoutMethod\": \"\",\r\n" + 
			"  \"pcCreatePermissionAllowed\": true,\r\n" + 
			"  \"pcModifyPermissionAllowed\": true,\r\n" + 
			"  \"pcDeletePermissionAllowed\": false,\r\n" + 
			"  \"userAttributeMapping\": [\r\n" + 
			"    {\r\n" + 
			"      \"sourceAttr\": \"oTExternalID3\",\r\n" + 
			"      \"destAttr\": \"__NAME__\",\r\n" + 
			"      \"mappingFormat\": \"%s\"\r\n" + 
			"    }\r\n" + 
			"  ],\r\n" + 
			"  \"groupAttributeMapping\": [\r\n" + 
			"    {\r\n" + 
			"      \"sourceAttr\": \"cn\",\r\n" + 
			"      \"destAttr\": \"__NAME__\",\r\n" + 
			"      \"mappingFormat\": \"%s\"\r\n" + 
			"    }\r\n" + 
			"  ],\r\n" + 
			"  \"resourceType\": null,\r\n" + 
			"  \"connectorid\": null\r\n" + 
			"}";
}