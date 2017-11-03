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
        COPY target/*.war \$CATALINA_HOME/webapps/project1.war
        """)
    }
    deploy(host: "qualif.tz.zenika.com") {
        dockerd(ports: [[host: 80, container: 8080]], volumes: [[host: "/etc/localtime", container: "/etc/localtime:ro"]])
    }
}