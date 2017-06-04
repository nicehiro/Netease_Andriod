package org.nicehiro.mybook;

/**
 * Created by hiro on 17-5-19.
 */

public class PieData {

    private String name;
    private float value;
    private float percentage;

    private int color = 0;
    private float angle = 0;

    public PieData(String name, float value) {
        this.name = name;
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public float getAngle() {
        return angle;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
