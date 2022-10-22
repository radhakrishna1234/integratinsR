import groovy.sql.Sql
import java.sql.Driver

def tenantDBScript(String bpmPostgresSqlFile,String notifictionPostgresSqlFile,String notificationInitSqlFile,String ruleRepositorySqlFile,String scheduleRepositorySQlFile,String capSchemaRevisionSqlFile,String platformDb,String tenantDB,String platformDBHost,String platformDBPort,String platformDBUser,String platformDBPassword,String tenantDBUser,String tenantDBPassword,String tenantDBHost,String tenantDBPort) {

       println "Tenant DB Script >> Creating cap revision table script .............................."
	new File('./','capSchemaRevision.sql').withWriter('utf-8') {    
		writer -> 
		writer.writeLine '-- Table: public.cap_schema_revision'
		writer.writeLine '-- DROP TABLE public.cap_schema_revision;'
		writer.writeLine 'CREATE TABLE public.cap_schema_revision ( artifact_id character varying(50) COLLATE pg_catalog."default" NOT NULL,artifact_name character varying(256) COLLATE pg_catalog."default",    revision character varying(50) COLLATE pg_catalog."default", time_stamp bigint,CONSTRAINT cap_schema_revision_pkey PRIMARY KEY (artifact_id) ) WITH ( OIDS = FALSE)'
		writer.writeLine 'TABLESPACE pg_default;'
		writer.writeLine 'ALTER TABLE public.cap_schema_revision OWNER to appworks;'
	}
       println "Tenant DB Script >> end cap revision table script"

	 capSchemaRevisionSqlFile = new File('./capSchemaRevision.sql').getText('UTF-8').trim()

	def driver = Class.forName('org.postgresql.Driver').newInstance() as Driver 
	def props = new Properties()
	props.setProperty("DB_user", platformDBUser) 
	props.setProperty("DB_password", platformDBPassword)

	def props1 = new Properties()
	props1.setProperty("DB_user", tenantDBUser) 
	props1.setProperty("DB_password", tenantDBPassword)

	def conn1
	def sql1
	def sql2
	def conn2
        println "Tenant DB Script >> Creating Database started ................"
	def conn = driver.connect("jdbc:postgresql://"+platformDBHost+":"+platformDBPort+"/"+platformDb+"?user="+platformDBUser+"&password="+platformDBPassword, props)
	def sql = new Sql(conn)

	try {
		conn1 = driver.connect("jdbc:postgresql://"+tenantDBHost+":"+tenantDBPort+"/postgres"+"?user="+tenantDBUser+"&password="+tenantDBPassword, props1)
		sql1 = new Sql(conn1)
		sql1.execute("create database "+tenantDB)
		sleep(15000)
         println "Tenant DB Script >> Created Database "
         println "Tenant DB Script >> Starting execution of BPM , nofification ,rule tables in tenant DB ...................... "
		conn2 = driver.connect("jdbc:postgresql://"+tenantDBHost+":"+tenantDBPort+"/"+tenantDB+"?user="+tenantDBUser+"&password="+tenantDBPassword, props1)
		sql2 = new Sql(conn2)	
		conn2.setAutoCommit(false);	
        sql2.execute(bpmPostgresSqlFile)
		sql2.execute(notifictionPostgresSqlFile)
		sql2.execute(notificationInitSqlFile)
		sql2.execute(ruleRepositorySqlFile)
		sql2.execute(scheduleRepositorySQlFile)
		sql2.execute(capSchemaRevisionSqlFile)
	  println "Tenant DB Script >> End execution of BPM , nofification ,rule tables in tenant DB"
          println "Tenant DB Script >> Coping cap revision content from platform DB ..........................."
		sql.eachRow("Select *  from cap_schema_revision") {		
		sql2.execute("INSERT INTO cap_schema_revision(artifact_id,artifact_name, revision,time_stamp) VALUES ($it.artifact_id,$it.artifact_name, $it.revision, $it.time_stamp)")
		conn2.commit();
		
	 println "Tenant DB Script >> Copied cap revision content from platform DB"
	     
		}
	} finally {
        sql2.close()
        conn2.close()
		sql1.close()
		sql.close()
		conn.close()
		conn1.close()
	  }
}

def createAppworksUser(String platformDBHost,String platformDBPort,String platformDb,String platformDBUser,String platformDBPassword) {
def driver = Class.forName('org.postgresql.Driver').newInstance() as Driver 
def props = new Properties()
def conn = driver.connect("jdbc:postgresql://"+platformDBHost+":"+platformDBPort+"/"+platformDb+"?user="+platformDBUser+"&password="+platformDBPassword, props)
	def sql = new Sql(conn)
	try{
    sql.execute("CREATE USER appworks WITH PASSWORD 'appworks';")
	println "reated user appworks"
    }catch(Exception e) {
	println "appworks user already exists"	
	}
	finally {
	conn.close();
	sql.close();
	}

}

