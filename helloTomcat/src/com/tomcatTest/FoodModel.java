package com.tomcatTest;

public class FoodModel {
    private String title;
    private String material;
    private String method;
    private String username;
    
/*    FoodModel(){
        this.Id = null; //default
        this.title = title;
        this.material = material;
        this.method = method;
    }*/
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
