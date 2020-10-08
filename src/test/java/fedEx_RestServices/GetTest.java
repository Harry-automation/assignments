package fedEx_RestServices;

import io.restassured.path.json.JsonPath;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetTest {

    @Test
    public void GetEndpointDeatils()
    {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/posts/1");

        // print the body of the message
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);

        // assert 200 status code is returned.
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 200,"Correct Status code returned.");

        // assert body contains parameters
        JsonPath jsonPathEvaluator = response.jsonPath();
        String id = jsonPathEvaluator.get("id").toString();
        String title = jsonPathEvaluator.get("title");
        String body =  jsonPathEvaluator.get("body");

       // print id and title from response
        System.out.println("Id received from Response " + id);
        System.out.println("Title received from Response " + title);

        // Validate the response parameters are not null
        Assert.assertTrue(StringUtils.isNotEmpty(id), "Id from Reponse is not blank");
        Assert.assertEquals(id, "1", "Correct id received in the Response");

        Assert.assertTrue(StringUtils.isNotEmpty(title), "Title from Reponse is not blank");
        Assert.assertEquals(title, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "Correct title received in the Response");

        Assert.assertTrue(StringUtils.isNotEmpty(body), "Body from Reponse is not blank");
    }
}
