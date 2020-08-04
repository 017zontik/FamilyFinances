# Groshiky
I’ve finished the Java developer course and created this project.I’m going to continue work on this project and make it better and more comfortable.
This project is created to analyze and regulation the family budget.
User can add accounts, create transactions of incoming or spent money and see the final balance.
I also have plans to add an opportunity to use different currencies and reports.

Install MySQL Server and add User with create DB permissions.
Add this User to the application.properties. 

Here an example:
spring.datasource.url=jdbc:mysql://localhost:3306/groshiky?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=171986

Navigate to root directory and run the next sequence of commands one by one:
mvn clean package
mvn spring-boot:run

