

def solutionymlPreparation(String orgname, String resID, String otdsServer ,String partition, String tenantName) {

     if("".equals(tenantName.trim()))
	 otdsServer= otdsServer;
	 else
	 otdsServer = otdsServer+"/otdstenant/"+tenantName.trim()
       println " solution yml >> solution yaml preparation stated .........................."
	File file = new File("./solution.yml")
	file.append("\n")
	file.append("    "+orgname+":\n")
	file.append("      otds:\n")
	file.append("        resourceId: "+resID+"\n")
	file.append("        url: "+otdsServer+"\n")
	file.append("        rolesPartition: "+partition+"\n")
	file.append("      authenticators:\n")	
	file.append("        defaultLogin: OTCS_auth\n")
	file.append("        OTCS_auth:\n")
	file.append("          kind: OTDS\n")
	file.append("          publicLoginUrl: "+otdsServer+"/otdsws/login\n")
        println "solution yml >> Solution yaml preparation end"
}
