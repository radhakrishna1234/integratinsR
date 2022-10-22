


def addingRedirectURLToPublisher(String id, String redirectURL) {


  return "{\r\n" + 
				"  \"description\": \"Intelligent Viewing Publisher\",\r\n" + 
				"  \"redirectURLs\": [\r\n" + 
				"    \""+redirectURL+"\"\r\n" + 
				"  ],\r\n" + 
				"  \"id\": \"iv-publisher\",  \r\n" + 
				"  \"accessTokenLifeTime\": \"1000\",\r\n" + 
				"  \"refreshTokenLifeTime\": \"20000\",\r\n" + 
				"  \"authCodeLifeTime\": 20000,\r\n" + 
				"  \"allowRefreshToken\": true,\r\n" + 
				"  \"allowImpersonation\": false,\r\n" + 
				"  \"useSessionRefreshTokenLifeTime\": true,\r\n" + 
				"  \"allowedScopes\": [\r\n" + 
				"    \"read_any_markups\",\r\n" + 
				"    \"write_any_markups\"\r\n" + 
				"  ],\r\n" + 
				"  \"defaultScopes\": [\r\n" + 
				"    \"read_any_markups\",\r\n" + 
				"    \"write_any_markups\"\r\n" + 
				"  ]\r\n" + 
				"}";
				
				
}



def ivAppworscreation() {

  return 		"{\r\n" + 
				"   \"customAttributes\":[\r\n" + 
				"      \r\n" + 
				"   ],\r\n" + 
				"   \"name\":\"iv-appworks\",\r\n" + 
				"   \"description\":\"\",\r\n" + 
				"   \"confidential\":true,\r\n" + 
				"   \"redirectURLs\":[\r\n" + 
				"      \"\"\r\n" + 
				"   ],\r\n" + 
				"   \"logoutURL\":\"\",\r\n" + 
				"   \"secret\":\"123456789\",\r\n" + 
				"   \"logoutMethod\":\"\",\r\n" + 
				"   \"accessTokenLifeTime\":\"1000\",\r\n" + 
				"   \"refreshTokenLifeTime\":\"2000\",\r\n" + 
				"   \"allowRefreshToken\":true,\r\n" + 
				"   \"useSessionRefreshTokenLifeTime\":true,\r\n" + 
				"   \"allowedScopes\":[\r\n" + 
				"   \"create_publications\",\"view_publications\",\"view_any_publication\"\r\n" + 
				"      \r\n" + 
				"   ],\r\n" + 
				"   \"defaultScopes\":[\r\n" + 
				"   \"create_publications\",\"view_publications\",\"view_any_publication\"\r\n" + 
				"      \r\n" + 
				"   ],\r\n" + 
				"   \"authScopes\":null\r\n" + 
				"}";



}