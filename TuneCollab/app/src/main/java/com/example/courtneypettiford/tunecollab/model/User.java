package com.example.courtneypettiford.tunecollab.model;

import java.util.List;

/**
 * Created by courtneypettiford on 3/10/18.
 */

public class User {

    String firstName;
    String lastName;
    String stageName;
    List<String> influences;
    List<Genre> genres;
    List<Role> interests;
    List<Role> roles;
    List<Instrument> instruments;

    //TODO: add location in???

    public User(String firstName, String lastName, String stageName, List<Instrument> instruments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.stageName = stageName;
        this.instruments = instruments;
    }

    public User(String firstName, String lastName, String stageName,
                List<String> influences, List<Genre> genres, List<Role> roles,
                List<Role> interests, List<Instrument> instruments){
        this.firstName = firstName;
        this.lastName = lastName;
        this.stageName = stageName;
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

    public String getStageName() { return stageName; }

    public List<String> getInfluences() { return influences; }

    public List<Genre> getGenres() { return genres; }

    public List<Role> getRoles() { return roles; }

    public List<Instrument> getInstruments() { return instruments; }








}
