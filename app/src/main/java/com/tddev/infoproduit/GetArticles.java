package com.tddev.infoproduit;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GetArticles  extends AsyncTask<String , Void , JSONObject> {

    private JSONObject value;
    private Context context;
    private DBArticles DBArticles;
    private Spinner sp;
    public GetArticles(Context con,DBArticles itc){
        this.context=con;
        this.DBArticles=itc;

    }
    @Override
    protected JSONObject doInBackground( String... strings) {

        URL url;
        HttpURLConnection urlConnection = null;
        String result=null;
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(5000);
            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                result=readStream(in);
                Log.v("CatalogClient", result);
                //return result;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if(urlConnection!=null)
                urlConnection.disconnect();
        }
        JSONObject json=null;
        try {
            json = new JSONObject(result);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }



        return json;
    }

    @Override
    protected void onPostExecute(JSONObject s) {
        if(s!=null){
            try {
                JSONArray items = s.getJSONArray("recordset");
                DBArticles.suppressARTICLES();
                for (int i = 0; i < items.length(); i++) {
                    JSONObject entry = items.getJSONObject(i);
                    int n_art = entry.getInt("N_Produit");
                    String nom = entry.getString("Nom_Produit");
                    String pvht=entry.getString("Prix_Vente_Euro");
                    String Num=entry.getString("Num_Produit");
                    String pttc=entry.getString("Prix TTC");
                    String dpa=entry.getString("DernierPrixAchat_Euro");
                    String c=entry.getString("CodeStock");
                    String pays=entry.getString("Champ15");
                    String Qtes=entry.getString("Qte_Stock");
                    String QteBL=entry.getString("Qte_BL");
                    String QteBR= entry.getString("Qte_BR");
                    String Qte_cde=entry.getString("Qte_cde_client");
                    String paht=entry.getString("Prix_HT_Euro");
                    String Nom_four=entry.getString("Nom_Fournisseur");
                    String cb= entry.getString("Champ3");
                    DBArticles.insertARTICLES(n_art, nom,pvht,Num,pttc,dpa,c,pays,Qtes,QteBL,QteBR,Qte_cde,paht,Nom_four,cb);

                    //Log.i("NOM : ", String.valueOf(n_itc));
                }

                //loadSpinnerData(DBArticles, sp);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        else{
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, "Erreur : Actualiasations des employés impossible", duration);
            toast.show();
        }
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    private void loadSpinnerData(DBArticles db, Spinner sp) {

        List<String> labels = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sp.setAdapter(dataAdapter);
    }

}
