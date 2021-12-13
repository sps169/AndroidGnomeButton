package com.example.androidproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<GnomoItem> gnomos = new ArrayList<>();

        //Establecemos la vista al xml principal
        setContentView(R.layout.activity_main);

        //Creamos objetos que representan los componentes de la vista XML mediante el findViewByID
        EditText editTextID = findViewById(R.id.edit_text_id);
        EditText editTextNumber = findViewById(R.id.edit_text_number);
        Button button = this.findViewById(R.id.button);

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
                añadirGnomo(gnomos, editTextID.getText(), editTextNumber.getText());

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

    private void añadirGnomo(List<GnomoItem>gnomos, Editable text, Editable text1) {
        if (gnomos.size() >= 10) {
            gnomos.remove(0);
        }
        gnomos.add(new GnomoItem(text.toString(), text1.toString()));
    }

    private String test(String saludo) {
        if (saludo == null) {
            saludo = "hola";
        }
        return "Hola " + saludo;
    }

    private void testList() {
        List<String> listMutable = new ArrayList<String>();
    }


}