# SimpleUser
Simple signup and signin with spring boot and spring security json web token

## Featured :
- Spring Boot 
- Spring Security
- Spring Data JPA
- JWT Authentication & Authorization
- JWT Token
- Refresh Token

## Roles
- USER
- MODERATOR
- ADMIN 
- ROOT
 
## API
Methods | Url | Action |
--- | --- | --- |
| POST | /api/auth/signup | signup new account |
| POST | /api/auth/signin | login an account |
| POST | /api/auth/refreshtoken | get new token from refresh token |
| GET | /api/test/all | public access content |
| GET | /api/test/user | only user access content |
| GET | /api/test/mod | only moderator access content |
| GET | /api/test/admin | only admin access content |
| GET | /api/test/root | only root access content |

## Format JSON
### Signup
```
{
    "nama": "{name}",
    "email": "{email}",
    "username": "{username}",
    "password": "{password}",
    "roles": [
        {
        "id": {id_roles}
        }
    ]
}
```

### Signin
```
{
    "username": "{username}",
    "password": "{password}",
}
```

### Refresh Token
```
{
    "refreshToken": "{refreshtoken}"
}
```

## Database Relational
<img src="https://user-images.githubusercontent.com/58913447/178635841-a22bc244-9cd1-439d-9462-254e84eeb4e0.jpg" width="838" height="500"/>
