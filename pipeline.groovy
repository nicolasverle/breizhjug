@Library("breizhjug") _

def appName = "breizhjug"
def appPort = 8080
def imgName = "docker.io/nicolasverle/breizhjug"

building {
    java()
    createImage(tag: imgName,
        script:
        """
        FROM tomcat
        COPY target/*.war \$CATALINA_HOME/webapps/breizhjug.war
        """
    )
}

deploying(appName: appName, appPort: appPort) {
    kubectl(namespace: "qualif", manualValidation: true) {
        ingress("qualif.breizhjug.com") {
            service {
                deployment(replicas: 3) {
                    pod(name: appName, imagePullSecrets: ["breizhjug"]) {
                        container(image: imgName, imagePullPolicy: "Always")
                    }
                }
            }
        }
    }
}