package com.azsm.reeduacare.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.azsm.reeduacare.R;
import com.azsm.reeduacare.view.adapter.InternalCourceAdapter;
import com.azsm.reeduacare.model.CourceWiseModel;

import java.util.ArrayList;

public class ProvideCourceCategory extends AppCompatActivity {
    RecyclerView recyclerView;
    String catname;
    Toolbar toolbar;
    private ArrayList<CourceWiseModel> provideCourceModelClasses = new ArrayList<>();
    private Object LinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide_cource_category);
        recyclerView = findViewById(R.id.recyclervieww);
        ImageView backpress = findViewById(R.id.backMyOrders);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();            }
        });


        Bundle bundle=getIntent().getExtras();


        catname=bundle.getString("catname");
       // toolbar.setTitle(catname);
        Toast.makeText(this, ""+catname, Toast.LENGTH_SHORT).show();
        if(catname.equals("IIT")){
            getcatiit();
        }if(catname.equals("Foundation")){
            getcatfoundaation();
        }if(catname.equals("Special Prepration")){
            getcatspecialcource();
        }if(catname.equals("NEET")){
            getcatneet();
        }if(catname.equals("JEE/Advance")){
            getcatiit();
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    //now call onBackPressed to navigate from activity to fragment


    private void getcatiit()
    {

        provideCourceModelClasses.add(new CourceWiseModel(R.drawable.atoozlogo,"IIT Mains"));
        provideCourceModelClasses.add(new CourceWiseModel(R.drawable.atoozlogo,"IIT Advance"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(ProvideCourceCategory.this);
        recyclerView.setLayoutManager(layoutManager);
        InternalCourceAdapter courceProviderAdapter = new InternalCourceAdapter( provideCourceModelClasses,getApplicationContext());
        recyclerView.setAdapter(courceProviderAdapter);
    }

    private void getcatfoundaation()
    {

        provideCourceModelClasses.add(new CourceWiseModel(R.drawable.atoozlogo,"Senior Cbse(11th to 12th)"));
        provideCourceModelClasses.add(new CourceWiseModel(R.drawable.atoozlogo,"Junior Cbse(6th to 11th)"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(ProvideCourceCategory.this);
        recyclerView.setLayoutManager(layoutManager);
        InternalCourceAdapter courceProviderAdapter = new InternalCourceAdapter( provideCourceModelClasses,getApplicationContext());
        recyclerView.setAdapter(courceProviderAdapter);
    }
    private void getcatneet()
    {
        provideCourceModelClasses.add(new CourceWiseModel(R.drawable.atoozlogo,"NEET"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(ProvideCourceCategory.this);
        recyclerView.setLayoutManager(layoutManager);
        InternalCourceAdapter courceProviderAdapter = new InternalCourceAdapter( provideCourceModelClasses,getApplicationContext());
        recyclerView.setAdapter(courceProviderAdapter);
    }
    private void getcatspecialcource()
    {
        provideCourceModelClasses.add(new CourceWiseModel(R.drawable.atoozlogo,"NTSE"));
        provideCourceModelClasses.add(new CourceWiseModel(R.drawable.atoozlogo,"KVPY"));
        provideCourceModelClasses.add(new CourceWiseModel(R.drawable.atoozlogo,"NSEP"));
        provideCourceModelClasses.add(new CourceWiseModel(R.drawable.atoozlogo,"NDA"));
        provideCourceModelClasses.add(new CourceWiseModel(R.drawable.atoozlogo,"SCRM"));
        provideCourceModelClasses.add(new CourceWiseModel(R.drawable.atoozlogo,"RMO"));
        provideCourceModelClasses.add(new CourceWiseModel(R.drawable.atoozlogo,"IISO"));
      //  provideCourceModelClasses.add(new ProvideCourceModelClass(R.drawable.atoozlogo,"SCC/BANK"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(ProvideCourceCategory.this);
        recyclerView.setLayoutManager(layoutManager);
        InternalCourceAdapter courceProviderAdapter = new InternalCourceAdapter( provideCourceModelClasses,getApplicationContext());
        recyclerView.setAdapter(courceProviderAdapter);
    }
}
