package com.tddev.infoproduit.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.fragment.app.Fragment;


import com.tddev.infoproduit.DBArticles;
import com.tddev.infoproduit.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    private String n_article;
    
    public PlaceholderFragment(String n){
        this.n_article=n;
    }


    public static PlaceholderFragment newInstance(String n) {
        return (new PlaceholderFragment(n));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DBArticles dbarticles=new DBArticles(getActivity().getApplicationContext(), "articles.db");

        View in= inflater.inflate(com.tddev.infoproduit.R.layout.fragment_info, container, false);
        TextView nom=(TextView) in.findViewById(R.id.Nom);
        TextView pht=(TextView) in.findViewById(R.id.pht);
        TextView pttc=(TextView) in.findViewById(R.id.pttc);
        TextView c=(TextView) in.findViewById(R.id.c);
        nom.setText(dbarticles.getNomArticle(Integer.valueOf(n_article)));
        pht.setText(dbarticles.getpht(n_article)+" € HT");
        pttc.setText(dbarticles.getpttc(n_article)+" € TTC");
        c.setText(dbarticles.getc(n_article));

        return in;
    }
}
