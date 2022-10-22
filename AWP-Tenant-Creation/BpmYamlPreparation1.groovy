#!/usr/bin/env groovy
//groovy.grape.Grape.initGrape()
//@Grapes(
//   @Grab(group = "org.yaml", module = "snakeyaml", version = "1.23")
//)
import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.Yaml

 GroovyShell configReader = new GroovyShell()
 def config = configReader.parse(new File('ConfigFileReader.groovy'))
 def org = 'radha11'

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

    
    it.platform.organizations.{orgname}.services.instances = 'Audit3, WS-AppServer, Security Administration, Notification, Platform, ContentServerWithOTDS, Email, iHub, BPM'
    

}