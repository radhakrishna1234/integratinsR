import java.io.*; 

def solutionymlPreparation(String orgname, String CSToken, String CSURL,String BravaURL,String csrootFolder) {
       println " solution yml >> solution yaml preparation stated .........................."
       StringBuffer sb = new StringBuffer();

try{
 FileReader fr=new FileReader("solution.yml");    
          BufferedReader br=new BufferedReader(fr);    
  
          String data=null;    
          while((data=br.readLine())!=null){  
          sb.append(data);
          sb.append("\n");
          }  
          br.close();    
          fr.close();    
}catch(Exception e) {

}


      
if(!sb.contains(orgname+":")) {	
println " yaml not contains organization so appending it \n"
	File file = new File("./solution.yml")
	file.append("\n")
	file.append("    "+orgname+":\n")
	file.append("      management:\n")
	file.append("        health:\n")
	file.append("          ready:\n")
	file.append("            components: service.DocumentStore\n")
	file.append("            availability: false\n")	
	file.append("      services:\n")
	file.append("        instances: DocumentStore \n")
	file.append("        DocumentStore:\n")
	file.append("          kind: com.cordys.documentstore.applicationconnector.DocumentStoreConnector\n")
	file.append("      content:\n")
    file.append("        default: ContentServer\n");
    file.append("        ContentServer:\n");
    file.append("          kind: ContentServer\n");
	file.append("          authenticationKind: OtdsResource\n");
    file.append("          contentServerWebServicesUrl: "+CSURL+"/cws/services/DocumentManagement\n");
    file.append("          otdsResourceId: "+CSToken+"\n");
    file.append("          userForServiceStart: otadmin@otds.admin\n");
    file.append("          rootFolder: "+csrootFolder+"\n");
	file.append("          viewer: Intelligent Viewing\n");
	file.append("        viewers:\n");
	file.append("          Intelligent Viewing:\n");
	file.append("            kind: IntelligentViewing\n");
	file.append("            publicationServiceUrl: http://otiv-publication.default.appworks1.cfcr-lab.bp-paas.otxlab.net\n");
	file.append("            viewerServiceUrl: "+BravaURL+"\n");
	file.append("            clientId: iv-appworks\n");
	file.append("            clientSecret: 123456789\n");

	
        println "solution yml >> Solution yaml preparation end"

} else if (sb.toString().split().length==1) {
println " yaml contains organization so not appending it \n"
	File file = new File("./solution.yml")
	file.append("\n")
	//file.append("    "+orgname+":\n")
	file.append("      management:\n")
	file.append("        health:\n")
	file.append("          ready:\n")
	file.append("            components: service.DocumentStore\n")
	file.append("            availability: false\n")	
	file.append("      services:\n")
	file.append("        instances: DocumentStore \n")
	file.append("        DocumentStore:\n")
	file.append("          kind: com.cordys.documentstore.applicationconnector.DocumentStoreConnector\n")
	file.append("      content:\n")
    file.append("        default: ContentServer\n");
    file.append("        ContentServer:\n");
    file.append("          kind: ContentServer\n");
	file.append("          authenticationKind: OtdsResource\n");
    file.append("          contentServerWebServicesUrl: "+CSURL+"/cws/services/DocumentManagement\n");
    file.append("          otdsResourceId: "+CSToken+"\n");
    file.append("          userForServiceStart: otadmin@otds.admin\n");
    file.append("          rootFolder: "+csrootFolder+"\n");
	file.append("          viewer: Intelligent Viewing\n");
	file.append("        viewers:\n");
	file.append("          Intelligent Viewing:\n");
	file.append("            kind: IntelligentViewing\n");
	file.append("            publicationServiceUrl: http://otiv-publication.default.appworks1.cfcr-lab.bp-paas.otxlab.net\n");
	file.append("            viewerServiceUrl: "+BravaURL+"\n");
	file.append("            clientId: iv-appworks\n");
	file.append("            clientSecret: 123456789\n");
        println "solution yml >> Solution yaml preparation end"

}else {

println " organizations is in between the yaml is inserting content server yamls "
StringBuffer file = new StringBuffer();
    file.append("\n")
	file.append("    "+orgname+":\n")
	file.append("      management:\n")
	file.append("        health:\n")
	file.append("          ready:\n")
	file.append("            components: service.DocumentStore\n")
	file.append("            availability: false\n")	
	file.append("      services:\n")
	file.append("        instances: DocumentStore \n")
	file.append("        DocumentStore:\n")
	file.append("          kind: com.cordys.documentstore.applicationconnector.DocumentStoreConnector\n")
	file.append("      content:\n")
    file.append("        default: ContentServer\n");
    file.append("        ContentServer:\n");
    file.append("          kind: ContentServer\n");
	file.append("          authenticationKind: OtdsResource\n");
    file.append("          contentServerWebServicesUrl: "+CSURL+"/cws/services/DocumentManagement\n");
    file.append("          otdsResourceId: "+CSToken+"\n");
    file.append("          userForServiceStart: otadmin@otds.admin\n");
    file.append("          rootFolder: "+csrootFolder+"\n");
	file.append("          viewer: Intelligent Viewing\n");
	file.append("        viewers:\n");
	file.append("          Intelligent Viewing:\n");
	file.append("            kind: IntelligentViewing\n");
	file.append("            publicationServiceUrl: http://otiv-publication.default.appworks1.cfcr-lab.bp-paas.otxlab.net\n");
	file.append("            viewerServiceUrl: "+BravaURL+"\n");
	file.append("            clientId: iv-appworks\n");
	file.append("            clientSecret: 123456789\n");

   String[] sp = sb.toString().split(orgname+":");
   
   //println "11 " +sp[0]

   sp[0] = sp[0]+file.toString()+"\n";
   
   println sp[0];

try{
Writer fileWriter = new FileWriter("./solution.yml", false);
BufferedWriter buffer = new BufferedWriter(fileWriter);
buffer.write(sp[0])
buffer.write(sp[1])
buffer.close();
fileWriter.close();
}catch(Exception e) {
printStackTrace()
}

}
}


solutionymlPreparation("orgname", "SToken","CSURL","BravaURL","csrootFolder");