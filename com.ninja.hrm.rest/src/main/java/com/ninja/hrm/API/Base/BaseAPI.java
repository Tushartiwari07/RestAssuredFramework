package com.ninja.hrm.API.Base;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import restUtility.CreateProjectUtil;
import utility.FileUtility;
import utility.JavaUtility;
import utility.JsonUtility;

public class BaseAPI {
	public FileUtility flib = new FileUtility();
	public JsonUtility jsonlib = new JsonUtility();
	public JavaUtility javalib = new JavaUtility();
	public CreateProjectUtil createProj= new CreateProjectUtil();
	public static RequestSpecification specReqObj;
	public static ResponseSpecification specRespObj;
	
	
	@BeforeSuite
	public void configBS() throws FileNotFoundException, IOException{
		RequestSpecBuilder reqBuilder= new RequestSpecBuilder();
		reqBuilder.setContentType(ContentType.JSON);
		reqBuilder.setBaseUri(flib.getDataFromPropertiesFile("baseUri"));
		specReqObj = reqBuilder.build();
		
		ResponseSpecBuilder respBuilder = new ResponseSpecBuilder();
		respBuilder.expectContentType(ContentType.JSON);
		specRespObj=respBuilder.build();
	}
	@AfterSuite
	public void configAS() {
		
	}

}
