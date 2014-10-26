#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad de los Andes (Bogotá - Colombia)
# Departamento de Ingeniería de Sistemas y Computación
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n11_sistemaExperto
# Autor: Equipo Cupi2 2014
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo

# ---------------------------------------------------------
# Ejecución de las pruebas
# ---------------------------------------------------------

cd ../..
	
java -ea -classpath ./lib/sistemaExperto.jar:./test/lib/sistemaExpertoTest.jar:./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.sistemaExperto.test.EnfermedadTest
java -ea -classpath ./lib/sistemaExperto.jar:./test/lib/sistemaExpertoTest.jar:./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.sistemaExperto.test.FarmacoTest
java -ea -classpath ./lib/sistemaExperto.jar:./test/lib/sistemaExpertoTest.jar:./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.sistemaExperto.test.SintomaTest
java -ea -classpath ./lib/sistemaExperto.jar:./test/lib/sistemaExpertoTest.jar:./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.sistemaExperto.test.SistemaExpertoTest
cd bin/mac

stty echo
