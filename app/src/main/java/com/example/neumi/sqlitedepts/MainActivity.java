package com.example.neumi.sqlitedepts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;

public class MainActivity extends AppCompatActivity {

    private Departement dept;
    private EditText etSearch;
    private EditText etNoDept;
    private EditText etNoRegion;
    private EditText etNom;
    private EditText etNomStd;
    private EditText etSurface;
    private EditText etDateCreation;
    private EditText etChefLieu;
    private EditText etUrlWiki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dept = new Departement(this);
        setContentView(R.layout.activity_main);
        etSearch = findViewById(R.id.etSearch);
        etNoDept = findViewById(R.id.etNoDept);
        etNoRegion = findViewById(R.id.etNoRegion);
        etNom = findViewById(R.id.etNom);
        etNomStd = findViewById(R.id.etNomStd);
        etSurface = findViewById(R.id.etSurface);
        etDateCreation = findViewById(R.id.etDateCreation);
        etChefLieu = findViewById(R.id.etChefLieu);
        etUrlWiki = findViewById(R.id.etUrlWiki);
    }

    public void btSearch(View view) {
        String search = etSearch.getText().toString();
        try {
            dept.select(search);
            transfertDepObj();
            etNoDept.setEnabled(false);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void btInsert(View view) {
        try {
            transfertObjDep();
            dept.insert();
            clear();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void btClear(View view) {
        clear();
        dept = new Departement(this);
    }

    public void btUpdate(View view) {
        try {
            transfertObjDep();
            dept.update();
            clear();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void btDelete(View view) {
        try {
            dept = new Departement(this, etNoDept.getText().toString());
            dept.delete();
            clear();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void clear() {
        etSearch.getText().clear();
        etNoDept.getText().clear();
        etNoRegion.getText().clear();
        etNom.getText().clear();
        etNomStd.getText().clear();
        etSurface.getText().clear();
        etDateCreation.getText().clear();
        etChefLieu.getText().clear();
        etUrlWiki.getText().clear();
        etNoDept.setEnabled(true);
    }

    private void transfertDepObj() {
        etNoDept.setText(dept.getNoDept());
        etNoRegion.setText(String.valueOf(dept.getNoRegion()));
        etNom.setText(dept.getNom());
        etNomStd.setText(dept.getNomStd());
        etSurface.setText(String.valueOf(dept.getSurface()));
        etDateCreation.setText(dept.getDateCreation());
        etChefLieu.setText(dept.getChefLieu());
        etUrlWiki.setText(dept.getUrlWiki());
    }

    private void transfertObjDep() throws GeoException, ParseException {
        dept.setNoDept(etNoDept.getText().toString());
        dept.setNoRegion(Integer.parseInt(etNoRegion.getText().toString()));
        dept.setNom(etNom.getText().toString());
        dept.setNomStd(etNomStd.getText().toString());
        dept.setSurface(Integer.parseInt(etSurface.getText().toString()));
        dept.setDateCreation(etDateCreation.getText().toString());
        dept.setChefLieu(etChefLieu.getText().toString());
        dept.setUrlWiki(etUrlWiki.getText().toString());
    }
}
