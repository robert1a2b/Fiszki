# Fiszki - language learning app 
## Java, Spring Boot, Maven, MySQL, JPA, Rest API 
Simple CRUD Restful app to learn single words in different languages 

Main features:
* returning random word-translation sets in polish, english, german and spanish
* returning translation of typed word in polish, english, german and spanish
* creating sets of single word translations to memory game
* adding or deleting sets of word translations from database

## Technologies
Project is created with:
* Java 21
* Spring Boot 3.4.3
* Maven 4.0.0
* MySql 8.0

## Build and run the app using maven
To build app:  `mvn clean install`

To run app: `mvn spring-boot:run`

App is running at: `http://localhost:8080/`

## Rest APIs

### Set of all words from database in pl, en, de, es
`GET /api/words`

### Set of random word in pl, en, de, es
`GET /api/word`

### Set of all words from database in pl, en, de, es
GET /api/translate/{pl_word}

GET /api/pl ; GET /api/en ; GET /api/de ; GET /api/es

PUT /api/

DELETE /api/notes/{noteId}

