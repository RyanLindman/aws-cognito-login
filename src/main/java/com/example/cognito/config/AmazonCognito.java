package com.example.cognito.config;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

public class AmazonCognito {

    public static CognitoIdentityProviderClient getCognitoClient () {


        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();


        return CognitoIdentityProviderClient.builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(credentialsProvider)
                .build();


    }

    public static boolean revokeToken(CognitoIdentityProviderClient cognitoClient, String clientId, String clientSecret, String token) {
        try {
            var request = RevokeTokenRequest.builder()
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .token(token)
                    .build();

            cognitoClient.revokeToken(request);

            return true;

        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }

        return false;
    }

    public AdminDeleteUserRequest adminDeleteUserRequest(String userPoolId, String username) {


        return AdminDeleteUserRequest.builder()
                .userPoolId(userPoolId)
                .username(username)
                .build();
        }



}






