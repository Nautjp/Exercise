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




public class AlimentsDetail extends AppCompatActivity implements android.view.View.OnClickListener{

    Button btnSave ,  btnDelete;
    Button btnClose;
    EditText editTextName;
    EditText editTextMarque;
    EditText editTextQte;
    EditText editTextType;
    EditText editTextProperty;
    private int _Aliments_Id=0;
    String[] language ={"C","C++","Java",".NET","iPhone","Android","ASP.NET","PHP"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aliments_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextMarque = (EditText) findViewById(R.id.editTextMarque);
        editTextQte = (EditText) findViewById(R.id.editTextQte);
        editTextType = (EditText) findViewById(R.id.editTextType);
        editTextProperty = (EditText) findViewById(R.id.editTextProperty);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,language);
        AutoCompleteTextView editTextType = (AutoCompleteTextView) findViewById(R.id.editTextType);
        editTextType.setThreshold(1);//will start working from first character
        editTextType.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        editTextType.setTextColor(Color.BLACK);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        _Aliments_Id =0;
        Intent intent = getIntent();
        _Aliments_Id =intent.getIntExtra("aliments_Id", 0);
        AlimentsRepo repo = new AlimentsRepo(this);
        Aliments aliments = new Aliments();
        aliments = repo.getAlimentsById(_Aliments_Id);

        editTextQte.setText(String.valueOf(aliments.qte));
        editTextName.setText(aliments.name);
        editTextMarque.setText(aliments.marque);
        editTextType.setText(aliments.type);
        editTextProperty.setText("user");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accueil, menu);
        return true;
    }

    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            AlimentsRepo repo = new AlimentsRepo(this);
            Aliments aliments = new Aliments();
            aliments.qte= Integer.parseInt(editTextQte.getText().toString());
            aliments.marque=editTextMarque.getText().toString();
            aliments.name=editTextName.getText().toString();
            aliments.type=editTextType.getText().toString();
            aliments.property=editTextProperty.getText().toString();
            aliments.aliments_ID=_Aliments_Id;

            if (_Aliments_Id==0){
                _Aliments_Id = repo.insert(aliments);

                Toast.makeText(this,"New Aliments Insert",Toast.LENGTH_SHORT).show();
            }else{

                repo.update(aliments);
                Toast.makeText(this,"Aliments Record updated",Toast.LENGTH_SHORT).show();
            }
        }else if (view== findViewById(R.id.btnDelete)){
            AlimentsRepo repo = new AlimentsRepo(this);
            repo.delete(_Aliments_Id);
            Toast.makeText(this, "Aliments Record Deleted", Toast.LENGTH_SHORT);
            finish();
        }else if (view== findViewById(R.id.btnClose)){
            finish();
        }


    }

}
