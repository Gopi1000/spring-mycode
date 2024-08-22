package com.scube.crm.utils;

public enum UserRoles {   
  
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_CAMPAIGN("ROLE_CAMPAIGN"), 
    ROLE_MARKETING("ROLE_MARKETING"),
    ROLE_SALES("ROLE_SALES"),
	ROLE_LEADS("ROLE_LEADS");
	
    private String role;
    
    UserRoles(String role) {
    
        this.role = role;
    }
    
    
    public String getRole() {
    
        return role;
    }
}
