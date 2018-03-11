package com.example.courtneypettiford.tunecollab.model;

/**
 * Created by courtneypettiford on 3/10/18.
 */

public enum Instrument {

    GUITAR("Guitar"),
    BASS("Bass"),
    CLARINET("Clarinet"),
    PIANO("Piano"),
    VIOLIN("Violin"),
    BANJO("Banjo"),
    CELLO("Cello"),
    SAXOPHONE("Saxophone"),
    FLUTE("Flute"),
    TRUMPET("Trumpet"),
    DRUMS("Drums"),
    KEYBOARD("Keyboard"),
    ORGAN("Organ"),
    SYNTH("Synth"),
    OTHER("Other");

    private String type;

    Instrument(String string) {
        this.type = string;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Instruments{}";
    }

}
