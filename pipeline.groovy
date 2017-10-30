@Library("tz") _

setRegistry("192.168.33.62:5000")
def qualif = "qualif.tz.zenika.com"
flow {
    buildSources {
        java()
        analyze {
            failIf(criticalsExceed: 1)
        }
        createImage(script:
        """
			FROM tomcat
			COPY target/*.war \$CATALINA_HOME/webapps/project1.war
			HEALTHCHECK CMD curl --fail http://${qualif}/project1 || exit 1
		""")
    }
    deploy(host: qualif, port: 80) {
        dockerd(volumes: [[host: "/etc/timezone", container: "/etc/timezone:ro"]])
    }
}