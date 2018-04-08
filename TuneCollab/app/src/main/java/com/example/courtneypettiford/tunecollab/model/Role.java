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

    private String role;

    Role(String string) {
        this.role = string;
    }

    public String getRole() {
        return this.role;
    }

    @Override
    public String toString() {
        return this.role;
    }





}
