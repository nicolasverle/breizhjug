@Library("tz") _

setRegistry("192.168.33.62:5000")
flow {
    buildSources {
        java()
        analyze {
            failIf(criticalsExceed: 1)
        }
        createImage(script:
        """
			FROM tomcat
			COPY target/*.war \$CATALINA_HOME/webapp/project1.war
		""")
    }
    deploy(host: "qualif.tz.zenika.com", port: 80) {
        dockerd(volumes: [[host: "/etc/timezone", container: "/etc/timezone:ro"]])
    }
}