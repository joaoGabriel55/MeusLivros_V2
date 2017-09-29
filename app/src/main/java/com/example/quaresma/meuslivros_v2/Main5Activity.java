package com.example.quaresma.meuslivros_v2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import model.Livro;
import model.LivroAdapter;

public class Main5Activity extends AppCompatActivity {

    private static final int OPERERACAO = 1;
    List<Livro> listaLivro = new ArrayList<>();
    BancoHelper bancoHelper = new BancoHelper(this);
    Context context = this;
    LivroAdapter livroAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        listaLivro = bancoHelper.findAll();

        recyclerView.setAdapter(new LivroAdapter(this, listaLivro));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new MyRecyclerView(Main5Activity.this, recyclerView, new MyRecyclerView.OnItemClickListener() {

            @Override
            public void OnItemClick(View view, int i) {
                //massa
                LivroAdapter.LivroHolder livroHolder = new LivroAdapter.LivroHolder(view);


                livroHolder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recyclerView.addOnItemTouchListener(new MyRecyclerView(context, recyclerView, new MyRecyclerView.OnItemClickListener() {
                            @Override
                            public void OnItemClick(View view, int i) {
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

                            @Override
                            public void OnItemLongClick(View view, int i) {

                            }
                        }));
                    }
                });

            }

            @Override
            public void OnItemLongClick(View view, int i) {
                //Toast.makeText(Main5Activity.this, "Clique longo", Toast.LENGTH_SHORT).show();


            }

        }));

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = ItemTouchHelper.START;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();

                LivroAdapter adapter = (LivroAdapter) recyclerView.getAdapter();

                adapter.mover(fromPosition, toPosition);

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                LivroAdapter adapter = (LivroAdapter) recyclerView.getAdapter();
                adapter.removerTempo(pos);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                View itemView = viewHolder.itemView;
                Drawable background = new ColorDrawable(Color.GRAY);

                if (viewHolder.getAdapterPosition() == -1)
                    return;

                background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                background.draw(c);

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        });


        itemTouchHelper.attachToRecyclerView(recyclerView);


        //recyclerView.setItemAnimator(new LandingAnimator());
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        listaLivro = bancoHelper.findAll();
        recyclerView.setAdapter(new LivroAdapter(this, listaLivro));

    }
}
