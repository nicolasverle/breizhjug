@Library("tz") _

flow {
    buildSources {
        java()
        analyze {
            failIf(criticalsExceed: 1)
        }
        createImage(
                """
			FROM tomcat
			COPY target/*.war \$CATALINA_HOME/webapp/project1.war
		"""
        )
    }
    deploy(host: "qualif.tz.zenika.com", port: 80) {
        dockerd(image: "project1", volumes: [[host: "/etc/timezone", container: "/etc/timezone:ro"]])
    }
}