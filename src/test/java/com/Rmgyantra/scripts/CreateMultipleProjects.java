package com.Rmgyantra.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Rmgyantra.genericUtils.BaseApiClass;
import com.Rmgyantra.genericUtils.EndPoints;
import com.Rmgyantra.genericUtils.JavaUtils;
import com.Rmgyantra.pojoclass.RmgProject;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class CreateMultipleProjects extends BaseApiClass
{
	@Test(dataProvider = "datapro")
	public void createmultiple(String createdBy,String projectName,int teamSize )
	{
		RmgProject project=new RmgProject(createdBy, projectName,"Completed", teamSize);
		
		given()
		.contentType(ContentType.JSON)
		.body(project)
		
		.when()
		.post(EndPoints.addPRojecttest)
		
		.then()
		.assertThat().statusCode(201)
		.log().all();
	}
	
	@DataProvider
	public Object [] [] datapro()
	{
		Object [] [] obj=new Object [4] [3];
		obj[0][0]="siddu"+JavaUtils.getrandomnumber();
		obj[0][1]="tcs";
		obj[0][2]=1;
		
		obj[1][0]="abhi"+JavaUtils.getrandomnumber();
		obj[1][1]="itc info";
		obj[1][2]=5;
		
		obj[2][0]="adhi"+JavaUtils.getrandomnumber();
		obj[2][1]="wipro";
		obj[2][2]=2;
		
		obj[3][0]="manoj"+JavaUtils.getrandomnumber();
		obj[3][1]="google";
		obj[3][2]=22;
		
		return obj;
		
	}

}
