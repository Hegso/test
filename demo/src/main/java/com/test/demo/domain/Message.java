package com.test.demo.domain;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    private String firstName, lastName, lgn, email, text, dates, typText;

    public Message() {
    }

    public Message(String firstName, String lastName, String lgn, String email, String text, String typText) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.lgn = lgn;
        this.email = email;
        this.text = text;
        this.typText = typText;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd ' ' HH:mm:ss");
        Date date = new Date();

        dates = dateFormat.format( new Date() );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDates() {
        return dates;
    }

    public void setDates() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd ' ' HH:mm:ss");
        Date date = new Date();
        this.dates = dateFormat.format( new Date() );;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String surname) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTypText() {
        return typText;
    }

    public void setTypText(String typText) {
        this.typText = typText;
    }

    public String getLgn() {
        return lgn;
    }

    public void setLgn(String lgn) {
        this.lgn = lgn;
    }
}
