package com.spotify.oauth2.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.propertyReader;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class TokenManager {

    public static String renewToken() throws IOException {

        HashMap<String, String> formParams = new HashMap<String, String>();

        formParams.put("client_id", propertyReader.fetchPropertyData("client_id"));
        formParams.put("client_secret", propertyReader.fetchPropertyData("client_secret"));
        formParams.put("refresh_token", propertyReader.fetchPropertyData("refresh_token"));
        formParams.put("grant_type", propertyReader.fetchPropertyData("grant_type"));


        Response response = given().
                baseUri("https://accounts.spotify.com").
                contentType(ContentType.URLENC).
                formParams(formParams).
                when().
                post("/api/token").
                then().spec(CommonSpecBuilder.getResponseSpec()).
                extract().response();

        if (response.statusCode() != 200) {

            throw new RuntimeException("ABORT !!! Renew token is failed");
        }
        return response.path("access_token");
    }
}
