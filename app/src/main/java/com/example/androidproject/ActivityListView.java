package com.example.androidproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActivityListView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private JsonGnomos json;
    private JsonGnomos settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        TextView textView = (TextView) findViewById(R.id.text_bienvenida);
        json = new JsonGnomos("UltimosDiezGnomos.json",this);
        List<GnomoItem> gnomos;
        gnomos = json.load();
        textView.setText(textView.getText().toString().replace("?", gnomos.get(gnomos.size() - 1).getId()));
        //Creamos un recycle asociado al id de la lista del XML
        recyclerView = findViewById(R.id.last_gnomes_list);
        //Establecemos un linear layout para la lista que usa como contexto la actividad
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Establecemos el adaptador de la lista para utilizar los ViewHolder de los gnomos
        recyclerView.setAdapter(new GnomosRecycleViewAdapter(gnomos));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
