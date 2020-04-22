package utils.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import context.TestBase;
import io.restassured.response.Response;

public class CmnApiMethods extends TestBase {

	public String CreateNewUserInGoRestAPI() {
		
		//Get Random String for Email
				String email = GetRandomString(10)  + "@gmail.com";
				
		//Set Header
		HashMap<String, String> hm_header = new HashMap<String, String>();
		hm_header.put("Content-Type", "application/json");
				
		//Set Body
		String body_string = "{\r\n" + 
				"	\"gender\":\"female\",\r\n" + 
				"	\"last_name\":\"lastnamevisionit\",\r\n" + 
				"	\"first_name\":\"firstnamevisionit\",\r\n" + 
				"	\"email\":\"" + email + "\"\r\n" + 
				"}";
				
		//POST and fetch response
		Response resp = given().baseUri(server)
				.auth().oauth2(accessToken)
				.headers(hm_header).body(body_string)
				.when().post("/public-api/users");
				
		System.out.println("Response for this POST Request is:" + resp.asString());
				
		resp.then().assertThat()
		.body("_meta.code", equalTo(201))
		.body("_meta.success", equalTo(true))
		.body("_meta.message", equalTo("A resource was successfully created in response to a POST request. The Location header contains the URL pointing to the newly created resource."))
		.body("result.first_name",equalTo("firstnamevisionit"))
		.body("result.last_name",equalTo("lastnamevisionit"))
		.body("result.gender",equalTo("female"))
		.body("result.email",equalTo(email))
		.body("result.dob",equalTo(null));
				
		String id = resp.jsonPath().getString("result.id");
				
		return id;
	
	}
}
