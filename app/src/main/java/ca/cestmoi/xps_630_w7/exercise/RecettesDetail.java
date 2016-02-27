package ca.cestmoi.xps_630_w7.exercise;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import android.graphics.Color;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;




public class RecettesDetail extends AppCompatActivity implements android.view.View.OnClickListener{

    Button btnSave ,  btnDelete;
    Button btnClose;
    EditText editTextTitle;
    EditText editTextTime;
    EditText editTextDescription;
    EditText editTextMoreInfo;
    private int _Recettes_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recettes_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextTime = (EditText) findViewById(R.id.editTextTime);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        editTextMoreInfo = (EditText) findViewById(R.id.editTextMoreInfo);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        _Recettes_Id =0;
        Intent intent = getIntent();
        _Recettes_Id =intent.getIntExtra("recettes_Id", 0);
        RecettesRepo repo = new RecettesRepo(this);
        Recettes recettes = new Recettes();
        recettes = repo.getRecettesById(_Recettes_Id);


        editTextTitle.setText(recettes.title);
        editTextTime.setText(String.valueOf(recettes.time));
        editTextDescription.setText(recettes.description);
        editTextMoreInfo.setText(recettes.moreinfo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accueil, menu);
        return true;
    }

    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            RecettesRepo repo = new RecettesRepo(this);
            Recettes recettes = new Recettes();
            recettes.title=editTextTitle.getText().toString();
            recettes.time= Integer.parseInt(editTextTime.getText().toString());
            recettes.description=editTextDescription.getText().toString();
            recettes.moreinfo=editTextMoreInfo.getText().toString();
            recettes.recettes_ID=_Recettes_Id;

            if (_Recettes_Id==0){
                _Recettes_Id = repo.insert(recettes);

                Toast.makeText(this,"New Recettes Insert",Toast.LENGTH_SHORT).show();
            }else{

                repo.update(recettes);
                Toast.makeText(this,"Recettes Record updated",Toast.LENGTH_SHORT).show();
            }
        }else if (view== findViewById(R.id.btnDelete)){
            RecettesRepo repo = new RecettesRepo(this);
            repo.delete(_Recettes_Id);
            Toast.makeText(this, "Recettes Record Deleted", Toast.LENGTH_SHORT);
            finish();
        }else if (view== findViewById(R.id.btnClose)){
            finish();
        }


    }

}
