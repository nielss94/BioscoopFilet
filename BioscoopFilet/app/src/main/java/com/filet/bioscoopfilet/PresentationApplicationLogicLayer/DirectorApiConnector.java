package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.os.AsyncTask;

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

/**
 * Created by Bart on 30-3-2017.
 */

public class DirectorApiConnector extends AsyncTask<String, Void, String> {

    private DirectorAvailable listener = null;

    public DirectorApiConnector(DirectorAvailable listener) {
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

        String finalDirector = "Test";

        try {
            //Top level object
            JSONObject jsonObject = new JSONObject(result);

            // Getting all albums and start looping
            JSONArray films = jsonObject.getJSONArray("crew");

            for (int i = 0; i < films.length(); i++) {

                //Array level objects and products
                JSONObject film = films.getJSONObject(i);

                if (film.getString("job").equals("Director")) {

                    finalDirector = film.optString("name");
                    //Callback
                    listener.directorAvailable(finalDirector);
                    break;
                }
            }
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
    public interface DirectorAvailable {
        ;

        void directorAvailable(String director);
    }
}