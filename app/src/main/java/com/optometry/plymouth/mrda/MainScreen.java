package com.optometry.plymouth.mrda;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainScreen extends AppCompatActivity {

    /**
     * Stimuli Variables
     * Related variables to the
     */
    private int stimuliRounds = 20;
    private int sequentialErrors = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_main_screen);

        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //When "Start" button is clicked, do the following
                Intent intent = new Intent(MainScreen.this, Stimuli_Canvas.class);

                startActivity(intent);
            }
        });

        //When "Options" is clicked
        findViewById(R.id.btnOptions).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                final Dialog dialog = new Dialog(MainScreen.this);

                //Select our XML design
                dialog.setContentView(R.layout.options_menu);
                dialog.setTitle("Options");

                //Prevent closing the menu by clicking outside of it
                dialog.setCanceledOnTouchOutside(false);

                Button confirmButton = (Button) dialog.findViewById(R.id.dialogOptionsOK);

                // if Confirm button is clicked, save our settings and leave menu
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Note: User SharedPreferences to store/retrieve data
                        dialog.dismiss();
                    }
                });

                Button cancelButton = (Button) dialog.findViewById(R.id.dialogOptionsCancel);
                // if cancel button is clicked, just close the custom dialog
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                //Everything is prepared, show the user
                dialog.show();
            }
        });
    }
}
