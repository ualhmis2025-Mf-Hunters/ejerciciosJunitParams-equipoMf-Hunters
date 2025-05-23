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
