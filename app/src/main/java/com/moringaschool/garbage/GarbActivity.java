package com.moringaschool.garbage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GarbActivity extends AppCompatActivity {
    @BindView(R.id.locationTextView)
    TextView mLocationTextView;
    @BindView(R.id.listView)
    ListView mListView;
    private String[] restaurants = new String[] {"Truck A", "Truck B",
            "Truck C", "Truck D", "Truck E", "Truck F",
            "Truck G", "Truck H", "Truck I", "Truck J",
            "Truck K", "Truck L"};
    private String[] cuisines = new String[] {"Zone 1", "Zone 2", "Zone 3",
            "Zone 4", "Zone 5", "Zone 6", "Zone 7", "Zone 8", "Zone 9",
            "Zone 10", "Zone 11", "Zone 12", "Zone 13 "};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garb);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        garbArrayAdapter adapter = new garbArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants, cuisines);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(GarbActivity.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });
        mLocationTextView.setText("Trucks near you: " + location);
    }


}
