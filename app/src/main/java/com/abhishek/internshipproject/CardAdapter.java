package com.abhishek.internshipproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private final List<country> countrylist;
    Context context;

    public CardAdapter(List<country> rewayaList, Context context) {
        super();
        this.countrylist = rewayaList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wigetcard, viewGroup, false);
        return new CardViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        //Here you bind your views with the data from each object from the list

        country rewaya = countrylist.get(position);
        holder.name.setText("name: "+rewaya.name);
        holder.capital.setText("Capital: "+rewaya.capital);
        holder.population.setText("Population: "+rewaya.population);
        holder.region.setText("Region: "+rewaya.region);
        holder.subregion.setText("Sub Region: "+rewaya.subregion);
        holder.Borders.setText("Borders: "+rewaya.borders.toString());
        Picasso.get().load(rewaya.flag).placeholder(R.drawable.placeholder).into(holder.Image);
        String str = "";

            try {
                for (int i = 0; i < rewaya.languages.length(); i++) {
                    JSONObject jsonObject1 = null;
                    jsonObject1 = rewaya.languages.getJSONObject(i);
                    String iso639_1 = jsonObject1.getString("iso639_1").toString();
                    String iso639_2 = jsonObject1.getString("iso639_2").toString();
                    String name = jsonObject1.getString("name").toString();
                    String nativeName = jsonObject1.getString("nativeName").toString();
                    str += "\n iso639_1: " + iso639_1 + "\n iso639_2: " + iso639_2 + "\n name: " + name + "\n nativeName : " + nativeName + "\n";
                    holder.language.setText("Langauage: "+str);
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }

    }

    @Override
    public int getItemCount() {
        return countrylist.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        public ImageView Image;
        public TextView name, capital, region, subregion, population, Borders, language;

        public CardViewHolder(View itemView) {
            super(itemView);
            Image = itemView.findViewById(R.id.flag);
            name = itemView.findViewById(R.id.name);
            capital = itemView.findViewById(R.id.capital);
            region = itemView.findViewById(R.id.region);
            subregion = itemView.findViewById(R.id.subregion);
            population = itemView.findViewById(R.id.population);
            Borders = itemView.findViewById(R.id.borders);
            language = itemView.findViewById(R.id.language);

        }
    }
}