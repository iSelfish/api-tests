
package restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Test01_Get {


    @Test
    public void test1() {

        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(response.getBody().asString());
        System.out.println(response.statusLine());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }


    @Test
    public void test2() {

        given().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("data.id[0]", equalTo(7)).log().all();
    }


    @Test
    public void test3() {

        given().get("https://reqres.in/api/users?page=2").then().
                statusCode(200).
                body("data.id[1]", equalTo(8)).
                body("data.first_name", hasItems("Michael", "Lindsay")).
                body("data.email", hasItem("rachel.howell@reqres.in")).
                log().all();
    }
}