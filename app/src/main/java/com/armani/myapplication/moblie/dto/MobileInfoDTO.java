package com.armani.myapplication.moblie.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MobileInfoDTO {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("guarantee")
    @Expose
    private Boolean guarantee;

    @SerializedName("weight")
    @Expose
    private Long weight;

    @SerializedName("size")
    @Expose
    private Long size;

    @SerializedName("simCounts")
    @Expose
    private Long simCounts;

    @SerializedName("cpuType")
    @Expose
    private String cpuType;

    @SerializedName("memory")
    @Expose
    private Long memory;

    @SerializedName("camera")
    @Expose
    private Long camera;

    @SerializedName("osType")
    @Expose
    private String osType;

    @SerializedName("waterProof")
    @Expose
    private Boolean waterProof;

    @SerializedName("networkType")
    @Expose
    private String networkType;

    @SerializedName("companyName")
    @Expose
    private String companyName;


    @SerializedName("companyCountry")
    @Expose
    private String companyCountry;

    public MobileInfoDTO(){}

    public MobileInfoDTO(String name , String color , Boolean guarantee , Long weight , Long size ,
                         Long simCounts , String cpuType , Long memory , Long camera , String osType ,
                         Boolean waterProof , String networkType , String companyName , String companyCountry)
    {
        this.name = name ;
        this.color = color ;
        this.guarantee = guarantee ;
        this.weight = weight ;
        this.size = size ;
        this.simCounts = simCounts;
        this.cpuType = cpuType ;
        this.memory = memory ;
        this.camera = camera ;
        this.osType = osType ;
        this.waterProof = waterProof ;
        this.networkType = networkType ;
        this.companyName = companyName ;
        this.companyCountry = companyCountry ;

            }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Boolean guarantee) {
        this.guarantee = guarantee;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getSimCounts() {
        return simCounts;
    }

    public void setSimCounts(Long simCounts) {
        this.simCounts = simCounts;
    }

    public String getCpuType() {
        return cpuType;
    }

    public void setCpuType(String cpuType) {
        this.cpuType = cpuType;
    }

    public Long getMemory() {
        return memory;
    }

    public void setMemory(Long memory) {
        this.memory = memory;
    }

    public Long getCamera() {
        return camera;
    }

    public void setCamera(Long camera) {
        this.camera = camera;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public Boolean getWaterProof() {
        return waterProof;
    }

    public void setWaterProof(Boolean waterProof) {
        this.waterProof = waterProof;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

  /*  @Override
    public String toString(){
        return
                " " + this.name + "نام موبایل"+ "\n"+
                 this.name +"  : نام موبایل" + "\n"+
                 this.color+"  : رنگ"  +"\n"+
                 this.guarantee  + " : گارانتی" + "\n" +
                 "وزن  : " +  this.weight+ "\n"+
                 this.size +  " : سایز"+ "\n" +
                 this.simCounts +  " : تعداد سیم کارت"+ "\n" ;
              *//*   this.cpuType +  "پردازنده : "+ "\n" +
                 this.memory + " حافظه داخلی : "+ "\n" +
                 this.camera +" دوربین : "+ "\n" +
                 this.osType  + "نسخه سیستم عامل : "+ "\n" +
                 this.waterProof +"ضد آب : "+ "\n" +
                 this.networkType  +"شبکه های ارتباطی : "+ "\n" +
                 this.companyName  + "شرکت : "+ "\n" +
                 this.companyCountry + "کشور : " +"\n" ;*//*
    }*/
}
