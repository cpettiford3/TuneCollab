package com.example.courtneypettiford.tunecollab.model;

/**
 * Created by courtneypettiford on 3/10/18.
 */

public enum Role {
    SINGER("Singer"),
    INSTRUMENTALIST("Instrumentalist"),
    SONGWRITER("Songwriter"),
    PRODUCER("Producer"),
    AUDIO_TECHNICIAN("Audio technician"),
    SOUND_ENGINEER("Sound engineer");

    private String type;

    Role(String string) {
        this.type = string;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Role{}";
    }





}
