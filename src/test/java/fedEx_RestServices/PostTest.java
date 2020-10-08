package fedEx_RestServices;

import io.restassured.path.json.JsonPath;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostTest {

    @Test
    public void RegistrationSuccessful() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("title", "foo");
        requestParams.put("body", "bar");
        requestParams.put("userId", 1);

        // Adding  header
        request.header("Content-Type", "application/json; charset=UTF-8");

        request.body(requestParams.toJSONString());
        Response response = request.post("/posts");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);

        // validate status code for Post
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 201, "Correct Status code returned.");

        // Read parameters from response body
        JsonPath jsonPathEvaluator = response.jsonPath();
        String id = jsonPathEvaluator.get("id").toString();
        String title = jsonPathEvaluator.get("title");
        String body = jsonPathEvaluator.get("body");
        String userId = jsonPathEvaluator.get("userId").toString();

        // validate parameters in response are not null
        Assert.assertTrue(StringUtils.isNotEmpty(id), "Id from Reponse is not blank");
        Assert.assertTrue(StringUtils.isNotEmpty(title), "Title from Reponse is not blank");
        Assert.assertTrue(StringUtils.isNotEmpty(body), "Body from Reponse is not blank");
        Assert.assertTrue(StringUtils.isNotEmpty(userId), "userId from Reponse is not blank");
    }
}
