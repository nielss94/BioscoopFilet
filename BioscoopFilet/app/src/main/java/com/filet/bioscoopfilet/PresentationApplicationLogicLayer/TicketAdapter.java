//package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.filet.bioscoopfilet.DomainModel.Film;
//import com.filet.bioscoopfilet.Persistancy.DAOFactory;
//import com.filet.bioscoopfilet.Persistancy.FilmDAO;
//import com.filet.bioscoopfilet.R;
//
//import com.filet.bioscoopfilet.DomainModel.Ticket;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//
///**
// * Created by Felix on 29-3-2017.
// */
//
//public class TicketAdapter extends ArrayAdapter<Ticket> {
//
//    private DAOFactory factory;
//
//    public TicketAdapter(Context context, ArrayList<Ticket> tickets) {
//        super(context, 0, tickets);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Ticket ticket = getItem(position);
//
//        //create a ticket item
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_ticket_list_item, parent, false);
//        }
//
//        //initialise xml elements
//        ImageView QRCode = (ImageView) convertView.findViewById(R.id.ticketQRCode);
//        TextView filmTitle = (TextView) convertView.findViewById(R.id.ticketFilmTitle);
//
//        //add content to the xml elements
//        Picasso.with(getContext()).load(ticket.getQrCode()).into(QRCode);
//        filmTitle.setText(ticket.getTitle());
//
//        return convertView;
//    }
//}