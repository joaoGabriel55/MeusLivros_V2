package com.example.quaresma.meuslivros_v2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String PREF = "prefs";

    boolean logado;
    String username;
    String senha;

    /*@BindView(R.id.input_name)
    EditText nome;

    @BindView(R.id.input_pass)
    EditText pass;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPreferences = getSharedPreferences(PREF, MODE_PRIVATE);
        logado = sharedPreferences.getBoolean("logado",false);
        username = sharedPreferences.getString("username", "");
        senha = sharedPreferences.getString("senha", "");

        if (logado){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }


    public void entrar(View v){

        EditText nome = (EditText) findViewById(R.id.input_name);
        EditText pass = (EditText) findViewById(R.id.input_pass);

        if (nome.getText().toString().equals("Gabriel") && pass.getText().toString().equals("150898")){

            username = nome.getText().toString();
            senha = pass.getText().toString();
            logado = true;

            salvaDados();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            /*Snackbar snackbar = Snackbar.make((View) v.getParent(), "Dados invalidos!", Snackbar.LENGTH_SHORT);
            snackbar.show();*/
            Toast.makeText(this, "Dados invalidos!", Toast.LENGTH_SHORT).show();

        }

    }

    private void salvaDados(){

        SharedPreferences sharedPreferences = getSharedPreferences(PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("logado", logado);
        editor.putString("username", username);
        editor.putString("senha", senha);

        editor.commit();
    }
}
