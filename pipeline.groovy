@Library("breizhjugLib") _

def appName = "breizhjug"
int appPort = 8080
def imgName = "docker.io/nicolasverle/breizhjug"

building {
    java()
    createImage(
        tag: imgName,
        script: """
        FROM tomcat
        COPY target/*.war \$CATALINA_HOME/webapps/${appName}.war
        """
    )
}

deploying(appPort: appPort, appName: appName) {
    kubectl(namespace: "qualif", manualValidation: true) {
        ingress("qualif.breizhjug.com") {
            service {
                deployment(replicas: 3, name: appName, imagePullSecrets: ["breizhjug"]) {
                    container(image: imgName, imagePullPolicy: "Always")
                }
            }
        }
    }
}