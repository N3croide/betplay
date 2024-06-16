package com.aeb.genericClasses.person.domain;

public class Person {
    private String name;
    private String lastName;
    private Integer idCard;
    private Integer age;

    public Person(String name, String lastName, Integer idCard, Integer age) {
        this.name = name;
        this.lastName = lastName;
        this.idCard = idCard;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }
    public Integer getIdCard() {
        return idCard;
    }
    public Integer getAge() {
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setIdCard(Integer idCard) {
        this.idCard = idCard;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
