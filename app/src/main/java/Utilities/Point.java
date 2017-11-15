package Utilities;

import java.io.Serializable;

/**
 * Created by broly on 26/03/2017.
 */

public class Point implements Serializable {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    int x;
    int y;

    public Point(int xCoord, int yCoord)
    {
        this.x = xCoord;
        this.y = yCoord;
    }


}
