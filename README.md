# Tests jUnit con Cuenta Bancaria básica

El código fue realizado en Java 8 utilizando JUnit 5.

## Preparación
- Es necesario tener instalado [Gradle](https://gradle.org/)
- Clonar el repositorio en la carpeta deseada, desde la consola:
```
git clone https://github.com/rettke/TestJUnitExample
```
- Acceder a la carpeta del proyecto:
```
cd ./TestJUnitExample
```

## Ejecución
Para correr, ya en la carpeta desde la consola ejecutar:
```
gradle run --console=plain
``` 
Para así iniciar la ejecución del programa por consola.

## Pruebas
Para ejecutar las pruebas se debe utilizar el comando:
```
gradle test
```
Al ejecutar las pruebas se genera el archivo:
```
./build/reports/tests/test/index.html
```
Este archivo contiene un reporte con los resultados de todas las pruebas ejecutadas, simplemente abrirlo con cualquier navegador web.

## Archivos proyecto
Se trabajó principalmente en tres archivos,  en ``./src/main/java/test/cuenta/bancaria/`` se encuentra:
- `Cuenta.java`, el cual contiene la lógica del manejo de la cuenta bancaria.
- `Main.java`, el cual utiliza la clase Cuenta y controla la interacción con el usuario.

Por otro lado, en `./src/test/java/test/cuenta/bancaria/`:
- `CuentaTest.java`, que contiene todas las pruebas unitarias.
