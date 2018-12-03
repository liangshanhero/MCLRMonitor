package cn.edu.scau.cmi.colorCheck.dao.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Project;
import cn.edu.scau.cmi.colorCheck.domain.sqlLite.Rule;
import cn.edu.scau.cmi.colorCheck.util.HttpUtil;

public class MySqlService<T> {
//TODO 测试泛型方法成功后，删除该方法
    public static List<Project> getAllProject() {
//        HttpUtil httpUtil=new HttpUtil();
        Set<Project> allProjectSet=HttpUtil.getAllProjectData("Project");
        List<Project> allProjectList=new ArrayList<Project>();
        allProjectList.addAll(allProjectSet);
        return  allProjectList;
    }
    //    TODO ***使用泛型得到所有的基本数据
    // 例如：如果需要的是Project数据，就用Project
    //
    // ***
    public  List<T> getAllDate(T requiredDataType) {
        HttpUtil httpUtil=new HttpUtil();
        Set<T> allRequiredTypeDataSet=httpUtil.getAllRequiredTypeData(requiredDataType.getClass().getName());
        List<T> allRequiredTypeDataList=new ArrayList<T>();
        allRequiredTypeDataList.addAll(allRequiredTypeDataSet);
        return  allRequiredTypeDataList;
    }

//TODO 测试泛型方法成功后，删除该方法
    public static Set<Rule> getAllRules() {
        return  null;
    }
}