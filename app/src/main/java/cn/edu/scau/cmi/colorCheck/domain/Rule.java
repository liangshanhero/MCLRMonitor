package cn.edu.scau.cmi.colorCheck.domain;

import java.util.List;

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

    private List<Range_Rule> range_rules;
    private Linear_Rule linear_rule;

    public List<Range_Rule> getRange_rules() {
        return range_rules;
    }

    public void setRange_rules(List<Range_Rule> range_rules) {
        this.range_rules = range_rules;
    }

    public Linear_Rule getLinear_rule() {
        return linear_rule;
    }

    public void setLinear_rule(Linear_Rule linear_rule) {
        this.linear_rule = linear_rule;
    }

    @Override
    public String toString() {
        return name;
    }
}
