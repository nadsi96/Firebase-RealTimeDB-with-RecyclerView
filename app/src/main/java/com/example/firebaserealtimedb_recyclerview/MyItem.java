package com.example.firebaserealtimedb_recyclerview;

public class MyItem {
    private String profile; // firebase에서 이미지 가져올 때, url주소로 가져옴. 그 url을 바로 띄워줄 것
    private String id;
    private String name;
    private int age;

    public MyItem(){

    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
