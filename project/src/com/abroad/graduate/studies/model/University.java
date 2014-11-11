package com.abroad.graduate.studies.model;

import java.util.Date;

/**
 * Created by gp on 11/11/14.
 */
public class University {
    private String name;
    private String tutionfee;
    private String deadline;
    private String financialAid;
    private String url;
    private String image;
    private int gre;
    private int toefl;
    private float cgpa;

    public University(String name, String tutionfee,String deadline,
                      String financialAid,String url,
                      String image,int gre, int toefl,float cgpa) {

        this.name=name;
        this.tutionfee=tutionfee;
        this.deadline=deadline;
        this.financialAid=financialAid;
        this.url=url;
        this.image=image;
        this.gre=gre;
        this.toefl=toefl;
        this.cgpa=cgpa;

    }

    public String getName()
    {
        return name;
    }
}
