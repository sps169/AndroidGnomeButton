package com.example.androidproject;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;

public class SettingsDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        boolean nightMode = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.settings_fragment, null);
        JsonGnomos settings = new JsonGnomos("settings_gnomos.json", view.getContext());
        Switch modoNoche = view.findViewById(R.id.switch1);
        if (settings.loadSettings()) {
            modoNoche.setChecked(true);
        }
        builder.setPositiveButton(" Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (modoNoche.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    settings.saveSettings(true);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    settings.saveSettings(false);
                }
                dismiss();
            }
        });
        builder.setView(view);
        return builder.create();
    }
}
