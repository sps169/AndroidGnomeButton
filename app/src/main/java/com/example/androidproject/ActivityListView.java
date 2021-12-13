package com.example.androidproject;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActivityListView extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        List<GnomoItem> gnomos = new ArrayList<GnomoItem>();
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
