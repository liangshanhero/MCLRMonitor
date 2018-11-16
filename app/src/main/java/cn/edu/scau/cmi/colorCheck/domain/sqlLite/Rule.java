package cn.edu.scau.cmi.colorCheck.domain.sqlLite;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.domain.sqlLite.QuantitativeLinearRule;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.RangeRule;

/**
 * Created by Mr_Chen on 2018/6/28.
 */

public class Rule {
    public int id;
    public String name;
    public  int num;
    public String create_date;
    public String type;
    public int project_id;

    private List<RangeRule> range_rules;
    private QuantitativeLinearRule quantitativeLinear_rule;

    public List<RangeRule> getRange_rules() {
        return range_rules;
    }

    public void setRange_rules(List<RangeRule> range_rules) {
        this.range_rules = range_rules;
    }

    public QuantitativeLinearRule getQuantitativeLinear_rule() {
        return quantitativeLinear_rule;
    }

    public void setQuantitativeLinear_rule(QuantitativeLinearRule quantitativeLinear_rule) {
        this.quantitativeLinear_rule = quantitativeLinear_rule;
    }

    @Override
    public String toString() {
        return name;
    }
}
