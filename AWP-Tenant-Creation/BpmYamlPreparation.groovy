
import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.Yaml

 GroovyShell configReader = new GroovyShell()
 def config = configReader.parse(new File('ConfigFileReader.groovy'))
 def org =config.getProperty('tenantName');


int random_num = Math.abs(new Random().nextInt() % 600) + 1

// Logic
def transformFile(String filename, Closure transformation) {
    DumperOptions options = new DumperOptions()
    options.defaultFlowStyle = DumperOptions.FlowStyle.BLOCK
    def yaml = new org.yaml.snakeyaml.Yaml(options)

    new File('solution.yml').withInputStream {
        Iterable<Object> documents = yaml.loadAll(it)

        def collection = documents.findAll(transformation >> {true})

        new File('solution.yml').withWriter('UTF-8') {
            yaml.dumpAll(collection.iterator(), it)
        }
    }
}

//Transformation
transformFile('solution') {
      def org1 = "radha78"
    
    it.platform.organizations.tenantName1.services.instances = 'WS-AppServer, Notification, Platform, iHub, BPM, ContentServerWithOTDS'
    
}