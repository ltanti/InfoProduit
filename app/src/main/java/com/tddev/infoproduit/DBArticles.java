package com.tddev.infoproduit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBArticles extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "derbez";
    private static final String TA_ARTICLES = "ARTICLES";

    private static final String KEY_ID = "Id";
    private static final String KEY_N_ARTICLES = "N_ARTICLES";
    private static final String KEY_NAME = "Nom";
    private static final String KEY_PRENOM = "Prenom";
    // private static final String BDD = String.format("CREATE TABLE%s(%s integer primary key autoincrement, %sinteger%sstring%sstring);", TA_ARTICLES, KEY_ID, KEY_N_ARTICLES, KEY_NAME, KEY_PRENOM);
    private static final String Create_BDD = "CREATE TABLE " + TA_ARTICLES + "(" + KEY_ID + " integer primary key autoincrement, " + KEY_N_ARTICLES + " INTEGER, " + KEY_NAME + " TEXT, " + KEY_PRENOM + " TEXT) ";
    private static final String Cre_BDD = "CREATE TABLE ARTICLES ( N_ARTICLES INTEGER, Nom TEXT, PRIXHT TEXT, Num_produit TEXT, PRIXTTC TEXT, DERPACHAT TEXT, C TEXT, PAYSFOUR TEXT, QTESTOCK TEXT, QTEBL TEXT, QTEBR TEXT, QTECMDC TEXT, PRIXACHATHT TEXT, NOMFOURN TEXT, CODEBARRE TEXT)";

    public DBArticles(Context context, String DATABASE) {
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    ;

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(Cre_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBArticles.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TA_ARTICLES);
        onCreate(db);
    }

    public void suppressARTICLES() {
        this.getWritableDatabase().execSQL("DELETE FROM ARTICLES");
    }
    //private static final String Cre_BDD = "CREATE TABLE ARTICLES ( Id INTEGER PRIMARY KEY AUTOINCREMENT, N_ARTICLES INTEGER, Nom TEXT, PRIXHT TEXT, Num_produit TEXT, PRIXTTC TEXT, DERPACHAT TEXT, C TEXT, PAYSFOUR TEXT, QTESTOCK TEXT, QTEBL TEXT, QTEBR TEXT, QTECMDC TEXT, PRIXACHATHT TEXT, NOMFOURN TEXT, CODEBARRE TEXT)";

    public void insertARTICLES(int n, String name, String pht, String num, String pttc, String dpa, String c, String pays, String qtes, String qteBl, String QteBR, String qteCde, String paht, String nf, String cb ) {
        /*name=name.replace("\"","\'");
        sname=sname.replace("\"","\'");
        */
        name=name.replace("'","");
        nf=nf.replace("'","");
        String strsql = "insert into ARTICLES values ("
                + n + ", '" + name + "','"+pht+ "','"+num+ "','"+pttc+ "','"+dpa+ "','"+c+ "','"+pays+ "','"+qtes+ "','"+qteBl+ "','"+QteBR+ "','"+qteCde+ "','"+paht+ "','"+nf+ "','"+cb+"')";
        //String strsql="insert into ARTICLES (N_ARTICLES, Nom, Prenom) values (5, 'Tanti', 'Luca');";
        this.getWritableDatabase().execSQL(strsql);
        Log.i("InsertARTICLES", "INVOKE");
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String,String>> getStock(String nart){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> heureList = new ArrayList<>();
        String query;
        query = "SELECT QTESTOCK, QTEBL, QTEBR, QTECMDC FROM ARTICLES WHERE N_ARTICLES='"+nart+"'";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> heure = new HashMap<>();
            heure.put("QTESTOCk",cursor.getString(cursor.getColumnIndex("QTESTOCK")));
            heure.put("QTEBL",cursor.getString(cursor.getColumnIndex("QTEBL")));
            heure.put("QTEBR",cursor.getString(cursor.getColumnIndex("QTEBBR")));
            heure.put("QTECMDC",cursor.getString(cursor.getColumnIndex("QTECMDC")));
            heureList.add(heure);
        }
        return  heureList;
    }

    public List<String> getAllLabels() {
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM ARTICLES ORDER BY Nom ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String str = cursor.getString(2) + " " + cursor.getString(3) + "/" + cursor.getString(1);
                list.add(str);//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public String getQteStock(String n){
        String query = "SELECT QTESTOCK From ARTICLES WHERE N_ARTICLES=" + n;
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(query, null);
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String getQteBR(String n){
        String query = "SELECT QTEBR From ARTICLES WHERE N_ARTICLES=" + n;
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(query, null);
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    public String getQteBL(String n){
        String query = "SELECT QTEBL From ARTICLES WHERE N_ARTICLES=" + n;
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(query, null);
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String getQteCmd(String n){
        String query = "SELECT QTECMDC From ARTICLES WHERE N_ARTICLES=" + n;
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(query, null);
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    public String getfourn(String n){
        String query = "SELECT NOMFOURN From ARTICLES WHERE N_ARTICLES=" + n;
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(query, null);
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    public String getpays(String n){
        String query = "SELECT PAYSFOUR From ARTICLES WHERE N_ARTICLES=" + n;
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(query, null);
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    public String getpaht(String n){
        String query = "SELECT PRIXACHATHT From ARTICLES WHERE N_ARTICLES=" + n;
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(query, null);
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    public String getpht(String n){
        String query = "SELECT PRIXHT From ARTICLES WHERE N_ARTICLES=" + n;
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(query, null);
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    public String getpttc(String n){
        String query = "SELECT PRIXTTC From ARTICLES WHERE N_ARTICLES=" + n;
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(query, null);
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    public String getc(String n){
        String query = "SELECT C From ARTICLES WHERE N_ARTICLES=" + n;
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(query, null);
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public String getNameARTICLES(int nARTICLES) {
        String query = "SELECT Nom From ARTICLES WHERE N_ARTICLES=" + String.valueOf(nARTICLES);
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(query, null);
            return (cursor.moveToFirst()) ? cursor.getString(0) : null;
        } finally {
            if (cursor != null) cursor.close();
        }
    }
    public int articleExist(String n){
        String sql= "SELECT COUNT (*) FROM ARTICLES WHERE N_ARTICLES="+n;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public String getNomArticle(int np){
        String sqlquery="SELECT Nom FROM ARTICLES WHERE N_ARTICLES="+np;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlquery,null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public String getUnite(int np){
        String sqlquery="SELECT Unite FROM ARTICLES WHERE N_ARTICLES="+np;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlquery,null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }
}
