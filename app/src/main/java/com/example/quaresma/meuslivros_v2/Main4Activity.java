package com.example.quaresma.meuslivros_v2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Livro;

public class Main4Activity extends AppCompatActivity {

    List<Livro> listaLivro = new ArrayList<>();
    Livro liv = new Livro();
    String[] livro;
    String title;
    ArrayAdapter<String> adaptador;
    TextView titulo;
    TextView autor;
    TextView ano;
    RatingBar nota;

    BancoHelper bancoHelper = new BancoHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        /*SugarContext.init(this);*/
        titulo = (TextView) findViewById(R.id.titulo2);
        autor = (TextView) findViewById(R.id.autor2);
        ano = (TextView) findViewById(R.id.ano2);
        nota = (RatingBar) findViewById(R.id.nota2);

        listaLivro = bancoHelper.findAll();

        AutoCompleteTextView autoCompleteLivros = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        preencheArray();

        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, livro);
        autoCompleteLivros.setAdapter(adaptador);

        autoCompleteLivros.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                //Toast.makeText(Main4Activity.this, adaptador.getItem(i).toString(),Toast.LENGTH_SHORT).show();
                //Livro.executeQuery("");

                title = adaptador.getItem(i).toString();

                liv = bancoHelper.findByTitulo(title);

                titulo.setText(liv.getTitulo());
                autor.setText(liv.getAutor());
                ano.setText(liv.getAno());
                nota.setRating(liv.getNota());

            }
        });
    }

    public void preencheArray(){
        livro = new String[listaLivro.size()];
        for(int i = 0;i<listaLivro.size();i++){
            livro[i] = listaLivro.get(i).getTitulo();
        }
    }


/*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }*/
}
