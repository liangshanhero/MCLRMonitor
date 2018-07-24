package cn.edu.scau.cmi.rgbstudy.domain;

/**
 * Created by Mr_Chen on 2018/6/28.
 */

public class Sample {
    public int id;
    public int red;
    public int green;
    public int blue;
    public float result;
    public int project_id;
    public int target_id;

    private String target;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
