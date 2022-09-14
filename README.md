# FoodOrderApp
Simple multi-module console app imitating restaurant's ordering process. It lets creating new orders until needed and stores them in H2 in-memory database. 
It sets initial, neccessary DB data using app sub-module's DataLoader class. If needed, food available for user to choose can be easily edited or expanded in the DataLoader class.
All console logging is disabled for visibility. 
Database can be accessed at localhost:8080/h2-console . (JDBC URL: jdbc:h2:mem:test, Username: admin, password - none) 

Technologies used :
 Gradle, JUnit 5, Mockito, SpringBoot, Spring Data, Hibernate, H2 database
 
 HOW TO RUN APPLICATION:
  I. Through IDE
    1.Open project in IDE
    2.Run main method in app sub-module's Application class
  II. Through executable jar file
    1.Go to main repository's folder
    2.In IDE or in terminal, execute command 'gradle clean build' -> folder build/libs will be created in main repository's folder
    3.Open terminal and execute command 'java -jar FoodOrderApp-1.0-SNAPSHOT.jar'. Application will run in the terminal
