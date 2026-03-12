# user-soap-service

SOA Lab 06-ийн SOAP Authentication Service.

## Товч тайлбар
Энэхүү service нь хэрэглэгчийн бүртгэл, нэвтрэлт, token шалгалтыг хариуцна.

## Ашигласан технологи
- Java 17
- Maven
- JAX-WS / SOAP
- SQLite
- JDBC

## Үндсэн боломжууд
- `registerUser(username, password)`
- `loginUser(username, password)`
- `validateToken(token)`
- `getUserIdByToken(token)`

## Service URL
- SOAP Endpoint: `http://localhost:8081/auth`
- WSDL: `http://localhost:8081/auth?wsdl`

## Database
SQLite database ашигладаг.

Үндсэн хүснэгт:
- `users`

## Ажиллуулах заавар
1. Project-оо Eclipse эсвэл өөр IDE дээр нээнэ.
2. Maven dependencies татагдсан эсэхийг шалгана.
3. `SoapServerPublisher.java` файлыг ажиллуулна.
4. Browser дээр `http://localhost:8081/auth?wsdl` гэж шалгаж болно.

## SOAP Methods

### registerUser
Шинэ хэрэглэгч бүртгэнэ.

### loginUser
Хэрэглэгчийг нэвтрүүлж token буцаана.

### validateToken
Token хүчинтэй эсэхийг шалгана.

### getUserIdByToken
Token-оос хэрэглэгчийн ID-г олно.

## Тайлбар
Энэ service нь frontend болон JSON service-д authentication service хэлбэрээр ашиглагдана.
