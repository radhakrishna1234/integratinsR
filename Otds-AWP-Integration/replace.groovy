import groovy.sql.Sql
import java.sql.Driver

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

        GroovyShell configReader = new GroovyShell()
        def config = configReader.parse(new File('ConfigFileReader.groovy'))
        config.getProperty('');


	


def dbUserName= config.getProperty('DBUsername');
def dbPassword = config.getProperty('DBPassword');
def port =  config.getProperty('DBport');
def dbServerName =  config.getProperty('DBEndpoint');
def dbName = config.getProperty('platformOrg');




	
	
	 replace( dbUserName,  dbPassword, port, dbServerName, dbName);
	
	def replace(String dbUserName, String dbPassword,String port,String dbServerName,String dbName) {
	
		def driver = Class.forName('org.postgresql.Driver').newInstance() as Driver 
	def props = new Properties()
	
	
	def value = "";

def conn =  driver.connect("jdbc:postgresql://"+dbServerName+":"+port+"/"+dbName+"?user="+dbUserName+"&password="+dbPassword, props)
	def sql = new Sql(conn)
	
	sql.eachRow("SELECT *  FROM INFORMATION_SCHEMA.TABLES where table_name like '%opentextbasiccomponentsgc'") { rs ->
    //println "qqqq "+r
	value=rs.getString('table_name').toString();	
 } 
 println "------------>>>"+value
	value=value.substring(1,value.indexOf("opentext"));
	
	value = "O"+value+"OpenText";
	
	
	
	println "----"+value

replacefileData("username1",dbUserName,".//ContractCenterAnalytics//ContractCenterAnalytics//CCSQLDB.acconnprofiles")
replacefileData("password5",dbPassword,".//ContractCenterAnalytics//ContractCenterAnalytics//CCSQLDB.acconnprofiles")
replacefileData("dbname1",dbName,".//ContractCenterAnalytics//ContractCenterAnalytics//CCSQLDB.acconnprofiles")
replacefileData("dbserver1",dbServerName,".//ContractCenterAnalytics//ContractCenterAnalytics//CCSQLDB.acconnprofiles")
replacefileData("dbport1",port,".//ContractCenterAnalytics//ContractCenterAnalytics//CCSQLDB.acconnprofiles")
	
replacefileData("O2OpenText",value,".//ContractCenterAnalytics//ContractCenterAnalytics//Data Objects//OpenTextBasicComponentsSQL.datadesign")
replacefileData("O2OpenText",value,".//ContractCenterAnalytics//ContractCenterAnalytics//Data Objects//OpenTextContractCenterSQL.datadesign")		
	


replacefileData("username1",dbUserName,".//ContractCenterAnalytics//ContractCenterAnalytics//Data Objects//OpenTextBasicComponentsSQL.datadesign")
replacefileData("password5",dbPassword,".//ContractCenterAnalytics//ContractCenterAnalytics//Data Objects//OpenTextBasicComponentsSQL.datadesign")
replacefileData("dbname1",dbName,".//ContractCenterAnalytics//ContractCenterAnalytics//Data Objects//OpenTextBasicComponentsSQL.datadesign")
replacefileData("dbserver1",dbServerName,".//ContractCenterAnalytics//ContractCenterAnalytics//Data Objects//OpenTextBasicComponentsSQL.datadesign")
replacefileData("dbport1",port,".//ContractCenterAnalytics//ContractCenterAnalytics//Data Objects//OpenTextBasicComponentsSQL.datadesign")


replacefileData("username1",dbUserName,".//ContractCenterAnalytics//ContractCenterAnalytics//Data Objects//OpenTextContractCenterSQL.datadesign")
replacefileData("password1",dbPassword,".//ContractCenterAnalytics//ContractCenterAnalytics//Data Objects//OpenTextContractCenterSQL.datadesign")
replacefileData("dbname1",dbName,".//ContractCenterAnalytics//ContractCenterAnalytics//Data Objects//OpenTextContractCenterSQL.datadesign")
replacefileData("dbserver1",dbServerName,".//ContractCenterAnalytics//ContractCenterAnalytics//Data Objects//OpenTextContractCenterSQL.datadesign")
replacefileData("dbport1",port,".//ContractCenterAnalytics//ContractCenterAnalytics//Data Objects//OpenTextContractCenterSQL.datadesign")

compress(".//ContractCenterAnalytics")
}

def replacefileData(String oldString,String newString, String filePath) {
 
      //Instantiating the Scanner class to read the file
      Scanner sc = new Scanner(new File(filePath));
      //instantiating the StringBuffer class
      StringBuffer buffer = new StringBuffer();
      //Reading lines of the file and appending them to StringBuffer
      while (sc.hasNextLine()) {
         buffer.append(sc.nextLine()+System.lineSeparator());
      }
      String fileContents = buffer.toString();
      //System.out.println("Contents of the file: "+fileContents);
      //closing the Scanner object
      sc.close();    
      //Replacing the old line with new line
      fileContents = fileContents.replaceAll(oldString, newString);
      //instantiating the FileWriter class
      FileWriter writer = new FileWriter(filePath);
      //System.out.println("");
      //System.out.println("new data: "+fileContents);
      writer.append(fileContents);
      writer.flush();
	  
}



    def compress(String dirPath) {
        final Path sourceDir = Paths.get(dirPath);
        String zipFileName = dirPath.concat(".zip");
        try {
            final ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(zipFileName));
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
                    try {
                        Path targetFile = sourceDir.relativize(file);
                        outputStream.putNextEntry(new ZipEntry(targetFile.toString()));
                        byte[] bytes = Files.readAllBytes(file);
                        outputStream.write(bytes, 0, bytes.length);
                        outputStream.closeEntry();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }