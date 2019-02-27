package com.example.mgebhart16woche20;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    SearchView searchView;
    String[] splitedLine;
    List<Consumer> consumerList = new ArrayList<>();
    List<String> consumerNames = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, consumerNames);

        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        searchView = findViewById(R.id.searchView);

        readCSV();
        listView.setAdapter(arrayAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(consumerNames.contains(query))
                {
                    arrayAdapter.getFilter().filter(query);
                }else
                {
                    Toast.makeText(MainActivity.this, "No Match found", Toast.LENGTH_LONG);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


/*
        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(intent.getAction()))
        {
            //String query = intent.getStringExtra(SearchManager.Query);

        }
        */
    }


    public void readCSV() {
        int id = 0;
        String firstname = null;
        String lastname = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("customers_data.csv")));
            String line = reader.readLine();
            while((line = reader.readLine())!= null)
            {
                splitedLine = line.split(",");

                        id = Integer.valueOf(splitedLine[0]);
                        firstname = splitedLine[1];
                        lastname = splitedLine[2];
                        Consumer c = new Consumer(id, firstname, lastname);
                        consumerList.add(c);


                    }
            for(Consumer consumer : consumerList)
            {
                String peronName = consumer.getFirstname() + ", " + consumer.getLastname();
                consumerNames.add(peronName);
            }
                //setListView(consumerList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    /*
    private void getConsumers(String query)
    {
        List<String> output = new ArrayList<>();
        
        if(searchView != null)
        {
            for (String item : consumerNames) {
                if(item.toUpperCase().startsWith(query.toUpperCase())) {
                    output.add(item);
                }
            }
        }else
        {
            output=consumerNames;
        }
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, output);
        listView.setAdapter(arrayAdapter);
    }

    Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if(constraint != null && consumerNames != null)
            {
                for(int i = 0; i < consumerNames.size(); i++)
                {

                }
            }
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }

    };


    @Override
    public Filter getFilter() {
        return null;
    }
    */
}
