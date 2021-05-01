# songr

Songr is a web app that build using [spring-boot](https://spring.io/) framework in java

once you cloned the repo you need to follow the following steps in order to run the app:

* first make sure your postgres server is running, see this [link](https://www.postgresql.org/docs/9.1/server-start.html) to start the server
* once postgres server is running type this command in the terminal `psql` to open the server
* you need tp create database, use this command `CREATE DATABASE <name>` , replace the *name* with any name for your database and remember it to use it later in code

* open the project using [IntelliJ] (https://www.jetbrains.com/idea/download/) 
* navigate to folder [application.properties](src/main/resources/application.properties)

it should look like this

```java
spring.datasource.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/<database name>
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.jpa.hibernate.ddl-auto=update

```
1. change *database name* in the url to the database name you made in the previous steps
2. change the *username* to your username and the password to your password

and keep the rest as is

* navigate to [SongrApplication.java](src/main/java/com/example/songr/SongrApplication.java) and run the main method

* go to your favorite browser and past this link `localhost:8080`

now the website is open with a splash screen, click **continue** to continue to the web app

the home page is the `/album` route, where you can see all the albums, *in your case it is empty*

* to add an album visit this route `localhost:8080/add-album` or you can click on add album from the nav menu.

fill the form and click **Add Album** so the album is added to the database and now it appears in the `localhost:8080/album` route


* to add a song visit this route `localhost:8080/add-song` or you can click on add song from the nav menu.

fill the form and click **Add Song** so the song is added to the database and now it appears in the `localhost:8080/songs` route

to see the album details click on details of any album, to update or delete click the buttons you see in the details page where you can see the songs that belongs to the album as well, you can also see thier details, update them or delete them.

**In case of any error, an error page will be shown in the browser**

to see how the error page looks like visit this route `localhost:8080/error`.
