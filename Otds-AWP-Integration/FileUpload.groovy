import java.util.*;





 String response = "{\"template\":\"large\",\"hostName\":\"ihub-0\",\"serviceList\":{\"service\":[{\"startupType\":{\"value\":\"Auto\"},\"displayName\":\"Web\",\"serviceStatus\":{\"value\":\"Running\"},\"serviceDescription\":\"Web Server\",\"serviceName\":\"Web\"},{\"startupType\":{\"value\":\"Auto\"},\"displayName\":\"Cache\",\"serviceStatus\":{\"value\":\"Running\"},\"serviceDescription\":\"Cache Server\",\"serviceName\":\"Cache\"},{\"startupType\":{\"value\":\"Auto\"},\"displayName\":\"Platform\",\"serviceStatus\":{\"value\":\"Running\"},\"serviceDescription\":\"iHub Platform Services\",\"serviceName\":\"Platform\"},{\"startupType\":{\"value\":\"Auto\"},\"displayName\":\"BIRT\",\"serviceStatus\":{\"value\":\"Running\"},\"serviceDescription\":\"BIRT based Data Visualization Services\",\"serviceName\":\"BIRT\"},{\"startupType\":{\"value\":\"Auto\"},\"displayName\":\"Monitor\",\"serviceStatus\":{\"value\":\"Running\"},\"serviceDescription\":\"Monitoring Server\",\"serviceName\":\"Monitor\"},{\"startupType\":{\"value\":\"Auto\"},\"displayName\":\"Agent\",\"serviceStatus\":{\"value\":\"Running\"},\"serviceDescription\":\"Monitoring Agent\",\"serviceName\":\"Agent\"}]},\"operatingSystem\":\"Linux\"}";
	
checkNodeStatus(response,"Running");


def checkNodeStatus(String response, String status) {

long millis=System.currentTimeMillis();  
println "millis  "+millis

String input="notRunning";

for(int i=0;input.equals("notRunning");i++) {
println "before"
sleep 15000;
println "After"
long millis1=System.currentTimeMillis();

long diff = millis1-millis;

if(diff>=300000){
input="Running";
println "Timeout"
}

String[] runningNode = response.split(status);
println "length"+runningNode.length-1
if(runningNode.length-1==6) {
input="Running";
println "node"
}

}
	 
}	 
	



