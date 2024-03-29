<a href="https://www.youtube.com/c/ByteMauritanie?sub_confirmation=1">
<img src="src/main/resources/img/byte-logo.png" alt="Byte logo" height="200">
<img src="src/main/resources/img/youtube.png" alt="Byte logo" height="200">
</a>

# Spring Boot MenuItem CRUD REST API with PostgreSQL

<img src="src/main/resources/img/springboot-logo.png" alt="Byte logo" height="150">
<img src="src/main/resources/img/postgresql-logo.png" alt="Byte logo" height="150">


This is a simple Spring Boot REST API for managing menu item data. The application uses PostgreSQL
as
the database

## Getting Started

These instructions will help you set up and run the project on your local machine.

### Prerequisites

- Java Development Kit (JDK) installed (version 17 or later)
- PostgreSQL installed and running
    - *you can pick another database, but you'll have to do the
      necessary changes in `pom.xml` and `application.properties`
- Your favorite IDE (preferably IntelliJ) installed and running

### Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/abdelhalimjean/Bean-and-Berry-Spring-Boot-REST-API
    ```
2. Open the project folder in your favorite IDE
3. Open `src/main/resources/application.properties` and update the PostgreSQL connection properties
   if needed.
4. Ensure that the PostgreSQL server is running, and create the database `BeanAndBerry` and a user
   and
   password as mentioned in
   the `application.properties` file.
5. After running `SpringBootRestApiApplication.java` the application will be accessible
   at http://localhost:8080/ (you
   will see a "Whitelabel Error Page" don't worry, that's what's supposed to happen, it means
   everything is working)
6. After running the application, the tables will be automatically created, only then can you run
   the data initialization scripts.

## Data Initialization

The project includes an initial data script at `src/main/resources/insert_menu_items.sql`.

You can execute this scripts in your database to have some data to play with, otherwise you'll have
to insert the data yourself.

## API Endpoints

### Menu

#### Get All MenuItems

    URL: /api/menu/all
    Method: GET
    Description: Retrieve a list of all menu items.

#### Search MenuItems By Name

    URL: /api/menu/search?keyword=chocolate&category=cake
    Method: GET
    Description: Retrieve a list of menu items based on the keyword (name or ingredient) and category.

#### Get MenuItem by ID

    URL: /api/menu/{id}
    Method: GET
    Description: Retrieve details of a specific menu item by its ID.

#### Add a new MenuItem

    URL: /api/menu
    Method: POST
    Description: Add a new menu item to the database.

#### Update a MenuItem

    URL: /api/menu/{id}
    Method: PUT
    Description: Update details of a specific menu item by its ID.

#### Delete a MenuItem

    URL: /api/menu/{id}
    Method: DELETE
    Description: Delete a specific menu item by its ID.

#### Add a list of MenuItem in bulk

    URL: /api/menu/bulk
    Method: POST
    Description: Add a new menu item to the database.

## Author

Abdelhalim Jean

- https://www.linkedin.com/in/abdelhalimjean/
- https://www.youtube.com/c/ByteMauritanie
- https://www.facebook.com/bytemauritanie/

