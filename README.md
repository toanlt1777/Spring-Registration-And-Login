# Spring Registration And Login
### Tech 
- Spring Boot
- Spring Security
- Spring JPA
- LomBok
- MySQL
- JWT
- Java Mail Sender

## Registration
### Note
I've tested the mail sender with Maildev server node 

![Capture](https://user-images.githubusercontent.com/33459024/116781880-06cd0e80-aab0-11eb-885a-a48438177d8c.PNG)


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

## LOGIN

> You must need one provider (register before) to Authentication user

LOGIN cUrl:
```
curl --location --request POST 'http://localhost:8080/api/v1/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=1F3D52BDBE84345D46A8BBAEFD918AFE' \
--data-raw '{
    "username": "toanle",
    "password" : "1234567"
}'
```
![Capture1](https://user-images.githubusercontent.com/33459024/116791323-aa83e200-aae3-11eb-9740-9c5a0bf1cf95.PNG)

Next, use token to access resource home page

home cUrl:
```
curl --location --request GET 'http://localhost:8080/api/v1/home' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b2FubGUyIiwiaWF0IjoxNjE5ODkxODQxLCJleHAiOjE2MjA0OTY2NDF9.IXF4JcDCTWSXmYMPOGylhfztOIYjTt21Bd9W1WwBdRrJqPI3vM2WdDiVg8kSJkhH8AgiFsTfwKot5fKxF2Hqtw' \
--header 'Cookie: JSESSIONID=1F3D52BDBE84345D46A8BBAEFD918AFE'

```
### Home Page
![Capture2](https://user-images.githubusercontent.com/33459024/116791325-ac4da580-aae3-11eb-8fe7-deba475980f4.PNG)

### Admin Page:

``Token User (Forbidden)``

![Capture4](https://user-images.githubusercontent.com/33459024/116791358-ed45ba00-aae3-11eb-9ebb-a9a2d4fd972b.PNG)

``Token Admin``

![Capture3](https://user-images.githubusercontent.com/33459024/116791327-ace63c00-aae3-11eb-9c99-41f695dc3cce.PNG)


### Note
> When regiser, set RoleUser to Admin

> You must role Admin to access */api/v1/admin

> No invalidate token when logged out
