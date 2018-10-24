package com.example.neumi.sqlitedepts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Departement {
    private SQLiteDatabase db;
    private Context ctxt;
    String noDept;
    int noRegion;
    private String nom;
    private String nomStd;
    private int surface;
    private Date dateCreation;
    private String chefLieu;
    private String urlWiki;
    private Region region;

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
            noDept = (cursor.getString(0));
            noRegion = (Integer.parseInt(cursor.getString(1)));
            nom = (cursor.getString(2));
            nomStd = (cursor.getString(3));
            surface = (Integer.parseInt(cursor.getString(4)));
            dateCreation = new SimpleDateFormat("yyyy-MM-dd").parse(cursor.getString(5));
            chefLieu = (cursor.getString(6));
            urlWiki = (cursor.getString(7));
            String[] colonnesReg = {"no_region", "nom"};
            String critereReg = "no_region = " + noRegion;
            Cursor cursorReg = db.query("regions", colonnesReg, critereReg, null, null, null, null);
            if (cursorReg.getCount() > 0) {
                cursorReg.moveToFirst();
                region = new Region(Integer.parseInt(cursorReg.getString(0)), cursorReg.getString(1));
            }
        } else {
            throw new GeoException(" Départements introuvable!");
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
            throw new GeoException("Numéro de département non renseigné");
        }
    }

    public void update() throws Exception {
        if (!getNoDept().isEmpty()) {
            ContentValues values = new ContentValues();
            values.put("no_dept", noDept);
            values.put("no_region", noRegion);
            values.put("nom", nom);
            values.put("nom_std", nomStd);
            values.put("surface", surface);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            values.put("date_creation", sdf.format(dateCreation));
            values.put("chef_lieu", chefLieu);
            values.put("url_wiki", urlWiki);
            String critere = "no_dept = '" + noDept + "'";
            db.update("departements", values, critere, null);
        } else {
            throw new GeoException("Numéro de département non renseigné");
        }
    }

    public void insert() throws Exception {
        if (!getNoDept().isEmpty()) {
            ContentValues values = new ContentValues();
            values.put("no_dept", noDept);
            values.put("no_region", noRegion);
            values.put("nom", nom);
            values.put("nom_std", nomStd);
            values.put("surface", surface);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            values.put("date_creation", sdf.format(dateCreation));
            values.put("chef_lieu", chefLieu);
            values.put("url_wiki", urlWiki);

            try {
                db.insert("departements", "", values);
            } catch (Exception ex) {
                throw ex;
            }
        } else {
            throw new GeoException("Numéro de département non renseigné");
        }
    }

    public String getNoDept() {
        return noDept;
    }

    public void setNoDept(String noDept) throws GeoException {
        Pattern p = Pattern.compile("^([0-9][0-9])|(97[12346])|(2[AB])$");
        Matcher m = p.matcher(noDept);
        Boolean b = m.matches();
        if (!noDept.isEmpty()) {
            if (b) {
                this.noDept = noDept;
            } else {
                throw new GeoException("Numéro de département non valide!");
            }
        } else {
            throw new GeoException("Numéro de département vide");
        }
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

    public void setNom(String nom) throws GeoException {
        if (!nom.isEmpty()) {
            this.nom = nom;
        } else {
            throw new GeoException("Nom de département vide");
        }

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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dateCreation);
    }

    public void setDateCreation(String dateCreation) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.dateCreation = sdf.parse(dateCreation);
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
