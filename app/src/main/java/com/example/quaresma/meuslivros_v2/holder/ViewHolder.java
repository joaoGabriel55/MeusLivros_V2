package com.example.quaresma.meuslivros_v2.holder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quaresma.meuslivros_v2.R;

/**
 * Created by Quaresma on 13/09/2017.
 */

public class ViewHolder {

    public final TextView textViewTitulo;
    public final TextView textViewAutor;
    public final CheckBox checkBox;
    public final ImageView img;

    public ViewHolder(View v) {
        textViewTitulo = (TextView) v.findViewById(R.id.titulo);
        textViewAutor = (TextView) v.findViewById(R.id.autor);
        checkBox = (CheckBox) v.findViewById(R.id.checkBox);
        img = (ImageView) v.findViewById(R.id.img);
    }
}
