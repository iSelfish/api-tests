package restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Test01_Post {


    @Test
    public void test1() {

        RestAssured.baseURI = "https://reqres.in/api";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "morpheus");
        requestParams.put("job", "leader");

        request.header("Content-Type", "application/json");
        request.body(requestParams.toJSONString());
        Response response = request.post("/users");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);

        String name = response.jsonPath().get("name");
        Assert.assertEquals("morpheus", name);

        String job = response.jsonPath().get("job");
        Assert.assertEquals("leader", job);

        String createdAt = response.jsonPath().get("createdAt");
        Assert.assertFalse(createdAt.isEmpty());
    }


    @Test
    public void test2() {

        RestAssured.baseURI = "https://reqres.in/api";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "sydney@fife");

        request.header("Content-Type", "application/json");
        request.body(requestParams.toJSONString());
        Response response = request.post("/register");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);

        String error = response.jsonPath().get("error");
        Assert.assertEquals("Missing password", error);
    }


    @Test
    public void test3() {

        RestAssured.baseURI = "https://reqres.in/api";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "eve.holt@reqres.in");
        requestParams.put("password", "pistol");

        request.header("Content-Type", "application/json");
        request.body(requestParams.toJSONString());
        Response response = request.post("/register");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        int id = response.jsonPath().get("id");
        Assert.assertEquals(4, id);

        String token = response.jsonPath().get("token");
        Assert.assertEquals("QpwL5tke4Pnpja7X4", token);
    }
}