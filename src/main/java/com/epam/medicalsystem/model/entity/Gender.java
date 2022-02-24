package com.epam.medicalsystem.model.entity;

import java.util.Optional;

public enum Gender {
    MALE(1),
    FEMALE(2),
    OTHER(3);

    private final int genderId;

    Gender(int genderId) {
        this.genderId = genderId;
    }

    public int getGenderId() {
        return genderId;
    }

    public static Optional<Gender> valueOfGenderId(int genderId) {
        for (Gender e : values()) {
            if (e.genderId == genderId) {
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }
}