The education java project.

The project contains spring boot + angular (CRUD).
When deploying on a tomcat, rename the war archive to base.war.
The java machine must be started in UTF-8 mode.
The java machine needs to be run in UTF-8 mode, so here is an 
example of a script to run a tomcat application in windows.

Example of a script setenv.bat to start a tomcat:

  set JAVA_OPTS=-server -Dfile.encoding=UTF-8
  catalina.bat start


