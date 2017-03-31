package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.filet.bioscoopfilet.DomainModel.Actor;
import com.filet.bioscoopfilet.DomainModel.Cinema;
import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.R;

import java.util.ArrayList;

public class FilmOverviewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, FilmApiConnector.FilmsAvailable {

    private ArrayList<Film> films = new ArrayList<>();

    ListView filmList;
    private FilmAdapter filmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_overview);


        //Setting toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.films);
        setSupportActionBar(myToolbar);

//        //FOR DEMO
//        Cinema cinema = new Cinema(1, null, null, null, null, null);
//
//        films.add(new Film(1, cinema, "Harry Potter: en de Steen der Wijzen", "2D NL",
//                    "NL","30/03/2017", "Fantasy", 90, 12,  "Op zijn elfde verjaardag verneemt Harry Potter dat zijn overleden ouders twee machtige tovenaars waren en dat hij zelf ook over magische krachten beschikt. Hij krijgt zijn opleiding aan Zweinsteins Hogeschool voor Hekserij en Hocus Pocus. Daar begint het avontuur van zijn leven.", "",
//                    "6.5",  "","https://image.tmdb.org/t/p/w500//dCtFvscYcXQKTNvyyaQr2g2UacJ.jpg",  ""));
//
//        films.add(new Film(2, cinema, "Mad Max: Fury Road", "2D OV",
//                "EN","30/03/2017", "Actie", 90, 16,  "Achtervolgd door zijn turbulente verleden, is Mad \"Max\" Rockatansky ervan overtuigd dat hij alleen kan overleven in de huidige postapocalyptische wereld als hij in zijn eentje opereert. Toch komt hij in contact met een groep die in een oorlogsvoertuig, bestuurd door de imperator Furiosa, door het ‘Wasteland’ trekt. De groep is op de vlucht voor de tiran Immortan Joe, van wie iets onvervangbaars is gestolen. De woedende krijgsheer stuurt al zijn bendes achter de rebellen aan, wat uitmondt in een meedogenloze, bloedstollende Road War.", "",
//                "9.3",  "","https://image.tmdb.org/t/p/w500//kqjL17yufvn9OVLyXYpvtyrFfak.jpg",  ""));
//
//        films.add(new Film(3, cinema, "Star Wars Episode VII: The Force Awakens", "3D OV",
//                "NL","30/03/2017","Sci-Fi", 90, 12,  "Dertig jaar na de overwinning op het Galactische Keizerrijk is er een nieuwe bedreiging: de kwaadaardige Kylo Ren en de First Order. Stormtrooper Finn loopt over naar het verzet, crasht op woestijnplaneet Jakku en ontmoet hier Rey. Zij heeft een droid die belangrijke geheime informatie bevat over de locatie van de laatst overgebleven Jedi Knight, Luke Skywalker. Het duo werkt samen met Han Solo om te zorgen dat het verzet deze informatie in handen krijgt.", "",
//                "8.5",""  ,"https://image.tmdb.org/t/p/w500/weUSwMdQIa3NaXVzwUoIIcAi85d.jpg",  ""));
//
//        films.add(new Film(4, cinema, "Deadpool", "2D OV",
//                "EN","30/03/2017","Comic", 90, 16,  "Wade Wilson is een speciale agent van de overheid die, nadat hij onderworpen is aan een behandeling die hem bovenmenselijke genezende krachten heeft gegeven, een alter-ego in de vorm van 'Deadpool' aanneemt. Gewapend met zijn nieuwe vaardigheden en een verwrongen gevoel voor humor jaagt hij op de man die bijna zijn hele leven verwoest heeft.", "",
//                "7.5","",  "https://image.tmdb.org/t/p/w500/inVq3FRqcYIRl2la8iZikYYxFNR.jpg",  ""));
//
//        films.add(new Film(5, cinema, "Logan", "2D OV",
//                "EN","30/03/2017","Comic", 90, 16,  "In 2029 is de mutant bevolking aanzienlijk geslonken en de X-Men zijn ontbonden. Logan (Hugh Jackman) zijn vermogen om zichzelf te genezen is slinkende en hij ontfermt zich nu samen met de mutant Caliban (Stephen Merchant) over de zieke Professor X (Patrick Stewart) in een schuilplaats op de grens met Mexico. Zij zullen daar de rest van hun leven ondergedoken blijven. Maar wanneer een jonge mutant op de vlucht voor een gevaarlijke organisatie zijn pad kruist, kan hij zich niet langer verstoppen en voelt hij zich uiteindelijk gedwongen haar te helpen.", "",
//                "8.5","",  "https://image.tmdb.org/t/p/w500/45Y1G5FEgttPAwjTYic6czC9xCn.jpg",  ""));
//
//        films.add(new Film(6, cinema, "Minions", "3D NL",
//                "NL","30/03/2017","Comedy", 90, 3,  "De Minions bestaan al sinds het begin van de evolutie. Van een geel eencellig organisme zijn ze uitgegroeid tot wezens met slechts één doel: het dienen van de meest ambitieuze schurken. Wanneer hun meesters vernietigd worden besluiten ze zich te isoleren van de wereld en te verkassen naar Antarctica. Ergens in de jaren zestig drijft het ontbreken van een leider hen echter tot waanzin. Ze besluiten daarom een nieuwe te gaan zoeken.", "",
//                "5.5","",  "https://image.tmdb.org/t/p/w500/q0R4crx2SehcEEQEkYObktdeFy.jpg",  ""));
//
//        films.add(new Film(7, cinema, "Source Code", "2D OV",
//                "EN","30/03/2017", "Action",90, 12,  "Colter Stevens wordt met een schok wakker in een trein op weg naar Chicago, en heeft geen idee waar hij is. Het laatste wat hij zich herinnert is een helikoptermissie in Irak. Nu zit hij in het lichaam van iemand anders, maar voor hij iets kan doen, vernietigt een bomexplosie het complete treinstel en alle passagiers. Vlak na de treinexplosie komt Colter verdwaasd bij in een isolatiekamer, ingesnoerd op een stoel in zijn militaire gevechtstenue, waar commandant Carol Goodwin via een videogesprek een paar vragen stelt waar Colter tot zijn verbazing het antwoord op weet. Dan komen computers tot leven en is Colter plots weer terug in de trein, op exact hetzelfde moment als de vorige keer, voortrazend richting Chicago. Colter concludeert dat hij zich in een simulator bevindt, en net zo vaak de aanslag mee moet maken tot hij weet wie de dader is.", "",
//                "10", "", "https://image.tmdb.org/t/p/w500/cpl7R5d3qwWvykRRSxdhQ0htstU.jpg",  ""));

        //Setting adapter
        filmAdapter = new FilmAdapter(this, films);

        //Declaration of ListView
        filmList = (ListView) findViewById(R.id.filmListView);
        filmList.setOnItemClickListener(this);
        filmList.setAdapter(filmAdapter);

        String[] urls = new String[]{"https://api.themoviedb.org/3/movie/upcoming?api_key=863618e1d5c5f5cc4e34a37c49b8338e&language=nl"};
        FilmApiConnector getFilms = new FilmApiConnector(this);
        getFilms.execute(urls);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Film film = films.get(position);

        Intent intent = new Intent(getApplicationContext(), FilmDetailAgendaActivity.class);
        intent.putExtra("FILM", film);
        startActivity(intent);
    }

    @Override
    public void filmsAvailable(ArrayList<Film> result) {

        findViewById(R.id.loadingPanel).setVisibility(View.GONE);

        //Clear current products
        films.clear();

        //Put new products in ArrayList
        for (Film f: result) {
            films.add(f);
        }

        //Notify the changes
        filmAdapter.notifyDataSetChanged();
    }

}
