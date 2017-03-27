Tech-Stack Used to build application:

Project structure was generated using mvn archtype:generate and then choosing option wildfly-javaee7-webapp-ear-archetype

Wildfly(JBoss) Application Server - Version 10.1.0.Final
MySQL Server : 5.6.34-log MySQL Community Server (GPL)
JAX-WS(Soap) : Apache CXF (Default runtime for wildfly)
JAX-RS(Rest) : RestEasy ( Default runtime for wildfly)
CDI : DI Framework used for main app
Spring : DI Framework used for Unit Testing
Persistence : JPA provided by Hibernate
JMS : ActiveMQ (Part of wildfly AS)
JUnit : Unit Test Framework
Mockito : Mocking framework
Maven : for build integration.
SoapUI : For testing Soap Services.

Before build and deployment of the application:
1) Install & start MySQL database
2) Create a datasource with jndi name java:/jdbc/mysqlds in wildfly (modify standalone-full.xml to add a data source)
3) Run the db.sql srcipt present in the sql directory in ebj module
4) Create queue "queue/paramsQueue3". 

Security Considerations:

1. Both SOAP and REST services are secured using application level security.
2. Security services are provided by non-invasive http interceptors.
3. Cleints are required to send username and encrypted password as http headers(http headers should be "Username" = unicouser and "Password" = enrypted value of (P@ssw0rd!)).
4. Passwords are encrypted/decrypted using public/private key mechanism.
5. Public key has been uploaded to the github.
6. Application can be further secured by adding Container managed security and enabling SSL on the server.

MultiUser Environment:

Applications scales well in multi user environment. 
Each rest call is handled by a new object, while methods to handle Soap calls has been synchronised.

Username/Password (to be set in http headers)
unicouser/P@ssw0rd!
password must be encrypted and Base64 encoded before it is sent to the server.

Testing:
Application was tested using custom build java rest client/Postman and Soap UI.

URLS:
Rest Push Service URL:
http://localhost:8080/EnterpriseApp-web/rest/gcd/push/16/20

SOAP Service WSDL
http://localhost:8080/EnterpriseApp-web/GCDService?wsdl

SOAP Service:
http://localhost:8080/EnterpriseApp-web/GCDService
