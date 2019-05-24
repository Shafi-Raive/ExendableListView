package com.example.shafi.research;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.shafi.research.Adapter.CustomAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter customAdapter;
    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        prepareListData();

        expandableListView = findViewById(R.id.idEX);
        customAdapter = new CustomAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(customAdapter);


    }

    private void prepareListData() {

        String[] header = getResources().getStringArray(R.array.header);
        String[] child = getResources().getStringArray(R.array.child);

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        for(int i= 0; i< header.length; i++){

            listDataHeader.add(header[i]);

            List<String> ans = new ArrayList<>();
            ans.add(child[i]);

            listDataChild.put(listDataHeader.get(i),ans);
        }
    }
}
