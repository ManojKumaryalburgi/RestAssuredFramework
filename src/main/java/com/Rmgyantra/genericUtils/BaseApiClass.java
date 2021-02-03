package com.Rmgyantra.genericUtils;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import static io.restassured.RestAssured.*;

public class BaseApiClass 
{
	public JavaUtils random=new JavaUtils();
	@BeforeSuite
	public void configBeforeSuite() {
		baseURI = "http://localhost";
		port = 8084;
		
		DataBaseUtilities.connectToDB();
	}
	
	@AfterSuite
	public void configAfterSuite() throws SQLException {
		DataBaseUtilities.closeDb();
	}


}
