# Sesion 08 - JENKINS

## Instalación de plugins adicionales 🛠️
Es recomendable actualizar Jenkins a la última versión para evitar bugs y vulnerabilidades de seguridad. Para ello accede a tu máquina Jenkins por ssh y ejecuta actualización de paquetes:

```bash
sudo apt-get update -y
sudo apt-get upgrade -y
```

Tras la actualización y reinicio de Jenkins tenemos que instalar varios plugins adicionales si aun no están instalados: *GitHub integration, Maven integration, Cobertura, Jacoco, Code Coverage API, JavaDoc, Warnings Next Generation, Embeddable Build Status Plugin, xUnit, Monitoring*.

Haz click en **Manage Jenkins > Manage Plugins**. En la pestaña **Available busca Github integration**, seleccionalo y pulsa en **Download now and install after restart**.

![FOTO1](/sesion05Junit/imgREADME/FOTO1.png)

![FOTO2](/sesion05Junit/imgREADME/FOTO2.png)

![FOTO3](/sesion05Junit/imgREADME/FOTO3.png)

![FOTO4](/sesion05Junit/imgREADME/FOTO4.png)
(No apararece porque ya está instalado)


Instala además los plugins *Maven Integration, Cobertura, JavaDoc, xUnit, JaCoCo (Java Code Coverage), Code Coverage , Warnings Next Generation, Embeddable Build Status Plugin, Monitoning, Job Configuration History y Pipeline Configuration History*.

Por último, marca Restart Jenkins para completar la instalación. Tras unos segundos, vuelve a iniciar sesión y tendrás los plugins instalados.

![FOTO5](/sesion05Junit/imgREADME/FOTO5.png)


El plugin Embeddable Build Status necesita dar acceso al usuario anónimo en la configuración de seguridad de Jenkins, para que se pueda leer el estado de construcción del proyecto:

![FOTO1](/sesion05Junit/imgREADME/FOTO1.png)

![FOTO6](/sesion05Junit/imgREADME/FOTO6.png)

![FOTO7](/sesion05Junit/imgREADME/FOTO7.png)


<br><br><br><br>

## Creación de un proyecto con Ant 🎯 

Cada uno de los miembros del equipo deberá crear una nueva tarea de **estilo libre** 

![FOTO8](/sesion05Junit/imgREADME/FOTO8.png)

![FOTO9](/sesion05Junit/imgREADME/FOTO9.png)

Jenkins conecta al repositorio donde están los fuentes para descargarlos. Selecciona Git, y añade esta URL: https://github.com/ualhmis/connect-four.git. Al tratarse de un repositorio público no necesita credenciales.

![FOTO10](/sesion05Junit/imgREADME/FOTO10.png)

En la sección de BUILD, creamos un nuevo paso y elegimos Ant. Ponemos la versión y en Destinos ponemos *all*   

![FOTO11](/sesion05Junit/imgREADME/FOTO11.png)

En las acciones a ejecutar después seleccionamos **Publish Junit test result report** y añadimos la ruta del archivo con los resultados de los test Junit en xml ```target/test-results/*.xml```

![FOTO12](/sesion05Junit/imgREADME/FOTO12.png)

Posterior a esto se añade otra acción a ejecutar después para Javadoc: **Publish Javadoc** y añadimos la ruta donde se han generado los archivos Javadoc ```target/docs```

![FOTO13](/sesion05Junit/imgREADME/FOTO13.png)


Una vez todo configurado guardamos y es el momento de construir el proyecto. Aquí podremos ver que el resultado es inestable porque hay dos tests que fallan. Para ver la gráfica habrá que hacer al menos dos construcciones.

![FOTO14](/sesion05Junit/imgREADME/FOTO14.png)

<br><br><br><br>

## Creación de un proyecto Maven

Crear una nueva tarea y marcarla como Maven con el nombre ej07-maven-nombreMiembro.

![FOTO8](/sesion05Junit/imgREADME/FOTO8.png)

![FOTO15](/sesion05Junit/imgREADME/FOTO15.png)


Marcar la casilla **Git** y añadir la URL del repositorio

![FOTO16](/sesion05Junit/imgREADME/FOTO16.png)

En la sección de build, añade la ruta correcta al archivo pom.xml y por último añade los goals: clean package

![FOTO17](/sesion05Junit/imgREADME/FOTO17.png)


Ahora se configura un Webhook: construcción tras un push en GitHub. 
1. En GitHub, seleccionamos el repositorio sobre el que queremos activar la construcción en Jenkins y hacemos clic en: Settings > WebHooks > Add webhook
2. En Payload URL: ```https://{YOUR_JENKINS-URL}/github-webhook/

![FOTO18](/sesion05Junit/imgREADME/FOTO18.png)

Finalmente, en la configuración del proyecto en Jenkins, en la sección Build Trigers, marca la opción **GitHub hook tirigger from GITScm polling**

![FOTO19](/sesion05Junit/imgREADME/FOTO19.png)

A partir de ahora, cuando el repositorio en GitHub reciba un push notificará a Jenkins para que lance la construcción automáticamente.

Añade los resultados de la cobertura obtenidos con JaCoCO: entra de nuevo en la configuración del proyecto, en post-build actions añade una acción a ejecutar después y selecciona **Record JaCoCo coverage report** y configura las rutas correctas como en la imagen.

![FOTO20](/sesion05Junit/imgREADME/FOTO20.png)


Guarda los cambios y construye el proyecto. Al actualizar el proyecto verás la gráfica de Cobertura. Si ejecutas un par de builds la gráfica muestra la linea de evolución. Y si haces clic en la gráfica, verás el informe detallado.

![FOTO21](/sesion05Junit/imgREADME/FOTO21.png)

![FOTO22](/sesion05Junit/imgREADME/FOTO22.png)

Para generar la documentación en Javadoc y publicarla en la página del proyecto, simplemente añade los goals ```javadoc:javadoc javadoc:aggregate```. Para generar la documentación Site de Maven y publicarla, simplemente añade el goal ```site```. Esto se ha hecho directamente cuando se cambiaron previamente como se puede ver en al foto anterior.

Para poder visualizar correctamente el Site, hay que cambiar la configuración de seguridad de Jenkins predeterminada que es muy restrictiva para prevenir de archivos HTML/JS maliciosos. Para modificar la configuración, abre la consola de scritps (Manage Jenkins / Script Console), y ejecuta estas líneas para desestablecer la cabecera:
```
System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "")
System.getProperty("hudson.model.DirectoryBrowserSupport.CSP")
```

![FOTO23](/sesion05Junit/imgREADME/FOTO23.png)

<br><br><br><br>

## Creacicón de un Proyecto Pipeline

Crea un nuevo proyecto y dale el nombre y selecciona tipo pipeline. El nombre debe ser ej07-pipeline-nombreMiembro.

![FOTO8](/sesion05Junit/imgREADME/FOTO8.png)

![FOTO24](/sesion05Junit/imgREADME/FOTO24.png)

Indica la URL del proyecto en Github. Utiliza aquí la URL de tu proyecto de la práctica 7.


En la sección Pipeline, disponemos de un cuadro de texto en el que añadir la descripción de nuestro pipeline utilizando la sintaxis declarativa que Jenkins proporciona. Vamos a ver cómo hacerlo. Este es el pipeline entero, y cambiarlo a Pipeline script from SCM para Jenkisfile de GitHub:

```
pipeline {
  agent any

  tools {
    // Nombre dado a la instalación de Maven en "Tools configuration"
    maven "maven default"
  }

  stages {
    stage('Git fetch') {
      steps {
        // Get some code from a GitHub repository
        git branch: 'master', url: 'https://github.com/ualhmis2025-Mf-Hunters/ejerciciosJunitParams-equipoMf-Hunters.git'
      }
    }
    stage('Compile, Test, Package') {
      steps {
        // When necessary, use '-f path-to/pom.xml' to give the path to pom.xml
        // Run goal 'package'. It includes compile, test and package.
        sh "mvn  -f sesion05Junit/pom.xml clean package"
      }
      
      post {
        // Record the test results and archive the jar file.
        success {
          junit '/target/surefire-reports/TEST-*.xml'
          archiveArtifacts '/target/*.jar'
          jacoco( 
            execPattern: '/target/jacoco.exec',
            classPattern: '/target/classes',
            sourcePattern: '/src/',
            exclusionPattern: '/test/'
          )
          publishCoverage adapters: [jacocoAdapter('/target/site/jacoco/jacoco.xml')] 
        }
      }
    }
      stage ('Analysis') {
    steps {
      // Warnings next generation plugin required
      sh "mvn -f sesion05Junit/pom.xml checkstyle:checkstyle site -DgenerateReports=false"
      sh "mvn -f sesion05Junit/pom.xml site"
    }
    post {
      success {
        dependencyCheckPublisher pattern: '/target/site/es/dependency-check-report.xml'
        recordIssues enabledForFailure: true, tool: checkStyle()
        recordIssues enabledForFailure: true, tool: pmdParser()
        recordIssues enabledForFailure: true, tool: cpd()
        recordIssues enabledForFailure: true, tool: findBugs()
        recordIssues enabledForFailure: true, tool: spotBugs()
      }
    }
  }
      stage ('Documentation') {
      steps {
        sh "mvn -f sesion05Junit/pom.xml javadoc:javadoc javadoc:aggregate"
      }
      post{
        success {
          step $class: 'JavadocArchiver', javadocDir: 'sesion05Junit/target/reports/apidocs', keepAll: false
          publishHTML(target: [reportName: 'Maven Site', reportDir: 'sesion05Junit/target/reports', reportFiles: 'index.html', keepAll: false])
        }
      }
    }
  }
}
```