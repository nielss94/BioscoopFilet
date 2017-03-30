package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.DomainModel.Show;
import com.filet.bioscoopfilet.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Bart on 29-3-2017.
 */

public class ShowAdapter extends ArrayAdapter<Show> {

    public ShowAdapter(Context context, ArrayList<Show> shows){
        super(context, 0, shows);
    }

    @Override
    public View getView (int posistion, View convertView, ViewGroup parent){

        //Declaration of show
        Show show = getItem(posistion);
        Film film = show.getFilm();

        //Make convertView
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_film_agenda_list_item, parent, false);
        }

        //Declaration of TextViews
        TextView title = (TextView) convertView.findViewById(R.id.listFilmTitleId);
        TextView genre = (TextView) convertView.findViewById(R.id.listGenreId);
        TextView age = (TextView) convertView.findViewById(R.id.listAgeId);
        TextView version = (TextView) convertView.findViewById(R.id.listVersionId);
        TextView imdb = (TextView) convertView.findViewById(R.id.listIMDBId);
        TextView time = (TextView) convertView.findViewById(R.id.listFilmTime);

        //Declaration of ImageView
        ImageView poster = (ImageView) convertView.findViewById(R.id.listFilmImage);


        //Filling TextViews with show info
        title.setText(film.getTitle());
        genre.setText("Genre: "+film.getGenre());
        age.setText("Leeftijd: " + film.getAge());
        version.setText("Versie: " +film.getVersion());
        imdb.setText("IMDB: " + film.getIMDBScore());
        time.setText(show.getTime()+"");

        //Filling image (FOR DEMO)
        Picasso.with(getContext()).load(film.getPosterURL()).into(poster);

        //Returning view for display
        return convertView;
    }
}
