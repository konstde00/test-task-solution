## General info
Hello, my name is Kostya.
This is my solution of your test task.
Using it, you can join two or more JSON data sources (urls, .json or .csv files).
To solve it I have used Hibernate and Spring frameworks
(while solving it I used SOLID and TDD principles)
To run this project you can use Apache Tomcat web server (I used version 9.0.54) (configure this project as web application) or run it locally by using "main" method in
<a href="https://github.com/konstde00/test-task-solution/blob/main/src/main/java/Application.java">Application</a> class.

## Functionality

*You can add your links (with protocole name) or files in a <a href="https://github.com/konstde00/test-tast-solution/blob/main/src/main/resources/dataSources.csv">dataSources</a> file in format:

```

fileName="YOUR FILE NAME;
urlName="YOUR URL;

```

like

```

fileName="input.json";

```
or
```

urlName="https://test-api-heroku.herokuapp.com/testLink/1";

```

*Also ou can input a single data-source path (url or name of the file located in <a href="https://github.com/konstde00/test-tast-solution/tree/main/src/main/resources">resources</a> directory)

*Moreover, you can run this project as a web app and use "/giveInput" to send url or data-source path interactively

All products from input data sources will be saved into remote DB (mapped using Hibernate), it's properties are already 
added into <a href="https://github.com/konstde00/test-tast-solution/blob/main/src/main/resources/application.properties">application.properties</a> file (you can add your own DB by adding your properties in this file)
In order to avoid duplications in DB I have created UniqueFieldDuplicationException, that will be thrown if in input 
will be two or more objects with different names and same ID.
After working out all sources, that you have mentioned, you will receive report of all products and their amounts in console.

To test it I have created own API, you can also try to use it. It is available by link 
<a href="https://test-api-heroku.herokuapp.com/testLink/1">api link</a>
(its possible to generate as many test links as we need by setting request param, up to BigInteger size)
or look through it source code on its repository
<a href="https://github.com/konstde00/api-test-heroku">api repo</a>


## Structure

In this project I used N-tier (multi-tier) architecture concept and divide all app into three layers:

```
*Presentation level
*Service level
*Data access (dao) level
```
Each of them has a specific responsibility, logic of working out an input in different layers is separated.



