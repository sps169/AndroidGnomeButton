package com.example.androidproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GnomosRecycleViewAdapter extends RecyclerView.Adapter<GnomosRecycleViewAdapter.GnomoItemHolder> {

    private List<GnomoItem> listGnomos;

    public GnomosRecycleViewAdapter (List<GnomoItem> gnomos) {
        this.listGnomos = gnomos;
    }
    @NonNull
    @Override
    //metodo que inicia la interfaz del item
    public GnomoItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gnomo_item, parent, false);
        return new GnomoItemHolder(view);
    }

    //metodo que rellena la interfaz del item
    @Override
    public void onBindViewHolder(@NonNull GnomoItemHolder holder, int position) {
        GnomoItem item = listGnomos.get(position);
        holder.idValue.setText(item.getId());
        holder.numValue.setText(item.getNum());
    }

    @Override
    public int getItemCount() {
        return listGnomos.size();
    }

    //elementos del layout, esto es lo que utiliza el recycle view para representar
    //cada elemento.
    public class GnomoItemHolder extends RecyclerView.ViewHolder {
        TextView id, idValue, num, numValue;
        //para insertar una imagen, click derecho en drawable + assert vector, metemos el icono .svg descargado en local
        //y creamos un xml a partir de dicho .svg
        //dicho xml se añade al image view de la vista XML mediante la etiqueta android:src="@drawable/<<nombre>>"
        ImageView image;
        public GnomoItemHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.text_view_item_id);
            idValue = itemView.findViewById(R.id.text_view_item_id_value);
            num = itemView.findViewById(R.id.text_view_item_number);
            numValue = itemView.findViewById(R.id.text_view_item_number_value);
            image = itemView.findViewById(R.id.gnomo_image);
        }
    }

    public void addGnomo (GnomoItem gnomo) {
       listGnomos.add(gnomo);
       notifyDataSetChanged();
    }

}
