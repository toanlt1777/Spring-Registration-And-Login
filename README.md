# Spring Registration And Login
### Tech 
- Spring Boot
- Spring Security
- Spring JPA
- LomBok
- MySQL
- Java Mail Sender

### Note
I've tested the mail sender with Maildev server node 

``$ npm install -g maildev``

``$ maildev``

Reference: https://github.com/maildev/maildev

Registration cUrl:

```curl --location --request POST 'http://localhost:8080/api/v1/registration' \
--header 'Content-Type: application/json' \
--data-raw '{
    "lastName": "toan",
    "firstName": "le",
    "email": "toanlt1777@gmail.com",
    "username": "toanle",
    "password": "1234567"
}'
```

Confirmation cUrl example: 

```
curl --location --request GET 'http://localhost:8080/api/v1/registration/confirm?token=YourToken'
```
