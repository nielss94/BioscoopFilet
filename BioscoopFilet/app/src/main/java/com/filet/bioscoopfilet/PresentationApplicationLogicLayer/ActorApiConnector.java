package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.os.AsyncTask;

import com.filet.bioscoopfilet.DomainModel.Actor;

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

public class ActorApiConnector extends AsyncTask<String, Void, String> {

    private ActorAvailable listener = null;

    private ArrayList<Actor> actors = new ArrayList<>();

    public ActorApiConnector(ActorAvailable listener) {
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
            return result;
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {

        try {
            //Top level object
            JSONObject jsonObject = new JSONObject(result);

            // Getting all albums and start looping
            JSONArray films = jsonObject.getJSONArray("cast");

            for (int i = 0; i < 3; i++) {

                Actor a = new Actor(i, "", "");

                //Array level objects and products
                JSONObject film = films.getJSONObject(i);

                a.setFirstName(film.optString("name"));

                actors.add(a);

            }
            //Callback
            listener.actorAvailable(actors);
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
    public interface ActorAvailable {
        ;

        void actorAvailable(ArrayList<Actor> actors);
    }
}