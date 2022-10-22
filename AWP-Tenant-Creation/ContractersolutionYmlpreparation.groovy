

def solutionymlPreparation(String tenantDB,String tenantDBHost,String tenantDBPort,String deployList, String tenantDBUser,String tenantDBPassword,String CSToken,String CSURL,String BravaURL,String csrootFolder) {
       println " solution yml >> solution yaml preparation stated .........................."
	File file = new File("./solution.yml")
	file.append("\n")
	file.append("    "+tenantDB+":\n" + 
	  		"      management:\n" + 
	  		"        health:\n" + 
	  		"          ready:\n" + 
	  		"            components:\n" + 
	  		"            availability: false     \n" + 
	  		"      package:\n" + 
	  		"        deployList: OpenText Entity Common Models, OpenText CC Analytic Dashboards, OpenText CC Configurable Workflow, OpenText Common Search Component, OpenText Basic Components, OpenText CC Analytic Dashboards, OpenText Content Library, OpenText Contract Center ACL, OpenText Contract Center General Dashboards, OpenText Contract Center Import, OpenText Contract Center Roles, OpenText Contract Center, OpenText Contract Initiation, OpenText Contract Negotiation, OpenText Document Generation, OpenText DocuSign Integrator, OpenText Docusign Services, OpenText Notifications, OpenText Party Management, OpenText Salesforce Integrator, OpenText SAP Accelerator\n" + 
	  		"      services:\n" + 
	  		"        instances: Audit3, WS-AppServer, Security Administration, Notification, Platform, ContentServerWithOTDS, Email, iHub\n" + 
	  		"        Audit3:\n" + 
	  		"          kind: com.cordys.audit.connector.AuditConnector\n" + 
	  		"        WS-AppServer:\n" + 
	  		"          classpath: ':/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/ContractCenter.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/ContractCenterImport.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/negotiation.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/salesforceintegrator.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/notifications.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/ContentLibrary.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/CCWorkflow.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/DocuSignIntegrator.jar:/opt/appworks/components/bpmengine/poi-ooxml-schemas.jar:/opt/appworks/components/bpmengine/poi-ooxml.jar:/opt/appworks/components/bpmengine/dom4j.jar:/opt/appworks/components/bpmengine/commons-collections4.jar:/opt/appworks/components/bpmengine/commons-compress.jar:/opt/appworks/components/bpmengine/poi.jar:/opt/appworks/ext/jackson-core.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/BCWSApp.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/com/opentext/apps/gc/docgen/DocumentGenerationPackage.jar'\n" + 
	  		"          enableAutoCleanup: true\n" + 
	  		"          initializeDatabase: true\n" + 
	  		"          initializeEIS: true\n" + 
	  		"          auditEnabled: off\n" + 
	  		"          multiTenant: true\n" + 
	  		"          dataSource: defaultDataSource     \n" + 
	  		"        BPM:\n" + 
	  		"          kind: BPM\n" + 
	  		"          classpath: ':/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/ContractCenter.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/ContractCenterImport.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/negotiation.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/salesforceintegrator.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/notifications.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/ContentLibrary.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/CCWorkflow.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/DocuSignIntegrator.jar:/opt/appworks/components/bpmengine/poi-ooxml-schemas.jar:/opt/appworks/components/bpmengine/poi-ooxml.jar:/opt/appworks/components/bpmengine/dom4j.jar:/opt/appworks/components/bpmengine/commons-collections4.jar:/opt/appworks/components/bpmengine/commons-compress.jar:/opt/appworks/components/bpmengine/poi.jar:/opt/appworks/ext/jackson-core.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/Java/Java Archives/BCWSApp.jar:/opt/appworks/cluster/shared/wsappserver/deploy/organization/CC211/com/opentext/apps/gc/docgen/DocumentGenerationPackage.jar'\n" + 
	  		"          activateDebugPoints: 'false'\n" + 
	  		"          agingTime: PT1H\n" + 
	  		"          attachmentsTimeout: PT1M\n" + 
	  		"          autoRecoverProcessInstances: 'false'\n" + 
	  		"          caseNetworkSyncLevel: '1'\n" + 
	  		"          clientTimeout: PT30S\n" + 
	  		"          dataSource: defaultDataSource\n" + 
	  		"          deleteAbortedInstanceWithNoMonitoring: 'true'\n" + 
	  		"          doCacheAssignments: 'true'\n" + 
	  		"          enableAging: 'true'\n" + 
	  		"          enableCasePessimisticLocking: 'true'\n" + 
	  		"          enableCrashRecovery: 'true'\n" + 
	  		"          enableNonProcessModel: 'false'\n" + 
	  		"          enablePriority: 'true'\n" + 
	  		"          enableProcessMonitoring: 'true'\n" + 
	  		"          enableRuleEngine: 'false'\n" + 
	  		"          maxInstanceCount: '0'\n" + 
	  		"          maxInstanceInterval: PT1S\n" + 
	  		"          maxIterationCount: '0'\n" + 
	  		"          orchestratorQueueThreshold: '5'\n" + 
	  		"          priorityAlgorithm: Aging\n" + 
	  		"          processModelCacheSize: '200'\n" + 
	  		"          ruleConfiguration: system\n" + 
	  		"          schedulerConfiguration: system\n" + 
	  		"          threadPoolSizeForLongLivedProcess: '5'\n" + 
	  		"          validateProcessModelOnDeployment: 'true'\n" + 
	  		"          webServiceExecutionDurationWarningThreshold: '1000'\n" + 
	  		"        Security Administration:\n" + 
	  		"          xds: BasicXDS\n" + 
	  		"        Email:\n" + 
	  		"          kind: com.eibus.applicationconnector.email.MailConnector\n" + 
	  		"          emailBoxes:\n" + 
	  		"            Inbox:\n" + 
	  		"              profile: sysadmin\n" + 
	  		"              restartInProgress: 'false'\n" + 
	  		"              triggersDefinitionXmlStoreFilePath: default_triggers\n" + 
	  		"            instances: Inbox\n" + 
	  		"          startAutomatically: true\n" + 
	  		"          storage: \n" + 
	  		"            dataSource: 'EMAIL_DB'\n" + 
	  		"            maxEmailsPerPollingCycle: '15'\n" + 
	  		"            maxExecutionThreads: '5'\n" + 
	  		"            maxNumberOfRows: '100'\n" + 
	  		"            pollerInterval: PT10S\n" + 
	  		"            compressData: 'false'\n" + 
	  		"        ContentServerWithOTDS:\n" + 
	  		"          kind: DocumentStore\n" + 
	  		"      content:\n" + 
	  		"        default: ContentServer\n" + 
	  		"        ContentServer:\n" + 
	  		"          kind: ContentServer\n" + 
	  		"          bravaCapabilitySet: PS_BV\n" + 
	  		"          bravaPublishOnUpload: true\n" + 
	  		"          bravaServerUrl: "+BravaURL+"\n" + 
	  		"          contentServerWebServicesUrl: "+CSURL+"/cws/services/DocumentManagement\n" + 
	  		"          otdsResourceId: "+CSToken+"\n" + 
	  		"          userForServiceStart: otadmin@otds.admin\n" + 
	  		"          rootFolder: "+csrootFolder+"\n" + 
	  		"      auditing:\n" + 
	  		"        dataSource: defaultDataSource\n" + 
	  		"      database:\n" + 
	  		"        dataSources:\n" + 
	  		"          defaultDataSource:\n" + 
	  		"            connection: sysConnection\n" + 
	  		"          xdsDataSource:\n" + 
	  		"            connection: sysConnection\n" + 
	  		"          EMAIL_DB:\n" + 
	  		"            connection: sysConnection\n" + 
	  		"        connections:\n" + 
	  		"          sysConnection:\n" + 
	  		"            kind: PostgreSql\n" + 
	  		"            connectionString:  jdbc:postgresql://"+tenantDBHost+":"+tenantDBPort+"/"+tenantDB+"?user="+tenantDBUser+"&password="+tenantDBPassword+"\n" + 
	  		"            database: "+tenantDB+"\n" + 
			"            dbaPassword: "+tenantDBPassword+"\n" + 
			"            dbaUserName: "+tenantDBUser+"\n" + 
	  		"            dbaPassword: "+tenantDBPassword+"\n" + 
	  		"            dbaUserName: "+tenantDBUser+"\n" + 
	  		"            jdbcDriver: org.postgresql.Driver\n" + 
	  		"            jdbcXaDriver: org.postgresql.xa.PGXADataSource\n" + 
	  		"            password: "+tenantDBPassword+"\n" + 
	  		"            userName: "+tenantDBUser+"\n" + 
	  		"      xds:\n" + 
	  		"        BasicXDS:\n" + 
	  		"          cacheSize: 9876\n" + 
	  		"          compressionContentSize: 8543\n" + 
	  		"          dataSource: xdsDataSource\n" + 
	  		"          enableCompression: true\n" + 
	  		"      email:\n" + 
	  		"        incoming:\n" + 
	  		"          numberOfPollerThreads: '1'\n" + 
	  		"          port: 995\n" + 
	  		"          properties:\n" + 
	  		"            'mail.pop3.ssl.enable': true    \n" + 
	  		"          protocol: POP3\n" + 
	  		"          server: pop.gmail.com\n" + 
	  		"        outgoing:\n" + 
	  		"          authenticationRequired: true\n" + 
	  		"          contentType: PLAIN\n" + 
	  		"          mime:\n" + 
	  		"            bypassSmime: 'false'\n" + 
	  		"            checkCrl: 'false'\n" + 
	  		"            enableSmime: 'false'\n" + 
	  		"            encryptMails: 'false'\n" + 
	  		"            signMails: 'false'\n" + 
	  		"          server: smtp.gmail.com\n" + 
	  		"          port: 465\n" + 
	  		"          properties:\n" + 
	  		"            'mail.smtp.ssl.enable': true\n" + 
	  		"        profiles:\n" + 
	  		"            sysadmin:\n" + 
	  		"              kind: Password\n" + 
	  		"              displayName: cc202dev@gmail.com\n" + 
	  		"              emailAddress: cc202dev@gmail.com\n" + 
	  		"              password: CCawp21.1\n" + 
	  		"              userName: cc202dev@gmail.com");
	}		
			