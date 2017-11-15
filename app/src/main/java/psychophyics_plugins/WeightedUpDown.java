package psychophyics_plugins;

/**
 * Created by broly on 13/03/2017.
 */

public class WeightedUpDown {

    public WeightedUpDown() {
    }

    public int calculateNextLevel(Boolean correct, int level) {
        final double threshold = 0.75;

        if(correct){
            level++;
        }else{
            level = (int) Math.round(level - (threshold / (1 - threshold)));
        }

        return level;
    }
}