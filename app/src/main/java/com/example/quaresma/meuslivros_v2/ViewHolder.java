package com.example.quaresma.meuslivros_v2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Quaresma on 18/09/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder{

    final TextView textViewTitulo;
//    final TextView textViewAutor;
//    final TextView textViewAno;
    final RatingBar ratingBarNota;


    public ViewHolder(View itemView) {
        super(itemView);

        textViewTitulo = (TextView) itemView.findViewById(R.id.title2);
        ratingBarNota = (RatingBar) itemView.findViewById(R.id.nota);
    }
}
