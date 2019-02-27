package com.example.mgebhart16woche20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Filterable {
    ListView listView;
    SearchView searchView;
    String[] splitedLine;
    List<Consumer> consumerList = new ArrayList<>();
    List<String> consumerNames = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, consumerNames);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        searchView = findViewById(R.id.searchView);
        readCSV();
        listView.setAdapter(arrayAdapter);

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



    @Override
    public Filter getFilter() {
        return null;
    }
}
