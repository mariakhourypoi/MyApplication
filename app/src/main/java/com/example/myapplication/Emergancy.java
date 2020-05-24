package com.example.myapplication;

public class Emergancy {
    private String emergancyName;


    public Emergancy(String emergancyName ) {
        this.emergancyName=emergancyName;

    }

    public String getEmergancyName() {
        return emergancyName;
    }

    public void setEmergancyName(String emergancyName) {
        this.emergancyName = emergancyName;
    }

    @Override
    public String toString() {
        return emergancyName ;
    }
}
