package com.example.android.moviepopup;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class FetchJSONData extends AsyncTask<Void, Void, Void> {
    public static ArrayList<MovieStructure> movieObjectList = new ArrayList<>();
    MainActivity main;
    public FetchJSONData(MainActivity main){
        this.main = main;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://api.themoviedb.org/3/movie/popular?api_key=bb527388b12351f469d567558bd8384d");
            JsonGetter(internetGetter(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        main.wiringAdapterToRecyclerView();
    }

    public String internetGetter(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public void JsonGetter (String jsonObject) {
        JSONObject moviePackage = null;
        try {
            moviePackage = new JSONObject(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONArray singleObject = moviePackage.getJSONArray("results");
            for (int i = 0; i < singleObject.length(); i++) {
                JSONObject theMovie = singleObject.getJSONObject(i);
                String titleMovie = theMovie.getString("title");
                String imageMovie = theMovie.getString("poster_path");
                String overviewMovie = theMovie.getString("overview");
                String releaseDateMovie = theMovie.getString("release_date");
                double voteAverageMovie = theMovie.getInt("vote_average");
                double popularityOfMovie = theMovie.getDouble("popularity");

                MovieStructure movieInfo = new MovieStructure(titleMovie,imageMovie,overviewMovie,releaseDateMovie,voteAverageMovie,popularityOfMovie);
                movieObjectList.add(movieInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
