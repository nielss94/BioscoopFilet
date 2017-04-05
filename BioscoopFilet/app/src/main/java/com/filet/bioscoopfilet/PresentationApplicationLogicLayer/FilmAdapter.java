package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Bart on 29-3-2017.
 */

public class FilmAdapter extends ArrayAdapter<Film>  {

    public FilmAdapter(Context context, ArrayList<Film> films){
        super(context, 0, films);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){

        String notRated = getContext().getResources().getString(R.string.not_yet_rated);
        String ageText = getContext().getResources().getString(R.string.age);
        String versionText = getContext().getResources().getString(R.string.version);

        //Declaration of film
        Film film = getItem(position);

        //Make convertView
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_film_list_item, parent, false);
        }

        //Declaration of TextViews
        TextView title = (TextView) convertView.findViewById(R.id.listFilmTitleId);
        TextView genre = (TextView) convertView.findViewById(R.id.listGenreId);
        TextView age = (TextView) convertView.findViewById(R.id.listAgeId);
        TextView version = (TextView) convertView.findViewById(R.id.listVersionId);
        TextView imdb = (TextView) convertView.findViewById(R.id.listIMDBId);

        //Declaration of ImageView
        ImageView poster = (ImageView) convertView.findViewById(R.id.listFilmImage);

        //Filling TextViews with film info
        title.setText(film.getTitle());
        genre.setText("Genre: "+film.getGenre());
        if (film.getAge() == 99){
            age.setText(notRated);
        }
        else{
            age.setText(ageText + " " + film.getAge());
        }
        version.setText(versionText + " " +film.getVersion());
        imdb.setText(getContext().getResources().getString(R.string.review)+ " " + film.getIMDBScore());

        //Filling image
        Picasso.with(getContext()).load(film.getPosterURL()).into(poster);

        //Returning view for display
        return convertView;
    }
}
