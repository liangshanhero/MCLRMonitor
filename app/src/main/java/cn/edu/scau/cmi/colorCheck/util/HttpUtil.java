package cn.edu.scau.cmi.colorCheck.util;

import java.io.IOException;

import cn.edu.scau.cmi.colorCheck.R;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

//    private static final String PREFIX_URL = "http://202.161.162.90:8081/pigRs/";
//不要在代码里改配置，到res/alues/string.xml修改
    //CmiApplication.getContext().getString(R.string.prefix_url);
    //http://120.78.190.240:8000/pigRs/

    private static String getCompleteURL(String postfixURL){
        return  CmiApplication.getContext().getString(R.string.prefix_url) + postfixURL;
    }

    public static String sendHttpRequest(Request request) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static Request getParamsPostRequest(String postURL, String params){
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), params);
        return new Request.Builder().post(requestBody).url(getCompleteURL(postURL)).build();
    }

    public static Request getJsonPostRequest(String postfixURL, String json){
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        return  new Request.Builder().post(requestBody).url(getCompleteURL(postfixURL)).build();
    }

    public static Request getGetRequest(String postfixURL){
        return  new Request.Builder().url(getCompleteURL(postfixURL)).build();
    }


}
