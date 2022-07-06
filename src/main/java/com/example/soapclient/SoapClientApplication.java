package com.example.soapclient;

import com.example.soapclient.wsdl.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            System.out.println("StatusCode: " + serviceStatus.getStatus() +
                    ", Message: " + serviceStatus.getMessage());
            if (!serviceStatus.getError().isEmpty()){
                serviceStatus.getError().forEach(errorSOAP -> System.out.println(errorSOAP.getMessage()));
            }
            /////////////////////////////////////////////////////////
            System.out.println("--- Add User ---");
            String login2 = "";
            String name2 = "";
            String password2 = "";
            AddUserDetailsResponse addUserDetailsResponse2 = usersClient.addUser(login2, name2, password2, 1L, 2L );

            UserDetails userDetails2 = addUserDetailsResponse2.getUserDetails();
            if (userDetails2 != null) {
                System.out.println(userDetails2.getLogin() + ", "+ userDetails2.getUsername());
            }
            ServiceStatus serviceStatus2 = addUserDetailsResponse2.getServiceStatus();
            System.out.println("StatusCode: " + serviceStatus2.getStatus() +
                    ", Message: " + serviceStatus2.getMessage());
            if (!serviceStatus2.getError().isEmpty()){
                serviceStatus2.getError().forEach(errorSOAP -> System.out.println(errorSOAP.getMessage()));
            }
            /////////////////////////////////////////////////////////
            System.out.println("--- Add User ---");
            String login3 = "";
            String name3 = "";
            String password3 = "qweqweqweqwe";
            AddUserDetailsResponse addUserDetailsResponse3 = usersClient.addUser(login3, name3, password3, 1L, 2L );

            UserDetails userDetails3 = addUserDetailsResponse3.getUserDetails();
            if (userDetails3 != null) {
                System.out.println(userDetails3.getLogin() + ", "+ userDetails3.getUsername());
            }
            ServiceStatus serviceStatus3 = addUserDetailsResponse3.getServiceStatus();
            System.out.println("StatusCode: " + serviceStatus3.getStatus() +
                    ", Message: " + serviceStatus3.getMessage());
            if (!serviceStatus3.getError().isEmpty()){
                serviceStatus3.getError().forEach(errorSOAP -> System.out.println(errorSOAP.getMessage()));
            }
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
            ServiceStatus serviceStatus4 = updateArticleResponse.getServiceStatus();
            System.out.println("StatusCode: " + serviceStatus4.getStatus() +
                    ", Message: " + serviceStatus4.getMessage());
            if (!serviceStatus4.getError().isEmpty()){
                serviceStatus4.getError().forEach(errorSOAP -> System.out.println(errorSOAP.getMessage()));
            }
            //////////////////////////////////////////////////////////
            System.out.println("--- Get all Users ---");
            getUsersResponse = usersClient.getAllUsers();
            getUsersResponse.getUserDetails()
                    .forEach(e -> System.out.println(e.getLogin() + ", "+ e.getUsername()));
            /////////////////////////////////////////////////////////
            System.out.println("--- Delete User ---");
            String login4 = "wer";
            DeleteUserDetailsResponse deleteUserDetailsResponse = usersClient.deleteUser(login4);
            ServiceStatus serviceStatus5 = deleteUserDetailsResponse.getServiceStatus();
            System.out.println("StatusCode: " + serviceStatus5.getStatus() +
                    ", Message: " + serviceStatus5.getMessage());
            //////////////////////////////////////////////////////////
            System.out.println("--- Get all Users ---");
            getUsersResponse = usersClient.getAllUsers();
            getUsersResponse.getUserDetails()
                    .forEach(e -> System.out.println(e.getLogin() + ", "+ e.getUsername()));
            /////////////////////////////////////////////////////////

        };
    }

}
