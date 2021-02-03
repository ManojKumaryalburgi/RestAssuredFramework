package com.Rmgyantra.scripts;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Rmgyantra.genericUtils.BaseApiClass;
import com.Rmgyantra.genericUtils.DataBaseUtilities;
import com.Rmgyantra.genericUtils.EndPoints;
import com.Rmgyantra.pojoclass.RmgProject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;

public class AddSingleProjectWithCreated extends BaseApiClass
{
	@Test
	public void addprojects_using_JsonObject()
	{
		
		JSONObject obj=new JSONObject();
		obj.put("createdBy","adhi"+random.getrandomnumber());
		obj.put("projectName","BigData");
		obj.put("status","Completed");
		obj.put("teamSize", 2);
		
		given()
		.contentType(ContentType.JSON)
		.body(obj)
		
		.when()
		.post(EndPoints.addPRojecttest)
		
		.then()
		.assertThat().statusCode(201)
		.log().all();
		
	}

	
	@Test
	public void addprojects_using_pojo() throws Throwable
	{
		RmgProject project=new RmgProject("abhi"+random.getrandomnumber(),"ITC","On Going", 100);
	Response res = given()
		.contentType(ContentType.JSON)
		.body(project)
		
		.when()
		.post(EndPoints.addPRojecttest);
		
		res.then()
		.assertThat().statusCode(201)
		
		.log().all();
		String actualmsg = res.jsonPath().get("msg");
		System.out.println(actualmsg);
		String apiProjectID = res.jsonPath().get("projectId");
		
		String dbProjectId = DataBaseUtilities.executeQueryAndGetData("select * from project",1, apiProjectID);
		
		Assert.assertEquals(dbProjectId,apiProjectID);
		
	}

	
}
