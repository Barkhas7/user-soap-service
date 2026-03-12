package com.barkhas.soap;
import com.barkhas.soap.service.AuthServiceImpl;
import com.barkhas.soap.util.DatabaseInitializer;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import jakarta.xml.ws.Endpoint;
import java.net.InetSocketAddress;

public class SoapServerPublisher {

    public static void main(String[] args) {
        try {
            DatabaseInitializer.initialize();

            HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
            HttpContext context = server.createContext("/auth");
            context.getFilters().add(new CorsFilter());

            Endpoint endpoint = Endpoint.create(new AuthServiceImpl());
            endpoint.publish(context);

            server.start();

            System.out.println("SOAP Service is running at: http://localhost:8081/auth?wsdl");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}