def activateteRes(String csUserName, String cdpassword, String csHost) {
    
return  "{\r\n" + 
			"   \"resourceName\":\"cs\",\r\n" + 
			"   \"id\":\"cs\",\r\n" + 
			"   \"description\":\"Content Server\",\r\n" + 
			"   \"displayName\":\"cs\",\r\n" + 
			"   \"resourceID\":\"3b81f0b5-147c-46e7-ae63-4f6aaf471339\",\r\n" + 
			"   \"resourceState\":1,\r\n" + 
			"   \"userSynchronizationState\":true,\r\n" + 
			"   \"resourceDN\":\"cn=cs,ou=Resources,dc=identity,dc=opentext,dc=net\",\r\n" + 
			"   \"resourceType\":\"cs\",\r\n" + 
			"   \"accessRoleList\":[\r\n" + 
			"      {\r\n" + 
			"         \"id\":\"Access to cs\",\r\n" + 
			"         \"name\":\"Access to cs\",\r\n" + 
			"         \"description\":\"Content Server access role for resource cs\",\r\n" + 
			"         \"status\":false\r\n" + 
			"      }\r\n" + 
			"   ],\r\n" + 
			"   \"impersonateList\":null,\r\n" + 
			"   \"impersonateAnonymousList\":null,\r\n" + 
			"   \"pcCreatePermissionAllowed\":true,\r\n" + 
			"   \"pcModifyPermissionAllowed\":true,\r\n" + 
			"   \"pcDeletePermissionAllowed\":true,\r\n" + 
			"   \"logoutURL\":\"\",\r\n" + 
			"   \"logoutMethod\":\"\",\r\n" + 
			"   \"allowImpersonation\":false,\r\n" + 
			"   \"connectorName\":\"Content Server\",\r\n" + 
			"   \"connectorid\":\"cs\",\r\n" + 
			"   \"userAttributeMapping\":[\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"oTExternalID1\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"__NAME__\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"givenName\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"FirstName\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"initials\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"MiddleName\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"sn\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"LastName\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"oTTelephoneNumber\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"Phone\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"title\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"Title\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"l\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"OfficeLocation\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"oTFacsimileTelephoneNumber\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"Fax\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"mail\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"MailAddress\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"oTDepartment\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"GroupID\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"oTType\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"Type\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"SecurityClearanceLevel\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"SupplementalMarkings\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"TimeZone\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"ds-pwp-account-disabled\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"AccountDisabled\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      }\r\n" + 
			"   ],\r\n" + 
			"   \"groupAttributeMapping\":[\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"cn\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"__NAME__\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"sourceAttr\":[\r\n" + 
			"            \"oTType\"\r\n" + 
			"         ],\r\n" + 
			"         \"destAttr\":\"Type\",\r\n" + 
			"         \"mappingFormat\":\"%s\"\r\n" + 
			"      }\r\n" + 
			"   ],\r\n" + 
			"   \"connectionParamInfo\":[\r\n" + 
			"      {\r\n" + 
			"         \"name\":\"fMemberWSDL\",\r\n" + 
			"         \"value\":\""+csHost+"/cws/services/MemberService?wsdl\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"name\":\"fAuthWSDL\",\r\n" + 
			"         \"value\":\""+csHost+"/cws/services/Authentication?wsdl\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"name\":\"fUsername\",\r\n" + 
			"         \"value\":\""+csUserName+"\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"name\":\"fPassword\",\r\n" + 
			"         \"value\":\""+cdpassword+"\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"name\":\"fDefaultGroup\",\r\n" + 
			"         \"value\":\"DefaultGroup\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"name\":\"fExternalDefaultGroup\",\r\n" + 
			"         \"value\":\"External Users\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"name\":\"fDefaultPermMask\",\r\n" + 
			"         \"value\":\"2063\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"         \"name\":\"fDisableDelete\",\r\n" + 
			"         \"value\":\"False\"\r\n" + 
			"      }\r\n" + 
			"   ],\r\n" + 
			"   \"logonStyle\":\"\",\r\n" + 
			"   \"logonUXVersion\":\"0\"\r\n" + 
			"}";


}