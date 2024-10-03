package com.crio.coderhack.constants;

public enum Badges {
    CODE_NINJA("Code Ninja"),
    CODE_CHAMP("Code Champ"),
    CODE_MASTER("Code Master");

    private final String name;

    Badges(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
