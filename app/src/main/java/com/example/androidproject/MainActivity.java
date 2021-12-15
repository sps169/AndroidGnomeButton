package com.example.androidproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private JsonGnomos jsonSerializar;
    private JsonGnomos settings;
    private boolean nightMode = false;
    private List<GnomoItem> gnomos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Establecemos la vista al xml principal
        setContentView(R.layout.activity_main);
        getPersistanceData();
        //Creamos objetos que representan los componentes de la vista XML mediante el findViewByID
        EditText editTextID = findViewById(R.id.edit_text_id);
        EditText editTextNumber = findViewById(R.id.edit_text_number);
        Button button = this.findViewById(R.id.button);
        RadioButton purple = this.findViewById(R.id.purple_color);
        RadioButton red = this.findViewById(R.id.red_color);
        RadioButton green = this.findViewById(R.id.green_color);

        //Añadimos un listener al boton para programar la accion al ser pulsado
        button.setOnClickListener(view -> {

            //Comprobamos si alguno de los campos esta vacío
            if (editTextID.getText().toString().isEmpty() || editTextNumber.getText().toString().isEmpty()) {
                //Si esta vacio, le decimos con un AlertDialog que la ha cagao
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);

                //Establece mensaje y boton de aceptar
                dialog.setMessage("Error en los parametros...");
                dialog.setPositiveButton("Ok, no volvera a pasar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialogI = dialog.create();
                dialogI.show();
            } else
            {
                if (purple.isChecked()) {
                    añadirGnomo(gnomos, editTextID.getText(), editTextNumber.getText(), "purple");
                }else if(red.isChecked()) {
                    añadirGnomo(gnomos, editTextID.getText(), editTextNumber.getText(), "red");
                }else{
                    añadirGnomo(gnomos, editTextID.getText(), editTextNumber.getText(), "green");
                }
                persistData();
                //creamos un Intent
                Intent intent = new Intent(this, ActivityListView.class);
                //arrancar la activity
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings_menu) {
            SettingsDialog settingsDialog = new SettingsDialog();

            settingsDialog.show(getSupportFragmentManager(),"Ajustes");
        }
        return false;
    }

    private void añadirGnomo(List<GnomoItem>gnomos, Editable text, Editable text1, String color) {
        if (gnomos.size() >= 10) {
            gnomos.remove(0);
        }
        gnomos.add(new GnomoItem(text.toString(), text1.toString(), color));
    }

    private void getPersistanceData() {
        jsonSerializar = new JsonGnomos("UltimosDiezGnomos.json", MainActivity.this.getApplicationContext());
        try {
            gnomos = jsonSerializar.load();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void persistData () {
        jsonSerializar = new JsonGnomos("UltimosDiezGnomos.json", MainActivity.this.getApplicationContext());
        jsonSerializar.save(gnomos);
    }

}