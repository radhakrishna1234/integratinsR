def updatePartition(String partitionName) {
    
return  "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
				"  <SOAP:Body>\r\n" + 
				"    <UpdateConfiguration xmlns=\"http://schemas.cordys.com/security/2.0/otds\">\r\n" + 
				"      <tuple>\r\n" + 
				"        <old>\r\n" + 
				"          <Configuration>\r\n" + 
				"            <Partition>"+partitionName+"</Partition>\r\n" + 
				"          </Configuration>\r\n" + 
				"        </old>\r\n" + 
				"        <new>\r\n" + 
				"          <Configuration>\r\n" + 
				"            <Partition>"+partitionName+"</Partition>\r\n" + 
				"          </Configuration>\r\n" + 
				"        </new>\r\n" + 
				"      </tuple>\r\n" + 
				"    </UpdateConfiguration>\r\n" + 
				"  </SOAP:Body>\r\n" + 
				"</SOAP:Envelope>";

}