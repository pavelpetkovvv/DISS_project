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

* **GET** /api/v1/messages/conversation/John?username=Frank&password=123

returns the conversation between John and Franks and sets seen = *true*
on the messages with sender = *John*. Messages are returned in the same order
they have been sent.

**example request:**

**GET** /api/v1/messages/conversation/user1?username=user2&password=123

**example response:**
```
[
    {
        "id": "95352464-f0a7-441a-835a-fc23a806d190",
        "content": "Ko sta?",
        "sender": "user1",
        "recipient": "user2",
        "sent": "2022-01-23T12:06:11.209+00:00",
        "seen": true
    },
    {
        "id": "f2f7890c-826c-4911-bd83-41f3fed5f369",
        "content": "Shte izlizame li?",
        "sender": "user1",
        "recipient": "user2",
        "sent": "2022-01-23T12:06:24.329+00:00",
        "seen": true
    },
    {
        "id": "aa2c7297-68ed-4969-892f-fb251a22c8b8",
        "content": "Koga?",
        "sender": "user1",
        "recipient": "user2",
        "sent": "2022-01-23T12:06:42.153+00:00",
        "seen": true
    },
    {
        "id": "2509237b-f020-47ef-9c41-d7989f2387d0",
        "content": "Da",
        "sender": "user2",
        "recipient": "user1",
        "sent": "2022-01-23T12:06:32.679+00:00",
        "seen": true
    },
    {
        "id": "33c26a7e-b211-44e0-9549-a7bcdbb1529f",
        "content": "Shte ti zvunna da se razberem",
        "sender": "user2",
        "recipient": "user1",
        "sent": "2022-01-23T12:07:52.129+00:00",
        "seen": true
    }
]
```

* **GET** /api/v1/messages/contacts?username=John&password=123

returns al users that have sent messages to John or have received message
from John

**example response:**

```
[
    "Karl",
    "Frank"
]
```

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