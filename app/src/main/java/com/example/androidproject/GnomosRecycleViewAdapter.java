package com.example.androidproject;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GnomosRecycleViewAdapter extends RecyclerView.Adapter<GnomosRecycleViewAdapter.GnomoItemHolder> {

    private final int PURPLE = 0;
    private final int RED = 1;
    private final int GREEN = 2;
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
        GnomoItem item = listGnomos.get(listGnomos.size() - 1 - position);
        holder.idValue.setText(item.getId());
        holder.numValue.setText(item.getNum());
        String color = item.getColor();
        switch (color) {
            case "red":
                holder.drawableImageRed.setVisibility(View.VISIBLE);
                holder.drawableImageGreen.setVisibility(View.GONE);
                holder.drawableImagePurple.setVisibility(View.GONE);
                break;
            case "purple":
                holder.drawableImageGreen.setVisibility(View.GONE);
                holder.drawableImageRed.setVisibility(View.GONE);
                holder.drawableImagePurple.setVisibility(View.VISIBLE);
                break;
            case "green":
                holder.drawableImageRed.setVisibility(View.GONE);
                holder.drawableImageGreen.setVisibility(View.VISIBLE);
                holder.drawableImagePurple.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listGnomos.size();
    }

    //elementos del layout, esto es lo que utiliza el recycle view para representar
    //cada elemento.
    public class GnomoItemHolder extends RecyclerView.ViewHolder {
        TextView id, idValue, num, numValue;
        ImageView drawableImagePurple;
        ImageView drawableImageGreen;
        ImageView drawableImageRed;
        public GnomoItemHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.text_view_item_id);
            idValue = itemView.findViewById(R.id.text_view_item_id_value);
            num = itemView.findViewById(R.id.text_view_item_number);
            numValue = itemView.findViewById(R.id.text_view_item_number_value);
            drawableImagePurple = itemView.findViewById(R.id.gnome_purple_view);
            drawableImageGreen = itemView.findViewById(R.id.gnome_green_view);
            drawableImageRed = itemView.findViewById(R.id.gnome_red_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initAnimations();
                }
            });
        }

        private void initAnimations() {
            Animation rotateAnimation;
            Animation rotateAnimationFast;
            Animation rotateAnimationMegaFast;
            rotateAnimation = AnimationUtils.loadAnimation(itemView.getContext(),R.anim.rotation);
            rotateAnimationFast = AnimationUtils.loadAnimation(itemView.getContext(),R.anim.rotation_fast);
            rotateAnimationMegaFast = AnimationUtils.loadAnimation(itemView.getContext(),R.anim.rotation_mega_fast);
            if (drawableImagePurple.getVisibility() == View.VISIBLE)
                drawableImagePurple.startAnimation(rotateAnimationFast);
            if (drawableImageRed.getVisibility() == View.VISIBLE)
                drawableImageRed.startAnimation(rotateAnimation);
            if (drawableImageGreen.getVisibility() == View.VISIBLE)
                drawableImageGreen.startAnimation(rotateAnimationMegaFast);
        }
    }

    public void addGnomo (GnomoItem gnomo) {
       listGnomos.add(gnomo);
       notifyDataSetChanged();
    }

}
