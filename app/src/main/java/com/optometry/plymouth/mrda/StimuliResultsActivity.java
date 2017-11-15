package com.optometry.plymouth.mrda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

import Helpers.trialData;

public class StimuliResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stimuli_results);

        Intent intent = getIntent();
        Map<Integer, trialData> userHistory = (Map<Integer, trialData>) intent.getSerializableExtra("userHistory");

        //TO:DO plan layout of the results activity
    }





}
