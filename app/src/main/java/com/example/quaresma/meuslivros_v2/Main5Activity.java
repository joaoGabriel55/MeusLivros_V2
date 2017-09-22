package com.example.quaresma.meuslivros_v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.LandingAnimator;
import model.Livro;
import model.LivroAdapter;

public class Main5Activity extends AppCompatActivity {

    private static final int OPERERACAO = 1;
    List<Livro> listaLivro = new ArrayList<>();
    BancoHelper bancoHelper = new BancoHelper(this);
    Context context = this;
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

        recyclerView.addOnItemTouchListener(new MyRecyclerView(Main5Activity.this, recyclerView, new MyRecyclerView.OnItemClickListener() {

            @Override
            public void OnItemClick(View view, int i) {
                Toast.makeText(Main5Activity.this, "Clique simples", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClick(View view, int i) {
                //Toast.makeText(Main5Activity.this, "Clique longo", Toast.LENGTH_SHORT).show();
                Bundle b = new Bundle();
                Intent intent = new Intent(Main5Activity.this, Main2Activity.class);

                Log.i("lista", listaLivro.get(i).toString());
                b.putInt("Id", (int) listaLivro.get(i).getId());
                b.putString("Titulo", listaLivro.get(i).getTitulo());
                b.putString("Autor", listaLivro.get(i).getAutor());
                b.putString("Ano", listaLivro.get(i).getAno());
                b.putDouble("Nota", listaLivro.get(i).getNota());

                intent.putExtras(b);

                startActivity(intent);
            }

        }));

        recyclerView.setItemAnimator(new LandingAnimator());

    }


}
