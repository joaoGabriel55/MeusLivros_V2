package com.example.quaresma.meuslivros_v2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.Livro;
import model.LivroAdapter;

public class Main5Activity extends AppCompatActivity {

    ListView lista;
    List<Livro> listaLivro = new ArrayList<>();
    BancoHelper bancoHelper = new BancoHelper(this);
    CheckBox lido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        lista = (ListView) findViewById(R.id.minhalista);
        lido = (CheckBox) findViewById(R.id.checkBox);

        listaLivro = bancoHelper.findAll();

        lista.setAdapter(new LivroAdapter(this, listaLivro, lido));
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Livro livro = listaLivro.get(i);
                Toast.makeText(Main5Activity.this, ""+livro.getTitulo(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setLido(View v){

        for(int i=0; i< lista.getCount(); i++) {

        }
    }
}
