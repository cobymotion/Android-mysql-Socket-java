# Android-mysql-Socket-java
Example of connection between Android App and Java App by socket technologies 

The project contains three folder: 
- **dump_db** : Contains one file with the database structure for example. The database has "dbcontacto" name. Only contains one contact table with next structure: 
````
CREATE TABLE `contacto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
````
- **practica007** : Project in Android Studio with support for Ice Cream Sandwitch Version. Permission for Internet connection and Status Network. AsynTask controls sockets request. Port 6007 
- **Server** : Project in Netbeans, it's support for Java 8.0. Implemets Maven and Mysql Connector 6.0.5. Server Socket in the port 6007. Connection for MYSQL 5.6. 


## REQUERIMENTS 

- Server MYSQL 5.6 
- Netbeans 8.0.2
- Android Studio 2.3.3 

## Testing enviroment

- Load dump 
- Change connections data in Connection.java 
```
    private String user="MyUserMysql"; 
    private String pass="MyPassword"; 
    private String databaseName="databasename"; 
    private String host="Direction server"; 
```
- Change connections data ServerSocket in ServerProcess.java (Optional)

- Connection configuration in Android : ClienteSocket.java

```
private static final String host = "192.168.1.10";
private static final int port = 6007;
```
>> Note: This project is part of a example, so it will return strings in a format separated by symbols 
