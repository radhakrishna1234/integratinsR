
def getProperty(String name) {
def  props = new Properties();
File propFile = new File('CsConfig.properties')
props.load(propFile.newDataInputStream())
def config = new ConfigSlurper().parse(props)
return config[name] 
}