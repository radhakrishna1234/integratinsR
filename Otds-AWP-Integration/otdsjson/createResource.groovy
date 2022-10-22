def createResource(String resolurceName, String platformURL, String userName, String password) {
    
return  "{\r\n" + 
				"  \"resourceName\": \""+resolurceName+"\",\r\n" + 
				"  \"description\": \"\",\r\n" + 
				"  \"displayName\": \"\",\r\n" + 
				"  \"resourceID\": null,\r\n" + 
				"  \"logonStyle\": \"\",\r\n" + 
				"  \"logonUXVersion\": \"0\",\r\n" + 
				"  \"logoutURL\": \"\",\r\n" + 
				"  \"logoutMethod\": \"\",\r\n" + 
				"  \"pcCreatePermissionAllowed\": true,\r\n" + 
				"  \"pcModifyPermissionAllowed\": true,\r\n" + 
				"  \"pcDeletePermissionAllowed\": true,\r\n" + 
				"  \"connectorid\": \"rest\",\r\n" + 
				"  \"resourceType\": \"rest\",\r\n" + 
				"  \"userAttributeMapping\": [\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"oTExternalID3\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"__NAME__\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"displayname\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"DisplayName\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"mail\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Email\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"oTTelephoneNumber\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Telephone\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"oTMobile\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Mobile\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"oTFacsimileTelephoneNumber\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Fax\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"oTStreetAddress,l,st,postalCode,c\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Address\",\r\n" + 
				"      \"mappingFormat\": \"%s%n%s %s %s%n%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"oTCompany\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Company\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"ds-pwp-account-disabled\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"AccountDisabled\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"c\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-CountryOrRegion\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"gender\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-Gender\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"displayName\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-DisplayName\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"oTStreetAddress\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-Address\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"l\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-City\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"mail\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-Email\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"givenName\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-FirstName\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"sn\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-LastName\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"initials\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-MiddleNames\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"oTMobile\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-Mobile\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"postalCode\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-PostalCode\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"st\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-StateOrProvince\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"title\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-title\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"physicalDeliveryOfficeName\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-physicalDeliveryOfficeName\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"oTFacsimileTelephoneNumber\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-oTFacsimileTelephoneNumber\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"notes\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-notes\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"oTCompany\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-oTCompany\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"oTDepartment\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-oTDepartment\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"birthDate\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-Birthday\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"cn\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-UserName\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"Description\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-UserDescription\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"oTTelephoneNumber\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-Phone\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"displayName\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-IdentityDisplayName\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"groupAttributeMapping\": [\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"cn\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"__NAME__\",\r\n" + 
				"      \"mappingFormat\": \"%js:function format(name) { return name.replace(/&/g,\\\"-and-\\\"); }\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"description\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Description\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"description\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-Description\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"sourceAttr\": [\r\n" + 
				"        \"displayName\"\r\n" + 
				"      ],\r\n" + 
				"      \"destAttr\": \"Identity-DisplayName\",\r\n" + 
				"      \"mappingFormat\": \"%s\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"connectorName\": \"REST (Generic)\",\r\n" + 
				"  \"connectionParamInfo\": [\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"fBaseURL\",\r\n" + 
				"      \"value\": \""+platformURL+"\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"fUsername\",\r\n" + 
				"      \"value\": \""+userName+"\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"fPassword\",\r\n" + 
				"      \"value\": \""+password+"\"\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}";
}