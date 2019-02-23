package com.example.android.moviepopup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Intent intent = getIntent();
        int i = intent.getIntExtra("index",0);
        ImageView image = findViewById(R.id.image_in_details);
        TextView title = findViewById(R.id.title_in_details);
        TextView voteAverage = findViewById(R.id.vote_Average);
        TextView releaseDate = findViewById(R.id.release_date_in_details);
        TextView overview = findViewById(R.id.overview__in_details);

        Picasso.get()
                .load("http://image.tmdb.org/t/p/w185/" + FetchJSONData.movieObjectList.get(i).imageMovie)
                .placeholder(R.drawable.loadingicon)
                .error(R.drawable.internetfailed)
                .into(image);
        title.setText(FetchJSONData.movieObjectList.get(i).titleMovie);
        releaseDate.setText(FetchJSONData.movieObjectList.get(i).releaseDateMovie);
        overview.setText(FetchJSONData.movieObjectList.get(i).overviewMovie);
        voteAverage.setText(FetchJSONData.movieObjectList.get(i).voteAverage + "/10");

    }
}
