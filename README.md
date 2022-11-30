Steps:
1)Create Account:
method:POST
url: http://localhost:8080/register
(saves user data in database > mysql)

2)Login: 
method:POST
url: http://localhost:8080/authenticate
(generates JWT token)

3)Get all images uploaded by the current use
method:GET
headers:Authorization:Bearer JWToken
http://localhost:8080/api/social/v1/users/{userId}/files

4)Post an image in the users own account
method:POST
headers:Authorization:Bearer JWToken
http://localhost:8080/api/social/v1/users/{userId}/files

5)Swagger Url (Api Documentation)
http://localhost:8080/swagger-ui.html