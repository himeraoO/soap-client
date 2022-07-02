package com.example.soapclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WSConfigClient {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.soapclient.wsdl");
        return marshaller;
    }
    @Bean
    public UsersClient usersClient(Jaxb2Marshaller marshaller) {
        UsersClient client = new UsersClient();
        client.setDefaultUri("http://localhost:8080/service/usersWsdl.wsdl");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
