package com.example.doctor.Model;

public class DoctorModel {

    private String name;
    private String specialization;
    private String phone;
    private String city;
    private String imageurl;


    public DoctorModel() {
    }

    public DoctorModel(String name, String specialization, String phone, String city, String imageurl) {
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
        this.city = city;
        this.imageurl = imageurl;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }


}
