package com.example.soapclient;

import com.example.soapclient.wsdl.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class UsersClient extends WebServiceGatewaySupport {

    public GetUsersResponse getAllUsers() {
        GetUsersRequest request = new GetUsersRequest();
        GetUsersResponse response = (GetUsersResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:8080/service/getUsersRequest"));
        return response;
    }

    public GetUserDetailsByLoginResponse getUser(String login) {
        GetUserDetailsByLoginRequest request = new GetUserDetailsByLoginRequest();
        request.setLogin(login);
        GetUserDetailsByLoginResponse response = (GetUserDetailsByLoginResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:8080/service/getUserDetailsByLoginRequest"));
        return response;
    }

    public DeleteUserDetailsResponse deleteUser(String login) {
        DeleteUserDetailsRequest request = new DeleteUserDetailsRequest();
        request.setLogin(login);
        DeleteUserDetailsResponse response = (DeleteUserDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:8080/service/deleteUserDetailsRequest"));
        return response;
    }

    public AddUserDetailsResponse addUser(String login, String name, String password, Long... longs) {
        AddUserDetailsRequest request = new AddUserDetailsRequest();
        request.setLogin(login);
        request.setName(name);
        request.setPassword(password);
        if (longs.length != 0){
            for (Long l:longs) {
                request.getRolesIds().add(l);
            }
        }
        AddUserDetailsResponse response = (AddUserDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:8080/service/addUserDetailsRequest"));
        return response;
    }
    public UpdateUserDetailsResponse updateUser(UserDetails userDetails, Long... longs) {
        UpdateUserDetailsRequest request = new UpdateUserDetailsRequest();
        request.setUserDetails(userDetails);
        if (longs.length != 0){
            for (Long l:longs) {
                request.getRolesIds().add(l);
            }
        }
        UpdateUserDetailsResponse response = (UpdateUserDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:8080/service/updateUserDetailsRequest"));
        return response;
    }

}