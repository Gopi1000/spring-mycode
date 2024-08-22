package com.scube.techboot.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

public class Dropdownutils {
	
	
	public static List<String> dropdownList = new ArrayList<>();

	public static List<String> getFunctionarea() {
		// TODO Auto-generated method stub
        try{
			
			String areaOfinterested=TechbootResourceBundle.getDropdown("AreaOfInterested");
			dropdownList=Arrays.asList(areaOfinterested.split(","));
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
				
		return dropdownList;
	}

	public static List<String> getIndustryType() {
		// TODO Auto-generated method stub
		
		try{
			String industryType=TechbootResourceBundle.getDropdown("industry.tpye");
			dropdownList=Arrays.asList(industryType.split(","));
		}catch(Exception e){
			e.printStackTrace();
		}
		return dropdownList;
	}
	public static List<String> getQuestionType() {
		// TODO Auto-generated method stub
		
		try{
			String questionType=TechbootResourceBundle.getDropdown("question.type");
			dropdownList=Arrays.asList(questionType.split(","));
		}catch(Exception e){
			e.printStackTrace();
		}
		return dropdownList;
	}
	public static List<String> getCompanyType() {
		try{
			String companyType=TechbootResourceBundle.getDropdown("company.type");
			dropdownList=Arrays.asList(companyType.split(","));
		}catch(Exception e){
			e.printStackTrace();
		}
		return dropdownList;
	}

	

	public static List<String> getCityType() {
		try{
			String CityType=TechbootResourceBundle.getDropdown("student.city");
			dropdownList=Arrays.asList(CityType.split(","));
		}catch(Exception e){
			e.printStackTrace();
		}
		return dropdownList;
	}

	public static List<String> getStateType() {
		try{
			String stateType=TechbootResourceBundle.getDropdown("student.states");
			dropdownList=Arrays.asList(stateType.split(","));
		}catch(Exception e){
			e.printStackTrace();
		}
		return dropdownList;
	}

	public static List<String> getLanguageType() {
		// TODO Auto-generated method stub
		try{
			String lagType=TechbootResourceBundle.getDropdown("student.lang");
			dropdownList=Arrays.asList(lagType.split(","));
		}catch(Exception e){
			e.printStackTrace();
		}
		return dropdownList;
	}

	public static List<String> getMaritalStatusType() {
		// TODO Auto-generated method stub
		try{
			String maritlType=TechbootResourceBundle.getDropdown("student.maritalStatus");
			dropdownList=Arrays.asList(maritlType.split(","));
		}catch(Exception e){
			e.printStackTrace();
		}
		return dropdownList;
	}

	public static List<String> getGenderType() {
		try{
			String genderType=TechbootResourceBundle.getDropdown("student.gender");
			dropdownList=Arrays.asList(genderType.split(","));
		}catch(Exception e){
			e.printStackTrace();
		}
		return dropdownList;
	}
	
	public static List<String> getCustomerType(){
		try {
			String customerType=TechbootResourceBundle.getDropdown("customer.type");
			dropdownList=Arrays.asList(customerType.split(","));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return dropdownList;
	}
	
	public static List<String> getSpecificationType(){
		try {
			String specificationType=TechbootResourceBundle.getDropdown("specification.type");
			dropdownList=Arrays.asList(specificationType.split(","));
			
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		
		return dropdownList;
		
		
	}
	
	
}
