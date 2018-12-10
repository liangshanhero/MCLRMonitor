package cn.edu.scau.cmi.colorCheck.olderVersion.dao.mysqlThroughNetNowNoUseReadyToDelete;

import android.os.AsyncTask;

import java.util.List;

import cn.edu.scau.cmi.colorCheck.domain.mysql.User;

public class UseAsyncTask extends AsyncTask {
    private List<User> userList;
    @Override
    public Object doInBackground(Object[] objects) {
        try {
          String httpRequestString="http://139.159.188.31:8080/colorCheckServer/User";
            MyHttpClient.getUsers(httpRequestString);
//            String httpRequestString=HttpUtil.getGetRequest("/User").toString();
//            Log.e("准备发送的请求是：：",httpRequestString);
//
//            String jsonData = HttpUtil.gainJsonResultFromServer(HttpUtil.getGetRequest("User"));
//            Toast.makeText(CmiApplication.getContext(), jsonData, Toast.LENGTH_LONG).show();
//
//                userList = new Gson().fromJson(jsonData,new TypeToken<List<User>>(){}.getType());
//
//                Log.e("第二个用户名是：",userList.get(1).getName());

            return "第一个用户的姓名是：success"+userList.get(0).getName();
        } catch (Exception e) {
            return "没有正确获取用户的名称";
        }
    }
}