package com.optometry.plymouth.mrda;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import Helpers.trialData;

public class Results extends AppCompatActivity {

    private Map<Integer, trialData> userHistory;
    String totalTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        userHistory = (Map<Integer, trialData>)bundle.getSerializable("userHistory");
        totalTime = bundle.getString("totalTime");

        placeContents();
        
        //save everything in a file
        try {
            saveToFile();
        } catch (IOException e) {
            Toast.makeText(this, "Cannot write to File", Toast.LENGTH_LONG);
        }
    }

    private void saveToFile() throws IOException {

        String trialData;
        String filename = "0-1.txt";
        File file = new File(this.getExternalFilesDir("MRDA"), filename);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

        for(int i = 0; i < userHistory.size(); i++){
            trialData tData = userHistory.get(i);

            String trialNumber = String.valueOf(tData.getTrialNumber());
            String stimuliName = tData.getStimuliName();

            StringBuilder trueIndeces = new StringBuilder();
            for(int j = 0; j < tData.getTrueIndexes().size(); j++){
                String trueIndex = String.valueOf(tData.getTrueIndexes().get(j));
                if(j == tData.getTrueIndexes().size() - 1){
                    trueIndeces.append(trueIndex);
                }else{
                    trueIndeces.append(trueIndex + ", ");
                }

            }
            trueIndeces.toString();

            StringBuilder selectedIndeces = new StringBuilder();
            for(int j = 0; j < tData.getSelectedIndexes().size(); j++){
                String selectedIndex = String.valueOf(tData.getSelectedIndexes().get(j));
                if(j == tData.getSelectedIndexes().size() - 1){
                    selectedIndeces.append(selectedIndex);
                }else{
                    selectedIndeces.append(selectedIndex + ", ");
                }

            }
            selectedIndeces.toString();

            StringBuilder accuracyPoints = new StringBuilder();
            for(int j = 0; j < tData.getAccuracyList().size(); j++){
                int xCoord = tData.getAccuracyList().get(j).getX();
                int yCoord = tData.getAccuracyList().get(j).getY();
                String point = String.format("(%d, %d)", xCoord, yCoord);
                if(j == tData.getAccuracyList().size() - 1) {
                    accuracyPoints.append(point);
                }else{
                    accuracyPoints.append(point + ", ");
                }
            }
            accuracyPoints.toString();

            String elapsedTimeMS = String.valueOf(tData.getElapsedTimeMs());

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String timeStamp = df.format(tData.getTimeStamp());


            trialData = String.format("%s, %s, [%s], [%s], [%s], %s, %s\n", trialNumber, stimuliName, trueIndeces, selectedIndeces, accuracyPoints, elapsedTimeMS, timeStamp);
            writer.append(trialData);
        }
        writer.close();
    }

    public void placeContents()
    {
        TextView txtDateView;
        TextView txtAverageStim;
        TextView txtTotalRounds;
        TextView txtTotalTime;

        txtDateView =  (TextView)findViewById(R.id.txtDate);
        txtTotalTime = (TextView)findViewById(R.id.txtTotalTime);
        txtTotalRounds =  (TextView)findViewById(R.id.txtTotalRounds);
        txtAverageStim =  (TextView)findViewById(R.id.txtOptStim);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        txtDateView.setText(df.format(new Date()));
        txtTotalTime.setText(totalTime);
        txtTotalRounds.setText(Integer.toString(userHistory.size()));
        txtAverageStim.setText(calculateThreashold());
    }

    private String calculateThreashold()
    {
        int sum = 0;
        for(int i = userHistory.size() - 5; i < userHistory.size();i++)
        {
            sum += userHistory.get(i).getLevel();
        }

        int averageLevel = sum / userHistory.size();

        //Now get trialName
        String trialName = "";
        for(int i = userHistory.size() - 5; i < userHistory.size();i++)
        {
            if(userHistory.get(i).getLevel() == averageLevel)
            {
                trialName = userHistory.get(i).getStimuliName();
            }
        }
        return trialName;
    }

    private Map<Integer, Double> calculateLevelPercentage() {
        int hits = 0;
        int misses = 0;

        Map<Integer, Double> dictPercentage = new HashMap<>();

        for(int i = 0; i < userHistory.size(); i++) {
            int total = 0;
            int numCorrect = 0;
            for (Map.Entry<Integer, trialData> entry : userHistory.entrySet()) {

                if (entry.getValue().getLevel() == i) {
                    total++;

                    if(entry.getValue().getIsCorrect())
                    {
                        numCorrect++;
                    }
                }

            }
            if(total > 0) {
                double percentage = (double) numCorrect / (double) total;
                dictPercentage.put(i, percentage);
            }
            //Do calc for each entry here
        }
        return dictPercentage;
    }
}
