package cn.edu.scau.cmi.colorCheck.dao.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Project;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Rule;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;

public class MySqlService {

    public static List<Project> getAllProject() {
        Set<Project> allProjectSet=HttpUtil.getAllProjects("Project");
        List<Project> allProjectList=new ArrayList<Project>();
        allProjectList.addAll(allProjectSet);
        return  allProjectList;
    }
    //    TODO ***使用泛型得到所有的基本数据***
    public static List<T> getAllProject(T ty) {
        Set<T> allProjectSet=HttpUtil.getAllProjects(ty.getClassName());
        List<T> allProjectList=new ArrayList<Project>();
        allProjectList.addAll(allProjectSet);
        return  allProjectList;
    }



    public static Set<Rule> getAllRules() {
//        TODO
        return  null;
    }
}
