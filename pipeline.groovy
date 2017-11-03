@Library("tz") _

def qualifHost = "qualif.tz.zenika.com"
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
        COPY target/*.war \$CATALINA_HOME/project1.war
        HEALTHCHECK CMD curl --fail http://${qualifHost}/project1 || exit 1
        """)
    }
    deploy(host: qualifHost) {
        dockerd(ports: [[host: 80, container: 8080]], volumes: [[host: "/etc/timezone", container: "/etc/timezone:ro"]])
    }
}