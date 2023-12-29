If you want locally run this project follow these steps:

- Clone the repository from GitHub: `git clone https://github.com/teract10s/vacancy-parser.git`
- Create a .env file and fill it with the necessary variables (an example of all the necessary variables in the
  .env.sample file)
  - In the variable MYSQLDB_USER, insert login of your database user
  - In the variable MYSQLDB_PASSWORD_USER, insert password of your database user
  - In the variable MYSQLDB_DATABASE_URL, insert url of your database
  - In the variable CSV_FILE_PATH, insert path to file when you want store vacancies data
- If you want work with existing database you have to change `spring.jpa.hibernate.ddl-auto` property in <b>application.properties</b> to `validate`
- Run project
- The project will be launched at the following link: http://localhost:8080
- Also you can use swagger for testing at the following link: http://localhost:8080/swagger-ui/index.html#