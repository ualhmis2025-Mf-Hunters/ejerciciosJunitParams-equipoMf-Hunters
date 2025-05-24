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


En la sección Pipeline, disponemos de un cuadro de texto en el que añadir la descripción de nuestro pipeline utilizando la sintaxis declarativa que Jenkins proporciona. Tras ponerlo debemos arreglar cosas como el nombre de la versión que debe ser el que estemos utilizando, en este caso **maven default**. O el link del repositorio: **https://github.com/ualhmis2025-Mf-Hunters/ejerciciosJunitParams-equipoMf-Hunters.git** y su rama que en nuestro caso es **master**. Debemos de cambiar tambien la zona donde se encuentra el POM.xml, que en nuestro caso es **sesion05Junit/pom.xml**.

```
pipeline {
  agent any

  tools {
    // Nombre dado a la instalación de Maven en "Tools configuration"
    maven "Default Maven"
  }

  stages {
    stage('Git fetch') {
      steps {
        // Get some code from a GitHub repository
        git branch: 'main', url: 'https://github.com/ualhmis/MavenEjercicios'
      }
    }
    stage('Compile, Test, Package') {
      steps {
        // When necessary, use '-f path-to/pom.xml' to give the path to pom.xml
        // Run goal 'package'. It includes compile, test and package.
        sh "mvn  -f sesion07Maven/pom.xml clean package"
      }
      post {
        // Record the test results and archive the jar file.
        success {
          junit '**/target/surefire-reports/TEST-*.xml'
          archiveArtifacts '**/target/*.jar'
        }
      }
    }
  }
}
```

Así se debería de ver el resultado actual después de haber puesto el script y haber hecho build now del proyecto:

![FOTO25](/sesion05Junit/imgREADME/FOTO25.png)

Para visualizar informe de cobertura en el pipeline, añade las dos siguientes linea al bloque post:
```
  success {
    junit '**/target/surefire-reports/TEST-*.xml'
    archiveArtifacts '**/target/*.jar'
    jacoco(
      execPattern: '**/target/jacoco.exec',
      classPattern: '**/target/classes',
      sourcePattern: '**/src/',
      exclusionPattern: '**/test/'
    )
    publishCoverage adapters: [jacocoAdapter('**/target/site/jacoco/jacoco.xml')]
  }
```

Y el resultado debe ser que añade el informe *Coverage Trend* y *Coverage report* :

![FOTO26](/sesion05Junit/imgREADME/FOTO26.png)

Para ejecutar y visualizar el análisis de Checkstyle, añade un nuevo stage al pipeline, en el que hay que arreglar tambien donde está el archivo pom.xml de manera que la ruta en este caso debería de ser **sesion05Junit/pom.xml**:
```
  stage ('Analysis') {
    steps {
	  // Warnings next generation plugin required
	  sh "mvn -f sesion07Maven/pom.xml checkstyle:checkstyle site -DgenerateReports=false"
    }
    post {
      success {
        recordIssues enabledForFailure: true, tool: checkStyle()
      }
    }
  }
```

El resultado con la implementación de CheckStyle deber ser el siguiente:

![FOTO27](/sesion05Junit/imgREADME/FOTO27.png)

Ahora debemos de añadir unos cuantos informes extras que son los siguientes:
1. PMD: añade el goal adecuado en la ejecución de maven y añade la publicación del informe:
```
recordIssues enabledForFailure: true, tool: pmdParser()
```

2. CPD: añade la publicación del informe:
```
recordIssues enabledForFailure: true, tool: cpd()
```

3. FingBugs: repite el proceso.

4. SpotBugs: repite el proceso.

El resultado final debe ser tal que así.
```
    stage ('Analysis') {
      steps {
	    // Warnings next generation plugin required
	    sh "mvn -f sesion07Maven/pom.xml checkstyle: checkstyle site -DgenerateReports=false"
      sh "mvn -f sesion07Maven/pom.xml site"
      }
      post {
        success {
          dependencyCheckPublisher pattern: '**/target/site/es/dependency-check-report.xml'
          recordIssues enabledForFailure: true, tool: checkStyle()
          recordIssues enabledForFailure: true, tool: pmdParser()
          recordIssues enabledForFailure: true, tool: cpd()
          recordIssues enabledForFailure: true, tool: findBugs()
          recordIssues enabledForFailure: true, tool: spotBugs()
        }
      }
    }
```

El resultado debe verse con todas estas implementaciones:

![FOTO28](/sesion05Junit/imgREADME/FOTO28.png)

Dependency Check de OWASP (Open Web Application Security Project) es una herramienta que permite identificar las dependencias de nuestro proyecto y comprobar si hay alguna de ellas que tiene vulnerabilidades conocidas. En la práctica anterior configuramos el plugin dependency-check-maven en el bloque <reporting> del pom.xml, por lo que este plugin se ejecuta cuando llamamos al goal site. Puesto que ya hemos ejecutado site en la fase anterior, no es necesario crear una nueva fase (stage) para generar el informe de Dependency-check, únicamente será necesario publicarlo en el pipeline.

Modifica el archivo pom.xml en tu proyecto y añade la siguiente línea para que genere el informe también en formato XML, que es el formato que lee el plugin:

```
      <plugin>
        <groupId>org.owasp</groupId>
        <artifactId>dependency-check-maven</artifactId>
        <version>5.3.2</version>
        <configuration>
          <skipTestScope>false</skipTestScope>
          <formats>
            <format>HTML</format>
            <format>XML</format>
          </formats>
        </configuration>
        ...
      </plugin>
```

Tras volver a construir el proyecto, aparecerá una nueva gráfica de Dependency Check en el proyecto. Si no tienes problemas de seguridad en las dependencias, esta gráfica estará en blanco. El enlace al informe de dependencias no aparece en la página principal del proyecto, en el menú de enlaces como el resto, sino que tienes que hacer clic en el número del último build, y en la nueva página ya aparece el enlace.


La siguiente fase recomendada en el pipeline, de las lista de fases genéricas, es la de generar la documentación

Es necesario instalar previamente el plugin HTML Publisher de Jenkins.

Añade esta fase al pipeline:

```
    stage ('Documentation') {
      steps {
	    sh "mvn -f sesion07Maven/pom.xml javadoc:javadoc javadoc:aggregate"
      }
      post{
        success {
          step $class: 'JavadocArchiver', javadocDir: 'sesion07Maven/target/site/apidocs', keepAll: false
          publishHTML(target: [reportName: 'Maven Site', reportDir: 'sesion07Maven/target/site', reportFiles: 'index.html', keepAll: false])
        }
      }
    }
```

La descripción del pipeline puede guardarse en un archivo llamado Jenkinsfile y guardarse en el repositorio como otro archivo de código más. Si haces esto, al configurar el proyecto en Jenkins debes elegir la opción Pipeline script from SCM en la sección de definición del pipeline. A continuación, debes proporcionar la URL del repositorio donde se encuentra el archivo Jenkinsfile.

![FOTO29](/sesion05Junit/imgREADME/FOTO29.png)


<div style="
    height: 10px;
    background: linear-gradient(90deg, #ff6b6b, #4ecdc4);
    margin: 50px 0;">
</div><div style="
    height: 10px;
    background: linear-gradient(90deg, #ff6b6b, #4ecdc4);
    margin: 50px 0;">
</div><div style="
    height: 10px;
    background: linear-gradient(90deg, #ff6b6b, #4ecdc4);
    margin: 50px 0;">
</div>

# SonarQube con Jenkins

## Configuración de la integración  entre Jenkins y SonarQube

El primer paso es crear un token en SonarQube para el usuario. Para ello, en SonarQube, creamos un nuevo usuario: userjenkins. Para administrar usuarios y grupos, una vez logueado como admin elige Administration > Security. Añade el usuario userjenkins, que de forma predeterminada se añade al grupo sonar-users.

![FOTO30](/sesion05Junit/imgREADME/FOTO30.JPG)

Accede con usuario userjenkins en SonarQube y en su perfil, crea un token y guardalo para usarlo después.

En Jenkins, instalamos el plugin SonarQube Scanner.

![FOTO31](/sesion05Junit/imgREADME/FOTO31.JPG)

Una vez instalado el plugin de SonarQube, lo configuramos: Administrar Jenkins > Configurar el Sistema, y bajamos hasta la sección SonarQube Servers. Marca la opción Environment variables Enable injection of SonarQube server configuration as build environment variables. Añadimos un nuevo SonarQube, damos un nombre al servidor, la URL de SonarQube, y añadimos la credencial de acceso al servidor mediante una nueva credencial de tipo Secret Text usando el token de autenticación. Dale el ID a la credencial: sonar_server

![FOTO32](/sesion05Junit/imgREADME/FOTO32.JPG)

![FOTO34](/sesion05Junit/imgREADME/FOTO34.JPG)

![FOTO33](/sesion05Junit/imgREADME/FOTO33.JPG)

A continuación debemos añadir la instalación de SonarQube Scanner en Administrar Jenkins > Global Tool configuration. Seleccionamos la última version que se instale automáticamente.

![FOTO35](/sesion05Junit/imgREADME/FOTO35.JPG)


## Añadir SonarQube a un proyecto Jenkins

### Tipo Maven 

En el archivo pom.xml tenemos que incorporar el plugin de SonarQube en el bloque build.

```
<build>
...
    <plugin>
      <groupId>org.sonarsource.scanner.maven</groupId>
      <artifactId>sonar-maven-plugin</artifactId>
      <version>3.9.0.2155</version>
    </plugin>
...
</build>
```

En la configuración del proyecto Jenkins, en la sección Entorno de Ejecución, marcamos la opción: Prepare SonarQube Scanner environment, y seleccionamos el token en el desplegable.

![FOTO36](/sesion05Junit/imgREADME/FOTO36.JPG)

En los Goals de Maven, añadimos sonar:sonar al final de lista de goals.

![FOTO37](/sesion05Junit/imgREADME/FOTO37.JPG)


Tras la ejecución, aparecerán los resultados de SonarQube. En el proyecto se mostrará una etiqueta con el valor del Quality Gate encontrado en el análisis. Haciendo clic en el enlace nos lleva al resultado detallado del análisis.

**AQUI ES EL ERROR QUE NOS DA ............................................................................................................................................................................................**

### Tipo Pipeline

Añade una nueva fase al Pipeline:

```
  stage('SonarQube analysis') {
    steps {
      withSonarQubeEnv(credentialsId: 'sonar_server', installationName: 'servidor_sonarqube') {
        sh 'mvn sonar:sonar'
      }
    }
  }
```

Alternativamente, se puede añadir a continuación una nueva fase que establezca el pipeline a UNSTABLE si falla el Quality Gate.

```
  stage("Quality Gate") {
    steps {
      timeout(time: 1, unit: 'HOURS') {
      // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
      // true = set pipeline to UNSTABLE, false = don't
        waitForQualityGate abortPipeline: true
      }
    }
  }
```

Esto sería el resultado

![FOTO38](/sesion05Junit/imgREADME/FOTO38.JPG)

## Análisis SonarQube desde Eclipse

Si deseas ejecutar el análisis de SonarQube desde Eclipse, debes llamar a la construcción maven el goal sonar:sonar y los parámetros siguientes:

```
sonar:sonar -Dsonar.host.url=http://sonarqube_server_url:9000  -Dsonar.login=user-token
```
