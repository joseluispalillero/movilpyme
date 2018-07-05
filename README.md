# movilpyme
proyecto de evaluación

#librerias
Spring MVC 
Hibernate
Jquery
DataTables
Bootstrap

Se puede visualizar las librerias en el archivo pom como referencia, aunque subi el war el cual ya esta empaquetado todas las librerias

# Configuración
Para la configuración se ocupa el archivo de spring llamado:
dispatcher-servlet.xml

Donde se tiene la siguiente configuración, donde se podra cambiar el username, password y la url para conectarse a la base de datos necesaria de manera local.
```sh
<beans:bean id="myDataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
      <beans:property name="driverClassName" value="com.mysql.jdbc.Driver" />
      <beans:property name="url" value="jdbc:mysql://localhost:3306/movilpyme?zeroDateTimeBehavior=convertToNull" />
      <beans:property name="username" value="root" />
      <beans:property name="password" value="12345678" />
</beans:bean>
```
 
