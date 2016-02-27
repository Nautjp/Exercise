package ca.cestmoi.xps_630_w7.exercise;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class RecettesAdd extends ListActivity  implements android.view.View.OnClickListener{

    Button btnAdd,btnGetAll;
    TextView aliments_Id;

    @Override
    public void onClick(View view) {
        if (view== findViewById(R.id.btnAdd)){

            Intent intent = new Intent(this,RecettesDetail.class);
            intent.putExtra("aliments_Id",0);
            startActivity(intent);

        }else {

            RecettesRepo repo = new RecettesRepo(this);

            ArrayList<HashMap<String, String>> alimentsList =  repo.getRecettesList();
            if(alimentsList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                        aliments_Id = (TextView) view.findViewById(R.id.aliments_Id);
                        String alimentsId = aliments_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),RecettesDetail.class);
                        objIndent.putExtra("aliments_Id", Integer.parseInt( alimentsId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter( RecettesAdd.this,alimentsList, R.layout.view_aliments_entry, new String[] { "id","name"}, new int[] {R.id.aliments_Id, R.id.aliments_name});
                setListAdapter(adapter);
            }else{
                Toast.makeText(this,"No aliments!",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aliments_add);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

    }


}
