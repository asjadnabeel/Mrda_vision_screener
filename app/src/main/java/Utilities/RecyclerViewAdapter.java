package Utilities;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.optometry.plymouth.mrda.R;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.Map;

import Helpers.trialData;

/**
 * Created by broly on 18/03/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private Activity activity;
    private Map<Integer, Double> percentages;
    private Map<Integer, trialData> userHistory;

    public RecyclerViewAdapter(Map<Integer, Double> percentages, Map<Integer, trialData> userHistory, Activity activity) {
        this.percentages = percentages;
        this.activity = activity;
        this.userHistory = userHistory;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.level, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.level.setText(Integer.toString(position + 1));

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < userHistory.size(); i++){
            String isCorrect =  String.valueOf(userHistory.get(i).getIsCorrect()).toUpperCase();
            String stimulusName = userHistory.get(i).getStimuliName();
            int level = userHistory.get(i).getLevel();

            if(level == position){
                sb.append(isCorrect.charAt(0) + " ");
            }else{
                //do nothing for now
            }
        }

        NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        defaultFormat.setMinimumFractionDigits(1);

        holder.percentage.setText(String.format("%s, %s, %s", sb, userHistory.get(position).getStimuliName(), defaultFormat.format(percentages.get(position))));
    }

    @Override
    public int getItemCount() {
        return percentages.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private TextView level;
        private TextView percentage;

        public ViewHolder(View itemView) {
            super(itemView);
            level = (TextView) itemView.findViewById(R.id.level);
            percentage = (TextView) itemView.findViewById(R.id.percentage);

        }
    }
}
