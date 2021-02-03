package com.Rmgyantra.scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Rmgyantra.genericUtils.BaseApiClass;
import com.Rmgyantra.genericUtils.DataBaseUtilities;
import com.Rmgyantra.genericUtils.EndPoints;
import com.Rmgyantra.genericUtils.JavaUtils;
import com.Rmgyantra.pojoclass.RmgProject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class AddProjectWitONGoingStatus extends BaseApiClass
{
	@Test
	public void addproject_OngoingStatus() throws Throwable
	{
		RmgProject project=new RmgProject("siddu"+JavaUtils.getrandomnumber(),"solar max","On Going", 100);
		
		Response res = given()
		.contentType(ContentType.JSON)
		.body(project)
		
		.when()
		.post(EndPoints.addPRojecttest);
		
		res.then()
		.assertThat().statusCode(201)
		.log().all();
		
		String apiProjectId=res.jsonPath().get("projectId");
		System.out.println(apiProjectId);
		
		String dbProjectid = DataBaseUtilities.executeQueryAndGetData("select * from project", 1, apiProjectId);
		Assert.assertEquals( dbProjectid,apiProjectId );
		
	}

}
