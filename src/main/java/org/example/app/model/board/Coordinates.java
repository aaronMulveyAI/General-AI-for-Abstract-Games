package org.example.app.model.board;

import static java.lang.Math.sqrt;

public class Coordinates implements Cloneable{

    public int[] QRS;
    public double[] XY;

    public Coordinates(int[] qrs) {
        this.QRS = qrs;
        this.XY = calculateXY();
    }

    private double[] calculateXY() {
        double[] XY = new double[2];
        XY[0] = (sqrt(3) * QRS[0] + sqrt(3) / 2.0 * QRS[1]);
        XY[1] = (3.0 / 2.0 * QRS[1]);
        return XY;
    }

}
