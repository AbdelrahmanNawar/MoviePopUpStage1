package com.example.android.moviepopup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements SendingIndex{
    public static RecyclerView myRecyclerView;
    public static GridLayoutManager myManager;
    public static MovieAdapter myAdapter;
    private static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FetchJSONData(this).execute();
        myRecyclerView = findViewById(R.id.movie_recycler_id);
        myManager = new GridLayoutManager(this, calculateNoOfColumns(this));
    }
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 200;
        int noOfColumns = (int) (dpWidth / scalingFactor);
        if(noOfColumns < 2)
            noOfColumns = 2;
        return noOfColumns;
    }

    public void wiringAdapterToRecyclerView() {
        myAdapter = new MovieAdapter(this);
        myAdapter.sortHolderType = FetchJSONData.pMovieObjectList;
        myAdapter.notifyDataSetChanged();
        myRecyclerView.setLayoutManager(myManager);
        myRecyclerView.setAdapter(myAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sorting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.sorting_by_highest_rate:
                myAdapter.sortHolderType = FetchJSONData.trMovieObjectList;
                myAdapter.notifyDataSetChanged();
                break;
            case R.id.sorting_by_most_popular:
                myAdapter.sortHolderType = FetchJSONData.pMovieObjectList;
                myAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(int index) {
        intent = new Intent(this, MovieDetails.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }
}
