
        GroovyShell configReader = new GroovyShell()
        def config = configReader.parse(new File('ConfigFileReader.groovy'))

		def resID = config.getProperty('appresid');
		def otdsServer= config.getProperty('server');
		def partition = config.getProperty('partitionName');
		def orgName = config.getProperty('platformOrg');
        def tenantName = config.getProperty('tenantName');
 
 
        GroovyShell shell1 = new GroovyShell()
        def solutionYmlpreparation = shell1.parse(new File('otdsYamlPreparation.groovy'))
        solutionYmlpreparation.solutionymlPreparation(orgName,resID,otdsServer,partition,tenantName);
