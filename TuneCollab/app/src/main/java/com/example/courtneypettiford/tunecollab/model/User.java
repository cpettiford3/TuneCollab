package com.example.courtneypettiford.tunecollab.model;

import java.util.List;

/**
 * Created by courtneypettiford on 3/10/18.
 */

public class User {

    private String firstName;
    private String lastName;
    private String stageName;
    private List<String> influences;
    private List<Genre> genres;
    private List<Role> interests;
    private List<Role> roles;
    private List<Instrument> instruments;
    private List<String> mostPlayedSpotifyArtists;
    private List<String> recentlyPlayedAlbums;
    private List<User> acceptedUsers;
    private List<User> rejectedUsers;

    //TODO: add location in???

    public User() {}

    public User(String firstName, String lastName, String stageName,
                List<String> influences, List<Genre> genres, List<Role> roles,
                List<Role> interests, List<Instrument> instruments,
                List<String> mostPlayedSpotifyArtists, List<String> recentlyPlayedAlbums,
                List<User> acceptedUsers, List<User> rejectedUsers){
        this.firstName = firstName;
        this.lastName = lastName;
        this.stageName = stageName;
        this.influences = influences;
        this.genres = genres;
        this.roles = roles;
        this.interests = interests;
        this.instruments = instruments;
        this.mostPlayedSpotifyArtists = mostPlayedSpotifyArtists;
        this.recentlyPlayedAlbums = recentlyPlayedAlbums;
        this.acceptedUsers = acceptedUsers;
        this.rejectedUsers = rejectedUsers;
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

    public List<Role> getInterests() { return interests; }

    public List<Instrument> getInstruments() { return instruments; }

    public List<String> getMostPlayedSpotifyArtists() { return mostPlayedSpotifyArtists; }

    public List<User> getAcceptedUsers() { return acceptedUsers; }

    public List<User> getRejectedUsers() { return rejectedUsers; }

}
