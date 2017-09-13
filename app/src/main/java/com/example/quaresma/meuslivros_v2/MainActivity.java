package com.example.quaresma.meuslivros_v2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String PREF = "prefs";


    TextView nomeUser;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(PREF, MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");

        nomeUser = (TextView) findViewById(R.id.nomeUser);

        nomeUser.setText(username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void sair(MenuItem m){
        SharedPreferences sharedPreferences = getSharedPreferences(PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void cadastrar(View v){
        startActivity(new Intent(this, Main2Activity.class));
    }

    public void listar(View v){
        startActivity(new Intent(this, Main3Activity.class));
    }

    public void buscar(View v){
        startActivity(new Intent(this, Main4Activity.class));
    }

    public void listView(View v){
        startActivity(new Intent(this, Main5Activity.class));
    }

}
