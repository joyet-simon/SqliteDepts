package com.example.neumi.sqlitedepts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.EmptyStackException;

public class Departement {
    private SQLiteDatabase db;
    private Context ctxt;
    String noDept;
    int noRegion;
    String nom;
    String nomStd;
    int surface;
    String dateCreation;
    String chefLieu;
    String urlWiki;

    public Departement(Context c) {
        DbGeo dbgeo = DbGeo.getInstance(c);
        db = dbgeo.getWritableDatabase();
        ctxt = c;
    }

    public void select(String no) throws Exception {
        String[] colonnes = {"no_dept", "no_region", "nom", "nom_std", "surface", "date_creation", "chef_lieu", "url_wiki"};
        String critere = "no_dept = '" + no + "'";
        Cursor cursor = db.query("departements", colonnes, critere, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            setNoDept(cursor.getString(0));
            setNoRegion(Integer.parseInt(cursor.getString(1)));
            setNom(cursor.getString(2));
            setNomStd(cursor.getString(3));
            setSurface(Integer.parseInt(cursor.getString(4)));
            setDateCreation(cursor.getString(5));
            setChefLieu(cursor.getString(6));
            setUrlWiki(cursor.getString(7));
        } else {
            throw new EmptyStackException();
        }
    }

    public Departement(Context c, String no) throws Exception {
        DbGeo dbgeo = DbGeo.getInstance(c);
        db = dbgeo.getWritableDatabase();
        ctxt = c;
        select(no);
    }

    public void delete() throws Exception {
        if (!getNoDept().isEmpty()) {
            String clause = "no_dept = " + getNoDept();
            db.delete("departements", clause, null);
        } else {
            throw new EmptyStackException();
        }
    }

    public void update() throws Exception {
        if (!getNoDept().isEmpty()) {
            ContentValues values = new ContentValues();
            values.put("no_dept", getNoDept());
            values.put("no_region", getNoRegion());
            values.put("nom", getNom());
            values.put("nom_std", getNomStd());
            values.put("surface", getSurface());
            values.put("date_creation", getDateCreation());
            values.put("chef_lieu", getChefLieu());
            values.put("url_wiki", getUrlWiki());
            String critere = "no_dept = '" + getNoDept() + "'";
            db.update("departements", values, critere, null);
        } else {
            throw new EmptyStackException();
        }
    }

    public void insert() throws Exception {
        if (!getNoDept().isEmpty()) {
            ContentValues values = new ContentValues();
            values.put("no_dept", getNoDept());
            values.put("no_region", getNoRegion());
            values.put("nom", getNom());
            values.put("nom_std", getNomStd());
            values.put("surface", getSurface());
            values.put("date_creation", getDateCreation());
            values.put("chef_lieu", getChefLieu());
            values.put("url_wiki", getUrlWiki());
            try {
                db.insert("departements", "", values);
            } catch (Exception ex) {
                throw ex;
            }
        } else {
            throw new EmptyStackException();
        }
    }

    public String getNoDept() {
        return noDept;
    }

    public void setNoDept(String noDept) {
        this.noDept = noDept;
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

    public String getNomStd() {
        return nomStd;
    }

    public void setNomStd(String nomStd) {
        this.nomStd = nomStd;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getChefLieu() {
        return chefLieu;
    }

    public void setChefLieu(String chefLieu) {
        this.chefLieu = chefLieu;
    }

    public String getUrlWiki() {
        return urlWiki;
    }

    public void setUrlWiki(String urlWiki) {
        this.urlWiki = urlWiki;
    }

}
