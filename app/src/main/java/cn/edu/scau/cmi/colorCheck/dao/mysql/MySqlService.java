package cn.edu.scau.cmi.colorCheck.dao.mysql;

import java.util.List;
import java.util.Set;

import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Project;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Rule;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;

public class MySqlService {

    public static Set<Project> getAllProject() {
        Set<Project> allProjectList=HttpUtil.getAllProjects("Project");
        return  allProjectList;
    }
    //    TODO ***使用泛型得到所有的基本数据***
//    public static Set<T> getAllBasicData() {
//        Set<Project> allProjectList=HttpUtil.getAllProjects("Project");
//        return  allProjectList;
//    }



    public static Set<Rule> getAllRules() {
//        TODO
        return  null;
    }
}
