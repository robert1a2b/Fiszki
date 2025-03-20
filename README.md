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
* H2 database
* Lombok

## Build and run the app using maven
To build app:  `mvn clean install`

To run app: `mvn spring-boot:run`

App is running at: `http://localhost:8080/`

H2 database console at: `http://localhost:8080/h2-console`
* login: sa
* password: password


## Rest APIs

### GET
#### Set of all words translations from database in pl, en, de, es : `/words`

#### Set of random word translations in pl, en, de, es: `/word`

#### Translation of typed pl word to en, de, es : `/translate/{pl_word}`

#### Random pl word : `/pl` 

##### Translation of word from '/pl' to en, de, es:  `/pl/en` `/pl/de` `/pl/es`

#### Random en word : `/en` 
* ##### Translation of word from '/en' to pl : `/en/pl`

#### Random de word : `/de` 
* ##### Translation of word from '/de' pl: `/de/pl`

#### Random es word : `/es` 
* ##### Translation of word from '/es' pl : `/es/pl`

#### Map of pl-en word pairs to create memo game : `/memotest/en`

#### Map of pl-de word pairs to create memo game : `/memotest/de`

#### Map of pl-es word pairs to create memo game : `/memotest/es`

### PUT 
#### Adding set of word in pl, en, de, es to database : `/words`

### DELETE 
#### Deleting typed pl_word with translations from database : `/words/{pl_word}`

