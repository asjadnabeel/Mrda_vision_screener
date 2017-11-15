package com.optometry.plymouth.mrda;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Stimuli_Canvas extends Activity {

    StimuliSubCanvas stimuliCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_stimuli__canvas);

        //Load game canvas into View
        stimuliCanvas = new StimuliSubCanvas(this);
        setContentView(stimuliCanvas);

//        if(stimuliCanvas.getVisibility() == View.GONE){
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            //ft.replace(R.id.fragment_container, sr);
//            ft.add(sr, null);
//            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//            ft.commit();
//        }
    }

    @Override
    public void onBackPressed() {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Closing Page")
                    .setMessage("Are you sure you want to exit the test?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
}