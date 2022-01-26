package com.tddev.infoproduit.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.tddev.infoproduit.DBArticles;
import com.tddev.infoproduit.R;

public class StockFragment extends Fragment {

    private String n_article;
    public StockFragment(String n){
        n_article=n;
    }


    public static StockFragment newInstance(String n) {
        return (new StockFragment(n));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DBArticles dbarticles=new DBArticles(getActivity().getApplicationContext(), "articles.db");

        View in= inflater.inflate(com.tddev.infoproduit.R.layout.fragment_page_stock, container, false);
        TextView qtestock=(TextView) in.findViewById(R.id.qtestock);
        TextView qteBL=(TextView) in.findViewById(R.id.qteBL);
        TextView qteBR=(TextView) in.findViewById(R.id.qteBR);
        TextView qtecmd=(TextView) in.findViewById(R.id.qtecde);
        qtestock.setText(dbarticles.getQteStock(n_article));
        qteBL.setText(dbarticles.getQteBL(n_article));
        qteBR.setText(dbarticles.getQteBR(n_article));
        qtecmd.setText(dbarticles.getQteCmd(n_article));

        return in;
    }
}