

def solutionymlPreparation(String tenantDB,String tenantDBHost,String tenantDBPort,String deployList, String tenantDBUser,String tenantDBPassword,String CSToken,String CSURL,String BravaURL,String csrootFolder) {
       println " solution yml >> solution yaml preparation stated .........................."
	File file = new File("./solution.yml")
	file.append("\n")
file.append("    "+tenantDB+":\r\n" + 
	            		"      package:\r\n" + 
	            		"        deployList: OpenText Entity Common Models, OpenText CC Configurable Workflow, OpenText Common Search Component, OpenText CC Analytic Dashboards, OpenText Content Library, OpenText Contract Center ACL, OpenText Contract Center General Dashboards, OpenText Contract Center Import, OpenText Contract Center Roles, OpenText Contract Center, OpenText Contract Initiation, OpenText Contract Negotiation, OpenText Document Generation, OpenText DocuSign Integrator, OpenText Docusign Services, OpenText Notifications, OpenText Party Management, OpenText Salesforce Integrator, OpenText SAP Accelerator\r\n" + 
	            		"      services:\r\n" + 
	            		"        instances: WS-AppServer, Notification, Platform, iHub, BPM\r\n" + 
	            		"        WS-AppServer:\r\n" + 
	            		"          classpath: ':/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/ContractCenter.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/ContractCenterImport.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/negotiation.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/salesforceintegrator.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/notifications.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/ContentLibrary.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/CCWorkflow.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/DocuSignIntegrator.jar:/opt/appworks/components/bpmengine/poi-ooxml-schemas.jar:/opt/appworks/components/bpmengine/poi-ooxml.jar:/opt/appworks/components/bpmengine/dom4j.jar:/opt/appworks/components/bpmengine/commons-collections4.jar:/opt/appworks/components/bpmengine/commons-compress.jar:/opt/appworks/components/bpmengine/poi.jar:/opt/appworks/ext/jackson-core.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/BCWSApp.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/com/opentext/apps/gc/docgen/DocumentGenerationPackage.jar'\r\n" + 
	            		"          enableAutoCleanup: true\r\n" + 
	            		"          initializeDatabase: true\r\n" + 
	            		"          initializeEIS: true\r\n" + 
	            		"          auditEnabled: off\r\n" + 
	            		"          multiTenant: true\r\n" + 
	            		"          dataSource: system     \r\n" + 
	            		"        BPM:\r\n" + 
	            		"          kind: BPM\r\n" + 
	            		"          classpath: ':/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/ContractCenter.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/ContractCenterImport.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/negotiation.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/salesforceintegrator.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/notifications.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/ContentLibrary.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/CCWorkflow.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/DocuSignIntegrator.jar:/opt/appworks/components/bpmengine/poi-ooxml-schemas.jar:/opt/appworks/components/bpmengine/poi-ooxml.jar:/opt/appworks/components/bpmengine/dom4j.jar:/opt/appworks/components/bpmengine/commons-collections4.jar:/opt/appworks/components/bpmengine/commons-compress.jar:/opt/appworks/components/bpmengine/poi.jar:/opt/appworks/ext/jackson-core.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/Java/Java Archives/BCWSApp.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/"+tenantDB+"/com/opentext/apps/gc/docgen/DocumentGenerationPackage.jar'\r\n" + 
	            		"          activateDebugPoints: 'false'\r\n" + 
	            		"          agingTime: PT1H\r\n" + 
	            		"          attachmentsTimeout: PT1M\r\n" + 
	            		"          autoRecoverProcessInstances: 'false'\r\n" + 
	            		"          caseNetworkSyncLevel: '1'\r\n" + 
	            		"          clientTimeout: PT30S\r\n" + 
	            		"          dataSource: defaultDataSource\r\n" + 
	            		"          deleteAbortedInstanceWithNoMonitoring: 'true'\r\n" + 
	            		"          doCacheAssignments: 'true'\r\n" + 
	            		"          enableAging: 'true'\r\n" + 
	            		"          enableCasePessimisticLocking: 'true'\r\n" + 
	            		"          enableCrashRecovery: 'true'\r\n" + 
	            		"          enableNonProcessModel: 'false'\r\n" + 
	            		"          enablePriority: 'true'\r\n" + 
	            		"          enableProcessMonitoring: 'true'\r\n" + 
	            		"          enableRuleEngine: 'false'\r\n" + 
	            		"          maxInstanceCount: '0'\r\n" + 
	            		"          maxInstanceInterval: PT1S\r\n" + 
	            		"          maxIterationCount: '0'\r\n" + 
	            		"          orchestratorQueueThreshold: '5'\r\n" + 
	            		"          priorityAlgorithm: Aging\r\n" + 
	            		"          processModelCacheSize: '200'\r\n" + 
	            		"          ruleConfiguration: system\r\n" + 
	            		"          schedulerConfiguration: system\r\n" + 
	            		"          threadPoolSizeForLongLivedProcess: '5'\r\n" + 
	            		"          validateProcessModelOnDeployment: 'true'\r\n" + 
	            		"          webServiceExecutionDurationWarningThreshold: '1000'\r\n" + 
	            		"        Notification:\r\n" + 
	            		"          kind: Notification\r\n" + 
	            		"        Email:\r\n" + 
	            		"          kind: com.eibus.applicationconnector.email.MailConnector\r\n" + 
	            		"          emailBoxes:\r\n" + 
	            		"            Inbox:\r\n" + 
	            		"              profile: sysadmin\r\n" + 
	            		"              restartInProgress: 'false'\r\n" + 
	            		"              triggersDefinitionXmlStoreFilePath: default_triggers\r\n" + 
	            		"            instances: Inbox\r\n" + 
	            		"          startAutomatically: true\r\n" + 
	            		"          storage: \r\n" + 
	            		"            dataSource: system\r\n" + 
	            		"            maxEmailsPerPollingCycle: '15'\r\n" + 
	            		"            maxExecutionThreads: '5'\r\n" + 
	            		"            maxNumberOfRows: '100'\r\n" + 
	            		"            pollerInterval: PT10S\r\n" + 
	            		"            compressData: 'false'\r\n" + 
	            		"        ContentServerWithOTDS:\r\n" + 
	            		"          kind: DocumentStore\r\n" + 
	            		"      content:\r\n" + 
	            		"        default: ContentServer\r\n" + 
	            		"        ContentServer:\r\n" + 
	            		"          kind: ContentServer\r\n" + 
	            		"          bravaCapabilitySet: PS_BV\r\n" + 
	            		"          bravaPublishOnUpload: true\r\n" + 
	            		"          bravaServerUrl: "+BravaURL+"\r\n" + 
	            		"          contentServerWebServicesUrl: "+CSURL+"/cws/services/DocumentManagement\r\n" + 
	            		"          otdsResourceId: "+CSToken+"\r\n" + 
	            		"          userForServiceStart: otadmin@otds.admin\r\n" + 
	            		"          rootFolder: "+csrootFolder+"\r\n" + 
	            		"      database:\r\n" + 
	            		"        dataSources:\r\n" + 
	            		"          system:\r\n" + 
	            		"            connection: PGSQLConn\r\n" + 
	            		"        connections:\r\n" + 
	            		"          PGSQLConn:\r\n" + 
	            		"            kind: PostgreSql\r\n" + 
	            		"            connectionString: jdbc:postgresql://"+tenantDBHost+":"+tenantDBPort+"/"+tenantDB+"\r\n" + 
	            		"            database: "+tenantDB+"\r\n" + 
	            		"            jdbcDriver: org.postgresql.Driver\r\n" + 
	            		"            jdbcXaDriver: org.postgresql.xa.PGXADataSource\r\n" + 
	            		"            password: "+tenantDBPassword+"\r\n" + 
	            		"            userName: "+tenantDBUser+"\r\n" + 
	            		"      email:\r\n" + 
	            		"        incoming:\r\n" + 
	            		"          numberOfPollerThreads: '1'\r\n" + 
	            		"          port: 995\r\n" + 
	            		"          properties:\r\n" + 
	            		"            'mail.pop3.ssl.enable': true    \r\n" + 
	            		"          protocol: POP3\r\n" + 
	            		"          server: pop.gmail.com\r\n" + 
	            		"        outgoing:\r\n" + 
	            		"          authenticationRequired: true\r\n" + 
	            		"          contentType: PLAIN\r\n" + 
	            		"          mime:\r\n" + 
	            		"            bypassSmime: 'false'\r\n" + 
	            		"            checkCrl: 'false'\r\n" + 
	            		"            enableSmime: 'false'\r\n" + 
	            		"            encryptMails: 'false'\r\n" + 
	            		"            signMails: 'false'\r\n" + 
	            		"          server: smtp.gmail.com\r\n" + 
	            		"          port: 465\r\n" + 
	            		"          properties:\r\n" + 
	            		"            'mail.smtp.ssl.enable': true\r\n" + 
	            		"        profiles:\r\n" + 
	            		"            sysadmin:\r\n" + 
	            		"              kind: Password\r\n" + 
	            		"              displayName: cc202dev@gmail.com\r\n" + 
	            		"              emailAddress: cc202dev@gmail.com\r\n" + 
	            		"              password: CCaws\$21.1\r\n" + 
	            		"              userName: cc202dev@gmail.com");
	}	
	
