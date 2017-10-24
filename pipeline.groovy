@Library("tz") _

flow {
    buildSources {
        java()
        analyze {
            failIf {
                lowerThan(coverage: 50.0)
                greaterThan(criticals: 1)
            }
        }
//        dockerize(
//        """
//			FROM tomcat8
//			ONBUILD COPY target/*.war /webapp/project1.war
//		"""
//        )
    }
//    deploy {
//        docker(
//            host: "192.168.33.15",
//
//        )
//    }
}