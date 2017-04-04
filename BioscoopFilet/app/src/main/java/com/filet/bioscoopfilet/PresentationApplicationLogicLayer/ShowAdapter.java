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
import com.filet.bioscoopfilet.Persistancy.DAOFactory;
import com.filet.bioscoopfilet.Persistancy.FilmDAO;
import com.filet.bioscoopfilet.Persistancy.SQLiteDAOFactory;
import com.filet.bioscoopfilet.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Bart on 29-3-2017.
 */

public class ShowAdapter extends ArrayAdapter<Show> {

    private final String TAG = getClass().getSimpleName();
    private DAOFactory factory;
    private ArrayList<Film> films = new ArrayList<>();

    public ShowAdapter(Context context, ArrayList<Show> shows) {
        super(context, 0, shows);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Declaration of show
        Show show = getItem(position);
        factory = new SQLiteDAOFactory(getContext());
        FilmDAO filmDAO = factory.createFilmDAO();
        films = filmDAO.selectData();
        Film film = null;
        for (int i = 0; i < films.size(); i++) {
            if(films.get(i).getFilmAPIID() == show.getFilmAPIID())
            {
                film = films.get(i);
            }
        }

        //Make convertView
        if (convertView == null) {
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
        genre.setText("Genre: " + film.getGenre());
        age.setText("Leeftijd: " + film.getAge());
        version.setText("Versie: " + film.getVersion());
        imdb.setText("IMDB: " + film.getIMDBScore());

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        time.setText(sdf.format(show.getTime()));

        //Filling image (FOR DEMO)
        Picasso.with(getContext()).load(film.getPosterURL()).into(poster);

        //Returning view for display
        return convertView;
    }
}
