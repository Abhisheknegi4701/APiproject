package com.abhishek.internshipproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class DataShow extends AppCompatActivity {
    ArrayList<country> countrylist = new ArrayList<>();
    CardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_show);

        AndroidNetworking.initialize(getApplicationContext());

        AndroidNetworking.get("https://restcountries.eu/rest/v2/region/{region}")
                .addPathParameter("region", "Asia")
                .addQueryParameter("limit", "3")
                .addHeaders("token", "1234")
                .setTag("test")
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject respons = response.getJSONObject(i);
                                String name = respons.getString("name");
                                String capital = respons.getString("capital");
                                String flag = respons.getString("flag");
                                String region = respons.getString("region");
                                String subregion = respons.getString("subregion");
                                String population = respons.getString("population");
                                JSONArray borders = respons.getJSONArray("borders");
                                JSONArray language = respons.getJSONArray("languages");
                                countrylist.add(new country(name, capital, region, flag, subregion, population, borders, language));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

        RecyclerView recyclerView = findViewById(R.id.listcountries);

        //Just your list of objects, in your case the list that comes from the db
        adapter = new CardAdapter(countrylist, this);

        //RecyclerView needs a layout manager in order to display data so here we create one
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        //Here we set the layout manager and the adapter to the listview
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.delete) {

            Toast.makeText(getApplicationContext(), "Deleted User's Login Detail", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}