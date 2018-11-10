package cn.edu.scau.cmi.colorCheck.dao;

import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.scau.cmi.colorCheck.domain.Check_Type;
import cn.edu.scau.cmi.colorCheck.domain.Linear_Rule;
import cn.edu.scau.cmi.colorCheck.domain.Project;
import cn.edu.scau.cmi.colorCheck.domain.Range_Rule;
import cn.edu.scau.cmi.colorCheck.domain.Rule;
import cn.edu.scau.cmi.colorCheck.domain.Sample;
import cn.edu.scau.cmi.colorCheck.domain.Target;

/**
 * Created by Mr_Chen on 2018/6/28.
 */

public class Service {

    public static int findProjectId(Project project){
        Cursor cursor1 = DAO.query("project",new String[]{"id"},"name=?",new String[]{project.name});
        while(cursor1.moveToNext()){
            return cursor1.getInt(cursor1.getColumnIndex("id"));
        }
        return -1;
    }

    public static int findRuleId(Rule rule){
        Cursor cursor1 = DAO.query("rule",new String[]{"id"},"name=? and project_id = ?",new String[]{rule.name,rule.project_id+""});
        while(cursor1.moveToNext()){
            return cursor1.getInt(cursor1.getColumnIndex("id"));
        }
        return -1;
    }
    public static List<Integer> findRuleId(int projectId){
        List<Integer> ruleIds = new ArrayList<>();
        Cursor cursor1 = DAO.query("rule",new String[]{"id"},"project_id=?",new String[]{projectId+""});
        while(cursor1.moveToNext()){
            ruleIds.add(cursor1.getInt(cursor1.getColumnIndex("id")));
        }
        return ruleIds;
    }
    public static int findTargetId(int projectId, String name){

        Cursor cursor1 = DAO.query("target",new String[]{"id"},"project_id=? and name=?"
                ,new String[]{projectId+"",name});
        while(cursor1.moveToNext()){
            return cursor1.getInt(cursor1.getColumnIndex("id"));
        }
        return -1;
    }


    public static List<String> getTargetsOfProject(int projectId){
        List<String> targets = new ArrayList<>();
        Cursor cursor = DAO.query("target",null,"project_id=?",new String[]{projectId+""});
        while (cursor.moveToNext()){
            targets.add(cursor.getString(cursor.getColumnIndex("name")));
        }
        return targets;
    }

    public static boolean addProject(Project project, Check_Type checkType){
       if(findProjectId(project) != -1){
           return false;
       }
        Cursor cursor = DAO.query(checkType.getClass().getSimpleName().toLowerCase(),
                new String[]{"id"},"name = ?",
                new String[]{checkType.name});
        while(cursor.moveToNext()){
            project.type_id  = cursor.getInt(cursor.getColumnIndex("id"));
        }
        DAO.insert(project);
        return true;
    }

    public static boolean addRule(Rule rule){
        if(findRuleId(rule)!=-1){
            return false;
        }
        DAO.insert(rule);
        int id = findRuleId(rule);
        if(rule.getLinear_rule()!=null){
            rule.getLinear_rule().rule_id = id;
            DAO.insert(rule.getLinear_rule());
        }else{
            for(Range_Rule range_rule : rule.getRange_rules()){
                range_rule.rule_id = id;
                DAO.insert(range_rule);
            }
        }
        return true;
    }
    public static  void addRangeSample(Sample sample){
        int targetId = findTargetId(sample.project_id,sample.getTarget());
        sample.target_id = targetId;
        addLinearSample(sample);
    }

    public static  void addLinearSample(Sample sample){
        DAO.insert(sample);
    }


    public static void addRangeOfProject(Project project, List<Target> targetList){
        int id = findProjectId(project);
        for(int i =0 ; i<targetList.size() ; i++){
            targetList.get(i).project_id = id;
            DAO.insert(targetList.get(i));
        }
    }



    public static List<Target> getProjectTarget(Project project){
        List<Target> targets = new ArrayList<>();
        int id = findProjectId(project);
        Cursor cursor = DAO.query("target",null,"project_id=?",new String[]{""+id});
        while(cursor.moveToNext()){
            Target target = new Target();
            target.id = cursor.getInt(cursor.getColumnIndex("id"));
            target.name = cursor.getString(cursor.getColumnIndex("name"));
            targets.add(target);
        }
        return targets;
    }


    public static List<Project> getAllProject(){
        List<Project> projects = new ArrayList<>();
        Cursor cursor = DAO.query("project",null,null,null);
        while(cursor.moveToNext()){
            Project project = new Project();
            project.name = cursor.getString(cursor.getColumnIndex("name"));
            project.id = cursor.getInt(cursor.getColumnIndex("id"));
            project.type_id= cursor.getInt(cursor.getColumnIndex("type_id"));
            project.memo = cursor.getString(cursor.getColumnIndex("memo"));
            projects.add(project);
        }
        return  projects;
    }


    public static List<Sample> getSampleOfProject(int projectId){
        List<Sample> samples = new ArrayList<>();
        Cursor cursor = DAO.query("sample",null,"project_id=?",new String[]{projectId+""});
        while(cursor.moveToNext()){
            Sample sample = new Sample();
            sample.id = cursor.getInt(cursor.getColumnIndex("id"));
            sample.red = cursor.getInt(cursor.getColumnIndex("red"));
            sample.green = cursor.getInt(cursor.getColumnIndex("green"));
            sample.blue = cursor.getInt(cursor.getColumnIndex("blue"));
            sample.result = cursor.getInt(cursor.getColumnIndex("result"));
            sample.target_id = cursor.getInt(cursor.getColumnIndex("target_id"));
            Cursor c = DAO.query("target",null,"id=?",new String[]{""+sample.target_id});
            while (c.moveToNext()){
                sample.setTarget(c.getString(c.getColumnIndex("name")));
            }
            samples.add(sample);
        }
        return samples;
    }


    public static List<Rule> getRulesOfProject(Project project){
        List<Rule> rules = new ArrayList<>();
        Cursor cursor = DAO.query("rule",null,"project_id=?",new String[]{""+project.id});
        while (cursor.moveToNext()){
            Rule rule = new Rule();
            rule.type = cursor.getString(cursor.getColumnIndex("type"));
            rule.create_date = cursor.getString(cursor.getColumnIndex("create_date"));
            rule.id = cursor.getInt(cursor.getColumnIndex("id"));
            rule.name = cursor.getString(cursor.getColumnIndex("name"));
            rule.num = cursor.getInt(cursor.getColumnIndex("num"));
            Cursor c = DAO.query("linear_rule",null,"rule_id=?",new String[]{rule.id+""});
            while(c.moveToNext()){
                Linear_Rule linear_rule = new Linear_Rule();
                linear_rule.id = c.getInt(c.getColumnIndex("id"));
                linear_rule.k1 = c.getFloat(c.getColumnIndex("k1"));
                linear_rule.k2 = c.getFloat(c.getColumnIndex("k2"));
                linear_rule.k3 = c.getFloat(c.getColumnIndex("k3"));
                linear_rule.b = c.getFloat(c.getColumnIndex("b"));
                rule.setLinear_rule(linear_rule);
            }
            Cursor c1 = DAO.query("range_rule",null,"rule_id=?",new String[]{rule.id+""});
            List<Range_Rule> range_rules = new ArrayList<>();
            while(c1.moveToNext()){
                Range_Rule range_rule = new Range_Rule();
                range_rule.id = c1.getInt(c1.getColumnIndex("id"));
                range_rule.r_start = c1.getInt(c1.getColumnIndex("r_start"));
                range_rule.g_start = c1.getInt(c1.getColumnIndex("g_start"));
                range_rule.b_start = c1.getInt(c1.getColumnIndex("b_start"));
                range_rule.r_end = c1.getInt(c1.getColumnIndex("r_end"));
                range_rule.g_end = c1.getInt(c1.getColumnIndex("g_end"));
                range_rule.b_end = c1.getInt(c1.getColumnIndex("b_end"));
                range_rule.target_id = c1.getInt(c1.getColumnIndex("target_id"));
                Cursor c2 = DAO.query("target",new String[]{"name"},"id=?",new String[]{""+ range_rule.target_id});
                while(c2.moveToNext()){
                    range_rule.setResult(c2.getString(c2.getColumnIndex("name")));
                }
                range_rules.add(range_rule);
            }
            rule.setRange_rules(range_rules);
            rules.add(rule);
        }
        return rules;
    }

    public static List<String> getAllCheckType(){
        List<String> check_types = new ArrayList<>();
        Cursor cursor = DAO.query("check_type",null,null,null);
        while(cursor.moveToNext()){
            check_types.add(cursor.getString(cursor.getColumnIndex("name")));
        }
        return check_types;
    }

    public static void deleteProject(Project project){
        DAO.delete("project","id=?",new String[]{""+project.id});
        DAO.delete("target","project_id=?",new String[]{""+project.id});
        List<Integer> rules = findRuleId(project.id);
        for(Integer integer : rules){
            deleteRule(integer);
        }
    }

    public static void deleteRule(int ruleId){
        DAO.delete("rule","id=?",new String[]{""+ruleId});
        DAO.delete("linear_rule","rule_id=?",new String[]{""+ruleId});
        DAO.delete("range_rule","rule_id=?",new String[]{""+ruleId});
    }

    public static String getCheckType(int id){
        Cursor cursor = DAO.query("check_type",new String[]{"name"},"id= ?",new String[]{""+id});
        while(cursor.moveToNext()){
            return cursor.getString(cursor.getColumnIndex("name"));
        }
        return "";
    }

    public static void addRangeRule(int projectId){
        String subSQL = "select red,green,blue,target.name as tname" +
                " from sample join target on sample.target_id = target.id" +
                " where sample.project_id = "+projectId;

        Rule rule = new Rule();
        rule.project_id = projectId;
        rule.create_date = new SimpleDateFormat("yyyy-MM-dd HH:ss").format(new Date());
        rule.type = "统计";
        rule.name = rule.create_date +" "+ rule.type;

        Cursor cursor = DAO.rawQuery("select min(red),min(green),min(blue),max(red),max(green),max(blue),tname" +
                " from ("+subSQL+")"+" group by tname",null);
        List<Range_Rule> range_rules = new ArrayList<>();
        while(cursor.moveToNext()){
            Range_Rule range_rule = new Range_Rule();
            range_rule.r_start = cursor.getInt(cursor.getColumnIndex("min(red)"));
            range_rule.g_start = cursor.getInt(cursor.getColumnIndex("min(green)"));
            range_rule.b_start = cursor.getInt(cursor.getColumnIndex("min(blue)"));
            range_rule.r_end = cursor.getInt(cursor.getColumnIndex("max(red)"));
            range_rule.g_end = cursor.getInt(cursor.getColumnIndex("max(green)"));
            range_rule.b_end = cursor.getInt(cursor.getColumnIndex("max(blue)"));
            range_rule.setResult(cursor.getString(cursor.getColumnIndex("tname")));
            range_rule.target_id = findTargetId(projectId,range_rule.getResult());
            range_rules.add(range_rule);
        }
        rule.setRange_rules(range_rules);
        addRule(rule);
    }


    //定性检查
    public static String rangeCheck(int r, int g, int  b, int projectId){
        String subSQL = "select red,green,blue,target.name as tname" +
                " from sample join target on sample.target_id = target.id" +
                " where sample.project_id = "+projectId;

        Cursor cursor = DAO.rawQuery("select avg(red),avg(green),avg(blue),tname" +
                " from ("+subSQL+")"+" group by tname",null);


        String result="空";
        Float distance = null;
        while(cursor.moveToNext()){
            Sample sample = new Sample();
            float average_R =  cursor.getFloat(cursor.getColumnIndex("avg(red)"));
            float average_G =  cursor.getFloat(cursor.getColumnIndex("avg(green)"));
            float average_B =  cursor.getFloat(cursor.getColumnIndex("avg(blue)"));

            String name = cursor.getString(cursor.getColumnIndex("tname"));

            float currentDistance = (float) Math.sqrt((average_R-r)*(average_R-r) + (average_G-g)*(average_G-g) + (average_B-b)*(average_B-b));
            if(distance == null || currentDistance < distance){
                result = name;
                distance = currentDistance;
            }
        }
            return result;
    }
}
