package cn.edu.scau.cmi.colorCheck.domain;

/**
 * Created by Mr_Chen on 2018/6/7.
 */

public class QuantitativeLinearRule {

    public int id;
    public int rule_id;
    public float k1;
    public float k2;
    public float k3;
    public float b;


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("result = ");
        if(k1!= 0){
            if(k1 < 0){
                sb.append("- ");
            }
            sb.append(Math.abs(k1)).append(" * ").append("R");
        }
        if(k2 != 0){
            if(this.k2 > 0){
                sb.append(" + ");
            }else{
                sb.append(" - ");
            }
            sb.append(Math.abs(k2)).append(" * ").append("G");
        }
        if(k3 != 0){
            if(k3 > 0){
                sb.append(" + ");
            }else{
                sb.append(" - ");
            }
            sb.append(Math.abs(k3)).append(" * ").append("B");
        }
        if(b!= 0){
            if(b > 0){
                sb.append(" + ");
            }else{
                sb.append(" - ");
            }
            sb.append(Math.abs(b));
        }

        if(sb.toString().equals("result = ")){
            sb.append("0");
        }
        return sb.toString();
    }
}
