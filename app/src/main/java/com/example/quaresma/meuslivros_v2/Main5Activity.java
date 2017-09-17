package com.example.quaresma.meuslivros_v2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import model.Livro;
import model.LivroAdapter;

public class Main5Activity extends AppCompatActivity {

    private static final int OPERERACAO = 1;
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

        clickLista();
    }


    private void clickLista(){

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                //final:
                //definição de uma variável, significa que a variável não pode assumir outro valor, tornando-se uma constante.
                final Livro livro = (Livro) adapterView.getAdapter().getItem(i);
                final CharSequence[] itens = {getString(R.string.alterar), getString(R.string.excluir), getString(R.string.cancelar)};

                AlertDialog.Builder ops = new AlertDialog.Builder(Main5Activity.this);
                ops.setItems(itens, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int op) {
                        String opcao = (String) itens[op];

                        if(opcao.equals(getString(R.string.alterar))){

                            Intent myIntent = new Intent(Main5Activity.this, Main2Activity.class);

                            Bundle params = new Bundle();

                            params.putString("titulo", listaLivro.get(i).getTitulo());
                            params.putString("autor", listaLivro.get(i).getAutor());
                            params.putString("ano", listaLivro.get(i).getAno());
                            params.putDouble("nota", listaLivro.get(i).getNota());

                            myIntent.putExtras(params);
                            startActivityForResult(myIntent, OPERERACAO);

                        }else if(opcao.equals(getString(R.string.excluir))){
                            excluir(livro);
                        }else if(opcao.equals(getString(R.string.cancelar))){
                            dialog.cancel();
                        }
                    }

                });

                ops.setTitle(getString(R.string.opcoes));
                AlertDialog alertDialog = ops.create();
                alertDialog.show();
            }
        });
    }

    private void excluir(final Livro livro){
        AlertDialog.Builder msg = new AlertDialog.Builder(Main5Activity.this);
        msg.setMessage(getString(R.string.msg_delete));
        msg.setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                View v = null;
                int retorno = new BancoHelper(Main5Activity.this).delete(livro);

                if (retorno == 1) {
                    Snackbar snackbar = Snackbar.make((View) v.getParent(), getString(R.string.correct), Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    listaLivro = bancoHelper.findAll();
                } else {
                    Snackbar snackbar = Snackbar.make((View) v.getParent(), getString(R.string.error), Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }


            }
        });

        msg.setNegativeButton(getString(R.string.nao), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        msg.create().show();

    }

    public void setLido(View v){

        for(int i=0; i< lista.getCount(); i++) {

        }
    }
}
