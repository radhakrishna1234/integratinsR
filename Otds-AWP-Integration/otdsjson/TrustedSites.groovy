def trustedSites(String siteName) {
    
return  "{\r\n" + 
	        		"  \"stringList\": [\r\n" + 
	        		"    \""+siteName+"\"\r\n" + 
	        		"  ]\r\n" + 
	        		"}";


}


def trustedSites(String[] siteName) {

siteName.size();
String data= "";
for(i=0;i<siteName.size();i++) {
if (i!=0)
data = data+",";
data = data+"\""+siteName[i]+"\"";
}
    
return  "{\r\n" + 
	        		"  \"stringList\": ["+data+"]\r\n" + 
	        		"}";


}