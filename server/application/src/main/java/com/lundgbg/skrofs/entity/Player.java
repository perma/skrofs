package com.lundgbg.skrofs.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lundgbg.skrofs.rest.CustomDateDeserializer;
import com.lundgbg.skrofs.rest.CustomDateSerializer;

public class Player {

    private long id;
    private String firstName;
    private String surName;
    private String gender;
    private Date birthday;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(final String surName) {
        this.surName = surName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getBirthday() {
        return birthday;
    }

    @JsonDeserialize(using = CustomDateDeserializer.class)
    public void setBirthday(final Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", firstName=" + firstName + ", surName=" + surName + ", gender=" + gender
                        + ", birthday=" + birthday + "]";
    }

}
