package com.example.neumi.sqlitedepts;

public class Region {

    private int noRegion;
    private String nom;

    public Region(int noRegion, String nom) {
        this.noRegion = noRegion;
        this.nom = nom;
    }

    public int getNoRegion() {
        return noRegion;
    }

    public void setNoRegion(int noRegion) {
        this.noRegion = noRegion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
