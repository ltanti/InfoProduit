package com.tddev.infoproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button synch;
    private Button ok;
    private EditText article;
    DBArticles dbarticles=new DBArticles(this, "articles.db");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        synch=(Button) findViewById(R.id.Synch);
        synch.setOnClickListener(this);
        ok=(Button) findViewById(R.id.ok);
        ok.setOnClickListener(this);
        article=(EditText) findViewById(R.id.article);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Synch:
                new GetArticles(this,dbarticles).execute("http://srv-everwin:8081/getinfopep");
                break;

            case R.id.ok:
                Intent intent= new Intent(this, info.class);
                intent.putExtra("Article",article.getText().toString() );
                startActivity(intent);
        }
    }
}