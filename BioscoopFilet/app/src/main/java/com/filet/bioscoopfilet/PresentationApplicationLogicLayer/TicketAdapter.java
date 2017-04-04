package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.filet.bioscoopfilet.DomainModel.Film;
import com.filet.bioscoopfilet.DomainModel.Ticket;
import com.filet.bioscoopfilet.Persistancy.DAOFactory;
import com.filet.bioscoopfilet.Persistancy.FilmDAO;
import com.filet.bioscoopfilet.Persistancy.SQLiteDAOFactory;
import com.filet.bioscoopfilet.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

/**
 * Created by Bart on 29-3-2017.
 */

public class TicketAdapter extends ArrayAdapter<Ticket> {

    private DAOFactory factory;
    private ArrayList<Film> films = new ArrayList<>();

    public TicketAdapter(Context context, ArrayList<Ticket> tickets){
        super(context, 0, tickets);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){

        //Declaration of film
        Ticket ticket = getItem(position);
        factory = new SQLiteDAOFactory(getContext());
        FilmDAO filmDAO = factory.createFilmDAO();
        films = filmDAO.selectData();
        Film film = null;
        for (int i = 0; i < films.size(); i++) {
            if(films.get(i).getFilmAPIID() == ticket.getShow().getFilmAPIID())
            {
                film = films.get(i);
            }
        }

        //Make convertView
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_ticket_list_item, parent, false);
        }

        //Declaration of Views
        TextView filmTitle = (TextView) convertView.findViewById(R.id.ticketFilmTitle);
        ImageView qrCode = (ImageView) convertView.findViewById(R.id.ticketQRCode);

        //Filling Views with ticket info
        filmTitle.setText(film.getTitle());

        //Filling image
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(ticket.getQrCode()+"", BarcodeFormat.QR_CODE, 220, 220);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        //Returning view for display
        return convertView;
    }
}
