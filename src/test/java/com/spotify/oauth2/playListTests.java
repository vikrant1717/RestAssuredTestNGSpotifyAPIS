package com.spotify.oauth2;
import com.spotify.oauth2.api.CommonSpecBuilder;
import com.spotify.oauth2.pojo.PlayList;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import utilities.Fakerutils;

import java.io.IOException;

import static com.spotify.oauth2.api.Route.USERS;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class playListTests {

    @Test
    public void shouldBeAbleToCreatePlaylist() throws IOException {
        PlayList requestPlay = new PlayList().
                setName(Fakerutils.generateName()).
                setDescription(Fakerutils.generateDescription()).
                //setName("New Playlist").
                //setDescription("New playlist description").
                setPublic(false);

        PlayList responseplaylist = given(CommonSpecBuilder.getRequestSpec()).
                body(requestPlay).
                when().
                post(USERS +"/3144xf6cdsw3dp3ptqab23ihjekm/playlists").
                then().spec(CommonSpecBuilder.getResponseSpec()).
                assertThat().statusCode(201).
                extract().response().
                as(PlayList.class);

        assertThat(responseplaylist.getName(), equalTo(requestPlay.getName()));
        assertThat(responseplaylist.getDescription(), equalTo(requestPlay.getDescription()));
        assertThat(responseplaylist.getPublic(), equalTo(requestPlay.getPublic()));

    }

    @Test
    public void getPlaylist() throws IOException {
        given(CommonSpecBuilder.getRequestSpec()).
                when().get("/playlists/2v6p8V5l3hGccfKGmbltJQ").
                then().spec(CommonSpecBuilder.getResponseSpec()).
                assertThat().statusCode(200).
                log().all().
                body("name", equalTo("New Playlist"),
                        "description", equalTo("New playlist description")).
                contentType(ContentType.JSON);
    }

    @Test
    public void updateExistingPlayList() throws IOException {
        String payload = "{\n" +
                "    \"name\": \"New Playlist\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given(CommonSpecBuilder.getRequestSpec()).
                body(payload).
                when().
                put("/playlists/2v6p8V5l3hGccfKGmbltJQ").
                then().spec(CommonSpecBuilder.getResponseSpec()).
                assertThat().statusCode(200);
    }
}

