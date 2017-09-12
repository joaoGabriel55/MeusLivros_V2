package com.example.quaresma.meuslivros_v2;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.orm.SugarContext;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Livro;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText titulo;

    @BindView(R.id.editText2)
    EditText autor;

    @BindView(R.id.editText3)
    EditText ano;

    @BindView(R.id.ratingBar)
    RatingBar nota;

    Livro livro = new Livro();
    BancoHelper bancoHelper = new BancoHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SugarContext.init(this);

        ButterKnife.bind(this);
    }

    public void salvar(View v) {

        livro.setTitulo(titulo.getText().toString());
        livro.setAutor(autor.getText().toString());
        livro.setAno(ano.getText().toString());
        livro.setNota(nota.getRating());

        bancoHelper.save(livro);


        Toast.makeText(this, "Livro Salvo com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void cancelar(View v) {
        Snackbar snackbar = Snackbar.make((View) v.getParent(), "Clique em cancelar", Snackbar.LENGTH_SHORT).setAction      ("Cancelar", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main2Activity.this, "Cancelou", Toast.LENGTH_SHORT).show();

                finish();
            }

        });

        snackbar.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }


}
