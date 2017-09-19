package com.example.quaresma.meuslivros_v2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.Livro;
import model.LivroAdapter;

public class Main5Activity extends AppCompatActivity {

    private static final int OPERERACAO = 1;
    List<Livro> listaLivro = new ArrayList<>();
    BancoHelper bancoHelper = new BancoHelper(this);
    LivroAdapter livroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        listaLivro = bancoHelper.findAll();

        recyclerView.setAdapter(new LivroAdapter(this, listaLivro));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new MyRecyclerView(Main5Activity.this, recyclerView, new MyRecyclerView.OnItemClickListener(){

            @Override
            public void OnItemClick(View view, int i) {
                Toast.makeText(Main5Activity.this, "Clique simples", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClick(View view, int i) {
                Toast.makeText(Main5Activity.this, "Clique longo", Toast.LENGTH_SHORT).show();
            }
        }));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }




}
