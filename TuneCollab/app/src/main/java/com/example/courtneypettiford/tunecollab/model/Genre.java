package com.example.courtneypettiford.tunecollab.model;

/**
 * Created by courtneypettiford on 3/10/18.
 */

public enum Genre {
    ALTERNATIVE("Alternative"),
    CHRISTIAN("Christian"),
    GOSPEL("Gospel"),
    COUNTRY("Country"),
    EXPERIMENTAL("Experimental"),
    INDIE("Indie"),
    POP("Pop"),
    REGGAE("Reggae"),
    SINGER_SONGWRITER("Singer-Songwriter"),
    AMERICANA("Americana"),
    CLASSIC_ROCK("Classic Rock"),
    DANCE("Dance"),
    HARD_ROCK("Hard Rock"),
    JAZZ("Jazz"),
    LATIN("Latin"),
    ROCK("Rock"),
    SOUL_FUNK("Soul/Funk"),
    WORLD("World"),
    BLUES("Blues"),
    CLASSICAL("Classical"),
    HIP_HOP("Hip-Hop"),
    RAP("Rap"),
    K_POP("K-pop"),
    METAL("Metal"),
    OLDIES("Oldies"),
    RHYTHM_BLUES("R&B"),
    FOLK("Folk"),
    NEW_AGE("New aAge"),
    SKA("Ska"),
    EDM("EDM");

    private String genre;

    Genre(String string) {
        this.genre = string;
    }

    public String getGenre() {
        return this.genre;
    }

    @Override
    public String toString() {
        return this.genre;
    }




}
