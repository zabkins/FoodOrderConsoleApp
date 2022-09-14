# FoodOrderApp
Simple multi-module console app imitating restaurant's ordering process. It lets creating new orders until needed and stores them in H2 in-memory database.<br> 
It sets initial, neccessary DB data using app sub-module's DataLoader class. If needed, food available for user to choose can be easily edited or expanded<br> in the DataLoader class.
All console logging is disabled for visibility.<br> 
Database can be accessed at localhost:8080/h2-console . (JDBC URL: jdbc:h2:mem:test, Username: admin, password - none)<br> <br>

Technologies used :<br>
 Gradle, JUnit 5, Mockito, SpringBoot, Spring Data, Hibernate, H2 database<br><br>
 
 HOW TO RUN APPLICATION:<br>
  1. Through IDE<br>
   - Open project in IDE<br>
   - Run main method in app sub-module's Application class<br>
  2. Through executable jar file<br>
   - Go to main repository's folder<br>
   - In IDE or in terminal, execute command 'gradle clean build' -> folder build/libs will be created in main repository's folder<br>
   - Open terminal and execute command 'java -jar FoodOrderApp-1.0-SNAPSHOT.jar'. Application will run in the terminal<br>
