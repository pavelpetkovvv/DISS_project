# DISS_project
Project for DISS (design and integration of software systems) course at uni


### How to run project

* mvn docker:start - to start database as Docker container

* mvn spring-boot:run

If you start database with *docker:start* you can use default settings. If
you want to use other database the following environment variables have to be set:

* DB_HOST
* DB_PORT
* DB_NAME
* DB_USERNAME
* DB_PASSWORD


### API endpoints

---

***Users***

* **POST** /api/v1/users with body:

```
{
    "username": "Frank",
    "password": "123"
}
```

creates a user with username *Frank* and password *123*. Returns *400 Bad
Request* when username already exists

* **DELETE** /api/v1/users?username=Frank&password=123

deletes user with username = *Frank* and password = *123*

---

***Messages***

* **POST** /api/v1/messages?username=Frank&password=123 with body:

```
{
    "content": "Hello, friend",
    "recipient": "John"
}
```

sends message from *Frank* to *John*

* **GET** /api/v1/messages/received?username=Frank&password=123

returns all messages user with username = *Frank* has received

example:

```
[
    {
        "id": "97903d34-d123-4ea6-aa76-9dfd9a0c59c8",
        "content": "Hello, friend",
        "sender": "John",
        "recipient": "Frank",
        "seen": true
    },
    {
        "id": "1b933631-f691-42b9-8092-ede110364d7f",
        "content": "How are you?",
        "sender": "John",
        "recipient": "Frank",
        "seen": false
    },
    {
        "id": "1b933631-f691-42b9-8092-ede110364d7f",
        "content": "Whats up?",
        "sender": "Steven",
        "recipient": "Frank",
        "seen": false
    }
]
```

**seen** is returned as *false* when the user has not read the message yet

* **GET** /api/v1/messages/received/John?username=Frank&password=123

returns all messages user with username = *Frank* has received from user
with username = *Steven*

* **GET** /api/v1/messages/sent?username=Frank&password=123

returns all messages user with username = *Frank* has sent

* **GET** /api/v1/messages/sent/John?username=Frank&password=123

returns all messages user with username = *Frank* has sent to user
with username = *John*

All Messages endpoints require authorization and return *403 Forbidden* when
username or password are not correct.

---

***Authorization***

* GET /api/v1/auth?username=John&password=123

returns if authorization for user with username = *John* and password = *123*
was successful.

Example:

When successful:
```
{
    "status": 200,
    "message": "Authorization successful"
}
```

When unsuccessful:

```
{
    "status": 403,
    "error": "Incorrect username or password"
}
```

---