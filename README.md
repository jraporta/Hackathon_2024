## Back-end development skill test for the "2024 Saló de l'Ocupació" hackathon.

This project involves the development of an API to manage users and activities in a web application.

## Core Technologies
* **Spring Boot** - Java framework.
* **MySQL** - database for persistent storage.
* **Gradle** - build tool and dependency management.

## Executing the project locally
1. Prerequisites: have **JDK 21**, **MySQL** and **Gradle** installed.
2. Clone github repo
3. MySQL sSetup
    * Edit the `application.properties` file to update the `user` and `password` fields according to your MySQL installation.
    * Create a new database using: `CREATE DATABASE hackathon;`
4. Open a terminal in the project directory and run the project:
    * For Linux/macOS: `./gradlew bootRun`
    * For Windows: `gradlew.bat bootRun`

## API reference endpoints

### User Management
* Register a new user:
`POST /appActivitats/user`
* Update user infomation:
`PUT /appActivitats/users/:id`
* Get user information:
`GET /appActivitats/users/:id`
* Delete a user:
`DELETE /appActivitats/users/:id`

### Activity Management
* Create a new activity:
`POST /appActivitats/activity`
* Get activity information:
`GET /appActivitats/activities/:id`
* Enroll a user in an activity:
`POST /appActivitats/activities/:id/users/:user_id`

### Activity Import/Export
* Export all activities to JSON format:
`GET /appActivitats/activities`
* Import activities from a JSON file:
`POST /appActivitats/activities` 

## JSON Format

### User
``` 
{
    "firstName": "María",
    "lastName": "Pérez",
    "age": 28,
    "email": "maria@mail.com"
}
```

### Activity
```
{
    "name": "Dibuixa Manga",
    "scheduledDateTime": "2024-10-09T18:00:00",
    "capacity": 15,
    "description": "Aprèn a dibuixar els teus personatges favorits."
}
```
