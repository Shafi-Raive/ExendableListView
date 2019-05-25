package com.example.shafi.research;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.shafi.research.Adapter.CustomAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter customAdapter;
    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private  int lastCollapse  = -1;

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

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                String grpName = listDataHeader.get(groupPosition);
                Toast.makeText(getApplicationContext(), grpName, Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                String grpName = listDataHeader.get(groupPosition);
                Toast.makeText(getApplicationContext(), grpName  +"  is collapsed", Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                String childString = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                Toast.makeText(getApplicationContext(), childString, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastCollapse != -1 && lastCollapse != groupPosition){

                    expandableListView.collapseGroup(lastCollapse);
                }
                lastCollapse = groupPosition;
            }
        });


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
