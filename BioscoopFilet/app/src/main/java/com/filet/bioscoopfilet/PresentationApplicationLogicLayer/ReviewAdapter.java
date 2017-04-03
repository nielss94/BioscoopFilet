package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.filet.bioscoopfilet.DomainModel.Review;
import com.filet.bioscoopfilet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Bart on 29-3-2017.
 */

public class ReviewAdapter extends ArrayAdapter<Review> {

    public ReviewAdapter(Context context, ArrayList<Review> reviews){
        super(context, 0, reviews);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){

        //Declaration of film
        Review review = getItem(position);

        //Make convertView
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_review_list_item, parent, false);
        }

        //Declaration of TextViews
        TextView name = (TextView) convertView.findViewById(R.id.reviewListName);
        TextView reviewDescription = (TextView) convertView.findViewById(R.id.reviewListDescription);
        ImageView star1 = (ImageView) convertView.findViewById(R.id.reviewListStar1);
        ImageView star2 = (ImageView) convertView.findViewById(R.id.reviewListStar2);
        ImageView star3 = (ImageView) convertView.findViewById(R.id.reviewListStar3);
        ImageView star4 = (ImageView) convertView.findViewById(R.id.reviewListStar4);
        ImageView star5 = (ImageView) convertView.findViewById(R.id.reviewListStar5);

        //Filling TextViews with film info
        name.setText(review.getVisitor().getFirstName() + " " + review.getVisitor().getLastName());
        reviewDescription.setText(review.getDescription());

        switch (review.getScore()) {
            case 1:
                star1.setImageResource(R.drawable.filled_star);
                star2.setImageResource(R.drawable.empty_star);
                star3.setImageResource(R.drawable.empty_star);
                star4.setImageResource(R.drawable.empty_star);
                star5.setImageResource(R.drawable.empty_star);
                break;
            case 2:
                star1.setImageResource(R.drawable.filled_star);
                star2.setImageResource(R.drawable.filled_star);
                star3.setImageResource(R.drawable.empty_star);
                star4.setImageResource(R.drawable.empty_star);
                star5.setImageResource(R.drawable.empty_star);
                break;
            case 3:
                star1.setImageResource(R.drawable.filled_star);
                star2.setImageResource(R.drawable.filled_star);
                star3.setImageResource(R.drawable.filled_star);
                star4.setImageResource(R.drawable.empty_star);
                star5.setImageResource(R.drawable.empty_star);
                break;
            case 4:
                star1.setImageResource(R.drawable.filled_star);
                star2.setImageResource(R.drawable.filled_star);
                star3.setImageResource(R.drawable.filled_star);
                star4.setImageResource(R.drawable.filled_star);
                star5.setImageResource(R.drawable.empty_star);
                break;
            case 5:
                star1.setImageResource(R.drawable.filled_star);
                star2.setImageResource(R.drawable.filled_star);
                star3.setImageResource(R.drawable.filled_star);
                star4.setImageResource(R.drawable.filled_star);
                star5.setImageResource(R.drawable.filled_star);
                break;
        }

        //Returning view for display
        return convertView;
    }
}
