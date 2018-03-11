package com.example.courtneypettiford.tunecollab.model;

import java.util.List;

/**
 * Created by courtneypettiford on 3/10/18.
 */

public class User {

    Integer uID;
    String firstName;
    String lastName;
    String email;
    String password;
    String location;
    List<String> influences;
    List<Genre> genres;
    List<Role> interests;
    List<Role> roles;
    List<Instrument> instruments;


    public User(String firstName, String lastName, String location, List<String> influences,
                List<Genre> genres, List<Role> roles, List<Role> interests,
                List<Instrument> instruments){
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.influences = influences;
        this.genres = genres;
        this.roles = roles;
        this.interests = interests;
        this.instruments = instruments;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }







}
