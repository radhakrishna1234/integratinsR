import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;


/***************************************/
def getAuthTocken(String endPointURL, String userName, String password) { 

String urlParameters  = "username="+userName+"&password="+password+"";
//String urlParameters  = "username=otadmin@otds.admin&password=Password1!";
byte[] postData = urlParameters.getBytes("utf-8");
 def post1 = new URL(endPointURL).openConnection();
 post1.setRequestMethod("POST")
 post1.setDoOutput(true)
 post1.setRequestProperty("Content-Type", "application/x-www-form-urlencoded") 
 
 try {
    DataOutputStream wr = new DataOutputStream(post1.getOutputStream()) 
   wr.write( postData );
 } catch(Exception e) {
 println " ==== >>>> aaa"+e
 }
 def resp1=post1.getInputStream().getText()
 JSONParser parse = new JSONParser();
 JSONObject jobj = (JSONObject)parse.parse(resp1);
 String ticket = (String) jobj.get("ticket") 
println "===>>>"+ticket
 return ticket;

}
getAuthTocken("http://a9311a72ba5ff41698a137bf4c52ded1-1444235206.eu-west-2.elb.amazonaws.com/cs/cs/api/v1/auth","otadmin@otds.admin","Password1!");




/***************************************/


def getID(String endpointURL, String Method, String name, String userName, String password) {
   def ticket = getAuthTocken("http://a9311a72ba5ff41698a137bf4c52ded1-1444235206.eu-west-2.elb.amazonaws.com/cs/cs/api/v1/auth",userName,password);
   println "ticker"+ ticket;
   
 def post = new URL(endpointURL).openConnection();
 //println "connection success "+Method
 post.setRequestMethod(Method) 
 post.setDoOutput(true)
 post.setRequestProperty("Content-Type", "application/json")
 post.setRequestProperty("OTCSTicket", ticket)
 //println "ticket"+ticket; 
  //println "request"+request; 
 post.getOutputStream().write(request.getBytes("UTF-8"));
 def resp=post.getInputStream().getText()
// println "***** response *****\n "+ resp
 return resp;
}
