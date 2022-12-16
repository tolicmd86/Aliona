package com.automation.test;

import com.automation.body.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;


public class Basics {
    public static void main(String[] args) {

        String getKey = "place_id";
        String key = "key";
        String value = "qaclick123";
        String resourcesPost = "maps/api/place/add/json";
        String resourcesPut = "maps/api/place/update/json";
        String resourcesGet= "maps/api/place/get/json";
        String contentType = "Content-Type";
        String contentValue = "application/json";
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given().log().all().queryParam(key, value).header(contentType, contentValue)
                .body(Payload.addPlace()).when().post(resourcesPost)
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();

        System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);

        String placeID = jsonPath.getString("place_id");

        System.out.println(placeID);

        String newAddress ="70 Summer walk, USA";
        String updatePayload = Payload.updateAddress(placeID,newAddress);



        given().log().all().queryParam(key, value).header(contentType, contentValue)
                .body(updatePayload)
                .when().put(resourcesPut).then().assertThat().log().all().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));


        String getPlaceResponse = given().log().all().queryParam(key, value).queryParam(getKey, placeID)
                .when().get(resourcesGet)
                .then().assertThat().log().all().statusCode(200)
                .extract().response().asString();

        JsonPath jsonPath1 = new JsonPath(getPlaceResponse);
        String actualAddress = jsonPath1.getString("address");
        System.out.println(actualAddress);

       // Assert.assertEquals(actualAddress,newAddress); //TestNG
        Assert.assertEquals(actualAddress,newAddress); // Junit


    }
}
