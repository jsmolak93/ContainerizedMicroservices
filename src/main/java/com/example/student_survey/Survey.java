package com.example.student_survey;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String telephone;
    private String email;
    private LocalDate surveyDate;

    private String likedMost; // students, campus, sports, etc.
    private String interestSource; // friends, TV, Internet, other
    private String recommendLikelihood; // Very Likely, Likely, Unlikely

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getSurveyDate() {
        return surveyDate;
    }
    public void setSurveyDate(LocalDate surveyDate) {
        this.surveyDate = surveyDate;
    }
    public String getLikedMost() {
        return likedMost;
    }
    public void setLikedMost(String likedMost) {
        this.likedMost = likedMost;
    }
    public String getInterestSource() {
        return interestSource;
    }
    public void setInterestSource(String interestSource) {
        this.interestSource = interestSource;
    }
    public String getRecommendLikelihood() {
        return recommendLikelihood;
    }
    public void setRecommendLikelihood(String recommendLikelihood) {
        this.recommendLikelihood = recommendLikelihood;
    }

    
    
}
