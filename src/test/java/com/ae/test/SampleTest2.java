package com.ae.test;

import com.ae.log.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.path.json.*;
import static io.restassured.RestAssured.*;
import io.restassured.common.mapper.TypeRef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SampleTest2 extends Log {

	Response response;
	JsonPath json;
	int emptycount, datacount;
	String responsebody;

	//@BeforeTest
	public void getRequest()
	{
		baseURI = "https://api.zippopotam.us/AD";
	}

	//@Test
	public void CountTest() {
		emptycount = 0;
		datacount = 0;
		for(int i=100;i<=100;i++) {
			response = given().
					when()
					.get("/AD"+i);		
			json = response.jsonPath();
			responsebody = response.body().asString();
			//System.out.println(i + ":" + responsebody);
			if(responsebody.equals("{}")) 
				emptycount = emptycount + 1;
			else
				datacount = datacount + 1;
		}
		log.info("emptycount: " + emptycount);
		log.info("datacount: " + datacount);
		log.info("property: " + prop.getProperty("first"));
		//System.out.println("emptycount: " + emptycount);
		//System.out.println("datacount: " + datacount);
	}

	//@Test
	void verifyResponseCodeType()
	{

		System.out.println(response.asPrettyString());
		System.out.println(response.getDetailedCookies());
		System.out.println(response.getHeaders());
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(),"application/json");

	}

	//@Test
	void verifyReponseDatas()
	{
		//System.out.println(json.getString("country"));		
		Assert.assertEquals(json.getString("country"), "United States");	

		//System.out.println(json.prettify());
		//System.out.println(json.getInt("'post code'"));
		Assert.assertEquals(json.getInt("'post code'"), 90210);
		System.out.println(json.getString("places[0].'place name'"));
		System.out.println(json.getInt("'post code'"));
		//all data need to verify

	}

	//@Test
	void verifyReponseData()
	{
		//System.out.println(json.getString("country"));		
		Assert.assertEquals(json.getString("country"), "Andorra");	

		//System.out.println(json.prettify());
		//System.out.println(json.getInt("'post code'"));
		Assert.assertEquals(json.getString("'post code'"), "AD100");
		log.error(json.get("places[0].'place name'"));
		log.warn(json.get("'post code'"));
		//all data need to verify

	}

	//@Test
	public void postrequesthashmap() {
		HashMap<String,Object> hmap = new HashMap<String,Object>();
		hmap.put("name", "john");
		hmap.put("job", "it");

		ArrayList<String> al = new ArrayList<String>();
		al.add("java");
		al.add("c");

		hmap.put("skilss",al);

		HashMap<String,Object> smap = new HashMap<String,Object>();
		smap.put("company", "startup");
		smap.put("email", "gmail");

		hmap.put("details", smap);

		response = given()
				.contentType(ContentType.JSON)
				.when()
				.body(hmap)
				.post("/users");

		json = response.jsonPath();
		System.out.println(response.asPrettyString());
		System.out.println(json.getString("details.company"));

	}

	//@Test
	public void postrequestjsonobject() {
		JSONObject hmap = new JSONObject();
		hmap.put("name", "john");
		hmap.put("job", "it");

		JSONArray al = new JSONArray();
		al.put("java");
		al.put("c");

		hmap.put("skilss",al);

		JSONObject smap = new JSONObject();
		smap.put("company", "startup");
		smap.put("email", "gmail");

		hmap.put("details", smap);

		System.out.println("request body" + hmap);

		response = given()
				.contentType(ContentType.JSON)
				.when()
				.body(hmap.toString())
				.post("/users");

		json = response.jsonPath();
		System.out.println(response.asPrettyString());
		System.out.println(json.getString("details.company"));

	}

	//@Test
	public void postrequestpojo() {

		EmployeePojo ep = new EmployeePojo("jess", "lead", new String[] {"java","C+"}, "st", "mail");

		response = given()
				.contentType(ContentType.JSON)
				.when()
				.body(ep)
				.post("/users");

		json = response.jsonPath();
		System.out.println(response.asPrettyString());
		System.out.println(json.getString("details.company"));

	}

	//@Test
	public void postresponsepojo() {

		EmployeePojo ep = new EmployeePojo("jess", "lead", new String[] {"java","C+"}, "st", "mail");

		response = given()
				.contentType(ContentType.JSON)
				.when()
				.body(ep)
				.post("/users");

		json = response.jsonPath();
		System.out.println(response.asPrettyString());
		System.out.println(json.getString("details.company"));

	}

	//@Test
	public void getresponsedeserialization() {

		ListPojo listpojo = given()
				.when()
				.get("https://reqres.in/api/users?page=2")
				.as(ListPojo.class);

		//System.out.println(listpojo.toString());
		System.out.println(listpojo.getSupport());
	}

	@Test
	public void getresponsemap() {

		Map<String,Object> map = given()
				.when()
				.get("https://reqres.in/api/users?page=2")
				.then().log().all().extract().response()
				.as(new TypeRef<Map<String,Object>>(){});

		//System.out.println(listpojo.toString());
		System.out.println("Using Map: " + (ArrayList)map.get("data"));
		ArrayList al = (ArrayList)map.get("data");
		System.out.println("Using ArrayList: " + al.get(1));
		Map submap1 = (Map)al.get(5);
		System.out.println("Using Sub Map: " + submap1.get("id"));
	}

}