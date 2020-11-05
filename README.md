# Street-Smart
## A scheduling and routing web service tailored to the commuter.
### Street-Smart is designed for those who have a schedule and places to be. Street-Smart allows for users to input their own meetings, classes, and appointments, along with their respective locations, to create a schedule. Users receive notifications on the best times to start their commute based on the times and places listed in their schedule.
---
This web service is fronted by Vue.js and backed by Spring Boot.  
To run the application locally:  
1. Download the source code.  
2. `cd` into the root directory and run `mvn clean install`. This will:  
    * Run `npm run build` for the frontend directory
    * Copy the frontend resources *static/*, *index.html*, and *favicon.ico* into *backend/src/main/resources/public*
    * Package the backend into a jar file that contains the Vue resources
    * Create several directories such as *target* and *bin*
3. Run the backend application either with  
    `java -jar backend/target/backend-0.0.1-SNAPSHOT.jar` **OR**  
    `java backend/src/main/com/dakotawild/street-smart/StreetSmart` **OR**  
    `mvn --projects backend spring-boot:run`
4. Go to **localhost:8081** to see the web service.