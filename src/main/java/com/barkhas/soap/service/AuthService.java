package com.barkhas.soap.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface AuthService {

    @WebMethod
    String registerUser(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password
    );
    
    @WebMethod
    int getUserIdByToken(@WebParam(name = "token") String token);

    @WebMethod
    String loginUser(
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password
    );

    @WebMethod
    boolean validateToken(
            @WebParam(name = "token") String token
    );
}