package Helpers;

import android.graphics.Point;
import android.view.MotionEvent;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by broly on 14/03/2017.
 */

public class trialData implements Serializable {
    int trialNumber;
    List<Integer> trueIndexes;
    List<Integer> selectedIndexes;
    List<Utilities.Point> accuracyList;
    boolean isCorrect;
    long elapsedTimeMs;
    Date timeStamp;
    String stimuliName;

    public trialData(int trialNumber, List<Integer> trueIndex, List<Integer> selectedIndex, boolean isCorrect, int level, String stimName,
                     List<Utilities.Point> accuracyList, long timeMs) {
        this.trialNumber = trialNumber;
        this.trueIndexes = trueIndex;
        this.selectedIndexes = selectedIndex;
        this.isCorrect = isCorrect;
        this.level = level;
        this.timeStamp = new Date();
        this.accuracyList = accuracyList;
        this.elapsedTimeMs = timeMs;
        this.stimuliName = stimName;
    }

    public int getTrialNumber() {
        return trialNumber;
    }

    public int getLevel() {
        return level;
    }

    int level;

    public String getStimuliName() { return stimuliName;}

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public List<Integer> getTrueIndexes() {
        return trueIndexes;
    }

    public List<Integer> getSelectedIndexes() {
        return selectedIndexes;
    }

    public List<Utilities.Point> getAccuracyList() {
        return accuracyList;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public long getElapsedTimeMs() {
        return elapsedTimeMs;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
}
