# About #

I am trying to convert old work(generic-table-struts1) to a Maven project, and I plan to do some improvement later.

# Note #

You may ask "how to use eclipse create a **Dynamic Web Project** with **Maven Support** and can still use **Run As -> Run on Server**?"

* This is my solution, see *generic-table-struts1-maven* project as an example which was created by using STS (Eclipse)
 * first the project was created with eclipse using Maven Archetype **maven-archetype-webapp**
 * second I changed the **Project Facets** configuration from Dynamic Web Module **2.3** to Dynamic Web Module **2.5**
 * third I updated web.xml to use 2.5 schema


>		<?xml version="1.0" encoding="UTF-8"?>
>		<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee"
>			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>			xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">
>		
>			<display-name>Archetype Created Web Application</display-name>
>			<welcome-file-list>
>				<welcome-file>index.jsp</welcome-file>
>			</welcome-file-list>
>		</web-app>

* then it's OK to run on server
