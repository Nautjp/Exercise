package ca.cestmoi.xps_630_w7.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainEntry extends AppCompatActivity implements View.OnClickListener {

    Button buttonAddAliments;
    Button buttonAddRecettes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        buttonAddAliments = (Button) findViewById(R.id.buttonAddAliments);
        buttonAddAliments.setOnClickListener(this);
        buttonAddRecettes = (Button) findViewById(R.id.buttonAddRecettes);
        buttonAddRecettes.setOnClickListener(this);
    }

    private void buttonAddAlimentsClick()
    {
        startActivity(new Intent("ca.cestmoi.xps_630_w7.exercise.AlimentsAdd"));
    }
    private void buttonAddRecettesClick()
    {
        startActivity(new Intent("ca.cestmoi.xps_630_w7.exercise.RecettesAdd"));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAddAliments:
                buttonAddAlimentsClick();
                break;
            case R.id.buttonAddRecettes:
                buttonAddRecettesClick();
                break;

        }
    }
}
