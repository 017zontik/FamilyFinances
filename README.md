# Groshiky
I’ve finished the Java developer course and created this project.I’m going to continue work on this project and make it better and more comfortable.
This project is created to analyze and regulation the family budget.
User can add accounts, create transactions of incoming or spent money and see the final balance.
I also have plans to add an opportunity to use different currencies and reports.

Option 1:
1.1. Install MySQL Server.
1.2. Create user "root" with password "171986"
1.3. Create database with name "groshiky" and grand all permissions for user from previous point

Option 2:
2.1. Clone current repository
2.2. Create databse with name "groshiky"
2.3. Change spring.datasource.username and spring.datasource.password with user data that has a red/write (better full) access to created database

Here an example:
spring.datasource.url=jdbc:mysql://localhost:3306/groshiky?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=171986

Option 3:
3.1. build project with maven
3.2 run project with dev profile
(mvn spring-boot:run -Drun.profiles=dev
java -jar -Dspring.profiles.active=dev <projectName>.jar)



