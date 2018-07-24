package cn.edu.scau.cmi.rgbstudy.domain;

/**
 * Created by Mr_Chen on 2018/6/7.
 */

public class Collection {
    private float red;
    private float green;
    private float blue;
    private float result;

    public Collection(float red, float green, float blue, float result) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.result = result;
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }
}
