package com.example.blooddonation.model;

public class User {
    private String id;
    private String name;
    private String phone;
    private String role;
    private String age;
    private String city;
    private String blood_group;
    private String location;
    private String email;
    private String gender;

    public User(String id,String name,String phone,String role,String age, String city, String blood, String location, String email, String gender){
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.role=role;
        this.age=age;
        this.city=city;
        this.blood_group =blood;
        this.location=location;
        this.email=email;
        this.gender=gender;
    }
    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
