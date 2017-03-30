package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.os.AsyncTask;

import com.filet.bioscoopfilet.DomainModel.Actor;
import com.filet.bioscoopfilet.DomainModel.Cinema;
import com.filet.bioscoopfilet.DomainModel.Film;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Bart on 30-3-2017.
 */

public class FilmApiConnector extends AsyncTask<String, Void, String> {

    private FilmsAvailable listener = null;

    public FilmApiConnector(FilmsAvailable listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {

        InputStream inputStream = null;
        int responseCode = -1;

        //URL we get from .execute()
        String filmUrl = params[0];
        String result = "";

        try {
            //Make URL object
            URL url = new URL(filmUrl);
            //Open connection on URL
            URLConnection urlConnection = url.openConnection();

            if (!(urlConnection instanceof HttpURLConnection)) {
                return null;
            }

            // Initiate a HTTP connection
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");

            // Do a request on the HTTP connection
            httpConnection.connect();

            // Check if connection is made using a response code
            responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpConnection.getInputStream();
                result = getStringFromInputStream(inputStream);
            } else {
            }
        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {

        try {
            //Top level object
            JSONObject jsonObject = new JSONObject(result);

            // Getting all albums and start looping
            JSONArray films = jsonObject.getJSONArray("results");

            ArrayList<Film> returnFilms = new ArrayList<>();

            for (int i = 0; i < films.length(); i++) {

                //Array level objects and products
                JSONObject film = films.getJSONObject(i);

                // Get title, specsTag and summary
                String filmID = film.optString("id");
                String title = film.optString("title");
                String version = "OV 2D";
                String language = film.optString("original_language");
                String release = film.optString("release_date");
                String genre = "Comedy";
                String length = "90";
                String age = "12";
                String description = film.optString("overview");
                String IMDBurl = "http";
                String IMDBScore = film.optString("vote_average");
                String trailerURL = "http";
                String posterURL = "https://image.tmdb.org/t/p/w500"+film.optString("poster_path");
                String director = "man";

                //Create new Product
                Film f = new Film(Integer.valueOf(filmID), new Cinema(1,null,null,null,null,null),title, version, language, release
                        , genre, Integer.valueOf(length), Integer.valueOf(age), description, IMDBurl, IMDBScore, trailerURL, posterURL, director);

                //Add Product to list
                returnFilms.add(f);


            }
            //Callback
            listener.filmsAvailable(returnFilms);
        } catch (JSONException ex) {
            //Log.e(TAG, "onPostExecute JSONException " + ex.getLocalizedMessage());

        }

    }

    //Convert InputStream to String
    private String getStringFromInputStream(InputStream inputStream) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    //Callback interface
    public interface FilmsAvailable {
        ;

        void filmsAvailable(ArrayList<Film> result);
    }
}
