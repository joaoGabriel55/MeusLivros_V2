package com.example.quaresma.meuslivros_v2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Livro;

public class Main2Activity extends AppCompatActivity {

    int id;

    @BindView(R.id.editText)
    EditText titulo;

    @BindView(R.id.editText2)
    EditText autor;

    @BindView(R.id.editText3)
    EditText ano;

    @BindView(R.id.ratingBar)
    RatingBar nota;

    @BindView(R.id.button3)
    Button salvar;

    @BindView(R.id.button4)
    Button cancelar;

    Livro livro = new Livro();
    BancoHelper bancoHelper = new BancoHelper(this);

    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            flag = false;
            id = bundle.getInt("Id");
            titulo.setText(bundle.getString("Titulo"));
            autor.setText(bundle.getString("Autor"));
            ano.setText(bundle.getString("Ano"));
            nota.setRating((float) bundle.getDouble("Nota"));

        } else {
           flag = true;
        }

        salvar();
        cancelar();
    }

    private void salvar() {

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                livro.setId(id);
                livro.setTitulo(titulo.getText().toString());
                livro.setAutor(autor.getText().toString());
                livro.setAno(ano.getText().toString());
                livro.setNota(nota.getRating());

                bancoHelper.save(livro, flag);



                Toast.makeText(Main2Activity.this, "Livro Salvo com sucesso", Toast.LENGTH_SHORT).show();
                finish();

            }
        });


    }

    private void cancelar() {
        cancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });


 /*       Snackbar snackbar = Snackbar.make((View) v.getParent(), "Clique em cancelar", Snackbar.LENGTH_SHORT).setAction("Cancelar", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main2Activity.this, "Cancelou", Toast.LENGTH_SHORT).show();

                finish();
            }

        });

        snackbar.show();*/

    }


}
