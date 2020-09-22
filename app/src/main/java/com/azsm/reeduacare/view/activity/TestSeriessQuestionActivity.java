package com.azsm.reeduacare.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.azsm.reeduacare.R;
import com.azsm.reeduacare.view.adapter.MyAdapter;
import com.google.android.material.tabs.TabLayout;

public class TestSeriessQuestionActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ImageView backpress;
    private Bundle bundle;
    public  static String idt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_seriess_question);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);
        backpress = findViewById(R.id.backMyOrders);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();            }
        });
       // toolbar = findViewById(R.id.toolbarrr);
         bundle = getIntent().getExtras();
            idt = bundle.getString("idt");
       // Toast.makeText(this, "id->"+id, Toast.LENGTH_SHORT).show();

        setHasOptionsMenu(true);
       // toolbar.setTitle("Full Syllabus Tests");
        tabLayout.addTab(tabLayout.newTab().setText("Active"));
        tabLayout.addTab(tabLayout.newTab().setText("Atempted"));


        final MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), getApplicationContext(), tabLayout.getTabCount(), idt);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }





    private void setHasOptionsMenu(boolean b) {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}