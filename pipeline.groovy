@Library("tz") _

setRegistry("dfdf")
String qualifHost = "qualif.tz.zenika.com"
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
    deploy(host: qualifHost, port: 80) {
        dockerd(ports: [[host: 80, container: 8080]], volumes: [[host: "/etc/timezone", container: "/etc/timezone:ro"]])
    }
}