package com.example.soapclient;

import com.example.soapclient.wsdl.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Collectors;

@SpringBootApplication
public class SoapClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapClientApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(UsersClient usersClient) {
        return args -> {

            /////////////////////////////////////////////////////////
            System.out.println("--- Add User ---");
            String login = "wer";
            String name = "Spring";
            String password = "S2p3ring";
            AddUserDetailsResponse addUserDetailsResponse = usersClient.addUser(login, name, password, 1L, 2L );
            UserDetails userDetails = addUserDetailsResponse.getUserDetails();
            if (userDetails != null) {
                System.out.println(userDetails.getLogin() + ", "+ userDetails.getUsername());
            }
            ServiceStatus serviceStatus = addUserDetailsResponse.getServiceStatus();
            System.out.println("StatusCode: " + serviceStatus.getStatusCode() +
                    ", Message: " + serviceStatus.getMessage());
            /////////////////////////////////////////////////////////
            System.out.println("--- Get all Users ---");
            GetUsersResponse getUsersResponse = usersClient.getAllUsers();
            getUsersResponse.getUserDetails()
                    .forEach(e -> System.out.println(e.getLogin() + ", "+ e.getUsername()));
            /////////////////////////////////////////////////////////
            System.out.println("--- Get Users by Login ---");
            GetUserDetailsByLoginResponse userDetailsByLoginResponse = usersClient.getUser("1");
            UserDetails userDetailsWithRoles = userDetailsByLoginResponse.getUserDetails();
            System.out.println(userDetailsWithRoles.getLogin() + ", "+ userDetailsWithRoles.getUsername());
            System.out.println("Roles");
            userDetailsWithRoles.getRoles().forEach(e -> System.out.println(e.getId() + ", " + e.getName()));
            /////////////////////////////////////////////////////////
            System.out.println("--- Update User ---");
            UserDetails userAllDetailsUpd  = new UserDetails();
            userAllDetailsUpd.setLogin("wer");
            userAllDetailsUpd.setUsername("NewSpring");
            userAllDetailsUpd.setPassword("1Q2W23asdasd");
            UpdateUserDetailsResponse updateArticleResponse = usersClient.updateUser(userAllDetailsUpd, 2L);
            ServiceStatus serviceStatus2 = updateArticleResponse.getServiceStatus();
            System.out.println("StatusCode: " + serviceStatus2.getStatusCode() +
                    ", Message: " + serviceStatus2.getMessage());
            //////////////////////////////////////////////////////////
            System.out.println("--- Get all Users ---");
            getUsersResponse = usersClient.getAllUsers();
            getUsersResponse.getUserDetails()
                    .forEach(e -> System.out.println(e.getLogin() + ", "+ e.getUsername()));
            /////////////////////////////////////////////////////////
            System.out.println("--- Delete User ---");
            String login2 = "wer";
            DeleteUserDetailsResponse deleteUserDetailsResponse = usersClient.deleteUser(login2);
            ServiceStatus serviceStatus3 = deleteUserDetailsResponse.getServiceStatus();
            System.out.println("StatusCode: " + serviceStatus3.getStatusCode() +
                    ", Message: " + serviceStatus3.getMessage());
            //////////////////////////////////////////////////////////
            System.out.println("--- Get all Users ---");
            getUsersResponse = usersClient.getAllUsers();
            getUsersResponse.getUserDetails()
                    .forEach(e -> System.out.println(e.getLogin() + ", "+ e.getUsername()));
            /////////////////////////////////////////////////////////

        };
    }

}
