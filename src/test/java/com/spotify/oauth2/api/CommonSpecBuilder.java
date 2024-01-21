package com.spotify.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.*;

import java.io.IOException;

import static com.spotify.oauth2.api.Route.BASE_PATH;

public class CommonSpecBuilder {

   // static String access_token = "BQDtFedjoETFA58IVfwyUiryPaAM3ek119AtfB9dfqL7zlPQbXj2HOOZu3SD09LWZ2lY6VKLlEujJ1YXJI6fOortMrcm0-P774Cn4qUAOmNUZ_xU9Y9Yp-a4gbapUtgpuRTL2mlw0D0O1yeLMZBs3QxO11dWCXjB9ctyMvF1tP0ZgjxGr-iA5_1p-ntMwM9UEyDU0cfNeW29CQkJE0MTDrAo5y2cmm7t0Xt24M3SjWtKabL3SbpnKZ6s4-yDhVJfdzWrsFuZbC_mDigr";


    public static RequestSpecification getRequestSpec() throws IOException {
        return  new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com").
                setBasePath(BASE_PATH). // imported static constant , check class Route for the implementation
               // setBasePath("/v1").
                addHeader("Authorization", "Bearer " + TokenManager.renewToken()).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();
    }

    public static ResponseSpecification getResponseSpec()
    {

       return new  ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();

    }
}
