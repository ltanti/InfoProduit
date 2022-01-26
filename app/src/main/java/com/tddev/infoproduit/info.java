package com.tddev.infoproduit;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.tddev.infoproduit.ui.main.SectionsPagerAdapter;
import com.tddev.infoproduit.databinding.ActivityInfoBinding;

public class info extends AppCompatActivity {
    DBArticles dbarticles=new DBArticles(this, "articles.db");

    private ActivityInfoBinding binding;
    public String n_article;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        n_article=intent.getStringExtra("Article");
        super.onCreate(savedInstanceState);

        binding = ActivityInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), n_article);
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);


    }

}