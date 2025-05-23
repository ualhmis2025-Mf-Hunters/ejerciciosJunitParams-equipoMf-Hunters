# Sesion 08 - JENKINS

## Instalación de plugins adicionales :hammer_and_wrench:
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

Repite los pasos para los plugins *Maven Integration, Cobertura, JavaDoc y xUnit*.
