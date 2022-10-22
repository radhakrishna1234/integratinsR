	 GroovyShell shell = new GroovyShell()
         def PlatformConfig = shell.parse(new File('IHUB_FinaOtdsConfig.groovy'))
 
        println java.time.LocalDateTime.now()

//def sysCon_URL  = "https://a065f70323eb04c5ba4974dcb0f847e3-691081319.us-west-1.elb.amazonaws.com:8501/";
//def AuthToken = "Yzg3OTVlNjItNmY3YS00ODhhLThlODgtNTFjMDI4N2JlMWYz"


def sysCon_URL  = "https://a05964f95e3484e46b9efa4cefc6cd8e-1145681618.us-west-1.elb.amazonaws.com:8501/";
def AuthToken = "OWI4MmZhNGEtMWJjOS00Zjg1LTgwOWItYWFjMjBmM2VjNTIz"

def authURL = "https://a05964f95e3484e46b9efa4cefc6cd8e-1145681618.us-west-1.elb.amazonaws.com:8501/api/login";



        PlatformConfig.stopServer(sysCon_URL,AuthToken);
PlatformConfig.checkNodeStatus(sysCon_URL,AuthToken,"Offline")
//sleep 120000
PlatformConfig.startServer(sysCon_URL,AuthToken);
PlatformConfig.checkNodeStatus(sysCon_URL,AuthToken,"Running")

        // PlatformConfig.stopServer("https://a065f70323eb04c5ba4974dcb0f847e3-691081319.us-west-1.elb.amazonaws.com:8501/","Yzg3OTVlNjItNmY3YS00ODhhLThlODgtNTFjMDI4N2JlMWYz")
        // PlatformConfig.checkNodeStatus("https://a065f70323eb04c5ba4974dcb0f847e3-691081319.us-west-1.elb.amazonaws.com:8501/","Yzg3OTVlNjItNmY3YS00ODhhLThlODgtNTFjMDI4N2JlMWYz","Offline");

         
         
        // PlatformConfig.startServer("https://a065f70323eb04c5ba4974dcb0f847e3-691081319.us-west-1.elb.amazonaws.com:8501/","Yzg3OTVlNjItNmY3YS00ODhhLThlODgtNTFjMDI4N2JlMWYz");
        // PlatformConfig.checkNodeStatus("https://a065f70323eb04c5ba4974dcb0f847e3-691081319.us-west-1.elb.amazonaws.com:8501/","Yzg3OTVlNjItNmY3YS00ODhhLThlODgtNTFjMDI4N2JlMWYz","Running");

         println java.time.LocalDateTime.now()
		 
		 //user5885