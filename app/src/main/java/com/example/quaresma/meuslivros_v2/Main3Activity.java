package com.example.quaresma.meuslivros_v2;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.orm.SugarContext;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Livro;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.titulo)
    TextView titulo;

    @BindView(R.id.autor)
    TextView autor;

    @BindView(R.id.ano)
    TextView ano;

    @BindView(R.id.ratingBar2)
    RatingBar nota;

    private List<Livro> lista;
    private int livroAtual = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        SugarContext.init(this);

        ButterKnife.bind(this);

        lista = Livro.listAll(Livro.class);

        for (int i = 0; i < lista.size(); i++){
            if(i == 0){
                atualizarParametros(i);
                livroAtual = 0;
            }
        }
    }

    private void atualizarParametros(int i){
        titulo.setText(lista.get(i).getTitulo());
        autor.setText(lista.get(i).getAutor());
        ano.setText(lista.get(i).getAno());
        nota.setRating(lista.get(i).getNota());
    }
    //@OnClick(R.id.button6)
    public void proximo(View v){
        if(livroAtual == lista.size() -1 ){
            Snackbar snackbar = Snackbar.make((View) v.getParent(), "Ultimo registro", Snackbar.LENGTH_SHORT);
            snackbar.show();
        } else {
            atualizarParametros(livroAtual + 1);
            livroAtual ++;
        }
    }

    public void anterior(View v){
        if(livroAtual == 0 ){
            Snackbar snackbar = Snackbar.make((View) v.getParent(), "Primeiro registro", Snackbar.LENGTH_SHORT);
            snackbar.show();
        } else {
            atualizarParametros(livroAtual - 1);
            livroAtual --;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }
}
