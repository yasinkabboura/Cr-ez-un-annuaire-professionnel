package com.example.annuairedecontact;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "first_name")
    String first_name;
    @ColumnInfo(name = "last_name")
    String last_name;
    @ColumnInfo(name = "job")
    String job;
    @ColumnInfo(name = "email")
    String email;
    @ColumnInfo(name = "phone")
    String phone;

    public Contact(String first_name, String last_name, String job, String email, String phone) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.job = job;
        this.email = email;
        this.phone = phone;
    }

    public Contact() {
    }

    public Contact(int id, String first_name, String last_name, String job, String email, String phone) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.job = job;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
