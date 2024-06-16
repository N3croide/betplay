package com.aeb.modules.professional.domain;

public class Professional {
    private Integer id;
    private String name;
    private Integer age;
    private String lastName;


    public Professional( Integer age, String name, String lastName) {
        this.name = name;
        this.age = age;
        this.lastName = lastName;
    }

    public Professional(Integer id, String name){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public Integer getAge() {
        return age;
    }


    public String getLastName() {
        return lastName;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setAge(Integer age) {
        this.age = age;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return String.format("\nID: %d\nNAME: %s\n", this.id, this.name);
    }
    
}
