String addLicense(String tenantName, String resName, String resID) {

return  "{\r\n" + 
			"  \"_oTLicenseType\": \"UNLICENSED\",\r\n" + 
			"  \"_oTLicenseResource\": \""+resID+"\",\r\n" + 
			"  \"_oTLicenseResourceName\": \""+resName+"\",\r\n" + 
			"  \"_oTLicenseProduct\": \"Content_Server\",\r\n" + 
			"  \"name\": \"Content_Server\",\r\n" + 
			"  \"location\": \"cn=Content_Server¹"+resID+",ou=Licenses,dc=identity,dc="+tenantName+",dc=root\",\r\n" + 
			"  \"id\": \"cn=Content_Server¹"+resID+",ou=Licenses,dc=identity,dc="+tenantName+",dc=root\",\r\n" + 
			"  \"description\": null,\r\n" + 
			"  \"values\": [\r\n" + 
			"    {\r\n" + 
			"      \"name\": \"oTLicenseeSig\",\r\n" + 
			"      \"values\": [\r\n" + 
			"        \"MMFIwIwBDUQwYIJBKAoDZAINhBvgckNqAhQkEiBGB9QwA0DBSAwQAEwFSAAAJSBCAAJT70Ow/gJgmEQ5uATgqEQAHAvkLEBAVn7s1728ZmBZJCh50ONp1ACeC8qs7FSXbvXXnZyktE1mQH2QJ3bU7IMKmrXtkJMtKeWf0Ky3aVWD7YVlUvrsNypZEetQywBpFbsT5JNpab1tRVmS0sc2AkuSF3aIBEZW9ztkY1RrHVbG0bCRAwwCE4AVAoQF=n=21hEdvQIDAQABAkB5uB4luaYu+72sxIHyZmanLC/BFlhHzGM6f80CD4p4pPMgx8wqY9lcgzeLEn79TsSOTdlrhD9n5DfOTNb8SF6lAiEA5YstQJzXXOUIig7vdZLzn5pHh+VTShpiNXSeFJ+cU78CIQCxHLpZBYWUhSDuWDYlsJgNOig1l4s4crI3cJv1P1J9gwIgcjd4gugH+X81aozX9iby9n2sDiC7+ul56KNobVamUL0CIAt41eUwQ1/EY8asdg1o6+BndPVLtJvaagrHQ4LWMCyrAiBvakIUldgwyYs58f0dilcTWgjOUDwcNPfdaV+FXjngKA==gA2\"\r\n" + 
			"      ]\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"name\": \"oTLicenseResource\",\r\n" + 
			"      \"values\": [\r\n" + 
			"        \""+resID+"\"\r\n" + 
			"      ]\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"name\": \"oTLicenseProduct\",\r\n" + 
			"      \"values\": [\r\n" + 
			"        \"Content_Server\"\r\n" + 
			"      ]\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"name\": \"oTLicenseModel\",\r\n" + 
			"      \"values\": [\r\n" + 
			"        \"USER_BASED\"\r\n" + 
			"      ]\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"name\": \"oTLicenseResourceName\",\r\n" + 
			"      \"values\": [\r\n" + 
			"        \""+resName+"\"\r\n" + 
			"      ]\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"name\": \"oTLicenseType\",\r\n" + 
			"      \"values\": [\r\n" + 
			"        \"UNLICENSED\"\r\n" + 
			"      ]\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"name\": \"entryDN\",\r\n" + 
			"      \"values\": [\r\n" + 
			"        \"cn=Content_Server¹"+resID+",ou=Licenses,dc=identity,dc="+tenantName+",dc=root\"\r\n" + 
			"      ]\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"name\": \"oTLicenseFile\",\r\n" + 
			"      \"values\": [\r\n" + 
			"        \"[Content_Server]\\nLicenseIssuer=OpenText Corp\\nLicenseIssued~dt=2017/01/03\\nOwner=Open Text Corp HQ - North America Support Center\\nCustomerID=C66A8FF92F0F4CE9825A4C42ADDBF09A\\nUnitName=Users\\nUnitCount~n=150100\\nLicenseType=Non-production\\nVersion~v=16.x.x\\nStartDate~dt=2017/01/03\\nLicenseExpires~dt=9999/12/31\\nID=69841-12347\\n\\n\\nSignature:Rtg2On6txE9dTtAjbDF4MB3QcZjJ7+AbtmR8SOb6fYtCi3WAOE7dEbLLj5Lcfj/wVSBbAQ+7w1nYn5wQ8VGMzwnUL01jw0e6NeT8iQ8s1Ynx4a6cjjZaQyMN2j0G5DebQ/u2ON9QUs/ua4n52oZQPWcEwGf5fJ1ksnNuRV+lGuQ=\\nOwner=OpenText\\nLicenseExpires~dt=9999/12/31\\nID=OpenTextProducts\\nLicenseIssued~dt=2017/01/03\\nLicenseIssuer=OpenText Corp\\n\\nPubkey:MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCUS9Lbg3B/x80Od+czZdmaPj8ahtUD62oqXP1V3Ly5+y5tFerFcuMLw+pJ4Vmfttpi64fCC/LWOnZY3fKjeUOCU2y/JByQEqG/n+ztPpUdhHyQ83p7jSAK0C2PtfgmrVSRbZT4f7ZCgozBraUga1KFEQvSjIuAR4K84++l3jn+RwIDAQAB\\nSignature:cnpq1FCO1zw8EO8NDShk9oaPbJMzmPYvL0Lsk6AkS3WsoZ3pLC4+B4xCZ0jeBNHjlM1hIp9nB8i1L0ZlBIhr9z2QXTgVIS1rbebBrAWU2wLZ0Xldh/0vJvnJCQqqDVa8nKH7ZEIR5fQUJMaipR2DUZU+fQif1cT7mwB/iX4E/Ls=\"\r\n" + 
			"      ]\r\n" + 
			"    }\r\n" + 
			"  ]\r\n" + 
			"}";
}