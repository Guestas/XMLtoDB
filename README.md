# XML to DB

## Functions
* This app downloads data from URL unzip and parse them. After parsing, it saves some needed data to database.
* If database doesn't exist app will create one in src/main/database with .sql file extension.

## Description
Start app: main file is in main/java/com/mc/MCe/MainApplicationXMLtoDB.java. After starting app it asks if you 
want to update data if they are already in database. (y for update n for just adding without updating existing).

App has basic testing which is in tests/java/com/mc/MCe/MainApplicationXMLtoDBTests.

I have used Spring framework with some other library's all of this is defined in pom.xml. Program build system is Maven.

Tables are connected kod obce is in Kod casti obce. I think it should be connected.