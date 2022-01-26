package com.tddev.infoproduit.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.tddev.infoproduit.DBArticles;
import com.tddev.infoproduit.R;
import com.tddev.infoproduit.info;

import org.w3c.dom.Text;

public class FournFragment extends Fragment {
    private String n_article;

    public FournFragment(String n){
        this.n_article=n;
    }
    public static FournFragment newInstance(String n) {
        return (new FournFragment(n));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DBArticles dbarticles=new DBArticles(getActivity().getApplicationContext(), "articles.db");
        View in= inflater.inflate(com.tddev.infoproduit.R.layout.fragment_page_fourn, container, false);
        TextView fourn=(TextView) in.findViewById(R.id.fourn);
        TextView pays=(TextView) in.findViewById(R.id.pays);
        TextView paht=(TextView) in.findViewById(R.id.paht);
        fourn.setText(dbarticles.getfourn(n_article));
        pays.setText(dbarticles.getpays(n_article));
        paht.setText(dbarticles.getpaht(n_article)+" €");

        return in;
    }
}
