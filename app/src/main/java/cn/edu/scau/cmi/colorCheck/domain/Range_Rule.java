package cn.edu.scau.cmi.colorCheck.domain;

/**
 * Created by Mr_Chen on 2018/6/28.
 * 是什么规则？？？
 *
 */

public class Range_Rule {
    public int id;
    public int r_start;
    public int r_end;
    public int g_start;
    public int g_end;
    public int b_start;
    public int b_end;

    public int rule_id;
    public int target_id;

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "R("+r_start+","+r_end+") " +
                "G("+g_start+","+g_end+") " +
                "B("+b_start+","+b_end+")-->" +result;
    }
}
