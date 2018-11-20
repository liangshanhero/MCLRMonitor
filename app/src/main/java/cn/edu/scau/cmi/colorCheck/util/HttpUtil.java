package cn.edu.scau.cmi.colorCheck.util;

import java.io.IOException;

import cn.edu.scau.cmi.colorCheck.R;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

//    TODO 测试的时候暂时固定，待以后在修复,不能正常获取URI
 private static final String getUserJsonUrlPrefix = "http://139.159.188.31:8080/colorCheckServer/";
//不要在代码里改配置，到res/alues/string.xml修改
    //CmiApplication.getContext().getString(R.string.prefix_url);
//    prefix_url是数据所在的网址的前缀，postURL是具体的数据后缀。

    private static String getCompleteURLString(String postfixURL){
//        String completeUrlString=CmiApplication.getContext().getString(R.string.prefix_url) + postfixURL;
        String completeUrlString=getUserJsonUrlPrefix + postfixURL;
        return  completeUrlString;
    }

    public static String getJsonDataFromWeb(Request request) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();//同步获取数据，但是是异步类调用
        return response.body().string();
    }

    public static Request getParamsPostRequest(String postURL, String params){
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), params);
        return new Request.Builder().post(requestBody).url(getCompleteURLString(postURL)).build();
    }

    public static Request getJsonPostRequest(String postfixURL, String json){
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        return  new Request.Builder().post(requestBody).url(getCompleteURLString(postfixURL)).build();
    }

    public static Request getGetRequest(String postfixURL){
          return  new Request.Builder().url(getCompleteURLString(postfixURL)).build();
    }
}