package com.armani.myapplication.moblie.dto;

public class MobileSimpleDTO {
    private Long id;
    private String name;
    private String companyName;


    public MobileSimpleDTO() {
    }

    public MobileSimpleDTO(Long id, String name, String company) {
        this.id = id;
        this.name = name;
        this.companyName = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return  this.companyName + "\n" + this.name ;
    }
}
