# Portfolio Application
Spring Boot + Thymeleaf app for generating developer job portfolio. Provides users the ability to register on the site, 
create a public developer profile with their relevant skills and add details, and links to their sample projects.

## Requirements

- JDK 17
- Maven 3

## Running the application locally
**Update application.properties with your postgresql database credentials.**

Remove test data generation code in PortfolioApplication.java if you don't need test data.
```shell
mvn spring-boot:run
```

## Notes
Homepage has a link to a sample portfolio with display name "John". Updated/remove this link if this portfolio doesn't exist.

## Sample Images
![Index](https://user-images.githubusercontent.com/8316955/228609163-a47e498f-33ea-4896-b2a2-d00b2e9ffa3e.JPG)
![EditProfile](https://user-images.githubusercontent.com/8316955/229135633-e395148a-c764-4b1f-bbba-a1765bde0243.JPG)
![SampleProfileUpdated](https://user-images.githubusercontent.com/8316955/229135635-ef4c352b-20a9-4b5d-8564-d9a88096624f.JPG)
![CreateNewProject](https://user-images.githubusercontent.com/8316955/229135636-e20b0b19-c704-4129-a299-c1d07a14da1b.JPG)
